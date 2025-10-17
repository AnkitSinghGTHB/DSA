class Solution {
public:
    int maxPartitionsAfterOperations(string s, int k) {
        int n = s.size();
        unordered_map<long long, int> memo;
        //so the segmentation fault likely came from an out-of-bounds memory access in a large DP table
        function<int(int, int, int)> dfs = [&](int i, int mask, int changed) -> int {
            if (i >= n) return 1;
            long long key = (long long)i << 32 | mask << 1 | changed;
            if (memo.count(key)) return memo[key];            
            int current_char = 1 << (s[i] - 'a');
            int new_mask = mask | current_char;
            int ans;            
            if (__builtin_popcount(new_mask) > k) {
                ans = dfs(i + 1, current_char, changed) + 1;
            }
            else {
                ans = dfs(i + 1, new_mask, changed);
            }            
            if (changed) {
                for (int j = 0; j < 26; j++) {
                    new_mask = mask | (1 << j);
                    if (__builtin_popcount(new_mask) > k) {
                        ans = max(ans, dfs(i + 1, 1 << j, 0) + 1);
                    } else {
                        ans = max(ans, dfs(i + 1, new_mask, 0));
                    }
                }
            }
            
            return memo[key] = ans;
        };        
        return dfs(0, 0, 1);
    }
};
