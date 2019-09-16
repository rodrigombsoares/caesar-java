package caesar;

import huffman.HuffmanUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner userScanner = new Scanner(System.in);
        System.out.println("Enter your Caesar key");
        int key = Integer.parseInt(userScanner.nextLine());

        String path = System.getProperty("user.dir");
        String filePath = Paths.get(path,  "text.txt").toString();

        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        String text = "";

        while (sc.hasNextLine()) {
            text += sc.nextLine();
        }

        System.out.println(text);

        String encrypted = getCypher(text, key, true);
        System.out.println("\nEncrypted string: " + encrypted);

//        String decoded = getCypher(encrypted, false);
//        System.out.println(decoded);

        HuffmanUtil huffUtil = new HuffmanUtil();
        String huffEncoded = huffUtil.encode(encrypted);
        System.out.println("\nHuffman encoded => " + huffEncoded);
        System.out.println("Huffman encoded size: " + huffEncoded.length() + " bits");
        System.out.println("String size without Huffman: " + encrypted.length()*4 + " bits");

    }

    public static String getCypher(String text, int key, boolean cypher){
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
