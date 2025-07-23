class Solution {
public:
    pair<int, string> removePattern(string s, string pat, int pt) {
        string st;
        int score = 0;
        for (char c : s) {
            if (pat == "ab") {
                if (!st.empty() && st.back() == 'a' && c == 'b') {
                    st.pop_back();
                    score += pt;
                }
                else {
                    st.push_back(c);
                }
            }
            else if (pat == "ba") {
                if (!st.empty() && st.back() == 'b' && c == 'a') {
                    st.pop_back();
                    score += pt;
                } 
                else {
                    st.push_back(c);
                }
            }
        }
        return {score, st};
    }

    int maximumGain(string s, int x, int y) {
        string high_pat, low_pat;
        int high_pt, low_pt;
        if (x >= y) {
            high_pat = "ab";
            high_pt = x;
            low_pat = "ba";
            low_pt = y;
        } 
        else {
            high_pat = "ba";
            high_pt = y;
            low_pat = "ab";
            low_pt = x;
        }

        auto res1 = removePattern(s, high_pat, high_pt);
        auto res2 = removePattern(res1.second, low_pat, low_pt);
        int total1 = res1.first + res2.first;

        auto res3 = removePattern(s, low_pat, low_pt);
        auto res4 = removePattern(res3.second, high_pat, high_pt);
        int total2 = res3.first + res4.first;

        return max(total1, total2);
    }
};
