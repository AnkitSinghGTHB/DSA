//AI assisted soln
class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        for (int[] e : edges) {
            dsu.union(e[0], e[1]);
        }

        int[] vertexCount = new int[n];
        int[] edgeCount = new int[n];

        for (int i = 0; i < n; i++) {
            vertexCount[dsu.find(i)]++;
        }
        for (int[] e : edges) {
            edgeCount[dsu.find(e[0])]++;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (vertexCount[i] > 0) {
                int v = vertexCount[i];
                if (edgeCount[i] == v * (v - 1) / 2) {
                    ans++;
                }
            }
        }
        return ans;
    }

    class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int a, int b) {
            int ra = find(a), rb = find(b);
            if (ra == rb) return;
            if (rank[ra] < rank[rb]) {
                parent[ra] = rb;
            } else if (rank[ra] > rank[rb]) {
                parent[rb] = ra;
            } else {
                parent[rb] = ra;
                rank[ra]++;
            }
        }
    }
}
