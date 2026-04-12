class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        //precompute positions: row = (c - 'A') / 6, col = (c - 'A') % 6
        int[][] pos = new int[26][2];
        for (int c = 0; c < 26; c++) {
            pos[c][0] = c / 6;
            pos[c][1] = c % 6;
        }
        int[] dp = new int[26];
        //after first character, other finger can be anywhere with zero cost
        int prev = word.charAt(0) - 'A';
        //initially dp[j] = 0 for all j (since the other finger is free)
        for (int j = 0; j < 26; j++) dp[j] = 0;
        
        for (int i = 1; i < n; i++) {
            int cur = word.charAt(i) - 'A';
            int[] newdp = new int[26];
            Arrays.fill(newdp, Integer.MAX_VALUE / 2); // large number
            for (int j = 0; j < 26; j++) {
                int cost = dp[j];
                if (cost >= Integer.MAX_VALUE / 2) continue;
                //opt 1: move the finger that typed previous character
                int d1 = Math.abs(pos[prev][0] - pos[cur][0]) + Math.abs(pos[prev][1] - pos[cur][1]);
                newdp[j] = Math.min(newdp[j], cost + d1);
                //opt 2: move the other finger
                int d2 = Math.abs(pos[j][0] - pos[cur][0]) + Math.abs(pos[j][1] - pos[cur][1]);
                newdp[prev] = Math.min(newdp[prev], cost + d2);
            }
            dp = newdp;
            prev = cur;
        }
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < 26; j++) {
            ans = Math.min(ans, dp[j]);
        }
        return ans;
    }
}
