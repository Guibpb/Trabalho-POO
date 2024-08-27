#include <iostream>
#include <ctime>


void media_vetores1(int x[], float a[], int n){
 for(int i = 0; i < n; i++){
   int _a = 0;
   for(int j = 0; j <= i; j++){
     _a += x[j];
   }
   a[i] =  _a/(i+1);
 }
}


void media_vetores2(int x[], float a[], int n){
 int sum = 0;


 for(int i = 0; i < n; i++){
   sum += x[i];
   a[i] = sum/(i+1);
 }
}


int main(){
 clock_t tempo1, tempo2;
 double tempo_total;
 float *a;
 int *x;
 int n;


 tempo1 = clock();//começa o codigo


 std::cin >> n;


 x = new int[n];
 a = new float[n];


 for(int i = 0; i < n; i++){
   x[i] = 1;
 }


 media_vetores1(x, a, n);
 //media_vetores2(x, a, n);




 tempo2 = clock(); //termina o codigo


 tempo_total = difftime(tempo2, tempo1)/CLOCKS_PER_SEC;


 std::cout << "Tempo = " << tempo_total << " segundos" << std::endl;


 delete [] x;
 delete [] a;
}

