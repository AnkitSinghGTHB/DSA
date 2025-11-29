class Solution {
  public:
    int countPairs(vector<int> &arr, int target) {
        // basically 2sum but with counts
        //this only checks if the complement exists 
        //once but doesn’t handle duplicates or multiple
        //occurrences properly
        /*
        int n=arr.size(),c=0; 
        if(n<2) return 0; 
        unordered_map<int,int> d={}; 
        for (int i=0;i<n;i++){ 
            if(d.count(target-arr[i])) c++; 
            d[arr[i]]=1; 
        } 
        return c;*/
        unordered_map<int, int> d={};;
        int cnt=0;
        for (int num:arr) {
            cnt +=d[target-num];//if target-num exists,
                                //it forms a valid pair
            d[num]++;//store this number for future pairs
        }
        return cnt;
    }
};
