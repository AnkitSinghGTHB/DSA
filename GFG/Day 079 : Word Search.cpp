class Solution {
  public:
    bool dfs(vector<vector<char>>& mat, int i, int j, string& word, int index) {
        if (index==word.length()) return true;
        if (i<0||i>=mat.size()||j<0||j>=mat[0].size()||
            mat[i][j]!=word[index]){
            return false;
        }
        char temp=mat[i][j];     
        mat[i][j]='#';
        bool found =dfs(mat, i+1, j, word, index+1)||
                    dfs(mat, i-1, j, word, index+1)||
                    dfs(mat, i, j+1, word, index+1)||
                    dfs(mat, i, j-1, word, index+1);
        mat[i][j]=temp;//bktrk
        return found;
    }

    bool isWordExist(vector<vector<char>>& mat, string& word) {
        //word can be made by adjacent cells, also
        //we cannot repeat ig like G-E-G isnt possible
        //how am i supposed to do this?
        //ofc i get that we need to use backtracking
        //for dfs, i can make graph but would be weird
        //uh so i first find the word[0] through the mat
        //costing me O(n*m), then from there i apply dfs
        //check bounds and char match, costs O(n*m*3^L)
        //temporarily mark it visited
        //recursively explore all 4 dirs
        //backtrack by restoring original char
        int n=mat.size(), m=mat[0].size();
        for(int i=0; i<n;i++){
            for (int j=0;j<m;j++){
                if (mat[i][j]==word[0]){
                    if (dfs(mat,i,j,word,0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
};
