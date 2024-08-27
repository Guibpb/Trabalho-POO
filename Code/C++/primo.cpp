#include <iostream>

using namespace std;

int main(){
    for(int i = 1; i < 100; i++){
        bool bule = true;

        for(int x = 2; x < i; x++){
            if(i != x && i%x == 0){
                bule = false;
                break;
            }
        }

        if(bule)
            cout << i << endl;
    }
}
