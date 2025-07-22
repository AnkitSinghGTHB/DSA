class Solution {
  public:
    int lis(vector<int>& arr) {
        vector<int> tails;
        for (int num : arr) {
            auto it = lower_bound(tails.begin(), tails.end(), num);
            if (it == tails.end()) {
                tails.push_back(num);
            }
            else {
                *it = num;
            }
        }
        return tails.size();
    }
};
