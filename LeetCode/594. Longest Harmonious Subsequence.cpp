class Solution {
public:
    int findLHS(vector<int>& nums) {
        //wew, this isnt super easy, but possible ngl
        //definitely sliding window, otherwise bruteforce is bad for time lol
        //first i sort the array then i just compare i and j
        //where i:0->n-1 and j:n-1->0 and i<=j
        //or maybe i can just make a frequency map and from there just check for
        //the pair that is harmonous
        unordered_map<int, int> freq;
        for (int num :nums) {
            freq[num]++;
        }
        int max_len = 0;
        for (auto& [num, count] : freq) {
            if (freq.find(num + 1) != freq.end()) {
                max_len = max(max_len, count + freq[num + 1]);
            }
        }
        return max_len;
    }
};
