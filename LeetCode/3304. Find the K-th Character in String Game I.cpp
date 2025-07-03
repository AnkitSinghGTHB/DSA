class Solution {
public:
    char kthCharacter(int k) {
        string word="a";
        while (word.size()<k){
            string next;
            for (char c:word){
                next+=(c == 'z'?'a':c+1);//next character with wrap around
            }
            word += next;
        }
        return word[k-1];//0based index
    }
};
