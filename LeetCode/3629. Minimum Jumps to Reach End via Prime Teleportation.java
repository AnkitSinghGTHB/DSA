class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        //find the maximum value to bound our spf array
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) maxVal = num;
        }
        //build the spf array using a sieve
        int[] spf = new int[maxVal + 1];
        for (int i = 2; i <= maxVal; i++) {
            spf[i] = i;
        }
        for (int i = 2; i * i <= maxVal; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= maxVal; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
        //mark which prime numbers are actually present in the array
        boolean[] isPrimePresent = new boolean[maxVal + 1];
        for (int num : nums) {
            if (num >= 2 && spf[num] == num) {
                isPrimePresent[num] = true;
            }
        }
        //build buckets mapping each prime to the indices divisible by it
        //we use flat arrays instead of HashMap<Integer, List<Integer>> to heavily optimize speed and GC.
        int[] head = new int[maxVal + 1];
        Arrays.fill(head, -1);
        // 2 * 3 * 5 * 7 * 11 * 13 * 17 = 510,510. A number <= 10^6 has at most ~7 distinct prime factors.
        int maxEdges = n * 8; 
        int[] to = new int[maxEdges];
        int[] next = new int[maxEdges];
        int edgeCount = 0;
        for (int i = 0; i < n; i++) {
            int temp = nums[i];
            int lastPrime = 0;
            while (temp > 1) {
                int p = spf[temp];
                //only register unique primes that are present as exact values in `nums`
                if (p != lastPrime) {
                    if (isPrimePresent[p]) {
                        to[edgeCount] = i;
                        next[edgeCount] = head[p];
                        head[p] = edgeCount++;
                    }
                    lastPrime = p;
                }
                temp /= p;
            }
        }
        //array based bfs
        int[] q = new int[n];
        int qHead = 0, qTail = 0;
        boolean[] visited = new boolean[n];
        q[qTail++] = 0;
        visited[0] = true;
        int jumps = 0;
        while (qHead < qTail) {
            int size = qTail - qHead;
            for (int i = 0; i < size; i++) {
                int curr = q[qHead++];
                //if we reached the target index
                if (curr == n - 1) return jumps;
                //adjacent steps
                if (curr - 1 >= 0 && !visited[curr - 1]) {
                    visited[curr - 1] = true;
                    q[qTail++] = curr - 1;
                }
                if (curr + 1 < n && !visited[curr + 1]) {
                    visited[curr + 1] = true;
                    q[qTail++] = curr + 1;
                }
                //prime teleportation logic
                int val = nums[curr];
                if (val >= 2 && spf[val] == val) { //current number is a prime
                    int edge = head[val];
                    while (edge != -1) {
                        int nextIdx = to[edge];
                        if (!visited[nextIdx]) {
                            visited[nextIdx] = true;
                            q[qTail++] = nextIdx;
                        }
                        edge = next[edge];
                    }
                    //clear the bucket to ensure we only traverse these teleportations once O(V+E)
                    head[val] = -1; 
                }
            }
            jumps++;
        }        
        return -1;
    }
}
