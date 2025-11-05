class Solution {
public:
    vector<long long> findXSum(vector<int>& nums, int k, int x) {
        //man ts so hard i asked deepseek to help me out
        int n = nums.size();
        vector<long long> ans;
        map<int, int> freq;
        auto comp = [](const pair<int, int>& a, const pair<int, int>& b) {
            if (a.first != b.first) return a.first > b.first;
            return a.second > b.second;
        };
        set<pair<int, int>, decltype(comp)> top(comp);
        set<pair<int, int>, decltype(comp)> rest(comp);
        long long total = 0;

        auto remove_from_sets = [&](pair<int, int> p) {
            if (top.find(p) != top.end()) {
                top.erase(p);
                total -= (long long) p.first * p.second;
                if (!rest.empty()) {
                    auto it = rest.begin();
                    pair<int, int> candidate = *it;
                    rest.erase(it);
                    top.insert(candidate);
                    total += (long long) candidate.first * candidate.second;
                }
            } 
            else if (rest.find(p) != rest.end()) {
                rest.erase(p);
            }
        };

        auto insert_into_sets = [&](pair<int, int> p) {
            if (top.size() < x) {
                top.insert(p);
                total += (long long) p.first * p.second;
            } 
            else {
                auto it = top.end();
                it--;
                pair<int, int> smallest_top = *it;
                if (comp(p, smallest_top)) {
                    top.erase(smallest_top);
                    total -= (long long) smallest_top.first * smallest_top.second;
                    rest.insert(smallest_top);
                    top.insert(p);
                    total += (long long) p.first * p.second;
                } 
                else {
                    rest.insert(p);
                }
            }
        };

        auto add_value = [&](int v) {
            int old_freq = freq[v];
            int new_freq = old_freq + 1;
            freq[v] = new_freq;
            if (old_freq > 0) {
                remove_from_sets({old_freq, v});
            }
            insert_into_sets({new_freq, v});
        };

        auto remove_value = [&](int v) {
            int old_freq = freq[v];
            int new_freq = old_freq - 1;
            if (new_freq == 0) {
                freq.erase(v);
            } 
            else {
                freq[v] = new_freq;
            }
            remove_from_sets({old_freq, v});
            if (new_freq > 0) {
                insert_into_sets({new_freq, v});
            }
        };

        for (int i = 0; i < k; i++) {
            add_value(nums[i]);
        }
        ans.push_back(total);

        for (int i = 1; i <= n - k; i++) {
            remove_value(nums[i - 1]);
            add_value(nums[i + k - 1]);
            ans.push_back(total);
        }

        return ans;
    }
};
