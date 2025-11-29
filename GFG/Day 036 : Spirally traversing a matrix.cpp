class Solution {
  public:
    vector<int> spirallyTraverse(vector<vector<int> > &mat) {
        //wow this is new, note i need to do in O(nm)
        /*
            my strat would be traverse arr[0][0 to m-1]
            then arr[1 to n-2][m-1]
            then arr[n-1][m-1 to 0]
            then arr[n-2 to 1][0]
            then maybe recursively call? maybe a while loop
        */
        vector<int> result;
        int top = 0,bottom=mat.size()-1;
        int left = 0,right=mat[0].size()-1;
        while (top<=bottom && left<=right){
            for(int i=left;i<=right;i++){//left to right
                result.push_back(mat[top][i]);}
            top++;
            for(int i=top;i<=bottom;i++){//top to bottom
                result.push_back(mat[i][right]);}
            right--;
            if (top<=bottom){//right to left
                for (int i=right;i>=left;i--){
                    result.push_back(mat[bottom][i]);}
                bottom--;
            }
            if (left<=right){//bottom to top
                for (int i=bottom;i>=top;i--){
                    result.push_back(mat[i][left]);}
                left++;
            }
        }
        return result;
    }
};
