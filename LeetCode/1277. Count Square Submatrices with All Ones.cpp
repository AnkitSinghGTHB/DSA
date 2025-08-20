class Solution {
public:
    int countSquares(vector<vector<int>>& matrix) {
        //idk why i read "count space submarines .." lol
        //lol, this reminds me of stuff i did in hackerrank
        //maybe first i count all the ones?
        //then have to find squares
        int m = matrix.size();
        int n = matrix[0].size();
        vector<vector<int>> dp(m, vector<int>(n, 0));
        int total = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    }
                    else {
                        dp[i][j] = min({dp[i-1][j], dp[i][j-1], dp[i-1][j-1]}) + 1;
                    }
                    total += dp[i][j];
                }
            }
        }
        return total;
        //alright, now i have all the 1x1 1s counted
        //how do i check the 2x2 1s ? i made a dp and added the cords while traversing
        //now lets traverse the dp and check 
        //confusion is, how am i supposed to scale the count to nxn matrices?
        //lets see
    }
};
