class Solution {
public:
    int possibleStringCount(string word) {
        if (word.empty()) 
            return 0;
        int total = 1;
        int n = word.size();
        int i = 0;
        while (i < n) {
            char current = word[i];
            int count = 0;
            while (i < n && word[i] == current) {
                count++;
                i++;
            }
            if (count >= 2) {
                total += (count - 1);
            }
        }
        return total;
    }
};
