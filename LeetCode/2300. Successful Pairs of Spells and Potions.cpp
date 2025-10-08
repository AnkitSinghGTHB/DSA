class Solution {
public:
    vector<int> successfulPairs(vector<int>& spells, vector<int>& potions, long long success) {
        //bruv i have dsa exam tomorrow and im doing dsa T-T
        int n = spells.size();
        int m = potions.size();
        vector<int> result(n);
        sort(potions.begin(), potions.end());        
        for (int i=0; i<n; i++) {
            long long spell = spells[i];
            long long minPotion = (success + spell-1) / spell; //ceiling division
            if (minPotion > potions[m-1]) {
                result[i] = 0;
                continue;
            }
            int left = 0, right = m-1;
            int firstIndex = m; //default to end if not found            
            while (left <= right) {
                int mid = left+(right-left)/2;
                if (potions[mid] >= minPotion) {
                    firstIndex = mid;
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            }
            result[i] = m - firstIndex;
        }
        return result;
    }
};
