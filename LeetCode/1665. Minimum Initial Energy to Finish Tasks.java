class Solution {
    public int minimumEffort(int[][] tasks) {
        //sort by (minimum - actual) descending
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
        
        int initial = 0;
        int current = 0;
        for (int[] t : tasks) {
            int actual = t[0];
            int min = t[1];
            if (current < min) {
                initial += min - current;
                current = min;
            }
            current -= actual;
        }
        return initial;
    }
}
