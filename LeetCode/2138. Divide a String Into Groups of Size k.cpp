class Solution {
public:
    vector<string> divideString(string s, int k, char fill) {
        vector<string> res;
        int i = 0,n = s.size();
        while (i < n) {
            string temp = "";
            int cnt = 0;
            while (cnt < k && i < n) {
                temp += s[i++];
                cnt++;
            }
            res.push_back(temp);
        }
        //Fill
        int l = res.back().size();
        if (l < k) {
            res.back() += string(k - l, fill);
        }

        return res;
    }
};
