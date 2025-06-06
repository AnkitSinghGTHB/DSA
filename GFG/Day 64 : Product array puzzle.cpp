// User function template for C++

class Solution {
  public:
    vector<int> productExceptSelf(vector<int>& arr) {
        //to do this in O(n) i might uh
        //get prefix and suffix products
        //and finally multiply prefix and suffix products
        int n = arr.size();
        vector<int> left(n, 1), right(n, 1), res(n);
        for (int i=1; i<n; ++i){
            left[i]=arr[i-1]*left[i-1];
        }
        for (int i=n-2; i>=0; --i){
            right[i]=arr[i+1]*right[i+1];
        }
        for (int i=0; i<n;++i){
            res[i]=left[i]*right[i];
        }
        return res;
    }
};
