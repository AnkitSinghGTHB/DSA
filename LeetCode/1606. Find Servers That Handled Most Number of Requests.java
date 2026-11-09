//used deepseek to understand
class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int n = arrival.length;
        int[] count = new int[k];

        // Free servers (ordered by ID)
        TreeSet<Integer> free = new TreeSet<>();
        for (int i = 0; i < k; i++) free.add(i);

        // Busy servers: min-heap by finish time, then server ID
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1])
        );

        for (int i = 0; i < n; i++) {
            int time = arrival[i];

            // Release servers that are now free
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] done = busy.poll();
                free.add(done[1]);
            }

            if (free.isEmpty()) continue; // drop request

            int start = i % k;
            Integer server = free.ceiling(start);
            if (server == null) {
                server = free.first(); // wrap around
            }

            free.remove(server);
            count[server]++;

            busy.offer(new int[]{time + load[i], server});
        }

        // Find max request count
        int maxCount = 0;
        for (int c : count) {
            if (c > maxCount) maxCount = c;
        }

        // Collect all servers with maxCount
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == maxCount) {
                result.add(i);
            }
        }
        return result;
    }
}
