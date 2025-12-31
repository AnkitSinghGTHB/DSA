class DSU {
public:
    vector<int> parent, rank;
    DSU(int n) {
        parent.resize(n);
        rank.resize(n, 0);
        for (int i = 0; i < n; ++i) parent[i] = i;
    }
    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    void unite(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx == ry) return;
        if (rank[rx] < rank[ry]) parent[rx] = ry;
        else if (rank[rx] > rank[ry]) parent[ry] = rx;
        else {
            parent[ry] = rx;
            rank[rx]++;
        }
    }
};

class Solution {
public:
    int latestDayToCross(int row, int col, vector<vector<int>>& cells) {
        int n = row * col;
        for (auto& cell : cells) {//convert cells to 0-indexed
            cell[0]--;
            cell[1]--;
        }
        DSU dsu(n + 2);
        int source = n, sink = n + 1;
        vector<bool> land(n, false);
        // dirs: up, down, left, right
        vector<pair<int, int>> dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // in reverse order
        for (int i = n - 1; i >= 0; --i) {
            int r = cells[i][0], c = cells[i][1];
            int id = r * col + c;
            land[id] = true;
            for (auto& d : dirs) {// connect with neighboring land cells
                int nr = r + d.first, nc = c + d.second;
                if (nr >= 0 && nr < row && nc >= 0 && nc < col) {
                    int nid = nr * col + nc;
                    if (land[nid]) {
                        dsu.unite(id, nid);
                    }
                }
            }            
            if (r == 0) dsu.unite(id, source);// connect to source/sink if on top/bottom row
            if (r == row - 1) dsu.unite(id, sink);            
            if (dsu.find(source) == dsu.find(sink)) {//check if source and sink are connected
                return i; // i corresponds to the day index (0-indexed day)
            }
        }
        return 0; // should never reach here because day 0 is always connected
    }
};
