        //i lowkey dont know why deepseek thought this would be the best
        //erm what the sigma

class Solution {
    private int max2, max3, max5, max7;
    private int[][][][] minLenDP;
    private int[][] digitExp;

    public String smallestNumber(String num, long t) {
        // 1. Factorize t
        int[] req = new int[4]; // 0:2, 1:3, 2:5, 3:7
        long tmp = t;
        int[] primes = {2, 3, 5, 7};
        for (int idx = 0; idx < 4; idx++) {
            while (tmp % primes[idx] == 0) {
                req[idx]++;
                tmp /= primes[idx];
            }
        }
        if (tmp != 1) return "-1"; // t has other prime factor

        // 2. Precompute digit exponents for digits 1..9 (digit 0 unused)
        digitExp = new int[10][4];
        digitExp[1] = new int[]{0,0,0,0};
        digitExp[2] = new int[]{1,0,0,0};
        digitExp[3] = new int[]{0,1,0,0};
        digitExp[4] = new int[]{2,0,0,0};
        digitExp[5] = new int[]{0,0,1,0};
        digitExp[6] = new int[]{1,1,0,0};
        digitExp[7] = new int[]{0,0,0,1};
        digitExp[8] = new int[]{3,0,0,0};
        digitExp[9] = new int[]{0,2,0,0};

        max2 = req[0]; max3 = req[1]; max5 = req[2]; max7 = req[3];

        // 3. Build minLen DP iteratively
        minLenDP = new int[max2+1][max3+1][max5+1][max7+1];
        for (int a = 0; a <= max2; a++)
            for (int b = 0; b <= max3; b++)
                for (int c = 0; c <= max5; c++)
                    for (int d = 0; d <= max7; d++)
                        minLenDP[a][b][c][d] = Integer.MAX_VALUE / 2;

        minLenDP[0][0][0][0] = 0;
        for (int a = 0; a <= max2; a++) {
            for (int b = 0; b <= max3; b++) {
                for (int c = 0; c <= max5; c++) {
                    for (int d = 0; d <= max7; d++) {
                        int cur = minLenDP[a][b][c][d];
                        if (cur >= Integer.MAX_VALUE / 2) continue;
                        for (int dig = 2; dig <= 9; dig++) {
                            int[] ex = digitExp[dig];
                            int na = Math.min(max2, a + ex[0]);
                            int nb = Math.min(max3, b + ex[1]);
                            int nc = Math.min(max5, c + ex[2]);
                            int nd = Math.min(max7, d + ex[3]);
                            if (cur + 1 < minLenDP[na][nb][nc][nd]) {
                                minLenDP[na][nb][nc][nd] = cur + 1;
                            }
                        }
                    }
                }
            }
        }

        int n = num.length();

        // Early check: if num is zero‑free and product satisfies req
        if (num.indexOf('0') == -1) {
            int[] prod = new int[4];
            for (char ch : num.toCharArray()) {
                int d = ch - '0';
                if (d != 0) add(prod, digitExp[d]);
            }
            if (ge(prod, req)) return num;
        }

        // Build prefix exponent arrays and zero‑flag
        int[][] prefExp = new int[n + 1][4];
        boolean[] prefZero = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(prefExp[i], 0, prefExp[i + 1], 0, 4);
            char ch = num.charAt(i);
            if (ch == '0') {
                prefZero[i + 1] = true;
            } else {
                prefZero[i + 1] = prefZero[i];
                add(prefExp[i + 1], digitExp[ch - '0']);
            }
        }

        // 4. Try to increase one digit (same length)
        for (int i = n - 1; i >= 0; i--) {
            if (prefZero[i]) continue; // prefix already has zero
            int curDigit = num.charAt(i) - '0';
            for (int d = curDigit + 1; d <= 9; d++) {
                int[] need = new int[4];
                int[] total = Arrays.copyOf(prefExp[i], 4);
                add(total, digitExp[d]);
                for (int j = 0; j < 4; j++) {
                    need[j] = Math.max(0, req[j] - total[j]);
                }
                int suffixLen = n - i - 1;
                if (suffixLen >= minLen(need[0], need[1], need[2], need[3])) {
                    String suffix = buildSmallest(need, suffixLen);
                    if (suffix != null) {
                        return num.substring(0, i) + d + suffix;
                    }
                }
            }
        }

        // 5. No same‑length solution → increase length
        int minTotal = minLen(req[0], req[1], req[2], req[3]);
        int L = Math.max(n + 1, minTotal);
        String result = buildSmallest(req, L);
        return result == null ? "-1" : result;
    }

    // Helper: add exponent array b to a
    private void add(int[] a, int[] b) {
        for (int i = 0; i < 4; i++) a[i] += b[i];
    }

    // Helper: check if a >= b componentwise
    private boolean ge(int[] a, int[] b) {
        for (int i = 0; i < 4; i++) if (a[i] < b[i]) return false;
        return true;
    }

    // Query minLen from precomputed DP (cap values)
    private int minLen(int e2, int e3, int e5, int e7) {
        int a = Math.min(max2, e2);
        int b = Math.min(max3, e3);
        int c = Math.min(max5, e5);
        int d = Math.min(max7, e7);
        return minLenDP[a][b][c][d];
    }

    // Greedily build the smallest string of given length that meets `need`
    private String buildSmallest(int[] need, int length) {
        if (length < minLen(need[0], need[1], need[2], need[3])) return null;
        StringBuilder sb = new StringBuilder();
        int[] curNeed = need.clone();
        for (int pos = 0; pos < length; pos++) {
            for (int d = 1; d <= 9; d++) {
                int[] ex = digitExp[d];
                int ne2 = Math.max(0, curNeed[0] - ex[0]);
                int ne3 = Math.max(0, curNeed[1] - ex[1]);
                int ne5 = Math.max(0, curNeed[2] - ex[2]);
                int ne7 = Math.max(0, curNeed[3] - ex[3]);
                int remaining = length - pos - 1;
                if (remaining >= minLen(ne2, ne3, ne5, ne7)) {
                    sb.append((char)('0' + d));
                    curNeed[0] = ne2; curNeed[1] = ne3; curNeed[2] = ne5; curNeed[3] = ne7;
                    break;
                }
            }
        }
        return sb.toString();
    }
}
