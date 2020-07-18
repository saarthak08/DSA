import java.io.BufferedReader;
import java.util.*;
import java.io.*;

public class Swayamvar {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n;
        n=Integer.parseInt(br.readLine());
        String g,b;
        b=br.readLine();
        g=br.readLine();
        Queue<Character> grooms=new LinkedList<>();   
        Deque<Character> brides = new ArrayDeque<Character>(); 
        for(int i=0;i<n;i++) {
            grooms.add(g.charAt(i));
            brides.add(b.charAt(i));
        }
        int k=0;
        while(!brides.isEmpty()) {
            if(k==n) {
                break;
            }
            if(brides.peekFirst()!=grooms.peek()) {

                char y=grooms.remove();
                grooms.add(y);
                k++;
            } else {
                k=0;
                brides.removeFirst();
                grooms.remove();
            }
        }
        System.out.println(brides.size());
    }
} 