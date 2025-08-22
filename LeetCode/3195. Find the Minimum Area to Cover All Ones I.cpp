class Solution {
public:
    int minimumArea(vector<vector<int>>& grid) {
        //so basically i need to find the min and max coords?
        //but uh im confused since my method might find wrong asnwers too
        int m = grid.size();
        int n = grid[0].size();
        int minRow = INT_MAX, maxRow = -1;
        int minCol = INT_MAX, maxCol = -1; //lol
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    minRow = min(minRow, i);
                    maxRow = max(maxRow, i);
                    minCol = min(minCol, j);
                    maxCol = max(maxCol, j);
                }
            }
        }
        if (minRow == INT_MAX){ //nothing found
            return 0;
        }
        int width = maxCol - minCol+1;
        int height = maxRow - minRow+1;
        return width *height;
    }
};
