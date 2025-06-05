class Solution {
public:
    vector<int> countPoints(vector<vector<int>>& points, vector<vector<int>>& queries) {
        //ykw? i didnt like circles from the start(yes from 11th grade)
        //but as much as i hated it, it wasnt that difficult tbh
        vector<int> result;
        for(auto& query : queries){
            int count=0;
            int cx=query[0],cy=query[1],r=query[2];
            int rSquared=r*r; //this i did for faster calc
            for(auto& point: points){
                int px=point[0],py=point[1];
                int dx=px-cx;
                int dy=py-cy;
                if(dx*dx+dy*dy<=rSquared){
                    count++;
                }
            }
            result.push_back(count);
        }
        return result;
    }
};
