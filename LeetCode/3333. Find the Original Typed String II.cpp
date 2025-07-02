class Solution {
public:
    int possibleStringCount(string word, int k) {
        //we need to determine the number of possible original strings that
        //alice might have intended to type
        //the original string can be derived from the given string by reducing one 
        //contiguous group of identical characters by exactly r = n - k characters,
        //leaving at least one character in the group
        //ok so my problem was that i oversimplified it
        //so now i am implementing dp soln
        //parse word into groups of consecutive characters
        //total number of original strings = product of all group sizes
        //if number of groups >= k, all ways are valid
        //dp to count invalid strings (those with length < k)
        //sum all dp[i] for i < k (invalid ways)
        //answer = totalWays - invalidWays
        //note: my solution wasnt effective enough, so i took help from cgpt
        //parse word into groups of consecutive identical characters
        //compute total number of original strings = product of all group sizes
        //if number of groups >= k, all combinations are valid
        //use DP to count invalid combinations (strings of length < k)
        //subtract invalid combinations from total to get the final answer
        const vector<int> groups = getConsecutiveLetters(word);
        const int totalCombinations =
            accumulate(groups.begin(), groups.end(), 1L,
                    [](long acc, int group) { return acc * group % kMod; });
        if (k <= groups.size())
            return totalCombinations;
        vector<int> dp(k);
        dp[0] = 1;  // Base case: empty string
        for (int i = 0; i < groups.size(); ++i) {
            vector<int> newDp(k);
            int windowSum = 0;
            int group = groups[i];
            for (int j = i; j < k; ++j) {
                newDp[j] = (newDp[j] + windowSum) % kMod;
                windowSum = (windowSum + dp[j]) % kMod;
                if (j >= group)
                windowSum = (windowSum - dp[j - group] + kMod) % kMod;
            }
            dp = std::move(newDp);
        }
        const int invalidCombinations =
            accumulate(dp.begin(), dp.end(), 0,
                    [](int acc, int count) { return (acc + count) % kMod; });
        return (totalCombinations - invalidCombinations + kMod) % kMod;
    }

 private:
    static constexpr int kMod = 1'000'000'007;
    vector<int> getConsecutiveLetters(const string& word) {
        vector<int> groups;
        int group = 1;
        for (int i = 1; i < word.length(); ++i)
        if (word[i] == word[i - 1]) {
            ++group;
        } else {
            groups.push_back(group);
            group = 1;
        }
        groups.push_back(group);
        return groups;
    }
};
