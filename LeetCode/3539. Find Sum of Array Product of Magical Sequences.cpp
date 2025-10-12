class Solution {
public:
//this solution was made with help from AI chatbot
    int magicalSum(int m, int k, vector<int>& nums) {
        const int MOD = 1e9 + 7;
        int n = nums.size();
        
        // Precompute combinations
        vector<vector<long long>> comb(m + 1, vector<long long>(m + 1, 0));
        for (int i = 0; i <= m; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i-1][j-1] + comb[i-1][j]) % MOD;
            }
        }
        
        // Precompute powers for each nums[i]
        vector<vector<long long>> pow_val(n, vector<long long>(m + 1, 1));
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                pow_val[i][j] = pow_val[i][j-1] * nums[i] % MOD;
            }
        }
        
        // Memoization table
        vector<vector<vector<vector<long long>>>> memo(
            m + 1, 
            vector<vector<vector<long long>>>(
                k + 1,
                vector<vector<long long>>(
                    n + 1,
                    vector<long long>(m + 1, -1)
                )
            )
        );
        
        function<long long(int, int, int, int)> dp = [&](int m_remaining, int k_remaining, int i, int carry) -> long long {
            // Check if impossible
            if (m_remaining < 0 || k_remaining < 0) return 0;
            
            // Count bits in carry
            int carry_bits = __builtin_popcount(carry);
            if (m_remaining + carry_bits < k_remaining) return 0;
            
            // Base case: no more numbers to choose
            if (m_remaining == 0) {
                return (k_remaining == carry_bits) ? 1 : 0;
            }
            
            // Base case: no more positions available
            if (i == n) return 0;
            
            // Check memo
            if (memo[m_remaining][k_remaining][i][carry] != -1) {
                return memo[m_remaining][k_remaining][i][carry];
            }
            
            long long res = 0;
            
            // Try choosing 0 to m_remaining copies of current position
            for (int count = 0; count <= m_remaining; count++) {
                long long contribution = comb[m_remaining][count] * pow_val[i][count] % MOD;
                
                int new_carry = carry + count;
                int bit = new_carry % 2;  // current bit that gets set
                int next_carry = new_carry / 2;
                
                res = (res + dp(m_remaining - count, k_remaining - bit, i + 1, next_carry) * contribution) % MOD;
            }
            
            return memo[m_remaining][k_remaining][i][carry] = res;
        };
        
        return dp(m, k, 0, 0);
    }
};
