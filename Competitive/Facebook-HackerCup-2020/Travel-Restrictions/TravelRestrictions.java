import java.util.*;
import java.io.*;

public class TravelRestrictions {

    private static File inputFile=new File("travel_restrictions_input.txt");
    private static File outputFile=new File("travel_restrictions_output.txt");
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
        String incoming=reader.readLine();
        String outgoing=reader.readLine();
        char result[][]=new char[n][n];
        writer.write("Case #"+testCase+":");
        writer.newLine();
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                result[i][j]='N';
                if(i==j) {
                    result[i][j]='Y';
                }
                else if(i-j==1||j-i==1) {
                    if(incoming.charAt(j)=='Y'&&outgoing.charAt(i)=='Y') {
                        result[i][j]='Y';
                    }
                } else if(i-j>1||j-i>1) {
                    if(i-j>1) {
                       int x=i-1;
                       if(outgoing.charAt(i)=='Y'&&incoming.charAt(x)=='Y'&&result[x][j]=='Y') {
                            result[i][j]='Y';
                        }
                    } else if(j-i>1) {
                        int x=j-1;
                        if(result[i][x]=='Y'&&incoming.charAt(j)=='Y'&&outgoing.charAt(x)=='Y') {
                            result[i][j]='Y';
                        }
                    }
                   
                }
                writer.append(result[i][j]);
            }
            writer.newLine();
        }
    } 
}