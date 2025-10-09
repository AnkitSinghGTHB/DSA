class Solution {
public:
    long long minTime(vector<int>& skill, vector<int>& mana) {
        //why does it feel like a prefix sum question
        //lets try anyways, more like a pipeline scheduling issue
        int n = skill.size();
        int m = mana.size();        
        vector<long long> prefixSkill(n + 1, 0);
        for (int i = 0; i < n; i++) {
            prefixSkill[i + 1] = prefixSkill[i] + skill[i];
        }        
        vector<long long> endTime(n, 0);        
        for (int j = 0; j < m; j++) {
            long long x = mana[j];
            long long startTime = 0;
            if (j > 0) {
                for (int i = 0; i < n; i++) {
                    long long reachTime = prefixSkill[i] * x;
                    startTime = max(startTime, endTime[i] - reachTime);
                }
            }            
            long long current = startTime;
            for (int i = 0; i < n; i++) {
                current += (long long)skill[i] * x;
                endTime[i] = max(endTime[i], current);
            }
        }        
        return endTime[n - 1];
    }
};
