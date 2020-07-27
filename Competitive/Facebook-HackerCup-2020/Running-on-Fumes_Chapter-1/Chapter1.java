import java.util.*;
import java.io.*;


public class Chapter1 {
        private static File inputFile=new File("inpu.txt");
        private static File outputFile=new File("output.txt");
        private static BufferedReader reader;
        private static BufferedWriter writer;
        private static int testCase=1;
        private static long maxSum=-1;
        private static boolean[] visited;
        private static long[] min;

        public static void main(String[] args) throws IOException,FileNotFoundException {
            reader=new BufferedReader(new FileReader(inputFile));
            writer=new BufferedWriter(new FileWriter(outputFile));
            int testCases=Integer.parseInt(reader.readLine());
            while(testCase<=testCases) {
                takeInput();
                testCase++;
            }        
            reader.close();
            writer.close();
        }

        private static void takeInput() throws IOException {
            String x=reader.readLine();
            String k[]=x.split(" ");
            int n=Integer.parseInt(k[0]);
            int m=Integer.parseInt(k[1]);
            int c[]=new int[n];
            for(int i=0;i<n;i++) {
                c[i]=Integer.parseInt(reader.readLine());
            }
            writer.append("Case #"+testCase+": "+calculate(n,m,c));
            //System.out.println("Case #"+testCase+": "+calculate(n,m,c));

            writer.newLine();
        }

        private static long calculate(int n, int m, int c[]) {
            long sum=0;
            if(m==1) {
                for(int i=1;i<n-1;i++) {
                    if(c[i]==0) {
                        return -1;
                    }
                }
            } else if(m>=n-1) {
                return 0;
            }
            int fb=0;
            for(int i=1;i<n-1;i++) {
                if(c[i]!=0) {
                    fb=i;
                    break;
                }
            }
            visited=new boolean[n];
            min=new long [n];
            for(int i=0;i<n;i++) {
                min[i]=-1;
            }
            find_max(n,m,0,c,0,fb);
            sum=maxSum;
            maxSum=-1;
            return sum;
        }

        private static boolean find_max(int n, int m, int index, int c[], long sum, int fb) {
            //System.out.println("Index: "+index+" Sum: "+sum);

            if(index+m>=n-1) {
                if(maxSum!=-1&&sum<maxSum) {
                    System.out.println("H13");
                    maxSum=sum;
                } else if(maxSum==-1) {
                    System.out.println("H12");
                    maxSum=sum;
                }
                return true;
            } else if(index+m<n-1&&index!=0&&index!=n-1&&c[index]==0) {
                return false;
            }
            else {
                long min_value=Integer.MAX_VALUE;
                int min_index=0;
                for(int i=index+1;i<=index+m;i++) {
                    if(c[i]!=0) {
                        sum+=(long)c[i];
                        boolean x=false;
                        System.out.println("1 Index: "+index+" Sum: "+sum);
                        if(min[i]==-1&&!visited[i]) {
                            System.out.println("H11");
                            x=find_max(n,m,i,c,sum,fb);
                        } else {
                            x=true;
                           System.out.println("H14");
                           System.out.println("maxSum: "+maxSum+" Sum: "+sum+"+ Min[i]:"+min[i]+" i:"+i);
                            if(sum+min[i]<maxSum) {
                                maxSum=sum+min[i];
                            } else if(maxSum==-1){
                                maxSum=sum+min[i];
                            }
                        }
                        sum-=(long)c[i];
                        if(x) {
                            if(min_value>c[i]) {
                                min_value=c[i];
                                min_index=i;
                               System.out.println("Min: "+min_value);
                            }
                        }
                        System.out.println("2 Index: "+index+" Sum: "+sum);
                    }
                }
                if(min_value!=Integer.MAX_VALUE) {
                    visited[index]=true;
                    System.out.println("Set Min: "+min_value + "Index: "+index);
                    min[index]=min_value+min[min_index];
                    return true;
                }
                else {
                    return false;
                }
            }
        }
}