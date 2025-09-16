class Solution {
public:
    //uh lets define gcd
    long long gcd(long long a, long long b) {
        while (b != 0) {
            long long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    vector<int> replaceNonCoprimes(vector<int>& nums) {
        //alright bro i took help from the comments
        vector<long long> st;
        for (int num : nums) {
            long long current = num;
            while (!st.empty()) {
                long long top = st.back();
                long long g = gcd(current, top);
                if (g == 1) break;
                st.pop_back();
                current = (current * top) / g;
            }
            st.push_back(current);
        }
        vector<int> result;
        for (long long num : st) {
            result.push_back(static_cast<int>(num));
        }
        return result;
    }
};
