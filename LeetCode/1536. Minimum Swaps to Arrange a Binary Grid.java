class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] lastOne = new int[n];
        for (int i = 0; i < n; i++) {
            int j = n - 1;
            while (j >= 0 && grid[i][j] == 0) j--;
            lastOne[i] = j; // -1 if no 1
        }        
        int[] sorted = lastOne.clone();//check feasibility
        java.util.Arrays.sort(sorted);
        for (int i = 0; i < n; i++) {
            if (sorted[i] > i) return -1;
        }        
        //simulate greedy swaps
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        for (int val : lastOne) list.add(val);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // find first row from i that has lastOne <= i
            int j = i;
            while (j < n && list.get(j) > i) j++;
            // bring row j to position i by adjacent swaps
            for (int k = j; k > i; k--) {
                // swap k and k-1
                int tmp = list.get(k);
                list.set(k, list.get(k-1));
                list.set(k-1, tmp);
                ans++;
            }
        }
        return ans;
    }
}
