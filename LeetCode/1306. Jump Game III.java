class Solution {
    public boolean canReach(int[] arr, int start) {
        //seems like a path searching problem
        int n = arr.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;        
        while (!queue.isEmpty()) {
            int i = queue.poll();
            if (arr[i] == 0) {
                return true;
            }
            int next1 = i + arr[i];
            int next2 = i - arr[i];
            if (next1 >= 0 && next1 < n && !visited[next1]) {
                visited[next1] = true;
                queue.offer(next1);
            }
            if (next2 >= 0 && next2 < n && !visited[next2]) {
                visited[next2] = true;
                queue.offer(next2);
            }
        }
        return false;
    }
}
