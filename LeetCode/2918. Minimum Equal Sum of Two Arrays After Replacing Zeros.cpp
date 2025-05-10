class Solution {
public:
    long long minSum(vector<int>& nums1, vector<int>& nums2) {
        //this must be a greedy apprch ques
        long long int s1 = 0, s2 = 0;
        bool z1 = false, z2 = false;
        int n=nums1.size(), m=nums2.size();
        for (int i=0;i<n;i++){
            if (nums1[i]==0){
                z1=true;
                nums1[i]=1;
            }
            s1+=nums1[i];
        }
        for(int i=0;i<m;i++){
            if (nums2[i]==0){
                z2=true;
                nums2[i]=1;
            }
            s2+=nums2[i];
        }

        if(z1 && z2){
            return max(s1,s2);
        }
        else if (!z1 && !z2){
            if (s1==s2){
                return s1;
            }
            else{
                return -1;
            }
        }
        else if (!z1 && z2){
            if(s1>=s2){
                return s1;
            }
            else{
                return -1;
            }
        }
        else if (z1 && !z2){
            if (s1<=s2){
                return s2;
            }
            else {
                return -1;
            }
        }
        return -1; //safe exit
    }
};
