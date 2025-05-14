class Solution {
  public:
    void setMatrixZeroes(vector<vector<int>> &mat) {
        // pretty easy ig? O(n*m)
        //ok facing an issue, changing approach
        /*
        int n=mat.size(), m=mat[0].size();
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (mat[i][j]==0){
                    //up
                    if(i!=0){
                        mat[i-1][j]=0;
                    }
                    //down
                    if(i!=n-1){
                        mat[i+1][j]=0;
                    }
                    //left
                    if(j!=0){
                        mat[i][j-1]=0;
                    }
                    //right
                    if(j!=m-1){
                        mat[i][j+1]=0;
                    }
                }
            }
        }
        */
        //we mark first row and column if they contain zero
        //then use first row and column to mark zeroes
        //zero out cells based on first row and column
        //finally zero out first row and column if needed
        int n=mat.size(), m=mat[0].size();
        bool firstRow=false, firstCol=false;
        for (int i=0; i<n;i++)
            if (mat[i][0] == 0) firstCol=true;
        for (int j=0; j<m;j++)
            if (mat[0][j] == 0) firstRow=true;
        for (int i=1;i<n;i++){
            for (int j=1;j<m;j++){
                if (mat[i][j]==0){
                    mat[i][0]=0;
                    mat[0][j]=0;
                }
            }
        }
        for (int i=1;i<n;i++){
            for (int j=1; j<m; j++){
                if (mat[i][0]==0 || mat[0][j]==0)
                    mat[i][j]=0;
            }
        }
        if (firstRow)
            for(int j=0;j<m;j++)
                mat[0][j]=0;
    
        if (firstCol)
            for(int i=0;i<n;i++)
                mat[i][0]=0;
    }
};
