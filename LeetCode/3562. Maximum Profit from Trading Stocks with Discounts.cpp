class Solution {
public:
//used ai to solve this question bcoz i cannot really get what are they asking
    int maxProfit(int n, vector<int>& present, vector<int>& future, vector<vector<int>>& hierarchy, int budget) {
        const int INF = -1e9;
        vector<vector<int>> children(n + 1);
        for (const auto& h : hierarchy) {
            children[h[0]].push_back(h[1]);
        }

        vector<vector<int>> dp0(n + 1), dp1(n + 1);

        function<void(int)> dfs = [&](int u) {
            if (children[u].empty()) {
                dp0[u].assign(budget + 1, INF);
                dp1[u].assign(budget + 1, INF);
                int cost0 = present[u - 1];
                int profit0 = future[u - 1] - cost0;
                int cost1 = present[u - 1] / 2;
                int profit1 = future[u - 1] - cost1;

                dp0[u][0] = 0;
                if (cost0 <= budget) {
                    dp0[u][cost0] = max(dp0[u][cost0], profit0);
                }
                dp1[u][0] = 0;
                if (cost1 <= budget) {
                    dp1[u][cost1] = max(dp1[u][cost1], profit1);
                }
                return;
            }

            vector<int> children0(budget + 1, INF);
            children0[0] = 0;
            vector<int> children1(budget + 1, INF);
            children1[0] = 0;

            for (int v : children[u]) {
                dfs(v);

                vector<int> new_children0(budget + 1, INF);
                for (int i = 0; i <= budget; ++i) {
                    if (children0[i] == INF) continue;
                    for (int j = 0; j <= budget - i; ++j) {
                        if (dp0[v][j] == INF) continue;
                        new_children0[i + j] = max(new_children0[i + j], children0[i] + dp0[v][j]);
                    }
                }
                children0 = move(new_children0);

                vector<int> new_children1(budget + 1, INF);
                for (int i = 0; i <= budget; ++i) {
                    if (children1[i] == INF) continue;
                    for (int j = 0; j <= budget - i; ++j) {
                        if (dp1[v][j] == INF) continue;
                        new_children1[i + j] = max(new_children1[i + j], children1[i] + dp1[v][j]);
                    }
                }
                children1 = move(new_children1);
            }

            int cost0 = present[u - 1];
            int profit0 = future[u - 1] - cost0;
            int cost1 = present[u - 1] / 2;
            int profit1 = future[u - 1] - cost1;

            vector<int> temp0(budget + 1, INF);
            if (cost0 <= budget) {
                for (int cc = 0; cc <= budget - cost0; ++cc) {
                    if (children1[cc] != INF) {
                        int tot_cost = cc + cost0;
                        int tot_profit = children1[cc] + profit0;
                        temp0[tot_cost] = max(temp0[tot_cost], tot_profit);
                    }
                }
            }

            vector<int> temp1(budget + 1, INF);
            if (cost1 <= budget) {
                for (int cc = 0; cc <= budget - cost1; ++cc) {
                    if (children1[cc] != INF) {
                        int tot_cost = cc + cost1;
                        int tot_profit = children1[cc] + profit1;
                        temp1[tot_cost] = max(temp1[tot_cost], tot_profit);
                    }
                }
            }

            dp0[u].assign(budget + 1, INF);
            for (int c = 0; c <= budget; ++c) {
                int val = INF;
                if (temp0[c] != INF) val = max(val, temp0[c]);
                if (children0[c] != INF) val = max(val, children0[c]);
                if (val != INF) dp0[u][c] = val;
            }

            dp1[u].assign(budget + 1, INF);
            for (int c = 0; c <= budget; ++c) {
                int val = INF;
                if (temp1[c] != INF) val = max(val, temp1[c]);
                if (children0[c] != INF) val = max(val, children0[c]);
                if (val != INF) dp1[u][c] = val;
            }
        };

        dfs(1);

        int ans = 0;
        for (int c = 0; c <= budget; ++c) {
            ans = max(ans, dp0[1][c]);
        }
        return ans;
    }
};
