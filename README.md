# Your Voice vs Atari Breakout
Move the paddle with your voice just by using two different sounds: a sound will make it go right, the other one will make it go left.

#### YouTube video:
[![ScreenShot](http://i.imgur.com/3Ksirj9.png)](https://www.youtube.com/watch?v=AeUyKfJJpM0)

## Source Code
### Overview
There are three packages in this project. The game and the audio engine are independent of each other. The main package is the "link" between them.

### Packages
- **main**: it contains the main function. Its job is to create a "link" between the game and the audio engine. A loop continuously reads the current frequency and chooses if the paddle has to be moved to the right, to the left or it shouldn't be moving.
- **breakout**: contains the Atari Breakout game. You could take this package and use it as a standalone game with simple code modifications.
- **audioengine**: contains the function that allows to get the current frequency of the sound from the microphone.

## Notes
Please, be aware that the game won't work well if there's some noise in your room or your microphone generates a disturbed input. The game doesn't implement any form of noise reduction, for that reason it's very sensible to the environment around you and the microphone you're using.

### Credits
The frequency detector was part of a [Guitar Tuner application](http://www.psychicorigami.com/2009/01/17/a-5k-java-guitar-tuner/).
