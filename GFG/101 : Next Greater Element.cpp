class Solution {
  public:
    vector<int> nextLargerElement(vector<int>& arr) {
        //uh? i dont get it, like i understan the ques
        //but idk how to solve this, even with stack
        //think harder, oh we need to check only right
        //bruh then i can just compare and push
        //uh, i realised the comparison wasnt right
        //basically i need to keep on checking until i 
        //find a greater element on the right
        int n =arr.size();
        vector<int> res(n, -1);
        stack<int> s;
        for (int i = n - 1; i >= 0; --i) {
            while (!s.empty() && s.top() <= arr[i]) {
                s.pop();
            }
            if (!s.empty()) {
                res[i] = s.top();
            }
            s.push(arr[i]);
        }
        return res;
    }
};
