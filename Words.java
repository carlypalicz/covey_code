import java.io.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;

public class Words
{
    public static String[] words = new String[3971];
    public static HashMap<String, String> wordPairs= new HashMap<String, String>();
    //this will be in a key, value pair of the word minus the first char and the words it maps to

    static void parseWords(String word, int idx){
        //two passes
        //first will store the word and it minus the first char into the hashmap, and the word in an array
        //second will go through word by word and lookup if its wordPair partial is in the hashmap
        
        words[idx] = word;

        String chopped = word.substring(1);
        if (wordPairs.get(chopped) != null){
            String initial = wordPairs.get(chopped);
            wordPairs.put(chopped, initial + ", " + word);
        }
        else {
            wordPairs.put(chopped, word);
        }
    }

    static void findPairs(){
        String wordPair = "";
        for (int i = 0; i < words.length; i++){
            wordPair += words[i].charAt(1);
            wordPair += words[i].charAt(4);
            wordPair += words[i].charAt(4);
            wordPair += words[i].charAt(5);
            wordPair += words[i].charAt(2);

            if (wordPairs.get(wordPair) != null){
                System.out.println(words[i] + ": " + wordPairs.get(wordPair));
            }

            wordPair = "";
        }
    }

    public static void main(String[] args) throws Exception
    {
        //parsing a CSV file into Scanner class constructor
        Scanner sc = new Scanner(new File("dub_letters.csv"));
        sc.useDelimiter("\n");   //sets the delimiter pattern
        int counter = 0;
        while (sc.hasNext())  //returns a boolean value
        {
            parseWords(sc.next(), counter);  //find and returns the next complete token from this scanner
            counter++;
        }
        sc.close();  //closes the scanner
        findPairs();
    }
}
