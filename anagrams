import java.util.Scanner;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;


public class Solution {

    public static boolean letterMatch(ArrayList<String> word, ArrayList<String> list){
        boolean result = false;

        for (int i = 0;i<list.size();i++){
            if(word.size()!=list.get(i).length()) {
                result = false;
            }
            else {
                String[] matchLetters = new String[list.get(i).length()];
                matchLetters = list.get(i).split("");
                ArrayList<String> matchWord = new ArrayList<String>(Arrays.asList(matchLetters));

                for (int j = 0; j<word.size();j++){

                    if(!matchWord.get(j).contains(word.get(j))) {
                        result = false;
                        break;}
                    else{
                        matchWord.remove(j);
                        word.remove(j);
                        result = true;}   
                }
            }
        }
        return result;
    }
    public static String SortingString(String string) {
           char charArray[] = string.toCharArray();
           Arrays.sort(charArray);
           String result = new String(charArray);
           return result;
        }
    public static ArrayList<String> DeDupe(ArrayList<String> list) {
        HashSet<String> set = new HashSet<String>(list);

        ArrayList<String> result = new ArrayList<String>(set);

        return result;


    }

    public static HashMap<String,Integer> Anagramarian(ArrayList<String> wordList) {
        HashMap<String,Integer> anaGroups = new HashMap<String,Integer>();        
        boolean toGroup;

        wordList = DeDupe(wordList);

        for(int i = 0;i<wordList.size();i++) {
            String[] letters = new String[wordList.get(i).length()];
       
          letters = wordList.get(i).split("");
            ArrayList<String> word = new ArrayList<String>(Arrays.asList(letters));
            String metaWord = SortingString(wordList.get(i));
            if(!anaGroups.containsKey(metaWord)){ 
                anaGroups.put(metaWord,1);
            }
            else{
                toGroup = letterMatch(word,wordList);
                if(toGroup = true) {
                    anaGroups.put(metaWord,anaGroups.get(metaWord)+1);
                }
            }           
            
        }
        return anaGroups;

    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); 
       
        String metaTarget;
        int answer;
        int iterations = in.nextInt(), test;
        String[] input =in.next().split(",");

        for (int i = 0;i<iterations;i++) {
            test = i+1;
            ArrayList<String> anagrams = new ArrayList<String>(Arrays.asList(input));
            HashMap<String,Integer> anaMap = new HashMap<String,Integer>();
            String targetWord = in.next();
            


            anaMap = Anagramarian(anagrams);

            metaTarget = SortingString(targetWord);
            if(anaMap.containsKey(metaTarget)) {
            answer = anaMap.get(metaTarget);}
            else {answer = 0;}

            System.out.println("Case #"+test+": "+ answer);
    }
    
}
}
