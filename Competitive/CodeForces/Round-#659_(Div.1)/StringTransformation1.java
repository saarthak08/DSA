import java.io.BufferedReader;
import java.util.*;
import java.io.*;


public class StringTransformation1 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        
        int t;
        t=Integer.parseInt(br.readLine());
        while(t>0) {
            int n;
            String a,b;
            n=Integer.parseInt(br.readLine());
            a=br.readLine();
            b=br.readLine();
            boolean visited[]=new boolean[20];
            int count=0;
            boolean flag=false;
            for(int i=0;i<n;i++) {
                if(a.charAt(i)>b.charAt(i)) {
                    flag=false;
                    break;
                } else {
                    int index=a.charAt(i)-'a';
                    if(!visited[index]) {
                        count++;
                        visited[index]=true;
                        flag=true;
                    }
                }
            }
            if(!flag) {
                System.out.println("-1");
            } else {
                System.out.println(count);
            }
            t--;
        }

    }
}