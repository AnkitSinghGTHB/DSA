class Solution {
public:
    long long minCost(vector<int>& basket1, vector<int>& basket2) {
        //uh looks like a greedy ques to me
        //this looks easy but surely isnt
        unordered_map<int, int> freq1, freq2;
        for (int x : basket1) freq1[x]++;
        for (int x : basket2) freq2[x]++;
        int min_val = INT_MAX;
        for (int x : basket1) 
            if (x < min_val) 
                min_val = x;
        for (int x : basket2) 
            if (x < min_val) 
                min_val = x;
        set<int> distinct;
        for (auto& p : freq1) distinct.insert(p.first);
        for (auto& p : freq2) distinct.insert(p.first);
        vector<int> list;
        for (int x : distinct) {
            int cnt1 = freq1.find(x) != freq1.end() ? freq1[x] : 0;
            int cnt2 = freq2.find(x) != freq2.end() ? freq2[x] : 0;
            long long total = (long long)cnt1 + cnt2;
            if (total % 2 != 0) {
                return -1;
            }
            int d = cnt1 - cnt2;
            d /= 2;
            if (d > 0) {
                for (int i = 0; i < d; i++) {
                    list.push_back(x);
                }
            }
            else if (d < 0) {
                d = -d;
                for (int i = 0; i < d; i++) {
                    list.push_back(x);
                }
            }
        }
        if (list.size() % 2 != 0) {
            return -1;
        }
        sort(list.begin(), list.end());
        int T = list.size() / 2;
        long long ans = 0;
        for (int i = 0; i < T; i++) {
            if (list[i] < 2 * min_val) {
                ans += list[i];
            }
            else {
                ans += 2 * (long long)min_val;
            }
        }
        return ans;
    }
};
