class Solution {
public:
    int minimumBoxes(vector<int>& apple, vector<int>& capacity) {
        int totalApples = 0;
        for (int a : apple) {
            totalApples += a;
        }        
        sort(capacity.begin(), capacity.end(), greater<int>());        
        int sum = 0;
        int count = 0;
        for (int c : capacity) {
            sum += c;
            count++;
            if (sum >= totalApples) {
                break;
            }
        }        
        return count;
    }
};
