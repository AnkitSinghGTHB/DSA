class Solution {
public:
    int maxKDivisibleComponents(int n, vector<vector<int>>& edges, vector<int>& values, int k) {
        vector<vector<int>> graph(n);
        for (const auto& e : edges) {
            graph[e[0]].push_back(e[1]);
            graph[e[1]].push_back(e[0]);
        }
        int count = 0;
        function<int(int, int)> dfs = [&](int u, int parent) {
            int total = values[u] % k;
            for (int v : graph[u]) {
                if (v == parent) continue;
                int rem = dfs(v, u);
                if (rem == 0) {
                    count++;
                } 
                else {
                    total = (total + rem) % k;
                }
            }
            return total;
        };
        dfs(0, -1);
        return count + 1;
    }
};
