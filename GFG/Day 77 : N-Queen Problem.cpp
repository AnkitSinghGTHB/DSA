// User function Template for C++

class Solution {
  public:
    //helper fn using backtracking
    void nqh(int j,int n,vector<int> &board,vector<bool> &rows, 
	vector<bool> &diag1,vector<bool> &diag2,vector<vector<int>> &res) {
    if (j>n) {
        res.push_back(board);
        return;
    }
    for (int i=1; i<=n; ++i) {
        if (!rows[i] && !diag1[i+j] && !diag2[i-j+n]){
            rows[i]=diag1[i+j]=diag2[i-j+n]=true;
            board.push_back(i);
            nqh(j+1, n, board, rows, diag1, diag2, res);
            board.pop_back();
            rows[i]=diag1[i+j]=diag2[i-j+n]=false;
        }
    }
}
    vector<vector<int>> nQueen(int n) {
        //this is a hood classic and idk its code lol
        //wait, i have to give all solns? demn
        //i think generating all permuations with recur
        //would be time taking, instead i should just
        //look up backtracking
        vector<vector<int>> res;
        vector<int> board;
        vector<bool> rows(n+1, false);
        vector<bool> diag1(2*n+1, false);
        vector<bool> diag2(2*n+1, false);
        nqh(1, n, board, rows, diag1, diag2, res);
        return res;
    }
};
