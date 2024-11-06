import java.util.ArrayList;
import java.util.List;
//Identify base case
//Isolate left and right sides of first string, progress through second string selecting character (n) to intereleave.
//Use recursion to fold characters from string 2 into string one until all possible positions have been 
//trasversed and added to result list.
//Append results to results array on each stack call
//Let the recursion fairy take the wheel
//Catch duplicates in substrings as part of recursion
//**Changed data type from Lists/ArrayLists to List/string/character to facilitate processing data
//Tests
//Two 3 character strings with diferent elements (abc,123)
////Expected Unique Strings: 10
////[123abc, 12a3bc, 12ab3c, 1a23bc, 1a2b3c, 1ab23c, a123bc, a12b3c, a1b23c, ab123c]
//Two 3 character strings with one shared element (abc, ade)
////Expected unique strings: 4
////[adeabc, adaebc, adabec, abadec]
//Two 4 character strings with no shared elements (abcd, 1234)
////Expected unique strings: 27
////[1234abcd, 123a4bcd, 123ab4cd, 123abc4d, 12a34bcd, 12a3b4cd, 
////12a3bc4d, 12ab34cd, 12ab3c4d, 12abc34d, 1a234bcd, 1a23b4cd, 1a23bc4d, 1a2b34cd, 1a2b3c4d, 1a2bc34d, 1ab234cd, 1ab23c4d,
////1ab2c34d, 1abc234d, a1234bcd, a123b4cd, a123bc4d, a12b34cd, a12b3c4d, a12bc34d, a1b234cd, a1b23c4d, a1b2c34d, a1bc234d, 
////ab1234cd, ab123c4d, ab12c34d, ab1c234d, abc1234d]
//Two 4 character strings with one shared element (abcd, adef)
////Expected unique strings: 15
////[adefabcd, adeafbcd, adeabfcd, adeabcfd, adaefbcd, adaebfcd, adaebcfd, adabefcd, adabecfd, adabcefd, abadefcd, abadecfd,
////abadcefd, abacdefd, abcadefd]
public class Solution {
   
    public static List<String> interleave (String string1, String string2) {
        List<String> result= new ArrayList<String>();
        
       if(string1.isEmpty()) {
           result.add(string2);
        }
        else if (string2.isEmpty()) {
            result.add(string1);
        }
        else {
            
               for (int i = 0; i <string1.length();i++){
                char charOne = string2.charAt(0);
                String left = string1.substring(0,i);
                String right = string1.substring(i);
                for(String f:interleave(right,string2.substring(1))){
                    //Check element borders for character equivalency and break out of the loop if found
                    if((!left.isEmpty() && left.charAt(left.length()-1) == charOne) || charOne == f.charAt(0)) {
                        break;
                    }
                    result.add(left+charOne+f);
                };
                } 
        }
        return result;
    }
    public static void main(String[] args) {
        String string1,string2;
       
        string1 = "abcd";
        string2 = "adef";
        System.out.println("Final Result: "+interleave(string1,string2));
          
        
        
    }
}
