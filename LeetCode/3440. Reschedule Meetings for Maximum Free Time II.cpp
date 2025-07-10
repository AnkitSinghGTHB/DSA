class Solution {
public:
    int maxFreeTime(int eventTime, vector<int>& startTime, vector<int>& endTime) {
        //im too tired to do it by myself, used help from deepseek
        //i couldnt directly solve it
        int n = startTime.size();
        if (n == 0) {
            return eventTime;
        }
        vector<vector<int>> intervals;
        for (int i = 0; i < n; i++) {
            intervals.push_back({startTime[i], endTime[i]});
        }
        sort(intervals.begin(), intervals.end());
        vector<long long> gaps(n+1);
        gaps[0] = intervals[0][0];
        for (int i = 1; i < n; i++) {
            gaps[i] = intervals[i][0] - intervals[i-1][1];
        }
        gaps[n] = eventTime - intervals[n-1][1];
        multiset<long long> S;
        unordered_map<long long, int> freq;
        for (int i = 0; i <= n; i++) {
            S.insert(gaps[i]);
            freq[gaps[i]]++;
        }
        long long ans = *S.rbegin();
        for (int i = 0; i < n; i++) {
            auto it = S.find(gaps[i]);
            S.erase(it);
            freq[gaps[i]]--;
            it = S.find(gaps[i+1]);
            if (it != S.end()) {
                S.erase(it);
                freq[gaps[i+1]]--;
            }
            long long d_i = intervals[i][1] - intervals[i][0];
            long long new_gap = gaps[i] + d_i + gaps[i+1];
            S.insert(new_gap);
            freq[new_gap]++;
            long long M1 = *S.rbegin();
            int count_M1 = freq[M1];
            long long M2 = 0;
            if (S.size() > 1) {
                if (count_M1 >= 2) {
                    M2 = M1;
                } else {
                    auto it_temp = S.find(M1);
                    if (it_temp != S.end()) {
                        S.erase(it_temp);
                        M2 = *S.rbegin();
                        S.insert(M1);
                    }
                }
            }
            bool condition = (count_M1 >= 2) || (M2 >= d_i);
            long long candidate_i;
            if (condition) {
                candidate_i = M1;
            }
            else {
                candidate_i = max(M2, M1 - d_i);
            }
            if (candidate_i > ans) {
                ans = candidate_i;
            }
            it = S.find(new_gap);
            S.erase(it);
            freq[new_gap]--;

            S.insert(gaps[i]);
            freq[gaps[i]]++;
            S.insert(gaps[i+1]);
            freq[gaps[i+1]]++;
        }
        return (int)ans;
    }
};
