class Solution {
  public:
    vector<int> kLargest(vector<int>& arr, int k) {
        //eh, i can do the easy version
        sort(arr.begin(),arr.end());
        int n=arr.size();
        vector<int> temp;
        int i=n-1;
        while(k>0){
            temp.push_back(arr[i]);
            i--;
            k--;
        }
        return temp;
    }
};
