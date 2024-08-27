#include <iostream>
#include <string>

using namespace std;

class Casa{
private:
    int quartos;
    string loc;

public:

    /*Casa (){//construtor default
        quartos = 2;
        loc = "Ribeirao Preto";
    }

    Casa (int _quartos, string _loc){//construtor inicializado
        quartos = _quartos;
        loc = _loc;
    }*/

    Casa (int _quartos = 1, string _loc = "Rio de Janeiro"){//construtor com parametros default
        quartos = _quartos;
        loc = _loc;
    }

    void sumario(){
        cout << "Casa em " << loc << " com " << quartos << " quartos." << endl;
    }

};

/*Casa::Casa(){ //construtor fora da Classe (nao sei se precisa ter o mesmo nome) acho q sim, senao vira uma funcao
    quartos = 4;
    loc = "Sao Paulo";
}*/

int main(){
    Casa casa1, casa2 (3, "Goiania");//objeto 1 default, 2 inicializado

    casa1.sumario();
    casa2.sumario();

    return 0;
}
