package caesar;

import huffman.HuffmanUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException {

        int key = getKey();
        String text = readText();


        String encrypted = getCypher(text, key, true);
        System.out.println("\nEncrypted string: " + encrypted);

        String decoded = getCypher(encrypted, key, false);
        System.out.println("Decrypted string: " + decoded);

        String huffEncoded = HuffmanUtil.encode(encrypted);
        System.out.println("\nHuffman encoded => " + huffEncoded);
        System.out.println("Huffman encoded size: " + huffEncoded.length() + " bits");
        System.out.println("String size without Huffman: " + encrypted.length()*4 + " bits");

    }

    private static int getKey(){
        Scanner userScanner = new Scanner(System.in);
        System.out.println("Enter your Caesar key");

        return Integer.parseInt(userScanner.nextLine());
    }

    private static String readText() throws FileNotFoundException {
        String path = System.getProperty("user.dir");
        String filePath = Paths.get(path,  "text.txt").toString();

        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        String text = "";

        while (sc.hasNextLine()) {
            text += sc.nextLine();
        }

        System.out.println("Input from file: " + text);
        return text;
    }

    private static String getCypher(String text, int key, boolean cypher){
        // cypher based on ascii table from 32 to 127
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
