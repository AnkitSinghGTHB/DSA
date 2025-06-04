class Solution {
  public:
    int longestSubarray(vector<int>& arr, int k) {
        //uh how do i do it
        //i can try initializing a map to store the first
        //occurence of each prefix sum then i traverse 
        //the array and calculate prefix sums 
        //if pSum ==k then update max lenght
        //if pSum-k exists in the map then it means 
        //the subarr between that index and curr index
        //has sum =k
        //thn i store the first occurence of each pSum only
        unordered_map<int, int> pSumIdx;
        int sum=0;
        int mxLength=0;
        for (int i=0;i<arr.size();++i){
            sum+=arr[i];
            if (sum==k) {
                mxLength=i+1;
            }
            if(pSumIdx.find(sum-k)!=pSumIdx.end()){
                mxLength = max(mxLength,i-pSumIdx[sum-k]);
            }
            if(pSumIdx.find(sum)==pSumIdx.end()){
                pSumIdx[sum]=i;
            }
        }
        return mxLength;
    }
};
