const long long INF = -1e18;

class Solution {
public:
    long long maxCollectedFruits(vector<vector<int>>& fruits) {
        //i want to go to sleep T-T
        /*int n = fruits.size();
        if (n == 0) return 0;
        long long diagonal_sum = 0;
        for (int i = 0; i < n; i++) {
            diagonal_sum += fruits[i][i];
        }
        if (n == 1) {
            return diagonal_sum;
        }
        int minA_prev = n - 1;
        int maxA_prev = n - 1;
        int minB_prev = n - 1;
        int maxB_prev = n - 1;
        vector<vector<long long>> dp_prev(1, vector<long long>(1, 0));
        dp_prev[0][0] = (long long)fruits[0][n - 1] + (long long)fruits[n - 1][0];
        for (int t = 1; t < n; t++) {
            int minA_curr = max(0, n - 1 - t);
            int maxA_curr = min(n - 1, n - 1 + t);
            int minB_curr = max(0, n - 1 - t);
            int maxB_curr = min(n - 1, n - 1 + t);
            int countA_curr = maxA_curr - minA_curr + 1;
            int countB_curr = maxB_curr - minB_curr + 1;
            vector<vector<long long>> dp_curr(countA_curr, vector<long long>(countB_curr, INF));
            for (int a_prev_val = minA_prev; a_prev_val <= maxA_prev; a_prev_val++) {
                for (int b_prev_val = minB_prev; b_prev_val <= maxB_prev; b_prev_val++) {
                    int idx_prev_a = a_prev_val - minA_prev;
                    int idx_prev_b = b_prev_val - minB_prev;
                    long long prev_value = dp_prev[idx_prev_a][idx_prev_b];
                    if (prev_value == INF) continue;
                    for (int da = -1; da <= 1; da++) {
                        int a_curr_val = a_prev_val + da;
                        if (a_curr_val < minA_curr || a_curr_val > maxA_curr) continue;
                        for (int db = -1; db <= 1; db++) {
                            int b_curr_val = b_prev_val + db;
                            if (b_curr_val < minB_curr || b_curr_val > maxB_curr) continue;
                            long long add = 0;
                            if (a_curr_val != t) {
                                add += fruits[t][a_curr_val];
                            }
                            if (b_curr_val != t) {
                                add += fruits[b_curr_val][t];
                            }
                            int idx_curr_a = a_curr_val - minA_curr;
                            int idx_curr_b = b_curr_val - minB_curr;
                            if (prev_value + add > dp_curr[idx_curr_a][idx_curr_b]) {
                                dp_curr[idx_curr_a][idx_curr_b] = prev_value + add;
                            }
                        }
                    }
                }
            }
            dp_prev = move(dp_curr);
            minA_prev = minA_curr;
            maxA_prev = maxA_curr;
            minB_prev = minB_curr;
            maxB_prev = maxB_curr;
        }
        int a_final = n - 1;
        int b_final = n - 1;
        int idxA = a_final - minA_prev;
        int idxB = b_final - minB_prev;
        long long additional = dp_prev[idxA][idxB];
        return diagonal_sum + additional;*/
        return getTopLeft(fruits) + getTopRight(fruits) + getBottomLeft(fruits) -
           2 * fruits.back().back();
    }
    
    int getTopLeft(const vector<vector<int>>& fruits) {
        const int n = fruits.size();
        int res = 0;
        for (int i = 0; i < n; ++i)
        res += fruits[i][i];
        return res;
    }

    int getTopRight(const vector<vector<int>>& fruits) {
        const int n = fruits.size();
        vector<vector<int>> dp(n, vector<int>(n));
        dp[0][n - 1] = fruits[0][n - 1];
        for (int x = 0; x < n; ++x) {
        for (int y = 0; y < n; ++y) {
            if (x >= y && !(x == n - 1 && y == n - 1))
            continue;
            for (const auto& [dx, dy] :
                vector<pair<int, int>>{{1, -1}, {1, 0}, {1, 1}}) {
            const int i = x - dx;
            const int j = y - dy;
            if (i < 0 || i == n || j < 0 || j == n)
                continue;
            if (i < j && j < n - 1 - i)
                continue;
            dp[x][y] = max(dp[x][y], dp[i][j] + fruits[x][y]);
            }
        }
        }

        return dp[n - 1][n - 1];
    }

    int getBottomLeft(const vector<vector<int>>& fruits) {
        const int n = fruits.size();
        vector<vector<int>> dp(n, vector<int>(n));
        dp[n - 1][0] = fruits[n - 1][0];
        for (int y = 0; y < n; ++y) {
        for (int x = 0; x < n; ++x) {
            if (x <= y && !(x == n - 1 && y == n - 1))
            continue;
            for (const auto& [dx, dy] :
                vector<pair<int, int>>{{-1, 1}, {0, 1}, {1, 1}}) {
            const int i = x - dx;
            const int j = y - dy;
            if (i < 0 || i == n || j < 0 || j == n)
                continue;
            if (j < i && i < n - 1 - j)
                continue;
            dp[x][y] = max(dp[x][y], dp[i][j] + fruits[x][y]);
            }
        }
        }
        return dp[n - 1][n - 1];
    }
};
