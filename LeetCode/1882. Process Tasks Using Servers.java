//had to ask the boss, i mean deepseek
class Solution {
    static class BusyServer {
        long availableTime;
        int weight;
        int index;

        BusyServer(long availableTime, int weight, int index) {
            this.availableTime = availableTime;
            this.weight = weight;
            this.index = index;
        }
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        // Free servers: smallest weight, then smallest index
        PriorityQueue<int[]> free = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // Busy servers: earliest available time first
        PriorityQueue<BusyServer> busy = new PriorityQueue<>((a, b) -> {
            if (a.availableTime != b.availableTime) {
                return Long.compare(a.availableTime, b.availableTime);
            }
            if (a.weight != b.weight) {
                return Integer.compare(a.weight, b.weight);
            }
            return Integer.compare(a.index, b.index);
        });

        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }

        int[] ans = new int[m];
        long currentTime = 0;

        for (int j = 0; j < m; j++) {
            // Task j has arrived at second j.
            currentTime = Math.max(currentTime, j);

            // Move all servers that are now free into the free heap.
            while (!busy.isEmpty() && busy.peek().availableTime <= currentTime) {
                BusyServer server = busy.poll();
                free.offer(new int[]{server.weight, server.index});
            }

            // If no free server, wait until the earliest busy server becomes free.
            if (free.isEmpty()) {
                currentTime = busy.peek().availableTime;

                while (!busy.isEmpty() && busy.peek().availableTime <= currentTime) {
                    BusyServer server = busy.poll();
                    free.offer(new int[]{server.weight, server.index});
                }
            }

            // Assign task j to the best free server.
            int[] server = free.poll();
            int weight = server[0];
            int index = server[1];

            ans[j] = index;

            // This server is now busy until currentTime + tasks[j].
            busy.offer(new BusyServer(currentTime + tasks[j], weight, index));
        }

        return ans;
    }
}
