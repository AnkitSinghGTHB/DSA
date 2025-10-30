class Solution {
public:
    int minNumberOperations(vector<int>& target) {
        int operations = target[0];//need at least target[0] operations to reach first element
        for (int i = 1; i < target.size(); i++) {
            if (target[i] > target[i - 1]) {
                operations += target[i] - target[i - 1];
            }
        }        
        return operations;
    }
};
