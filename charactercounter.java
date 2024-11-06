import java.util.*;
import java.io.*;
public class Solution {
  
  /*Notes
      Write a script that takes and array of strings and returns the word with the most vowels, the most consonants, and the most special characters. 
      In case of a tie, return the word lowest in lexographical order.
      
      Steps
      -Write a function that takes the array of strings as an input
      -Iterate accross strings and keep a count of each character type in character type arrays
      -Increment values that correspond with the words' positions in the character type arrays
      -Iterate accross character type arrays and compare counts as well as order then store max 
        values and corresponding position in input arrays
      -Store Input value by character type in the output array either as max vowels, consonants 
        or special characters
      -Return results as a comma separated string
  
  */
    public static String[] findTargetWords(String[] input) {
        Integer vowCount=0,consCount=0,speCount=0,maxVow = 0,maxCons = 0,maxSpec = 0;
        String[] output = new String[3];
        Integer[] vowArray = new Integer [input.length], consArray = new Integer [input.length], specArray = new Integer [input.length];
        for(int i = 0;i<input.length;i++) {
            vowCount= 0;
            consCount= 0;
            speCount = 0;
            for(int j = 0; j < input[i].length();j++) {
            if(input[i].substring(j,j+1).matches("[AEIOUYaeiouy]")) {
                vowCount++;
            }
            else if(input[i].substring(j,j+1).matches("[BCDFGHJKLMNPQRSTVWXZbcdfghjklmnpqrstvwxz]")) {
                consCount++;
            }
            else {speCount++;}
            
            vowArray[i] = vowCount;
            consArray[i] = consCount;
            specArray[i] = speCount;
        }
    }
        output[0] = input[0];
        output[1] = input[0];
        output[2] = input[0];
        for(int k = 0; k < input.length; k ++) {
            if(maxCons < consArray[k]) { 
                maxCons = consArray[k];
                output[0] = input[k];
            }
            else if (maxCons == consArray[k]) {
                for (int b = 0; b < k;b++) {
                int c = b+1;
       
                if(consArray[k-c] == consArray[k] && input[k].compareTo(input[k-c])<0 ){
                
                output[0] = input[k];
                }
                else if (consArray[k-c].equals(consArray[k]) && input[k].compareTo(input[k-c])>0){
                    output[2] = input[k-c];
                }
            }
        }
            if(maxVow < vowArray[k]) {
                maxVow = vowArray[k];
                output[1] = input[k];
            }
            else if (maxVow == vowArray[k]) {
                for (int b = 0; b < k;b++) {
                int c = b+1;
  
                if(vowArray[k-c] == vowArray[k] && input[k].compareTo(input[k-c])<0 ){
                output[1] = input[k];
                }
                else if (vowArray[k-c].equals(vowArray[k]) && input[k].compareTo(input[k-c])>0){
                    output[2] = input[k-c];
                    }
            }
        }
            
        if (maxSpec.equals(specArray[k]) ) {
                for (int b = 0; b < k;b++) {
                int c = b+1;
                if(specArray[k-c].equals(specArray[k]) && input[k].compareTo(input[k-c])<0 ){
                output[2] = input[k];
                }
                else if (specArray[k-c].equals(specArray[k]) && input[k].compareTo(input[k-c])>0){
                output[2] = input[k-c];
                }
            }
        }
        else if(maxSpec < specArray[k]) { 
            maxSpec = specArray[k];
            output[2] = input[k];
        }
        }
        return new String[] {output[0], output[1], output[2]};
    }
    
    public static void main(String[] args) {
        String[] result;
        result = findTargetWords(new String[] {"strengths", "ant 1", "turkey", "facetious"});
        System.out.println("Example 1 returned " + String.join(", ", result));
        result = findTargetWords(new String[] {"cat", "oat"});
        System.out.println("Example 2 returned " + String.join(", ", result));
    }
}
