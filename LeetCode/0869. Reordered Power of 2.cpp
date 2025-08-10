class Solution {
public:
    bool reorderedPowerOf2(int n) {
        //the problem description is just not descriptive enough
        //i wish they could fix that
        //basically check permutations of the digit in decimal form itself
        //then check if any of the permutation is power of 2
        static const set<string> powerSet = [] {
            set<string> s;
            long num = 1;
            for (int i = 0; i <= 30; ++i) {
                string str = to_string(num);
                sort(str.begin(), str.end());
                s.insert(str);
                num *= 2;
            }
            return s;
        }();

        string n_str = to_string(n);
        sort(n_str.begin(), n_str.end());
        return powerSet.find(n_str) != powerSet.end();
    }
};
