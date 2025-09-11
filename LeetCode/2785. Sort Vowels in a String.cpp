class Solution {
public:
    bool isVowel(char c) {
            c = tolower(c);
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    string sortVowels(string s) {
        //so i just sort the vowels? nice ig
        //what i can do is use an array to keep the indexes where to put the vowels
        //then i can sort the vowels after extracting? and sorting will be acc to ascii
        //then i traverse the string again and interchange the noted indexes?

        //ooh, or what i can do is, if i find a vowel, i just replace it with the one
        //from the sorted array? that would save me the space from indexing

        priority_queue<char, vector<char>, greater<char>> vowelHeap;
        for (char c : s) {
            if (isVowel(c)) {
                vowelHeap.push(c);
            }
        }
        for (int i = 0; i < s.size(); i++) {
            if (isVowel(s[i])) {
                s[i] = vowelHeap.top();
                vowelHeap.pop();
            }
        }
        return s;
    }
};
