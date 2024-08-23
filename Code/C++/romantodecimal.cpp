#include <iostream>

class Solution {
public:
    int romanToInt(std::string s) {
        int result;
        int tamanho = s.size();

        for(int i = 0; i < tamanho; i++){
            switch (s[i]){
                case 'M':
                    result += 1000;
                    break;

                case 'D':
                    result += 500;
                    break;

                case 'C':
                    if(i < tamanho - 1){
                        if(s[i+1] == 'M' || s[i+1] == 'D')
                            result -= 100;

                        break;
                    }
                    else
                        result += 100;
                    break;

                case 'L':
                    result += 50;
                    break;

                case 'X':
                    if(i < tamanho - 1){
                        if(s[i+1] == 'L' || s[i+1] == 'C')
                            result -= 10;

                        break;
                    }
                    else
                        result += 10;
                    break;

                case 'V':
                    result += 5;
                    break;

                case 'I':
                    if(i < tamanho - 1){
                        if(s[i+1] == 'X' || s[i+1] == 'V')
                            result--;

                        break;
                    }
                    else
                        result++;
                    break;
            }
        }
        return result;
    }
};

int main(){
    Solution solution;

    std::string s = "I";

    std::cout << solution.romanToInt(s) << std::endl;



    return 0;
}
