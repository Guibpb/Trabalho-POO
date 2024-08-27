#include <iostream>
#include <array>
#include <algorithm>
#include <string>

using namespace std;

void BubbleSort(array <int, 10> &vet){//define uma funçao passando um array por referencia
	bool cond = true;//condicao para terminar o loop while
	auto tamanho = vet.size() - 1;//tamanho menor por causa do i + 1, que ja atinge a ultima posicao do vetor

	while(cond){
		cond = false;

		for(auto i = 0; i < tamanho; i++){
			if(vet[i] > vet[i+1]){
				swap(vet[i], vet[i+1]);
				cond = true;
			}
		}

		tamanho--;//diminui sempre o tamanho por a ultima funcao
	}
}

int main(){
	array <int, 10> vet = {4, 3, 7, 9, 0, 8, 5, 2, 1, 6};//sintaxe para array com tamanho definido
	//se quiser um vetor de tamanho alocavel:
	// vector <int> ...

	BubbleSort(vet);

	for(int num : vet)
		cout << num << " ";

	return 0;
}
