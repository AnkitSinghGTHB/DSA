class Solution {
public:
    vector<vector<int>> findMatrix(vector<int>& nums) {
        //okk, it took a bit of time to figure out what is being asked
        //so i make new arrays for each element that is being repeated right?
        unordered_map<int, int> f;
        for (int n: nums){
            f[n]++;
        }
        int max_f = 0;
        for (auto& [n, count]:f){
            max_f = max(max_f, count);
        }
        vector<vector<int>> res(max_f);
        for (auto& [n, count]:f){
            for (int i=0; i<count; ++i){
                res[i].push_back(n);
            }
        }
        return res;
    }
};
