import java.util.*;
import java.io.*;


public class Timber {

        private static File inputFile=new File("timber_sample_input.txt");
        private static File outputFile=new File("output.txt");
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
            int input[][]=new int[n][2];
            for(int i=0;i<n;i++) {
                String x=reader.readLine();
                String k[]=x.split(" ");
                input[i][0]=Integer.parseInt(k[0]);
                input[i][1]=Integer.parseInt(k[1]);
            }
            //writer.append("Case #"+testCase+": "+find(input,0,new ArrayList<Integer[]>(),n,Integer.MIN_VALUE));
            System.out.println("Case #"+testCase+": "+find(input,0,new ArrayList<Integer[]>(),n,Integer.MIN_VALUE));

            writer.newLine();
    }


    private static int find(int input[][],int i, ArrayList<Integer[]> result, int n,int maxx) { 
        System.out.println("Index: "+i);      
        if(i==n) {
            int min;
            int max;
            int max_value=Integer.MIN_VALUE;
            for(int k=result.size()-1;k>=0;k--) {
                System.out.println(result.get(k)[0]+" "+result.get(k)[1]);
            }
            for(int k=0;k<result.size();k++) {
                Deque<Integer[]> deq=new LinkedList<Integer[]>();
                Deque<Integer[]> res=new LinkedList<Integer[]>();
                deq.add(result.get(k));
                min=result.get(k)[0];
                while(deq.size()!=0) {
                    Integer temp1[]=deq.removeFirst();
                    res.add(temp1);
                    boolean flag=false;
                    for(int m=0;m<result.size();m++) {
                        if(m!=i) {
                            Integer temp2[]=result.get(m);
                            if(temp1[1]==temp2[0]) {
                                deq.addFirst(temp2);
                                flag=true;
                            }
                        }
                    }
                    if(!flag) {
                        max=temp1[1];
                        if(max_value<Math.abs(max-min)) {
                            max_value=Math.abs(max-min);
                        }
                        if(deq.size()!=0) {
                            while(res.size()!=0&&res.peekLast()[1]!=deq.peekFirst()[0]) {
                                res.removeLast();
                            }
                        }
                    }
                }
            }
            if(maxx<max_value) {
                maxx=max_value;
            }
            return maxx;
        }
        System.out.println("Index: "+i+ " Input: "+input[i][0]+ " "+input[i][1]);  
        int p=input[i][0];
        int q=input[i][1];
        int x=p-q;
        int y=p;
        result.add(new Integer[]{x,y});
        maxx=find(input,i+1,result,n,maxx);
        x=p;
        y=p+q;
        while(i!=result.size()) {
            result.remove(result.size()-1);  
        }
        result.add(new Integer[]{x,y});
        maxx=find(input,i+1,result,n,maxx);
        return maxx;
    }
}