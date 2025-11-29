class Solution {
  public:
    vector<int> subarraySum(vector<int> &arr, int target) {
        //ok ok, maybe i can do it but how under O(n)
        //like yeah we gonna use sliding window, but :(
        int n=arr.size();
        int i=0;
        int curr_sum=0;
        for (int j=0;j<n;++j){
            curr_sum +=arr[j];
            while (curr_sum>target && i<=j) {//left shrink
                curr_sum -=arr[i];
                i++;
            }
            if (curr_sum==target){
                return {i+1, j+1};
            }
        }
        return {-1};//safe exit
    }
};
