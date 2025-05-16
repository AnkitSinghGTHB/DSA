class Solution {
public:
    //lets define a helper function
    int hamming(string& a, string& b) {
        int dist = 0;
        for (int i = 0; i < a.size(); ++i)
            if (a[i] != b[i]) dist++;
        return dist;
}
    vector<string> getWordsInLongestSubsequence(vector<string>& words, vector<int>& groups) {
        //part 1 easy, part 2 xtra tuff
        //this might be a dp problem again
        int n = words.size();
        vector<int> dp(n, 1), prev(n, -1); //dp and backtrack array
        int max_len = 1, end_at = 0;
        for (int i=0; i<n; ++i) {
            for (int j=0; j<i; ++j) {
                if (groups[i] != groups[j] && words[i].size() == words[j].size() && 
                    hamming(words[i], words[j]) == 1){
                    if (dp[j]+1 > dp[i]) {
                        dp[i]= dp[j]+1;
                        prev[i] = j;
                    }
                }
            }
            if (dp[i]>max_len) {
                max_len=dp[i];
                end_at=i;
            }
        }
        vector<string> res;//recover path
        for (int i=end_at; i!=-1; i=prev[i]) {
            res.push_back(words[i]);
        }
        reverse(res.begin(),res.end());
        return res;
    }
};
