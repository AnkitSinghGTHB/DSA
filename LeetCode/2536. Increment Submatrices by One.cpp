class Solution {
public:
    vector<vector<int>> rangeAddQueries(int n, vector<vector<int>>& queries) {
        //first pass: row-wise prefix sum
        //second pass: column-wise prefix sum
        vector<vector<int>> diff(n + 1, vector<int>(n + 1, 0));        
        for (auto& query : queries) {
            int r1 = query[0], c1 = query[1], r2 = query[2], c2 = query[3];            
            diff[r1][c1] += 1;
            diff[r1][c2 + 1] -= 1;
            diff[r2 + 1][c1] -= 1;
            diff[r2 + 1][c2 + 1] += 1;
        }        
        vector<vector<int>> mat(n, vector<int>(n, 0));        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    diff[i][j] += diff[i][j - 1];
                }
                mat[i][j] += diff[i][j];
            }
        }        
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    diff[i][j] += diff[i - 1][j];
                }
                mat[i][j] = diff[i][j];
            }
        }        
        return mat;
    }
};
