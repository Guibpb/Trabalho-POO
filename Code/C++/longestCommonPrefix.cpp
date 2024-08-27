#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

int main(){
    std::vector <std::string> strs = {""};
    std::string pfx;
    int tamanho = strs.size();

    for(int j = 0; j < 200; j++){
            bool bule = true;

            for(int i = 0; i < tamanho - 1; i++){
                if(strs[i][j] != strs[i+1][j]){
                    bule = false;
                    goto final;
                }
            }

            if(bule){
                pfx += strs[0][j];
            }
    }

    final:

    std::cout << pfx << std::endl;
}
