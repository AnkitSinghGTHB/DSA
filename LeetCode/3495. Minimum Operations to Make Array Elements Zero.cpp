class Solution {
public:
    long long minOperations(vector<vector<int>>& queries) {
        long long total_ans = 0;
        for (auto& q : queries) {
            long long l = q[0];
            long long r = q[1];
            long long total_sum = 0;
            int max_level = 0;
            long long start = 1;
            int k = 1;
            while (start <= r) {
                long long end = start * 4 - 1; //hers the main trick used
                if (end < l) {
                    start = end + 1;
                    k++;
                    continue;
                }
                long long seg_start = max(l, start);
                long long seg_end = min(r, end);
                if (seg_start <= seg_end) {
                    long long count = seg_end - seg_start + 1;
                    total_sum += count * k;
                    if (k > max_level) max_level = k;
                }
                start = end + 1;
                k++;
            }
            long long ops = max(static_cast<long long>(max_level), (total_sum + 1) / 2);
            total_ans += ops;
        }
        return total_ans;
    }
};
