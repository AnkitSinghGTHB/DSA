class Solution {
public:
    long long maxSubarrays(int n, vector<vector<int>>& conflictsImplars) {
        vector<vector<int>> events(n);
        for (auto& p : conflictsImplars) {
            int a = p[0], b = p[1];
            if (a == b) continue;
            int idx1 = a - 1, idx2 = b - 1;
            int L = min(idx1, idx2);
            int R = max(idx1, idx2);
            if (L < n) {
                events[L].push_back(R);
            }
        }
        vector<int> F0(n, n);
        vector<int> F1(n, n);
        vector<bool> uniqueMin(n, false);
        multiset<int> s;
        map<int, int> countMap;
        for (int i = n - 1; i >= 0; --i) {
            for (int R_val : events[i]) {
                s.insert(R_val);
                countMap[R_val]++;
            }
            if (!s.empty()) {
                F0[i] = *s.begin();
                auto it = s.begin();
                it++;
                if (it != s.end()) {
                    F1[i] = *it;
                }
                if (countMap[F0[i]] == 1) {
                    uniqueMin[i] = true;
                }
            }
        }
        long long base0 = 0;
        for (int i = 0; i < n; ++i) {
            base0 += (F0[i] - i);
        }
        map<int, vector<pair<int, long long>>> byR;
        for (int i = 0; i < n; ++i) {
            if (uniqueMin[i] && F0[i] < n) {
                byR[F0[i]].push_back({i, static_cast<long long>(F1[i] - F0[i])});
            }
        }
        map<int, vector<int>> indicesMap;
        map<int, vector<long long>> prefixMap;
        for (auto& kv : byR) {
            auto& list = kv.second;
            sort(list.begin(), list.end());
            vector<int> idxs;
            vector<long long> prefix;
            long long sum = 0;
            for (auto& p : list) {
                idxs.push_back(p.first);
                sum += p.second;
                prefix.push_back(sum);
            }
            indicesMap[kv.first] = idxs;
            prefixMap[kv.first] = prefix;
        }
        long long best = base0;
        for (auto& p : conflictsImplars) {
            int a = p[0], b = p[1];
            if (a == b) {
                if (base0 > best) best = base0;
                continue;
            }
            int L0 = min(a - 1, b - 1);
            int R0 = max(a - 1, b - 1);
            if (byR.find(R0) != byR.end()) {
                vector<int>& idxs = indicesMap[R0];
                auto it = upper_bound(idxs.begin(), idxs.end(), L0);
                if (it != idxs.begin()) {
                    int pos = distance(idxs.begin(), it) - 1;
                    long long add = prefixMap[R0][pos];
                    if (base0 + add > best) {
                        best = base0 + add;
                    }
                } 
                else {
                    if (base0 > best) best = base0;
                }
            } 
            else {
                if (base0 > best) best = base0;
            }
        }
        return best;
    }
};
