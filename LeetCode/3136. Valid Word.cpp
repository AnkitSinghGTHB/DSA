class Solution {
public:
    bool isValid(string word) {
        if (word.size() < 3) 
            return false;
        string vowels = "aeiouAEIOU";//why did bBcCdDgGhH work for 588 testcases
        bool foundVowel = false, foundConsonant = false;
        for (char c : word) {
            if (c >= '0' && c <= '9') {
                continue;
            }
            else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                if (!(foundVowel && foundConsonant)) {
                    if (vowels.find(c) != string::npos) {
                        foundVowel = true;
                    }
                    else {
                        foundConsonant = true;
                    }
                }
            }
            else {
                return false;
            }
        }
        return foundVowel && foundConsonant;
    }
};
