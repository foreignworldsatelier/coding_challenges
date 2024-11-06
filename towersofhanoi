import java.util.Scanner;
import java.util.*;
/*  Take the input as the number of discs on the first tower 
and print out each moves to get all discs to the 3rd tower.
Stack only smaller discs on larger discs
Stack to 2 first, then to three, back to one
For an odd number of discs go straight to 3, even goes to 2
*/



public class Solution {

    public static void hanoi(int m, Stack<Integer> source, Stack<Integer> target, Stack<Integer> spare,int rCount) {
        rCount++;
        System.out.println("New recursion: "+rCount);
        if(m>0){
        
        hanoi(m-1,source,spare,target,rCount);
        target.push(source.pop());
        System.out.println("Move "+target.peek()+" from " + source.firstElement()+" to "+target.firstElement());
       
        hanoi(m-1,spare,target,source,rCount);
    }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int iterations = in.nextInt(),rCount=0;
        for (int i = 0;i<iterations;i++) {        
        int m=in.nextInt();
        Stack<Integer> source = new Stack<Integer>();
        Stack<Integer> spare = new Stack<Integer>();
        Stack<Integer> target = new Stack<Integer>();

        source.push(1);
        spare.push(2);
        target.push(3);
        
        for(int j = m;j>0;j--) {
            source.push(j);
        }

        System.out.println("Case #"+(i+1)+":");
        hanoi(m,source,target,spare,rCount);
        }
    }


}
