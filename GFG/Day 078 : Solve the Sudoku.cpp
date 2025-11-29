class Solution {
  public:
    // Function to find a solved Sudoku.
    void solveSudoku(vector<vector<int>> &mat) {
        //hiyaaaa!!!! sudokuuu >w<
        //ok but i have solved this question in the
        //bruteforce manner (naive approach), but i 
        //need to solve using backtracking rn
        //uh i need to check the internet
        auto isSafe=[&](int row,int col,int num){
            for (int x=0;x<9;x++){
                if (mat[row][x]==num || mat[x][col]==num){
                    return false;
                }
                int startR=3*(row/3),startC=3*(col/3);
                if (mat[startR+x/3][startC+x%3]==num){
                    return false;
                }
            }
            return true;
        };
        function<bool(int,int)>solve=[&](int row, int col)-> bool {
            if (row==9){
                return true;
            }
            if (col==9){
                return solve(row + 1, 0);
            }
            if (mat[row][col]!=0){
                return solve(row,col+1);
            }
            for (int num=1;num<=9;num++){
                if (isSafe(row,col,num)){
                    mat[row][col]=num;
                    if (solve(row,col+1)){
                        return true;
                    }
                    mat[row][col]=0; //backtrack
                }
            }
            return false;
        };
        solve(0,0);
    }
};
