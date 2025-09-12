class Solution {
public:
    bool doesAliceWin(string s) {
        //uh i remember doing this with numbers, weird aah
        //bob gets cooked everytime
        int vowelCount = 0;
        for (char c : s) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowelCount++;
            }
        }
        return vowelCount > 0;
    }
};
