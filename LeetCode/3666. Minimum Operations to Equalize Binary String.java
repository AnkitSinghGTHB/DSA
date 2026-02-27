class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int zeros = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') zeros++;
        }
        if (zeros == 0) return 0;
        if (k > n) return -1; // not needed per constraints but safe

        // Sets of unvisited states, grouped by parity
        TreeSet<Integer> even = new TreeSet<>();
        TreeSet<Integer> odd = new TreeSet<>();
        for (int i = 0; i <= n; i++) {
            if (i % 2 == 0) even.add(i);
            else odd.add(i);
        }

        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        visited[zeros] = true;
        if (zeros % 2 == 0) even.remove(zeros);
        else odd.remove(zeros);
        queue.offer(zeros);

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int t = 0; t < size; t++) {
                int cur = queue.poll();
                if (cur == 0) return steps;

                int ones = n - cur;
                int iMin = Math.max(0, k - ones);
                int iMax = Math.min(k, cur);
                // range of possible next zeros counts
                int low = cur + k - 2 * iMax;
                int high = cur + k - 2 * iMin;
                int parity = (cur + k) & 1; // same as (cur + k) % 2
                TreeSet<Integer> set = (parity == 0) ? even : odd;

                Integer next = set.ceiling(low);
                while (next != null && next <= high) {
                    visited[next] = true;
                    set.remove(next);
                    queue.offer(next);
                    next = set.ceiling(next); // get next after the removed one
                }
            }
            steps++;
        }
        return -1;
    }
}
