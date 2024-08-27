#include <iostream>
#include <string>

using namespace std;

class Solution{
public:
	string IntToRoman(int num){
		string s[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		int n[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		int i = 0;
		string answer;

		while(num>0){
			if(num >= n[i]){
				num -= n[i];
				answer += s[i];
			}
			else
				i++;
		}

		return answer;
	}
};

int main(){
	cout << "Insira um numero em decimal: ";
	int n;
	cin >> n;
	Solution obj;

	cout << "Em romano eh: " << obj.IntToRoman(n) << endl;
}
