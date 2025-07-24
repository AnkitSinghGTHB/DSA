class Solution {
public:
    int minimumScore(vector<int>& nums, vector<vector<int>>& edges) {
        //this looks like a dfs ques, lmao
        int n = nums.size();
        int totalXOR = 0;
        for (int x : nums) totalXOR ^= x;
        vector<vector<int>> graph(n);
        for (auto& e : edges) {
            graph[e[0]].push_back(e[1]);
            graph[e[1]].push_back(e[0]);
        }
        vector<int> parent(n, -1);
        vector<int> depth(n, 0);
        vector<int> inTime(n, 0);
        vector<int> outTime(n, 0);
        vector<int> xorSubtree = nums;
        int timer = 0;
        function<void(int, int)> dfs = [&](int u, int p) {
            parent[u] = p;
            inTime[u] = timer++;
            for (int v : graph[u]) {
                if (v == p) continue;
                depth[v] = depth[u] + 1;
                dfs(v, u);
                xorSubtree[u] ^= xorSubtree[v];
            }
            outTime[u] = timer++;
        };
        dfs(0, -1);
        auto isAncestor = [&](int u, int v) {
            return (inTime[u] <= inTime[v] && outTime[u] >= outTime[v]);
        };
        vector<int> childEdges;
        for (int i = 1; i < n; i++) {
            childEdges.push_back(i);
        }
        int ans = INT_MAX;
        int m = childEdges.size();
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                int u = childEdges[i], v = childEdges[j];
                int a, b, c;
                if (isAncestor(u, v)) {
                    a = xorSubtree[v];
                    b = xorSubtree[u] ^ xorSubtree[v];
                    c = totalXOR ^ xorSubtree[u];
                } 
                else if (isAncestor(v, u)) {
                    a = xorSubtree[u];
                    b = xorSubtree[v] ^ xorSubtree[u];
                    c = totalXOR ^ xorSubtree[v];
                } 
                else {
                    a = xorSubtree[u];
                    b = xorSubtree[v];
                    c = totalXOR ^ a ^ b;
                }
                int maxVal = max({a, b, c});
                int minVal = min({a, b, c});
                ans = min(ans, maxVal - minVal);
            }
        }
        return ans;
    }
};
