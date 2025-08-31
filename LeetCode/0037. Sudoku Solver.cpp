class Solution {
public:
    void solveSudoku(vector<vector<char>>& board) {
        //i hope i dont need to check if the given matrix is solvable
        //the soduku rule is that we need unique digits in each 3x3 box and 
        //the digit should also be unique in the same vertical and horizontal line
        //lets use backtracking to solve this ig
        solve(board);
    }

    bool solve(vector<vector<char>>& board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board)) {
                                return true;
                            }
                            board[i][j] = '.'; // backtrack
                        }
                    }
                    return false; // no valid digit found
                }
            }
        }
        return true; // all cells filled
    }
    
    bool isValid(vector<vector<char>>& board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) return false; // check row
            if (board[i][col] == c) return false; // check column
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c){
                 return false; // check 3x3 box
            }
        }
        return true;
    }
};
