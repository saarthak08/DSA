import java.util.*;
import java.io.*;


public class Alchemy {

    private static File inputFile=new File("alchemy_input.txt");
    private static File outputFile=new File("alchemy_output.txt");
    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static int testCase=1;


    public static void main(String[] args) throws IOException,FileNotFoundException {
        reader=new BufferedReader(new FileReader(inputFile));
        writer=new BufferedWriter(new FileWriter(outputFile));
        int testCases=Integer.parseInt(reader.readLine());
        while(testCase<=testCases) {
            calculate();
            testCase++;
        }        
        reader.close();
        writer.close();
    }  

    private static void calculate() throws IOException {
        int n=Integer.parseInt(reader.readLine());
        String s=reader.readLine();
        Deque<Character> deq=new LinkedList<Character>();
        Deque<Character> temp=new LinkedList<Character>();
        for(int i=0;i<n;i++) {
            deq.add(s.charAt(i));
        }
        while(deq.size()>1) {
            char a=deq.remove();
            char b=deq.remove();
            char c=deq.remove();
            if(a==b&&b==c) {
                if(deq.size()==0) {
                    continue;
                }
                temp.add(a);
                deq.addFirst(b);
                deq.addFirst(c);
                continue;
            }            
            char x[]=new char[2];
            if(a=='A') {
                x[0]++;
            } else {
                x[1]++;
            }
            if(b=='A') {
                x[0]++;
            } else {
                x[1]++;
            } if(c=='A') {
                x[0]++;
            } else {
                x[1]++;
            }
            if(x[0]>x[1]) {
                deq.addFirst('A');
            } else {
                deq.addFirst('B');
            }
            while(temp.size()!=0) {
                deq.addFirst(temp.removeLast());
            }
        }
        if(deq.size()==0) {
            writer.append("Case #"+testCase+": N");
            writer.newLine();
        } else {
            writer.append("Case #"+testCase+": Y");
            writer.newLine();
        }
    }
}