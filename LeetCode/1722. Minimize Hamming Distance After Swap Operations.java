class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        DSU dsu = new DSU(n);
        for (int[] swap : allowedSwaps) {
            dsu.union(swap[0], swap[1]);
        }
        //group indices by component root
        Map<Integer, List<Integer>> components = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            components.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }
        int totalMatches = 0;
        for (List<Integer> indices : components.values()) {
            Map<Integer, Integer> srcFreq = new HashMap<>();
            Map<Integer, Integer> tgtFreq = new HashMap<>();
            for (int idx : indices) {
                srcFreq.put(source[idx], srcFreq.getOrDefault(source[idx], 0) + 1);
                tgtFreq.put(target[idx], tgtFreq.getOrDefault(target[idx], 0) + 1);
            }
            //count matches within this component
            for (Map.Entry<Integer, Integer> entry : srcFreq.entrySet()) {
                int val = entry.getKey();
                int srcCnt = entry.getValue();
                int tgtCnt = tgtFreq.getOrDefault(val, 0);
                totalMatches += Math.min(srcCnt, tgtCnt);
            }
        }
        return n - totalMatches;
    }
}

class DSU {
    int[] parent;
    int[] size;
    DSU(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
    void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) return;
        if (size[ra] < size[rb]) {
            parent[ra] = rb;
            size[rb] += size[ra];
        } 
        else {
            parent[rb] = ra;
            size[ra] += size[rb];
        }
    }
}
