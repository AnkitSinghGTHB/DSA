class Solution {
public:
    //a hleper dfs fn (modified)
    void dfs(int u, int parent, const vector<vector<int>>& tree, vector<int>& depth, int& even, int& odd) {
        if (depth[u] % 2 == 0) even++;
        else odd++;
        for (int v : tree[u]) {
            if (v != parent) {
                depth[v] = depth[u] + 1;
                dfs(v, u, tree, depth, even, odd);
            }
        }
    }
    vector<int> maxTargetNodes(vector<vector<int>>& edges1, vector<vector<int>>& edges2) {
        //another tree ques? bruh leavemealone T-T
        //tree path parity only depends on depth parity, so if i connect i from t1 to 
        //some node j from t2, the depth of each node in t1 gets shifted depending on 
        //parity of depth[j]
        //heres the hint, compute an array 'even' where 'even[u]' is the number of 
        //nodes at an even distance from node 'u', for every 'u' of the first tree. 
        //compute an array 'odd' where 'odd[u]' is the number of nodes at an odd 
        //distance from node 'u', for every 'u' of the second tree.
        //finally, 'answer[i] = even[i] + max(odd[1],odd[2],...,odd[m-1])'
        int n = edges1.size() + 1;
        int m = edges2.size() + 1;
        vector<vector<int>> tree1(n), tree2(m);
        for (auto& e : edges1)
            tree1[e[0]].push_back(e[1]), tree1[e[1]].push_back(e[0]);
        for (auto& e : edges2)
            tree2[e[0]].push_back(e[1]), tree2[e[1]].push_back(e[0]);
        vector<int> depth1(n, 0), depth2(m, 0);
        int even1 = 0, odd1 = 0;
        int even2 = 0, odd2 = 0;
        dfs(0, -1, tree1, depth1, even1, odd1);
        dfs(0, -1, tree2, depth2, even2, odd2);
        int max_odd2 = max(odd2, even2);//mx num of odd-dist nodes from any node in t2
        vector<int> answer(n);
        for (int i = 0; i < n; ++i) {
            //even[i] = nodes with same parity depth as i
            int ei = (depth1[i] % 2 == 0) ? even1 : odd1;
            answer[i] = ei + max_odd2;
        }
        return answer;
    }
};
