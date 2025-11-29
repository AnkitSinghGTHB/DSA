class Solution {
public:
    bool isPossible(vector<int>& arr, int k, int maxPages) {
        int count=1, currSum=0;
        for (auto a:arr){
            if (a>maxPages) return false;//cant assign this book to anyone
            if (currSum+a>maxPages){
                count++;
                currSum=a;
            }
            else{
                currSum+=a;
            }
        }
        return count<=k;
    }
    int findPages(vector<int> &arr, int k) {
        //smallest max subset after k subsets
        //need to somehow do in O(nlogn)
        int n =arr.size();
        if (k>n) return -1;
        int low = *max_element(arr.begin(), arr.end());
        int high = accumulate(arr.begin(), arr.end(), 0);
        int result = -1;
        while (low<=high){
            int mid=low+(high-low)/2;
            if (isPossible(arr,k,mid)) {
                result=mid;
                high=mid-1; //try for smaller max
            } else {
                low=mid+1;
            }
        }
        return result;
    }
};
