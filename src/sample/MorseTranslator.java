package sample;

import java.util.ArrayList;
import java.util.HashMap;


public class MorseTranslator {

	// Variables
	HashMap<Character, String> morse;

	private String[] morseOutput;

    // Constructor
	public MorseTranslator() {

        morse = new HashMap<>();

        morse.put('a',         ".-");   morse.put('b',        "-...");
        morse.put('c',       "-.-.");   morse.put('d',         "-..");
        morse.put('e',          ".");   morse.put('f',        "..-.");
        morse.put('g',        "--.");   morse.put('h',        "....");
        morse.put('i',         "..");   morse.put('j',        ".---");
        morse.put('k',        "-.-");   morse.put('l',        ".-..");
        morse.put('m',         "--");   morse.put('n',          "-.");
        morse.put('o',        "---");   morse.put('p',        ".--.");
        morse.put('q',       "--.-");   morse.put('r',         ".-.");
        morse.put('s',        "...");   morse.put('t',           "-");
        morse.put('u',        "..-");   morse.put('v',        "...-");
        morse.put('w',        ".--");   morse.put('x',        "-..-");
        morse.put('y',       "-.--");   morse.put('z',        "--..");
        morse.put('\u00e5', ".--.-");   morse.put('\u00e4',   ".-.-");
        morse.put('\u00f6',  "---.");

        morse.put('1',      ".----");   morse.put('2',       "..---");
        morse.put('3',      "...--");   morse.put('4',       "....-");
        morse.put('5',      ".....");   morse.put('6',       "-....");
        morse.put('7',      "--...");   morse.put('8',       "---..");
        morse.put('9',      "----.");   morse.put('0',       "-----");

        morse.put(' ',          "/");   morse.put(',',      "--..--");
        morse.put('.',     ".-.-.-");   morse.put('-',      "-....-");
        morse.put('(',      "-.--.");   morse.put(')',      "-..-.-");
        morse.put('@',     ".--.-.");   morse.put('"',      ".-..-.");
        morse.put('%',     ".--.." );   morse.put('\u00b4', ".----.");
        morse.put(';',     "-.-.-.");   morse.put(':',      "---...");
        morse.put('?',     "-..-." );   morse.put('!',        "---.");

    }


    // Method to return translation as a String[]
    public String[] toStringArray (String textToTranslate) {

    	// Set the size out the output array
    	morseOutput = new String[textToTranslate.length()];

    	char currentLetter;		// Temp variable
    	for (int i = 0; i < morseOutput.length; i++) {

    		// Get the letter to translate
    		currentLetter = textToTranslate.toLowerCase().charAt(i);

    		// If it's a known character, translate it and put it in the String array
    		if (!(morse.get(currentLetter) == null)) {
    			morseOutput[i] = morse.get(currentLetter);
    		}
    		else {
    			morseOutput[i] = " ";
    		}
    	}

    	return morseOutput;
    }



    // Method to return translation as a list in case the input string is very long
    public ArrayList<String> toArrayList (String textToTranslate) {

    	ArrayList<String> morseOutput = new ArrayList<>();

    	char currentLetter;		// Temp variable
    	for (int i = 0; i < textToTranslate.length(); i++) {

    		// Get the letter to translate
    		currentLetter = textToTranslate.toLowerCase().charAt(i);

    		// If it's a known character, translate it and put it in the String array
    		if (!(morse.get(currentLetter) == null)) {
    			morseOutput.add(morse.get(currentLetter));
    		}
    		else {
    			morseOutput.add(" ");
    		}
    	}

    	return morseOutput;
    }



    // Method to translate and make a printable String
    public String translateToString(String textToTranslate) {

    	String translationString = "";

    	char currentLetter;		// Temp variable
    	for (int i = 0; i < textToTranslate.length(); i++) {

    		// Get the letter to translate
    		currentLetter = textToTranslate.toLowerCase().charAt(i);

    		// If it's a known character, translate it and put it in the translate String
    		if (!(morse.get(currentLetter) == null)) {

    			// Check if there is a new word...
    	    	if (morse.get(currentLetter).equals("/")) {				// Sign for new word
    	    		translationString += "  ";							// Add also whitespace between words
    	    		translationString += morse.get(currentLetter) + " ";
    	    	}
    	    	// Else just add the code to the string
    	    	else {
    	    		translationString += morse.get(currentLetter);
    	    	}
    		}
    		// Add whitespace between letters
    		translationString += "  ";
    	}
    	return translationString;
    }


}

