class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        //i had to ask  deepseek,my brain wsnt working
        int MAXF = 1500;
        int[] freq = new int[MAXF + 1];
        for (int[] it : items) freq[it[0]]++;

        int[] totalMultiples = new int[MAXF + 1];
        for (int a = 1; a <= MAXF; a++) {
            for (int m = a; m <= MAXF; m += a) {
                totalMultiples[a] += freq[m];
            }
        }

        // group bonuses by price
        List<Integer>[] group = new List[budget + 1];
        for (int i = 0; i <= budget; i++) group[i] = new ArrayList<>();

        for (int[] it : items) {
            int factor = it[0], price = it[1];
            if (price > budget) continue;
            group[price].add(totalMultiples[factor]);
        }

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        int ans = 0;

        for (int c = budget; c >= 1; c--) {
            if (group[c].isEmpty()) continue;

            List<Integer> bonuses = group[c];
            bonuses.sort(Collections.reverseOrder());

            int[] prefix = new int[bonuses.size() + 1];
            for (int i = 0; i < bonuses.size(); i++) {
                prefix[i + 1] = prefix[i] + bonuses.get(i);
            }

            // c is the minimum price
            for (int k = 1; k <= bonuses.size() && k * c <= budget; k++) {
                int addCost = k * c;
                int addValue = prefix[k];
                for (int C = 0; C + addCost <= budget; C++) {
                    if (dp[C] < 0) continue;
                    int totalCost = C + addCost;
                    int value = dp[C] + addValue;
                    int extra = (budget - totalCost) / c;
                    ans = Math.max(ans, value + extra);
                }
            }

            // merge price c into dp (0/1 knapsack)
            for (int b : bonuses) {
                for (int C = budget; C >= c; C--) {
                    if (dp[C - c] >= 0) {
                        dp[C] = Math.max(dp[C], dp[C - c] + b);
                    }
                }
            }
        }

        return ans;
    }
}
