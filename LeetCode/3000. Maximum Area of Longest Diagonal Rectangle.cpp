class Solution {
public:
    int areaOfMaxDiagonal(vector<vector<int>>& dimensions) {
        //first of all diagonal^2 = length^2 + width^2 from pythagoras theorem
        //so max diagonal would generally mean max length and max width square sum root
        //alright to maximize the area, we need to make sure the absolute difference 
        //between length and width is least (i have read it in class 12th or 11th ig)
        //ok i am cutting off sqrt for performance improvement
        int mdiag_sqr = 0;
        int marea = 0;
        for (auto rect : dimensions){
            int l = rect[0];
            int w = rect[1];
            int diag_sqr = l*l + w*w; //im not sure if pow() helps here like python
            int area = l*w;
            if (diag_sqr > mdiag_sqr){
                mdiag_sqr =diag_sqr;
                marea = area;
            } 
            else if (diag_sqr == mdiag_sqr){
                if (area > marea){
                    marea = area;
                }
            }
        }
        return marea;
        //alright i understood that it is having a bad description
        //we need to get the max area of longest diag :(
        //lets fix that ig
    }
};
