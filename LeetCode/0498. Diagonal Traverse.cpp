class Solution {
public:
    vector<int> findDiagonalOrder(vector<vector<int>>& mat) {
        //alright i can cook ig
        //general traversal in M ixj -> M[i][j], i->0 to m, j-> 0 to n
        //0,0 -> 0,1 -> 0,2 -> 1,0 -> 1,1 -> 1,2 -> 2,0 -> 2,1 -> 2,2
        //here, we would traverse like, M[i,j] then M[j,i] and so on
        //0,0 -> 0,1 -> 1,0 -> 2,0 -> 1,1 -> 0,2 -> 1,2 -> 2,1 -> 2,2
        //im not able to show my thinking here, let me code it
        int m=mat.size();
        int n=mat[0].size();
        int i=0,j=0;
        vector<int> flat={};
        //maybe i could maintain a bool for each traversal?
        bool up = true; //direction: true for moving up, false for moving down
        //this while loop part is tricky
        while (i < m && j < n) {
            flat.push_back(mat[i][j]);
            if (up) { //movin up
                if (j == n-1) { //reached right boundary
                    i++;
                    up = false;
                } 
                else if (i == 0) { //reached top boundary
                    j++;
                    up = false;
                } 
                else {
                    i--;
                    j++;
                }
            } 
            else { //movin down
                if (i == m-1) { //reached bottom boundary
                    j++;
                    up = true;
                } 
                else if (j == 0) { //reached left boundary
                    i++;
                    up = true;
                } 
                else {
                    i++;
                    j--;
                }
            }
        }
        return flat;
    }
};
