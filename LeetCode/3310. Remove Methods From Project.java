//i couldnt solve
class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Build adjacency list
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] inv : invocations) {
            adj[inv[0]].add(inv[1]);
        }

        // Step 1: find all suspicious nodes reachable from k
        boolean[] suspicious = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(k);
        suspicious[k] = true;
        while (!stack.isEmpty()) {
            int u = stack.pop();
            for (int v : adj[u]) {
                if (!suspicious[v]) {
                    suspicious[v] = true;
                    stack.push(v);
                }
            }
        }

        // Step 2: check if any non‑suspicious node invokes a suspicious node
        for (int[] inv : invocations) {
            int a = inv[0], b = inv[1];
            if (!suspicious[a] && suspicious[b]) {
                // Cannot remove suspicious nodes → return all nodes
                List<Integer> all = new ArrayList<>();
                for (int i = 0; i < n; i++) all.add(i);
                return all;
            }
        }

        // Step 3: remove all suspicious nodes → keep only non‑suspicious
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) remaining.add(i);
        }
        return remaining;
    }
}
