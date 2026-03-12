// Unable to create solution during uni exams, I took help from AI
class Solution {
    // Disjoint Set Union with path compression and union by size
    class DSU {
        int[] parent, size;
        int comp;
        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            comp = n;
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        // returns true if the edge was added (i.e., two different components)
        boolean union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) return false;
            if (size[x] < size[y]) {
                int t = x; x = y; y = t;
            }
            parent[y] = x;
            size[x] += size[y];
            comp--;
            return true;
        }
        int components() {
            return comp;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {
        List<int[]> mandatory = new ArrayList<>();
        List<int[]> optional = new ArrayList<>();
        int maxStrength = 0;
        for (int[] e : edges) {
            if (e[3] == 1) mandatory.add(e);
            else optional.add(e);
            maxStrength = Math.max(maxStrength, e[2]);
        }

        // 1. Check that mandatory edges do not form a cycle
        DSU dsuMand = new DSU(n);
        for (int[] e : mandatory) {
            if (!dsuMand.union(e[0], e[1])) {
                return -1; // cycle in mandatory edges
            }
        }

        // 2. Check overall connectivity using all edges
        DSU dsuAll = new DSU(n);
        for (int[] e : edges) {
            dsuAll.union(e[0], e[1]);
        }
        if (dsuAll.components() > 1) return -1;

        // Binary search on answer X
        int low = 0, high = maxStrength * 2;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (can(mid, n, mandatory, optional, k)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean can(int X, int n, List<int[]> mandatory, List<int[]> optional, int k) {
        // All mandatory edges must have strength >= X
        for (int[] e : mandatory) {
            if (e[2] < X) return false;
        }

        DSU dsu = new DSU(n);
        // Add all mandatory edges (they are all >= X)
        for (int[] e : mandatory) {
            dsu.union(e[0], e[1]);
        }
        // Add optional edges that are already strong enough (no upgrade needed)
        for (int[] e : optional) {
            if (e[2] >= X) {
                dsu.union(e[0], e[1]);
            }
        }

        int comps = dsu.components();
        if (comps == 1) return true; // already connected

        // Now we need to connect the remaining components using upgradeable edges
        // (original < X but 2*original >= X)
        // First, get the root of each node in the current DSU
        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = dsu.find(i);
        }
        // Map each root to a consecutive id 0..comps-1
        Map<Integer, Integer> idMap = new HashMap<>();
        int id = 0;
        for (int i = 0; i < n; i++) {
            int r = root[i];
            if (!idMap.containsKey(r)) {
                idMap.put(r, id++);
            }
        }
        // DSU for the components themselves
        DSU dsu2 = new DSU(comps);
        // Process upgradeable edges
        for (int[] e : optional) {
            if (e[2] < X && 2L * e[2] >= X) { // upgradeable
                int ru = root[e[0]];
                int rv = root[e[1]];
                if (ru != rv) {
                    int uid = idMap.get(ru);
                    int vid = idMap.get(rv);
                    dsu2.union(uid, vid);
                }
            }
        }

        // If all components are connected by upgrade edges, then we can pick a spanning tree
        // using exactly (comps-1) upgrades. Check if that number is within budget.
        return dsu2.components() == 1 && (comps - 1) <= k;
    }
}
