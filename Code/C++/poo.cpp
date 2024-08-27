#include <iostream>
#include <string>

class Aluno{ //ate agr parece struct
private: //nao pode ser acessada por membros fora da classe
    std::string nome;
    int idade;

public:
    //funcao de acesso para o nome
    std::string getNome(){
        return nome;
    }
    //funcao de acesso para a idade
    int getIdade(){
        return idade;
    }
    //funcao de alterar a idade
    void setIdade(int nova_idade){
        idade = nova_idade;
    }
    //funcao para alterar o nome
    void setNome(std::string novo_nome){
        nome = novo_nome;
    }
};

int main(){
    Aluno aluno;

    /*nao posso fazer: aluno.nome = "Guilherme"; pois
    nao tenho permissoes para isso, entao altero usando
    essas duas funções abaixo*/

    aluno.setIdade(18);
    aluno.setNome("Guilherme");

    std::cout << aluno.getNome() << " tem " << aluno.getIdade() << " anos de idade." << std::endl;
}
