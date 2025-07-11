
class Solution {
  public:
    bool isBalanced(string& k) {
        //ok, how do i check dis using stack???
        //ok how about i just put into another stack
        //if both stacks have common then i remove both
        stack<char> st;
        for (char c : k) {
            if (c == '{' || c == '[' || c == '(') {
                st.push(c);
            }
            else if (c == '}' || c == ']' || c == ')') {
                if (st.empty()) return false;
                
                char top = st.top();
                st.pop();
                if ((c == '}' && top != '{') || 
                    (c == ']' && top != '[') || 
                    (c == ')' && top != '(')) {
                    return false;
                }
            }
        }
        return st.empty();
    }
};
