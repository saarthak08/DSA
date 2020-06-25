import java.util.*;

public class FashionableLee {

    private static Scanner sc=new Scanner(System.in);
    private static int[] inputArray;
    public static void main(String[] args) {
        int inputs;
        inputs=sc.nextInt();
        inputArray=new int[inputs];
        for(int i=0;i<inputs;i++) {
            inputArray[i]=sc.nextInt();
        }
        for(int i=0;i<inputs;i++) {
            if(inputArray[i]%4==0) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }    
    }


}