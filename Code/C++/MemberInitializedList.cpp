#include <iostream>

using namespace std;

class Livro{
private:
    const string titulo;
    const int paginas; //por serem constantes nao podem ser inicializadas da forma padrao

public:
    Livro() :
        titulo("Percy Jackson"), paginas(300) {} // Member initializer list

    int GetPaginas(){
        return paginas;
    }

    string GetTitulo(){
        return titulo;
    }

    ~Livro(){
        cout << "foi destruido coitado" << endl;
    }
};

int main(){
    Livro livro;
    bool a;

    cin >> a;

    return 0;
}
