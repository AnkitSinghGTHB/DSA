class Solution {
  public:
    int evaluate(vector<string>& arr) {
        //lol
        //use a stack to evaluate postfix expressions: push operands, pop two for operators, apply operation, push result, return final stack top
        stack<int> s;
        for (string& token : arr) {
            if (token == "+" || token == "-" 
             || token == "*" || token == "/") {
                int b = s.top(); s.pop();
                int a = s.top(); s.pop();
                if (token == "+") s.push(a + b);
                else if (token == "-") s.push(a - b);
                else if (token == "*") s.push(a * b);
                else s.push(a / b);
            }
            else {
                s.push(stoi(token));
            }
        }
        return s.top();
    }
};
