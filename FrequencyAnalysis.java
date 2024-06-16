package lab07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FrequencyAnalysis {

    public static Map<Character, Integer> charFrequency(String filename) throws IOException {
        //MAP STORES CHARACTER COUNT
        Map<Character, Integer> charCount = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line=br.readLine()) !=null){
                for(Character ch : line.toCharArray()){

                    //SET ALPHABET TO LOWER CASE
                    char charL = Character.toLowerCase(ch);

                    //CHECKS IF CHARACTER IS ALREADY PRESENT IN THE MAP
                    if(charCount.containsKey(charL)) {
                        charCount.put(charL, charCount.get(charL) + 1); //INCREMENT THE COUNT
                    }else{
                        charCount.put(charL, 1);//SET THE CHARACTER COUNT VALUE TO ONE
                    }
                }
            }
        }

        return charCount;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter filename: ");
        String filename = sc.next();

        try{
            Map<Character, Integer> count = charFrequency(filename);
            System.out.println("Frequency of each character:");
            System.out.println(count);
        }
        catch (IOException e){
            System.out.println("File not found");
        }
        sc.close();
    }
}
