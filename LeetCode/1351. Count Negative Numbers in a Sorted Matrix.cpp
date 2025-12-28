class Solution {
public:
    int countNegatives(vector<vector<int>>& grid) {
        //bruh what? this shoul have some optimization
        int count=0;
        for (int i=0;i<grid.size();i++){
            for (int j=0;j<grid[i].size();j++){
                if (grid[i][j]<0){
                    count++;
                }
            }
        }
        return count;
    }
};
