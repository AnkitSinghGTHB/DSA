class Solution {
public:
    double soupServings(int n) {
        //apparantly alot of people solved this question
        //ofc the probablifity that a used before b is ver high
        //it gets higher with higher n
        if (n>=4800){
            return 1;
        }
        int u = (n+24)/25;
        vector<vector<double>> memo(u+1, vector<double>(u+1,-1));
        return dp(u,u,memo);
    }
    double dp(int a, int b, vector<vector<double>>& memo) {
        if (a <= 0 && b <= 0) {
            return 0.5;
        }
        if (a <= 0) {
            return 1.0;
        }
        if (b <= 0) {
            return 0.0;
        }
        if (memo[a][b] != -1.0) {
            return memo[a][b];
        }
        double ans = 0.25*(dp(a - 4, b, memo)+dp(a - 3, b - 1, memo)+
                            dp(a - 2, b - 2, memo)+dp(a - 1, b - 3, memo));
        memo[a][b] = ans;
        return ans;
    }
};
