class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        // If target doesn't appear, return 0 (no subarray can have it as majority)
        boolean appears = false;
        for (int x : nums) {
            if (x == target) {
                appears = true;
                break;
            }
        }
        if (!appears) return 0;

        int n = nums.length;
        int[] pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int val = (nums[i] == target) ? 1 : -1;
            pref[i + 1] = pref[i] + val;
        }

        // Coordinate compression of prefix sums
        int[] sorted = pref.clone();
        Arrays.sort(sorted);
        // Remove duplicates manually
        int m = 1;
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] != sorted[i - 1]) {
                sorted[m++] = sorted[i];
            }
        }
        // Only first m elements are unique
        int[] uniq = Arrays.copyOf(sorted, m);

        Fenwick bit = new Fenwick(m);
        long ans = 0;

        for (int j = 0; j <= n; j++) {
            // Index of pref[j] in uniq (1-based for BIT)
            int idx = lowerBound(uniq, pref[j]) + 1;  // 1‑based index
            // Count previous prefix sums strictly less than pref[j]
            ans += bit.query(idx - 1);
            // Insert current prefix sum
            bit.add(idx, 1);
        }
        return ans;
    }

    // Returns the first index where value >= target
    private int lowerBound(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    // Fenwick Tree (Binary Indexed Tree)
    class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void add(int idx, int delta) {
            while (idx <= n) {
                tree[idx] += delta;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += tree[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }
}
