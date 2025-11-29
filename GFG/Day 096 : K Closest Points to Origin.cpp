class Solution {
  public:
    vector<vector<int>> kClosest(vector<vector<int>>& points, int k) {
        //just sort the points based on squared distance 
        //from origin adn then return the first k points
        sort(points.begin(), points.end(), 
        [](const vector<int>& a, const vector<int>& b) {
            return(a[0]*a[0]+a[1]*a[1])<(b[0]*b[0]+b[1]*b[1]);
        });
        
        return vector<vector<int>>(points.begin(), points.begin() + k);
    }
};
