class Solution {
  public:
    vector<string> result;
    void backtrack(string& path,
                   unordered_map<char,int>& freq, 
                   int length){
        if (path.size()==length){
            result.push_back(path);
            return;
        }
        for (auto& entry:freq){
            if (entry.second>0){
                path.push_back(entry.first);
                entry.second--;
                backtrack(path, freq, length);
                path.pop_back(); //backtrack
                entry.second++;
            }
        }
    }
    vector<string> findPermutation(string &s) {
        //ok but how do i do it usin backtrackin
        //ofc i cant do O(n^n)
        //first lets count
        unordered_map<char,int> f;
        for (int i=0;i<s.size();i++){
            f[s[i]]++;
        }
        //then what
        string path = "";
        backtrack(path, f, s.length());
        return result;
    }
};
