//seriously i had to ask this to deepseek cuz i didnt know
class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] arr = new int[n][4]; // [l, r, w, originalIndex]
        for (int i = 0; i < n; i++) {
            List<Integer> it = intervals.get(i);
            arr[i][0] = it.get(0);
            arr[i][1] = it.get(1);
            arr[i][2] = it.get(2);
            arr[i][3] = i;
        }
        // Sort by right endpoint ascending
        Arrays.sort(arr, (a, b) -> Integer.compare(a[1], b[1]));

        int[] L = new int[n], R = new int[n], W = new int[n], orig = new int[n];
        for (int i = 0; i < n; i++) {
            L[i] = arr[i][0];
            R[i] = arr[i][1];
            W[i] = arr[i][2];
            orig[i] = arr[i][3];
        }

        // prev[i] = largest index j < i such that R[j] < L[i]
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            int target = L[i] - 1;
            int lo = 0, hi = i - 1, ans = -1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (R[mid] <= target) {
                    ans = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            prev[i] = ans;
        }

        final int MAXC = 4;
        long[][] bestW = new long[n][MAXC + 1];
        long[][] bestList = new long[n][MAXC + 1];

        for (int p = 0; p < n; p++) {
            bestW[p][0] = 0;
            bestList[p][0] = pack(new int[0]);

            for (int c = 1; c <= MAXC; c++) {
                // Candidate 1: do not use interval p
                long w1 = (p > 0) ? bestW[p - 1][c] : -1;
                long list1 = (p > 0) ? bestList[p - 1][c] : 0;

                // Candidate 2: use interval p as the last one
                long w2 = -1;
                long list2 = 0;
                int q = prev[p];
                if (c == 1) {
                    w2 = W[p];
                    list2 = pack(new int[]{orig[p]});
                } else if (q >= 0) {
                    long prevW = bestW[q][c - 1];
                    if (prevW >= 0) {
                        w2 = prevW + W[p];
                        list2 = insert(bestList[q][c - 1], orig[p]);
                    }
                }

                // Choose the better candidate (max weight, then lexicographically smallest list)
                if (w1 > w2 || (w1 == w2 && compareLists(list1, list2) < 0)) {
                    bestW[p][c] = w1;
                    bestList[p][c] = list1;
                } else {
                    bestW[p][c] = w2;
                    bestList[p][c] = list2;
                }
            }
        }

        // Global best over all counts
        long bestWeight = -1;
        long bestListPacked = 0;
        for (int c = 0; c <= MAXC; c++) {
            long w = bestW[n - 1][c];
            long list = bestList[n - 1][c];
            if (w > bestWeight || (w == bestWeight && compareLists(list, bestListPacked) < 0)) {
                bestWeight = w;
                bestListPacked = list;
            }
        }
        return unpack(bestListPacked);
    }

    // Pack a sorted array of up to 4 indices into a long (16 bits each, 0xFFFF = empty)
    private long pack(int[] arr) {
        long res = 0xFFFFFFFFFFFFFFFFL;
        for (int i = 0; i < arr.length; i++) {
            int shift = (3 - i) * 16;
            res &= ~(0xFFFFL << shift);
            res |= ((long) arr[i]) << shift;
        }
        return res;
    }

    // Unpack a long into a sorted int array
    private int[] unpack(long packed) {
        int[] res = new int[4];
        int len = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (3 - i) * 16;
            int val = (int) ((packed >>> shift) & 0xFFFF);
            if (val == 0xFFFF) break;
            res[len++] = val;
        }
        return Arrays.copyOf(res, len);
    }

    // Insert a new index into a packed list, keeping it sorted
    private long insert(long packed, int newIdx) {
        int[] arr = unpack(packed);
        int[] newArr = Arrays.copyOf(arr, arr.length + 1);
        newArr[arr.length] = newIdx;
        Arrays.sort(newArr);
        return pack(newArr);
    }

    // Compare two packed lists lexicographically
    private int compareLists(long a, long b) {
        int[] arrA = unpack(a);
        int[] arrB = unpack(b);
        int minLen = Math.min(arrA.length, arrB.length);
        for (int i = 0; i < minLen; i++) {
            if (arrA[i] != arrB[i]) {
                return Integer.compare(arrA[i], arrB[i]);
            }
        }
        return Integer.compare(arrA.length, arrB.length);
    }
}
