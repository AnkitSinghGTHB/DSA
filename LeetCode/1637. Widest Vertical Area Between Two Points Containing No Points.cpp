class Solution {
public:
    int maxWidthOfVerticalArea(vector<vector<int>>& points) {
        //so i get all x values, sort em and find the max diff between consecutive xs
        vector<int> x_coords;
        for (auto& point:points) {//extracting x coords
            x_coords.push_back(point[0]);
        }
        sort(x_coords.begin(),x_coords.end());
        int maxWidth=0;//the max gap between consecutive xs
        for (int i=1; i<x_coords.size(); ++i) {
            maxWidth = max(maxWidth,x_coords[i]-x_coords[i-1]);
        }
        return maxWidth;
    }
};
