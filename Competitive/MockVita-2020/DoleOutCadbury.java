import java.util.*;
import java.io.*;

public class DoleOutCadbury {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int min_length;
        int max_length;
        int min_width;
        int max_width;
        min_length=Integer.parseInt(br.readLine());
        max_length=Integer.parseInt(br.readLine());
        min_width=Integer.parseInt(br.readLine());
        max_width=Integer.parseInt(br.readLine());
        int x=0;
        for(int i=min_length;i<=max_length;i++) {
            for(int j=min_width;j<=max_width;j++) {
                x+=getCount(i,j);
            }
        }
        System.out.println(x);
    }

    private static int getCount(int x,int y) {
        if(y==0) {
            return 0;
        } else {
            if(x==1) {
                return y;
            }
            else if(y==1) {
                return x;
            }
        }
        int min=Math.min(x,y);
        int resultProduct=x*y-min*min;
        x=min;
        y=resultProduct/min;
        return 1+getCount(x,y);
    }
}