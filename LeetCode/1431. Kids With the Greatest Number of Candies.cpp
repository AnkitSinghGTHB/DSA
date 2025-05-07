class Solution {
public:
    vector<bool> kidsWithCandies(vector<int>& candies, int extraCandies) {
        vector<bool> result={};
        int n=candies.size();
        int mx = *max_element(candies.begin(),candies.end());
        for (int i=0;i<n;i++){
            if(candies[i]+extraCandies>=mx){
                result.push_back(true);
            }
            else{
                result.push_back(false);
            }
        }
        return result;
    }
};
