class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int n = source.length();
        int m = original.length;
        final long INF = Long.MAX_VALUE / 2;
        long[][] dist = new long[26][26];
        for (int i = 0; i < 26; i++) {//initialize distances
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {//add given edges (keeping minimum cost)
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }        
        for (int k = 0; k < 26; k++) {//floyd-warshall
            for (int i = 0; i < 26; i++) {
                if (dist[i][k] == INF) continue;
                for (int j = 0; j < 26; j++) {
                    if (dist[k][j] == INF) continue;
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }        
        long total = 0;//compute total cost
        for (int i = 0; i < n; i++) {
            int s = source.charAt(i) - 'a';
            int t = target.charAt(i) - 'a';
            if (s == t) continue;
            if (dist[s][t] == INF) return -1;
            total += dist[s][t];
        }
        return total;
    }
}
