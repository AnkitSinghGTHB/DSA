import java.util.Arrays;
//had to tke help from deepseek, cuz i did a mistake in my previous code
class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] a = s.toCharArray();
        char[] sorted = a.clone();
        Arrays.sort(sorted);

        // Already sorted
        if (Arrays.equals(a, sorted)) {
            return 0;
        }

        // For n == 2, any proper substring has length 1,
        // so sorting cannot change anything.
        if (n == 2) {
            return -1;
        }

        // First and last mismatch positions
        int L = 0;
        while (L < n && a[L] == sorted[L]) L++;

        int R = n - 1;
        while (R >= 0 && a[R] == sorted[R]) R--;

        // If the mismatched part is not the whole string,
        // one operation on that proper substring sorts it.
        if (L != 0 || R != n - 1) {
            return 1;
        }

        // Both ends are wrong.
        // Check if the global min occurs before the last index,
        // or the global max occurs after the first index.
        char minChar = sorted[0];
        char maxChar = sorted[n - 1];

        boolean minBeforeLast = false;
        boolean maxAfterFirst = false;

        for (int i = 1; i <= n - 2; i++) {
            if (a[i] == minChar) minBeforeLast = true;
            if (a[i] == maxChar) maxAfterFirst = true;
        }

        if (minBeforeLast || maxAfterFirst) {
            return 2;
        }

        return 3;
    }
}
