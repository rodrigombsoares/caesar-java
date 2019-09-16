package caesar;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter username");

        String userName = myObj.nextLine();
        String encoded = getCypher(userName, true);
        System.out.println(encoded);

        String decoded = getCypher(encoded, false);
        System.out.println(decoded);
    }

    public static String getCypher(String text, boolean cypher){
        // cypher based on ascii table from 32 to 127
        int key = 1;
        int size = text.length();

        String encoded = "";

        for(int i=0; i<size; ++i) {
            char ch;
            if(cypher){
                ch = (char)(Math.floorMod((int)((text.charAt(i)) + key - 32), (127-31)) + 32);
            } else {
                ch = (char)(Math.floorMod((int)((text.charAt(i)) - key - 32), (127-31)) + 32);
            }
            encoded += ch;
        }

        return encoded;
    }
}
