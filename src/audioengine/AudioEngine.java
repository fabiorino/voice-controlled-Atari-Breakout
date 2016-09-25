package audioengine;

import javax.sound.sampled.*;

public class AudioEngine {

    AudioFormat format;
    DataLine.Info data_line_info;
    TargetDataLine target_data_line;

    public AudioEngine() {
        try {
            format = new AudioFormat(44100, 16, 1, true, false);
            data_line_info = new DataLine.Info(TargetDataLine.class, format);
            target_data_line = (TargetDataLine)AudioSystem.getLine(data_line_info);

            target_data_line.open(format);
            target_data_line.start();
        }
        catch(LineUnavailableException ex) {}
    }
    
    public double getCurrentFrequency() throws Exception {
        int i, j;

        byte[] buffer = new byte[2 * 1200];
        int[] a = new int[buffer.length / 2];

        int n;
        int value;
        
        double diff;
        double prev_diff = 0;
        double dx;
        double prev_dx = 0;
        double max_diff = 0;

        int sample_len = 0;
                
        int len;

        double frequency = 0;

        n = target_data_line.read(buffer, 0, buffer.length);
        for(i = 0; i < n; i += 2) {
            value = (int)((buffer[i] & 0xFF) | ((buffer[i + 1] & 0xFF) << 8));
            a[i >> 1] = value; // a[i / 2] = value;
        }

        len = a.length / 2;
        for(i = 0; i < len; i++) {
            diff = 0;

            for(j = 0; j < len; j++) {
                diff += Math.abs(a[j] - a[i + j]);
            }

            dx = prev_diff - diff;

            if(dx < 0 && prev_dx > 0 && diff < 0.1 * max_diff && sample_len == 0) {
                sample_len = i - 1;
            }

            prev_dx = dx;
            prev_diff = diff;
            max_diff = Math.max(diff, max_diff);
        }

        if(sample_len > 0) {
            frequency = format.getSampleRate() / sample_len;
        }

        return frequency;
    }
}
