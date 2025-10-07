class Solution {
public:
    vector<int> avoidFlood(vector<int>& rains) {
        //track the last time each lake was filled using a hash map
        //when a lake would flood (it rains on an already full lake), 
        //we need to have dried it after it was last filled but before the current rain
        //use a min-heap or TreeSet to efficiently find the earliest dry day that can prevent a flood
        int n = rains.size();
        vector<int> ans(n, -1);
        unordered_map<int, int> lastRain;
        set<int> dryDays;     
        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                dryDays.insert(i);
                ans[i] = 1;
            } 
            else {
                int lake = rains[i];                
                if (lastRain.count(lake)) {    
                    auto it = dryDays.upper_bound(lastRain[lake]);
                    if (it == dryDays.end()) {                
                        return {};
                    }                    
                    int dryDay = *it;
                    ans[dryDay] = lake;
                    dryDays.erase(dryDay);
                }                
                lastRain[lake] = i;
            }
        }
        for (int day : dryDays) {
            ans[day] = 1;
        }
        return ans;
    }
};
