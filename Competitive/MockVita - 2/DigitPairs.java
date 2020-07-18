import java.util.*;


public class DigitPairs {
    private static Scanner sc=new Scanner(System.in);
    public static void main(String[] args){
        int n;
        n=sc.nextInt();
        sc.nextLine();
        String a[]=new String[n];
        for(int i=0;i<n;i++) {      
            a[i]=sc.next();
        }
        String bitScore[]=new String[n];
        for(int i=0;i<n;i++) {
            int min=Character.getNumericValue(a[i].charAt(0));
            int max=Character.getNumericValue(a[i].charAt(0));
            for(int j=0;j<a[i].length();j++) {
                if(Character.getNumericValue(a[i].charAt(j))<min) {
                    min=Character.getNumericValue(a[i].charAt(j));
                }
                if(Character.getNumericValue(a[i].charAt(j))>max) {
                    max=Character.getNumericValue(a[i].charAt(j));
                }
            }
            Integer result=max*11+min*7;
            String r=result.toString();
            if(r.length()==3) {
                r=r.substring(1);
            }
            bitScore[i]=r;
        }
        int even[]=new int[10];
        int odd[]=new int[10];
        for(int i=0;i<n;i++) {
            if(i%2==0) {
                even[Character.getNumericValue(bitScore[i].charAt(0))]++;
            }
            else {
                odd[Character.getNumericValue(bitScore[i].charAt(0))]++;
            }
            
        }
        int count=0;
        for(int i=0;i<10;i++) {
            int x=0;
            if(even[i]==2) {
                x+=1;
            } else if(even[i]>=3) {
                x+=2;
            }
            if(odd[i]==2) {
                x+=1;
            } else if(odd[i]>=3) {
                x+=2;
            }
            if(x>2) {
                count+=2;
            }
            else {
                count+=x;
            }
        }
        System.out.println(count);
    }  
}