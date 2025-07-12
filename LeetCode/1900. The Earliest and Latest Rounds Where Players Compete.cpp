#include <vector>
#include <set>
#include <tuple>
#include <climits>
#include <algorithm>
using namespace std;

class Solution {
public:
    int get_segment(int a, int b, int c, int index) {
        if (index < a) return 0;
        if (index == a) return 1;
        if (index < a + b + 1) return 2;
        if (index == a + b + 1) return 3;
        return 4;
    }

    void dfs_simulate(int a, int b, int c, int i, int na, int nb, int nc, 
                      set<tuple<int, int, int>>& next_states, bool& met) {
        int total = a + b + c + 2;
        int j = total - 1 - i;

        if (i > j) {
            next_states.insert(make_tuple(na, nb, nc));
            return;
        }

        if (met) return;

        if (i == j) {
            int seg = get_segment(a, b, c, i);
            if (seg == 0) na++;
            else if (seg == 2) nb++;
            else if (seg == 4) nc++;
            next_states.insert(make_tuple(na, nb, nc));
            return;
        }

        int seg_i = get_segment(a, b, c, i);
        int seg_j = get_segment(a, b, c, j);

        if ((seg_i == 1 && seg_j == 3) || (seg_i == 3 && seg_j == 1)) {
            met = true;
            return;
        }

        if (seg_i == 1 || seg_i == 3) {
            dfs_simulate(a, b, c, i+1, na, nb, nc, next_states, met);
            return;
        }

        if (seg_j == 1 || seg_j == 3) {
            dfs_simulate(a, b, c, i+1, na, nb, nc, next_states, met);
            return;
        }

        int nna1 = na, nnb1 = nb, nnc1 = nc;
        if (seg_i == 0) nna1++;
        else if (seg_i == 2) nnb1++;
        else if (seg_i == 4) nnc1++;
        dfs_simulate(a, b, c, i+1, nna1, nnb1, nnc1, next_states, met);

        int nna2 = na, nnb2 = nb, nnc2 = nc;
        if (seg_j == 0) nna2++;
        else if (seg_j == 2) nnb2++;
        else if (seg_j == 4) nnc2++;
        dfs_simulate(a, b, c, i+1, nna2, nnb2, nnc2, next_states, met);
    }

    pair<int, int> dfs_state(int a, int b, int c, vector<vector<vector<pair<int, int>>>>& memo) {
        if (memo[a][b][c] != make_pair(-1, -1)) {
            return memo[a][b][c];
        }

        int total = a + b + c + 2;
        if (total == 2) {
            return memo[a][b][c] = {1, 1};
        }

        bool met = false;
        set<tuple<int, int, int>> next_states;
        dfs_simulate(a, b, c, 0, 0, 0, 0, next_states, met);

        if (met) {
            return memo[a][b][c] = {1, 1};
        }

        int min_round = INT_MAX;
        int max_round = INT_MIN;

        for (auto& state : next_states) {
            int na = get<0>(state);
            int nb = get<1>(state);
            int nc = get<2>(state);
            auto res = dfs_state(na, nb, nc, memo);
            min_round = min(min_round, res.first + 1);
            max_round = max(max_round, res.second + 1);
        }

        return memo[a][b][c] = {min_round, max_round};
    }

    vector<int> earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        int a = firstPlayer - 1;
        int b = secondPlayer - firstPlayer - 1;
        int c = n - secondPlayer;

        vector<vector<vector<pair<int, int>>>> memo(
            27, 
            vector<vector<pair<int, int>>>(
                27, 
                vector<pair<int, int>>(27, make_pair(-1, -1))
            )
        );

        auto res = dfs_state(a, b, c, memo);
        return {res.first, res.second};
    }
};
