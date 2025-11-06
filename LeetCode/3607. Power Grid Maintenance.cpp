class Solution {
public:
    vector<int> parent;

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    vector<int> processQueries(int c, vector<vector<int>>& connections, vector<vector<int>>& queries) {
        //i used deepseek to help me
        parent.resize(c + 1);
        iota(parent.begin(), parent.end(), 0);
        
        for (const auto& conn : connections) {
            int u = conn[0];
            int v = conn[1];
            int ru = find(u);
            int rv = find(v);
            if (ru != rv) {
                if (ru < rv) {
                    parent[rv] = ru;
                } else {
                    parent[ru] = rv;
                }
            }
        }
        
        vector<int> comp(c + 1);
        for (int i = 1; i <= c; i++) {
            comp[i] = find(i);
        }
        
        vector<set<int>> comp_sets(c + 1);
        for (int i = 1; i <= c; i++) {
            comp_sets[comp[i]].insert(i);
        }
        
        vector<int> res;
        for (const auto& q : queries) {
            int type = q[0];
            int x = q[1];
            int root = comp[x];
            if (type == 1) {
                if (comp_sets[root].count(x)) {
                    res.push_back(x);
                } else {
                    if (!comp_sets[root].empty()) {
                        res.push_back(*comp_sets[root].begin());
                    } else {
                        res.push_back(-1);
                    }
                }
            } else {
                if (comp_sets[root].count(x)) {
                    comp_sets[root].erase(x);
                }
            }
        }
        
        return res;
    }
};
