class Solution {
public:
    vector<string> removeAnagrams(vector<string>& words) {
        //if result is empty, add the first word
        //check if current word is an anagram of the last word in result
        //sort both strings to check if they are anagrams
        //if they are not anagrams, add to result
        vector<string> result;        
        for (const string& word : words) {
            if (result.empty()) {
                result.push_back(word);
                continue;
            }            
            string lastWord = result.back();
            string currentWord = word;            
            sort(lastWord.begin(), lastWord.end());
            sort(currentWord.begin(), currentWord.end());            
            if (lastWord != currentWord) {
                result.push_back(word);
            }
        }        
        return result;
    }
};
