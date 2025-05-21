class Solution {
    public String longestPalindrome(String s) {
        int maxLength=0,currLength=0;
        String maxString="",currString="";
        
        if(s.length()==1) {
            return s;
        }
        
        for(int i=0;i<s.length()-1;i++) {
            currString=expand(s,i,i);
            if(currString.length()>currLength) {
                maxString=currString;
                currLength=currString.length();
            }
            currString=expand(s,i,i+1);
             if(currString.length()>currLength) {
                maxString=currString;
                currLength=currString.length();
            }
        }
        return maxString;
    }
    
    public String expand(String s, int low, int high) {
        int len=s.length();
        while(low>=0&&high<len&&(s.charAt(low)==s.charAt(high))) {
            low--;
            high++;
        }
        return s.substring(low+1,high);
    }
}