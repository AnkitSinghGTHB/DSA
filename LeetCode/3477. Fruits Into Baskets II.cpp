class Solution {
public:
    int numOfUnplacedFruits(vector<int>& fruits, vector<int>& baskets) {
        int unplaced = 0;
        vector<bool> used(baskets.size(), false);
        for (int fruit : fruits) {
            bool placed = false;
            for (int i = 0; i < baskets.size(); ++i) {
                if (!used[i] && baskets[i] >= fruit) {
                    used[i] = true;
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                unplaced++;
            }
        }
        return unplaced;
    }
};
