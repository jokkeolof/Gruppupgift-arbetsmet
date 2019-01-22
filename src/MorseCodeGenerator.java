
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

// ****************************************************************************************************************************
// This code is meant to run in a separate thread so the main thread doesn't lock up waiting for the sound to finish playing.
// Create an object of the class, and call the methods from inside a thread's run()-method.
// ****************************************************************************************************************************
// Morse code generator takes a string[] or a plain string as a parameter containing morse code. As in "..." for 'S' and "---" for 'O'
// Before calling the play methods call the startSound() method to set the 'isPlaying' variable to true.
// To stop the sound from playing before finished, simply call the stopSound() method.
// ****************************************************************************************************************************
// **************************************************************
// **************************************************************
//
// Example on how to start thread from main thread:
//
//	Thread thread = new Thread() {
//		public void run() {
//			morseCodeGenerator.startSound();
//			morseCodeGenerator.playSound(<your string here>);
//		}
//	};
//	thread.start();
//
// **************************************************************


public class MorseCodeGenerator {

	// Sampling frequency
		private float SAMPLE_RATE = 8000f;

		private AudioFormat audioFormat;
		private SourceDataLine sourceDataLine;
		private byte[] buf;

		private int hz;
		private double volume;
		private boolean isPlaying;


		// Constructor
		public MorseCodeGenerator() {

			hz = 666;  // Tone frequency
			volume = 0.5;  // Tone volume

			buf = new byte[1];
		    audioFormat = new AudioFormat(
		            SAMPLE_RATE, // sampleRate
		            8,           // sampleSizeInBits
		            1,           // channels
		            true,        // signed
		            false);      // bigEndian

		} // End constructor




		// Public method for playing sound from morse code String Array
		public void playSound(String[] morseCode) {

			// Loop through the code array
			for (int i = 0; i < morseCode.length; i++) {

				if (isPlaying) {		// As long as we don't hit the STOP-button

					// Play the sound for each code
					playSound(morseCode[i]);

				}	else {		// Stop button is hit.
					return;
				}
			}

		}



		// Public method for playing sound from a single morse code String
		public synchronized void playSound(String morseCode) {

			// Loop through the code
			for (int i = 0; i < morseCode.length(); i++) {

				// If it's a . play a short tone
				if (morseCode.charAt(i) == '.') {
					playShort();
				} else playLong();  // Else play a long tone
			}

			// Create silence between letters
			try {
				Thread.sleep(130);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}



		// Method to play short beep
		private void playShort () {

			// Length of tone
			int millisec = 35;

			playTone(hz, millisec, volume);
		}


		// Method to play long tone
		private void playLong() {

			// Length of tone
			int millisec = 120;

			playTone(hz, millisec, volume);
		}


		// Method that actually plays the sound
		private void playTone(int hz, int millisec, double volume) {

		    try {
				sourceDataLine = AudioSystem.getSourceDataLine(audioFormat);

				sourceDataLine.open(audioFormat);
			    sourceDataLine.start();
			    for (int i=0; i < millisec*8; i++) {
			      double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
			      buf[0] = (byte)(Math.sin(angle) * 127.0 * volume);
			      sourceDataLine.write(buf,0,1);
			    }
			    sourceDataLine.drain();
			    sourceDataLine.stop();
			    sourceDataLine.close();

			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// Method to set the status to play
		public void stopSound() {
			isPlaying = false;
		}


		// Method to set status to stop
		public void startSound() {
			isPlaying = true;
		}
}
