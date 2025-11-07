class Solution {
public:
    long long maxPower(vector<int>& stations, int r, int k) {
        int n = stations.size();
        vector<long long> pref(n + 1, 0);
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + stations[i];
        }
        vector<long long> p(n);
        for (int i = 0; i < n; i++) {
            int left = max(0, i - r);
            int right = min(n - 1, i + r);
            p[i] = pref[right + 1] - pref[left];
        }
        
        long long low = 0, high = 1e18;
        while (low <= high) {
            long long mid = low + (high - low) / 2;
            if (check(mid, p, r, k)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }
    
    bool check(long long mid, vector<long long>& p, int r, int k) {
        int n = p.size();
        long long used = 0;
        long long cur_add = 0;
        queue<pair<int, long long>> q;
        for (int i = 0; i < n; i++) {
            while (!q.empty() && q.front().first + r < i) {
                cur_add -= q.front().second;
                q.pop();
            }
            if (p[i] + cur_add < mid) {
                long long need = mid - (p[i] + cur_add);
                if (used + need > k) {
                    return false;
                }
                used += need;
                int pos = i + r;
                if (pos >= n) {
                    pos = n - 1;
                }
                cur_add += need;
                q.push({pos, need});
            }
        }
        return true;
    }
};
