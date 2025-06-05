class Solution {
public:
    string reversePrefix(string word, char ch) {
        //uh so i need to reverse it like till a char
        int idx=word.find(ch);
        if(idx==string::npos) return word;
        reverse(word.begin(),word.begin()+idx+1);
        return word;
    }
};
