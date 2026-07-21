class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        //ngl i couldnt solve this question on my own so i had to ask ai
        int n = s.length();
        int totalOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') totalOnes++;
        }

        // Build runs: each element is [character (0 or 1), length]
        List<int[]> runs = new ArrayList<>();
        int i = 0;
        while (i < n) {
            char cur = s.charAt(i);
            int cnt = 0;
            while (i < n && s.charAt(i) == cur) {
                cnt++;
                i++;
            }
            runs.add(new int[]{cur - '0', cnt});
        }

        int maxGain = 0;
        // Only consider interior 1‑runs (surrounded by 0‑runs)
        for (int idx = 1; idx < runs.size() - 1; idx++) {
            int[] run = runs.get(idx);
            if (run[0] == 1) {
                int[] left = runs.get(idx - 1);
                int[] right = runs.get(idx + 1);
                if (left[0] == 0 && right[0] == 0) {
                    int gain = left[1] + right[1];
                    if (gain > maxGain) maxGain = gain;
                }
            }
        }

        return totalOnes + maxGain;
    }
}
