
class Solution {
  public:
    string decodedString(string &s) {
        //nice lol
        stack<string> chars;
        stack<int> nums;
        string res;
        int num = 0;
        for (char c : s) {
            if (isdigit(c)) {
                num = num * 10 + (c - '0');
            }
            else if (c == '[') {
                chars.push(res);
                nums.push(num);
                res = "";
                num = 0;
            }
            else if (c == ']') {
                string temp = res;
                res = chars.top();
                chars.pop();
                int count = nums.top();
                nums.pop();
                while (count--) res += temp;
            }
            else {
                res += c;
            }
        }
        return res;
    }
};
