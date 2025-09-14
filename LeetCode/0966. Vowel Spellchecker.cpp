class Solution {
public:
    vector<string> spellchecker(vector<string>& wordlist, vector<string>& queries) {
        unordered_set<string> exact;
        unordered_map<string, string> caseMap;
        unordered_map<string, string> vowelMap;
        
        for (string word : wordlist) {
            exact.insert(word);
            
            string lowerWord = toLower(word);
            if (caseMap.find(lowerWord) == caseMap.end()) {
                caseMap[lowerWord] = word;
            }
            
            string vowelKey = replaceVowels(lowerWord);
            if (vowelMap.find(vowelKey) == vowelMap.end()) {
                vowelMap[vowelKey] = word;
            }
        }
        
        vector<string> result;
        for (string query : queries) {
            if (exact.find(query) != exact.end()) {
                result.push_back(query);
                continue;
            }
            
            string lowerQuery = toLower(query);
            if (caseMap.find(lowerQuery) != caseMap.end()) {
                result.push_back(caseMap[lowerQuery]);
                continue;
            }
            
            string vowelKey = replaceVowels(lowerQuery);
            if (vowelMap.find(vowelKey) != vowelMap.end()) {
                result.push_back(vowelMap[vowelKey]);
                continue;
            }
            
            result.push_back("");
        }
        
        return result;
    }
    string toLower(string s) {
        string res;
        for (char c : s) {
            res += tolower(c);
        }
        return res;
    }
    string replaceVowels(string s) {
        string res;
        for (char c : s) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                res += '*';
            } else {
                res += c;
            }
        }
        return res;
    }
};
