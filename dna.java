import java.util.Scanner;

class Solution {
    static class Person {
        String name;
        int[] patterns;
        public Person(String name, int[] patterns) {
            this.name = name;
            this.patterns = patterns;
        }
    public static boolean patternMatch(String dna,String STR,int target) {
        int count = 0,endCount=0;
        int M = STR.length();
        int N = dna.length();
        Boolean match;
        String subDNA= "",tempDNA= "";
        
    for(int i = 0;i < (N-M+1);i++){
        subDNA = dna.substring(i,i+M).trim();
        if(subDNA.equals(STR) && tempDNA.equals(subDNA)) {
            count++;
            i = i+M-1;
            if(i>=N-M) {endCount = count;}
            tempDNA = subDNA;
            
        } else if (count == target) {
                endCount = count;
                break;
        } else if (subDNA.equals(STR)) {
            count = 1;
            i = i+M-1;
            tempDNA = subDNA;
            if(i>N-M) {endCount = count;}
            tempDNA = subDNA;
        
        } else {tempDNA = subDNA;}
      }
        match = endCount == target;
        return match;
}
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int patternNo = in.nextInt();
        
        String[] patterns = new String[patternNo];
        boolean result = false,success=false;
        String output="";
        
        String dNA;

        for (int p = 0;p<patternNo; p++) {
            patterns[p] = in.next();
        }
        int personNo = in.nextInt();
        int[] patternCts = new int[patternNo*personNo];
        Person[] people = new Person[personNo];
        String[] names = new String[personNo];
        int pct = 0;
        for (int r = 0; r< personNo;r++){
            names[r] = in.next();
            for (int c = 0; c < patternNo;c++) {
                patternCts[c+pct] = in.nextInt();
            }
            pct=pct+patternNo;
            
        }
        pct = 0;
        for (int p = 0;p<personNo;p++) {
            int[] subPat = new int[patternNo];
            for (int e = 0;e<patternNo;e++){
                subPat[e] = patternCts[pct+e];}
            people[p] = new Person(names[p],subPat);
            pct=pct+patternNo;
        }
        int dnaNo = in.nextInt();
        for (int d =0;d<dnaNo;d++) {
            dNA = in.next();
        output="";
        for (int m =0;m<personNo;m++){
            boolean[] match = new boolean[patternNo];
            for (int j = 0; j<patternNo; j++) {
                match[j] = people[m].patternMatch(dNA,patterns[j],people[m].patterns[j]);
            }
            for(boolean b : match) {
                if(!b) {
                    result = false;
                    break;
                } else {
            result = true;
                    }
            }
        
        
        if (result == true) {
            success=true;
            output = people[m].name;
    } else if (result ==false && output =="")
        {success=false;
            }
    
}
if(success==false) {
    System.out.println("Case #"+(d+1)+": "+"No match");
} else {System.out.println("Case #"+(d+1)+": "+output);}
}
    }
}
