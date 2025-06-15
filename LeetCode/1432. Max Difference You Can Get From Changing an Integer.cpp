class Solution {
public:
    int maxDiff(int num) {
        //bruteforcing would be a bad idea ngl
        string s=to_string(num);
        string s1=s;
        char toReplaceMax=' ';
        for (char c:s){
            if (c!='9'){
                toReplaceMax = c;
                break;
            }
        }
        if (toReplaceMax != ' ') {
            for (char &c : s1) {
                if (c == toReplaceMax) c = '9';
            }
        }
        string s2 = s;
        char toReplaceMin = ' ';
        if (s[0] != '1') {
            toReplaceMin = s[0];
            for (char &c : s2) {
                if (c == toReplaceMin) c = '1';
            }
        } else {
            for (int i = 1; i < s.size(); ++i) {
                if (s[i] != '0' && s[i] != '1') {
                    toReplaceMin = s[i];
                    break;
                }
            }
            if (toReplaceMin != ' ') {
                for (int i = 1; i < s2.size(); ++i) {
                    if (s2[i] == toReplaceMin) s2[i] = '0';
                }
            }
        }
        int maxNum = stoi(s1);
        int minNum = stoi(s2);
        return maxNum - minNum;
    }
};
