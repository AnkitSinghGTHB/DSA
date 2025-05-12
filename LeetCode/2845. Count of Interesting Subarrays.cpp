class Solution {
public:
    long long countInterestingSubarrays(vector<int>& nums, int modulo, int k) {
        //interesting ;)
        //O(n^2) i can do, can we make it more quick?
        // lets make a counting hashmap
        unordered_map<int, long long> freq;
        freq[0]=1; // empty prefix case
        int curr=0; long long result =0;
        for (int n: nums){
            if (n%modulo==k){
                curr++;
            }
            curr%=modulo; //overflow regulation
            int prevcount=(curr-k+modulo)%modulo;
            result+=freq[prevcount];
            freq[curr]++; //update
        }
        return result;
    }
};
