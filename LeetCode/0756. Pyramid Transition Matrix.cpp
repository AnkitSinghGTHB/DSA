class Solution {
public:
    bool pyramidTransition(string bottom, vector<string>& allowed) {
        //there was a part i coulnt really unerstan, there i took help
        unordered_map<string, vector<char>> mp;//map from a pair of characters (left, right) to list of possible top characters
        for (const string& pat : allowed) {
            string key = pat.substr(0, 2);
            mp[key].push_back(pat[2]);
        }
        unordered_map<string, bool> memo;
        return canBuild(bottom, mp, memo);
    }

    bool canBuild(const string& row, unordered_map<string, vector<char>>& mp, unordered_map<string, bool>& memo) {
        if (row.size() == 1) return true;
        if (memo.count(row)) return memo[row];        
        vector<vector<char>> choices;//collect possible top characters for each adjacent pair in the current row
        for (size_t i = 0; i < row.size() - 1; ++i) {
            string key = row.substr(i, 2);
            if (!mp.count(key)) {
                memo[row] = false;
                return false;
            }
            choices.push_back(mp[key]);
        }        
        string next;//try all combinations to build the next row
        if (backtrack(choices, 0, next, mp, memo)) {
            memo[row] = true;
            return true;
        }
        memo[row] = false;
        return false;
    }

    bool backtrack(const vector<vector<char>>& choices, size_t idx, string& next,
                   unordered_map<string, vector<char>>& mp, unordered_map<string, bool>& memo) {
        if (idx == choices.size()) {
            return canBuild(next, mp, memo);
        }
        for (char c : choices[idx]) {
            next.push_back(c);
            if (backtrack(choices, idx + 1, next, mp, memo)) {
                return true;
            }
            next.pop_back();
        }
        return false;
    }
};
