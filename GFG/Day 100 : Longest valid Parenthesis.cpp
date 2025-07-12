
class Solution {
  public:
    int maxLength(string& s) {
        //another stack question
        //oh, i realised, i didnt take bad inputs in
        /*stack<char> st;
        int count=0;
        for (char c : s) {
            if (c == '(') {
                st.push(c);
            }
            else if (c == ')') {
                if (st.empty()){
                    continue;
                }
                char top = st.top();
                st.pop();
                if (c == ')' && top == '('){
                    count+=2;
                }
            }
        }
        return count;*/
        stack<int> st;
        st.push(-1);
        int max_len = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] == '(') {
                st.push(i);
            }
            else {
                st.pop();
                if (st.empty()) {
                    st.push(i);
                }
                else {
                    max_len = max(max_len, i - st.top());
                }
            }
        }
        return max_len;
    }
};
