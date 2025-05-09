class Solution {
public:
    vector<int> findArray(vector<int>& pref) {
        //lol so we need a xorsum
        for (int i=pref.size()-1; i>0; --i) {
            pref[i] ^= pref[i-1]; // safe because we go backward
        }
        return pref;
    }
};
