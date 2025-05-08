class Solution {
  public:
    int kthMissing(vector<int> &arr, int k) {
        //bruh what are these O(log n) questions
        //acc to explanations, we need to count from 1
        //but there must be some underlying log n pattern
        /*  arr[i]-(i+1) givs the num of missing 
            ints before indx i
            we hv i+1 nums by idx i
            bSearch for the smallst idx wher 
            missing nums >=k
        */
        int left=0, right=arr.size()-1;
        while (left<=right) {
            int mid =left+(right-left)/2;
            int missing=arr[mid]-(mid+1);
            if (missing<k){
                left=mid+1;
            } else {
                right=mid-1;
            }
        }
        return k+left;
    }
};
