class Solution {
public:
    int reverseDegree(string s) {
        //ok ig i can do it pretty easily
        int n=s.size();
        int deg=0;
        for (int i=0;i<n;i++){
            deg+=('z'-s[i]+1)*(i+1); //char - char we get int
        }
        return deg;
    }
};
