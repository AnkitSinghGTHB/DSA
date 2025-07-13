class Solution {
public:
    int matchPlayersAndTrainers(vector<int>& players, vector<int>& trainers) {
        //a 2ptr greedy sorting ques in this econ?
        sort(players.begin(), players.end());
        sort(trainers.begin(), trainers.end());
        int i = 0, j = 0;
        int count = 0;
        int n = players.size(), m = trainers.size();
        while (i < n && j < m) {
            if (players[i] <= trainers[j]) {
                count++;
                i++;
                j++;
            }
            else {
                j++;
            }
        }
        return count;
    }
};
