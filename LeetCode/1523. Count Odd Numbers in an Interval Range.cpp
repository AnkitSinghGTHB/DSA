class Solution {
public:
    int countOdds(int low, int high) {
        int pair=high-low+1;
        if (pair%2==0){
            return pair/2;
        }
        else{
            if (low%2!=0 || high%2!=0){
                return pair/2 +1;
            }
            else{
                return pair/2;
            }
        }
    }
};
