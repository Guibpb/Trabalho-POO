#include <iostream>
#include <string>

using namespace std;

int main(){
	int vet[10] = {1, 3, 4, 2, 7, 6, 8, 9, 5, 0};

	for(auto num : vet){
		if(num == 5)
			break;

		cout << num << endl;
	}

	return 0;
}
