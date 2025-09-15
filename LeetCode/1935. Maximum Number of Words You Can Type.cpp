class Solution {
public:
    int canBeTypedWords(string text, string brokenLetters) {
        //is ts tuff gng?
        vector<bool> broken(26, false);
        for (char c : brokenLetters) {
            broken[c - 'a'] = true;
        }
        int count = 0;
        stringstream ss(text);
        string word;
        while (ss >> word) {
            bool canType = true;
            for (char c : word) {
                if (broken[c - 'a']) {
                    canType = false;
                    break;
                }
            }
            if (canType) {
                count++;
            }
        }
        return count;
    }
};
