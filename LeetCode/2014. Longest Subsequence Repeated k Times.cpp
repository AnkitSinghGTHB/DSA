class Solution {
//we first compute the freq of each char in the str to ensure that any candidate subseq doesn't use a char more times than available
// then start the bfs w/ an empty str as init candidate
//for each candidate in the curr lvl, i try to extend it by appending each char from z to a
//also i check if the candidate repeated k times forms a valid subseq of the of str, this i do efficiently by traversing the of str once per candidate
//finally once no more candidates can be extended, i select the lexgy largest candidate from the last valid level as the res
public:
    string longestSubsequenceRepeatedK(string s, int k) {
        int n = s.size();
        vector<int> freq(26, 0);
        for (char c : s) {
            freq[c - 'a']++;
        }
        
        vector<string> q;
        q.push_back("");
        string best = "";
        
        while (!q.empty()) {
            vector<string> next_q;
            for (const string& cand : q) {
                for (char c = 'z'; c >= 'a'; --c) {
                    int count_c = 0;
                    for (char ch : cand) {
                        if (ch == c) count_c++;
                    }
                    if (count_c + 1 > freq[c - 'a'] / k) {
                        continue;
                    }
                    
                    string new_cand = cand + c;
                    if (check(s, new_cand, k)) {
                        next_q.push_back(new_cand);
                    }
                }
            }
            
            if (next_q.empty()) {
                if (!q.empty()) {
                    sort(q.begin(), q.end());
                    best = q.back();
                }
                break;
            }
            q = next_q;
        }
        
        return best;
    }

private:
    bool check(const string& s, const string& cand, int k) {
        int len = cand.size();
        int total_len = len * k;
        int j = 0;
        for (char c : s) {
            if (c == cand[j % len]) {
                j++;
            }
            if (j == total_len) {
                return true;
            }
        }
        return false;
    }
};
