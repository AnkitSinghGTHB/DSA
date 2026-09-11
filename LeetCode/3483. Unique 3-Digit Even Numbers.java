class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];
        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;

        // 3-digit numbers: 100 to 999
        for (int num = 100; num <= 999; num++) {
            // must be even
            if (num % 2 != 0) continue;

            int a = num / 100;          // hundreds
            int b = (num / 10) % 10;    // tens
            int c = num % 10;           // ones

            int[] need = new int[10];
            need[a]++;
            need[b]++;
            need[c]++;

            boolean ok = true;
            for (int i = 0; i < 10; i++) {
                if (need[i] > freq[i]) {
                    ok = false;
                    break;
                }
            }

            if (ok) count++;
        }

        return count;
    }
}
