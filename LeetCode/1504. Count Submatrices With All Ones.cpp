class Solution {
public:
    int numSubmat(vector<vector<int>>& matrix) {
        //use dp, wait isnt this the same ques from prev day but like rectangles allowed?
        //alright i have pasted yesterday's code, now need to convert to rectangle
        //i took help from deepseek
        int m = matrix.size();
        int n = matrix[0].size();
        vector<vector<int>> left(m, vector<int>(n, 0));
        
        // Precompute left[i][j]: number of consecutive ones to the left including (i,j)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (j == 0) {
                        left[i][j] = 1;
                    } else {
                        left[i][j] = left[i][j-1] + 1;
                    }
                } else {
                    left[i][j] = 0;
                }
            }
        }
        
        int total = 0;
        // For each cell (i, j) as bottom-right corner
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int min_width = left[i][j];
                // Iterate upwards from i to 0
                for (int k = i; k >= 0; k--) {
                    if (left[k][j] == 0) break; // no more rectangles above
                    min_width = min(min_width, left[k][j]);
                    total += min_width;
                }
            }
        }
        
        return total;
    }
};
