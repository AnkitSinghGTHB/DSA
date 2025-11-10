class Solution {
public:
    int minOperations(vector<int>& nums) {
        int ans = 0;
        stack<int> st;
        for (int num : nums) {
            while (!st.empty() && st.top() >= num) {
                if (st.top() > num) {
                    ans++;
                }
                st.pop();
            }
            if (num != 0 && (st.empty() || st.top() != num)) {
                st.push(num);
            }
        }
        return ans + st.size();
    }
};
