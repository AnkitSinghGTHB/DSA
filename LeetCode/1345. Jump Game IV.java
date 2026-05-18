class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;        
        //map value -> list of indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }        
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        int steps = 0;        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int i = queue.poll();
                if (i == n - 1) return steps;                
                //jmp to i+1
                if (i + 1 < n && !visited[i + 1]) {
                    visited[i + 1] = true;
                    queue.offer(i + 1);
                }
                //jmp to i-1
                if (i - 1 >= 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    queue.offer(i - 1);
                }
                //jmp to all indices with same value
                if (map.containsKey(arr[i])) {
                    for (int j : map.get(arr[i])) {
                        if (!visited[j]) {
                            visited[j] = true;
                            queue.offer(j);
                        }
                    }
                    //clear to avoid reprocessing
                    map.remove(arr[i]);
                }
            }
            steps++;
        }
        return -1; //should not happen as graph is connected via +-1
    }
}
