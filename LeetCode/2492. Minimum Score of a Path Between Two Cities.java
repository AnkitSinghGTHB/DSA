class Solution {
    public int minScore(int n, int[][] roads) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int[] r : roads) {
            int u = r[0], v = r[1], w = r[2];
            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int[] nei : graph.get(u)) {
                int v = nei[0];
                if (!visited[v]) {
                    visited[v] = true;
                    q.offer(v);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int[] r : roads) {
            int u = r[0], v = r[1], w = r[2];
            if (visited[u] && visited[v]) {
                ans = Math.min(ans, w);
            }
        }
        return ans;
    }
}
