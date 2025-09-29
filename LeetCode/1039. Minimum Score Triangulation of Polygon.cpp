class Solution {
public:
    int minScoreTriangulation(vector<int>& values) {
        //n sides, vertex has weight, if there are n sides, then n vertexes
        //from n vertices, we get n-2 triangles
        //weight of triangle = prod of vertices weight
        //triangulation score = sum of triangle weights of n-2 triangles
        //another thing, the triangles in triangulation should not be intersecting
        //this looks like dp ques, but im not sure about efficiency rn
        //alright this feels way harder than i thought it was
        //i took help from cgpt for the dp part

        int n = values.size();
        vector<vector<int>> dp(n,vector<int>(n,0));
        for (int len=2; len < n; len++) {
            for (int i=0; i+len < n; i++) {
                int j = i+len;
                dp[i][j] = INT_MAX;
                for (int k = i+1; k<j; k++) {
                    dp[i][j] = min(dp[i][j], 
                                  dp[i][k] + dp[k][j] + values[i]*values[k]*values[j]);
                }
            }
        }
        return dp[0][n-1];
    }
};
