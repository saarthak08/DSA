#include<iostream>
#include<fstream>
#include<string>

using namespace std;

ifstream input_file;
ofstream outfile;

void show(int N, string I, string O,int test_case){
        outfile << "Case #" <<test_case<<":\n"; 
        char ans[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(i==j){
                    ans[i][j]='Y';
                }
                else if((i-j==1 || j-i==1) && I[j]=='Y' && O[i]=='Y'){
                    ans[i][j]='Y';
                }
                else if(j>i){
                    if(O[j-1]=='Y' && I[j]=='Y' && ans[i][j-1]=='Y')
                        ans[i][j]='Y';
                    else
                        ans[i][j]='N';
                }
                else{
                    if(O[i]=='Y' && I[i-1]=='Y' && ans[i-1][j]=='Y')
                        ans[i][j]='Y';
                    else
                        ans[i][j]='N';
                }
                outfile << ans[i][j];
            }
            outfile << "\n"; 
        }
}



int main(){
    input_file.open("input.txt");
    outfile.open("output.txt",ios_base::app);
    if (!input_file.is_open()) {
      return 0;
    }
    string line;
    getline(input_file,line);
    int T=stoi(line);
    int t=1;
    while(t<=T){
        getline(input_file,line);
        int N=stoi(line);
        string I,O;
        getline(input_file,I);
        getline(input_file,O);
        show(N,I,O,t);
        t++;
    }
    input_file.close();
    outfile.close();
}