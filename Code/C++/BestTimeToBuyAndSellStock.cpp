#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Solution{
public:
    int maxProfit(vector<int> &prices){
        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.size(); ++i) {
            if(prices[i] < minPrice)
                minPrice = prices[i];

            int profit = prices[i] - minPrice;
            // Update maxProfit if the current profit is higher than the previously recorded maxProfit
            maxProfit = std::max(maxProfit, profit);
        }

        return maxProfit;
    }
};

int main(){
    vector <int> prices = {7,1,5,3,6,4};

    Solution solucao;

    cout << solucao.maxProfit(prices) << endl;


    return 0;
}
