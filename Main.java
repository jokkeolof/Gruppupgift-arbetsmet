package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        HashMap<Character, String> morse = new HashMap<>();

            morse.put('a',  ".- "    );  morse.put('b',  "-... "  );
            morse.put('c',  "-.-. "  );  morse.put('d',  "-.. "   );
            morse.put('e',  ". "     );  morse.put('f',  "..-. "  );
            morse.put('g',  "--. "   );  morse.put('h',  ".... "  );
            morse.put('i',  ".. "    );  morse.put('j',  ".--- "  );
            morse.put('k',  "-.- "   );  morse.put('l',  ".-.. "  );
            morse.put('m',  "-- "    );  morse.put('n',  "-. "    );
            morse.put('o',  "--- "   );  morse.put('p',  ".--. "  );
            morse.put('q',  "--.- "  );  morse.put('r',  ".-. "   );
            morse.put('s',  "... "   );  morse.put('t',  "- "     );
            morse.put('u',  "..- "   );  morse.put('v',  "...- "  );
            morse.put('w',  ".-- "   );  morse.put('x',  "-..- "  );
            morse.put('y',  "-.-- "  );  morse.put('z',  "--.. "  );
            morse.put('å',  ".--.- " );  morse.put('ä',  ".-.- "  );
            morse.put('ö',  "---. "  );

            morse.put('1',  ".---- " );  morse.put('2',  "..--- " );
            morse.put('3',  "...-- " );  morse.put('4',  "....- " );
            morse.put('5',  "..... " );  morse.put('6',  "-.... " );
            morse.put('7',  "--... " );  morse.put('8',  "---.. " );
            morse.put('9',  "----. " );  morse.put('0',  "----- " );

            morse.put(' ',  " / "    );  morse.put(',',  "--..-- ");
            morse.put('.',  ".-.-.- ");  morse.put('-',  "-....- ");
            morse.put('(',  "-.--. " );  morse.put(')',  "-..-.- ");
            morse.put('@',  ".--.-. ");  morse.put('"',  ".-..-. ");
            morse.put('%',  ".--.. " );  morse.put('´',  ".----. ");
            morse.put(';',  "-.-.-. ");  morse.put(':',  "---... ");
            morse.put('?',  "-..-. " );  morse.put('!',  "---. "  );


        System.out.println("Please enter a word or a phrase to translate into morse code: ");

        StringBuilder sb = new StringBuilder();
        String value = input.nextLine();

        for (char c : value.toLowerCase().toCharArray()){
            sb.append(morse.get(c));
        }

        System.out.println(sb);
    }
}
