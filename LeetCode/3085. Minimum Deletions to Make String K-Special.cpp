class Solution {
public:
    int minimumDeletions(string word, int k) {
        //i wish we were on the same freakuency
        //ok first i get freq, then get diff, any diff >k, then count+=diff-k
        //ok i need to think how to do under O(n^2)
        //previous approach wasnt very nice, so i asked cgpt to help me out
        unordered_map<char, int> f;
        for (char c : word) {
            f[c]++;
        }
        vector<int> freqs;
        for (auto& it : f) {
            freqs.push_back(it.second);
        }
        int ans = INT_MAX;
        for (int base = 0; base <= 100000; base++) {//every psible freq as the base
            int deletions = 0;
            for (int freq : freqs) {
                if (freq < base) {
                    deletions += freq;//delete entire group
                } 
                else if (freq > base + k) {
                    deletions += freq - (base + k);//delete excess
                }
                //else: within [base, base + k] → no deletion
            }
            ans = min(ans, deletions);
        }
        return ans;
    }
};
