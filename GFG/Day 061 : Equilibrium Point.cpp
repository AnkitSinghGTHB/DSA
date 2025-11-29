class Solution {
  public:
    // Function to find equilibrium point in the array.
    int findEquilibrium(vector<int> &arr) {
        //now we start prefix sum type questions
        //first let me get the total sum
        //then leftsum =0 and we loop through the array
        //then inside loop, we get rightsum by subtracting
        //the curr elem from totalsum
        //if leftsum==rightsum then return current index
        //finally add curr element to leftsum
        int n=arr.size();
        int tSum=0;
        for (int i=0;i<n;i++){
            tSum+=arr[i];
        }
        int lSum=0;
        for (int i=0;i<n;i++){
            tSum-=arr[i];//i saved some mem by modifying it
            if (tSum==lSum){
                return i;
            }
            lSum+=arr[i];
        }
        return -1; //safe exit
    }
};
