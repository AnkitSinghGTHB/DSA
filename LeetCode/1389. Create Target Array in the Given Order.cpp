class Solution {
public:
    vector<int> createTargetArray(vector<int>& nums, vector<int>& index) {
        //imp fn: insert() 
        //insert element x at index i -> v.insert(v.begin() + i, x);
        vector<int> res;
        int i=index.size();
        for (int n=0;n<i;n++){
            res.insert(res.begin()+index[n],nums[n]);
        }
        return res;
    }
};
