class Solution {
public:
    int maxFreqSum(string s) {
        //wtf is this question
        unordered_map<char,int> freq;
        unordered_set<char> vowels ={'a', 'e', 'i', 'o', 'u'};
        int maxVowel = 0,maxConsonant = 0;
        for (char ch:s){
            freq[ch]++;
        }
        for (auto& [ch,count]:freq){
            if (vowels.count(ch)){
                maxVowel=max(maxVowel,count);
            }
            else{
                maxConsonant=max(maxConsonant,count);
            }
        }
        return maxVowel+maxConsonant;
    }
};
