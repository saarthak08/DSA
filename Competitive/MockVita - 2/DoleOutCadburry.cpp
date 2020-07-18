#include<iostream>

using namespace std;

int min_number(int x, int y) {
    return x<y?x:y;
}

int getCount(int x, int y) {
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
        int min=min_number(x,y);
        int resultProduct=x*y-min*min;
        x=min;
        y=resultProduct/min;
        return 1+getCount(x,y);
}

int main() {
    int min_length;
    int max_length;
    int min_width;
    int max_width;
    cin >> min_length;
    cin >> max_length;
    cin >> min_width;
    cin >> max_width;
    int x=0;
    if(0<min_length<1501&&min_length<max_length&&0<max_length<1501&&0<min_width<1501&&min_width<max_width&&0<max_width<1501) {
        for(int i=min_length;i<=max_length;i++) {
            for(int j=min_width;j<=max_width;j++) {
                x+=getCount(i,j);
            }
        }
    }
    cout << x;
    return 0;
}
