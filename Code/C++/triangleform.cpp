#include <iostream>

using namespace std;

int main(){
    int tamanho;

    cin >> tamanho;

    auto altura = tamanho*2 - 1;
    auto var = -1;

    for(auto i = 0; i < altura; i++){
        for(auto j = 0; j < tamanho; j++)
            cout << "* ";

        cout << endl;

        if(tamanho == 1)
            var *= -1;

        tamanho += var;
    }

    return 0;
}
