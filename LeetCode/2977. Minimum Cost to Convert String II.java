class Solution {
    private static class TrieNode {
        TrieNode[] children;
        int id;
        TrieNode() {
            children = new TrieNode[26];
            id = -1;
        }
    }

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        int n = source.length();        
        Map<String, Integer> strToId = new HashMap<>();//map each unique string to an id
        List<String> idToStr = new ArrayList<>();
        for (String s : original) {
            if (!strToId.containsKey(s)) {
                strToId.put(s, idToStr.size());
                idToStr.add(s);
            }
        }
        for (String s : changed) {
            if (!strToId.containsKey(s)) {
                strToId.put(s, idToStr.size());
                idToStr.add(s);
            }
        }
        int m = idToStr.size();
        boolean[] isOriginal = new boolean[m];
        boolean[] isChanged = new boolean[m];
        for (String s : original) {
            isOriginal[strToId.get(s)] = true;
        }
        for (String s : changed) {
            isChanged[strToId.get(s)] = true;
        }        
        TrieNode root = new TrieNode();//build trie of all unique strings
        for (int i = 0; i < m; i++) {
            String s = idToStr.get(i);
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
            node.id = i;
        }       
        long INF = Long.MAX_VALUE / 2;//build graph of strings
        long[][] dist = new long[m][m];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for (int i = 0; i < original.length; i++) {
            int u = strToId.get(original[i]);
            int v = strToId.get(changed[i]);
            int c = cost[i];
            if (c < dist[u][v]) {
                dist[u][v] = c;
            }
        }
        //yo we using floyd-warshall
        for (int k = 0; k < m; k++) {
            for (int i = 0; i < m; i++) {
                if (dist[i][k] == INF) continue;
                for (int j = 0; j < m; j++) {
                    if (dist[k][j] == INF) continue;
                    long nd = dist[i][k] + dist[k][j];
                    if (nd < dist[i][j]) {
                        dist[i][j] = nd;
                    }
                }
            }
        }
        //for each starting index, find all matching strings in source and target
        List<int[]>[] sourceMatches = new List[n];
        List<int[]>[] targetMatches = new List[n];
        for (int i = 0; i < n; i++) {
            sourceMatches[i] = new ArrayList<>();
            targetMatches[i] = new ArrayList<>();
        }
        // source
        for (int start = 0; start < n; start++) {
            TrieNode node = root;
            for (int i = start; i < n; i++) {
                int idx = source.charAt(i) - 'a';
                if (node.children[idx] == null) break;
                node = node.children[idx];
                if (node.id != -1) {
                    sourceMatches[start].add(new int[]{i - start + 1, node.id});
                }
            }
        }
        // target
        for (int start = 0; start < n; start++) {
            TrieNode node = root;
            for (int i = start; i < n; i++) {
                int idx = target.charAt(i) - 'a';
                if (node.children[idx] == null) break;
                node = node.children[idx];
                if (node.id != -1) {
                    targetMatches[start].add(new int[]{i - start + 1, node.id});
                }
            }
        }

        //build intervals for DP: for each start j, list of (end, cost)
        List<long[]>[] intervals = new List[n];
        for (int j = 0; j < n; j++) {
            intervals[j] = new ArrayList<>();
            // map length -> source id
            Map<Integer, Integer> srcMap = new HashMap<>();
            for (int[] p : sourceMatches[j]) {
                srcMap.put(p[0], p[1]);
            }
            // map length -> target id
            Map<Integer, Integer> tgtMap = new HashMap<>();
            for (int[] p : targetMatches[j]) {
                tgtMap.put(p[0], p[1]);
            }
            // common lengths
            for (Map.Entry<Integer, Integer> e : srcMap.entrySet()) {
                int len = e.getKey();
                int sid = e.getValue();
                if (tgtMap.containsKey(len)) {
                    int tid = tgtMap.get(len);
                    long c;
                    if (sid == tid) {
                        c = 0;
                    } else if (isOriginal[sid] && isChanged[tid]) {
                        c = dist[sid][tid];
                        if (c >= INF) continue;
                    } else {
                        continue;
                    }
                    int end = j + len;
                    if (end <= n) {
                        intervals[j].add(new long[]{end, c});
                    }
                }
            }
            // single character match
            if (source.charAt(j) == target.charAt(j)) {
                intervals[j].add(new long[]{j + 1, 0});
            }
        }
        long[] dp = new long[n + 1]; //dp
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int j = 0; j < n; j++) {
            if (dp[j] == INF) continue;
            for (long[] iv : intervals[j]) {
                int end = (int) iv[0];
                long c = iv[1];
                if (dp[j] + c < dp[end]) {
                    dp[end] = dp[j] + c;
                }
            }
        }
        return dp[n] == INF ? -1 : dp[n];
    }
}
