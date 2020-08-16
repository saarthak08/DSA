import java.util.*;
import java.io.*;


public class PerimetricChapter1 {

    private static File inputFile=new File("sample-input.txt");
    private static File outputFile=new File("sample-output.txt");
    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static long testCase=1;
    private static long W;
    private static int N,K;
    private static long L[], H[],P[];
    private static long AL,BL,CL,DL;
    private static long AH,BH,CH,DH;



    public static void main(String[] args) throws IOException,FileNotFoundException {
        reader=new BufferedReader(new FileReader(inputFile));
        writer=new BufferedWriter(new FileWriter(outputFile));
        long testCases=Long.parseLong(reader.readLine());
        while(testCase<=testCases) {
            input();
            calculate();
            testCase++;
        }        
        reader.close();
        writer.close();
    }  

    private static void input() throws IOException {
        String x=reader.readLine();
        String temp[]=x.split(" ");
        N=Integer.parseInt(temp[0]);
        K=Integer.parseInt(temp[1]);
        W=Long.parseLong(temp[2]);
        L=new long[N];
        H=new long[N];
        P=new long[N];
        x=reader.readLine();
        temp=x.split(" ");
        for(int i=0;i<K;i++) {
            L[i]=Long.parseLong(temp[i]);
        }
        x=reader.readLine();
        temp=x.split(" ");
        AL=Long.parseLong(temp[0]);
        BL=Long.parseLong(temp[1]);
        CL=Long.parseLong(temp[2]);
        DL=Long.parseLong(temp[3]);
        for(int i=K;i<N;i++) {
            L[i]=(((AL​*L[i-2])+(BL*L[i-1])+CL​)%DL​)+1;
        }
        x=reader.readLine();
        temp=x.split(" ");
        for(int i=0;i<K;i++) {
            H[i]=Long.parseLong(temp[i]);
        }
        x=reader.readLine();
        temp=x.split(" ");
        AH=Long.parseLong(temp[0]);
        BH=Long.parseLong(temp[1]);
        CH=Long.parseLong(temp[2]);
        DH=Long.parseLong(temp[3]);
        for(int i=K;i<N;i++) {
            H[i]=(((AH​*H[i-2])+(BH*H[i-1])+CH​)%DH​)+1;
        }
    }


    private static void calculate() throws IOException{
        long l[]=new long[N];
        long h[]=new long[N];
        long unionL=L[0];
        int unionLIndex=0;
        long maxH=0;
        System.out.println("\nCase #"+(int)testCase+": ");
        System.out.println("L:");
        for(int i=0;i<N;i++) {
            System.out.print(L[i]+"\t");
        }
        System.out.println("\nH:");
        for(int i=0;i<N;i++) {
            System.out.print(H[i]+"\t");
        }
        for(int i=0;i<N;i++) {
            long currH,currL;
            if(i==0) {
                currL=2*W;
                currH=2*H[0];
            } 
            else {
                if(L[i]<=L[i-1]+W) {
                    if(unionLIndex!=0) {
                        currL=2*(L[i]+W-unionL)+l[unionLIndex-1];
                    } else {
                        currL=2*(L[i]+W-unionL);
                    }
                    if(H[i]>H[i-1]) {
                        boolean flag=false;
                        maxH=0;
                        for(int j=i-1;j>=unionLIndex;j--) {
                            if(L[i]<=L[j]+W) {
                                if(H[j]>=H[i]) {
                                    flag=true;
                                    break;
                                }
                                if(maxH<H[j]) {
                                    maxH=H[j];
                                }
                            }
                        }
                        if(flag) {
                            currH=h[i-1];
                        } else {
                            currH=h[i-1]+2*Math.abs((H[i]-maxH));
                        }

                    } else {
                        currH=h[i-1];
                    }
                } else {
                    unionL=L[i];
                    unionLIndex=i;
                    currH=2*H[i]+h[i-1];
                    currL=2*W+l[i-1];
                }
            }
            if(maxH<currH) {
                maxH=currH;
            }
            h[i]=currH;
            l[i]=currL;
        }
        long res=1;
        long mod=(long)Math.pow(10,9);
        mod=mod+7;
        for(int i=0;i<N;i++) {
            P[i]=(h[i]+l[i])%mod;
        }     
        for(int i=0;i<N;i++) {
            res=(res*P[i])%mod;
        }
        writer.write("Case #"+(int)testCase+": "+res);
        writer.newLine();
    }
}