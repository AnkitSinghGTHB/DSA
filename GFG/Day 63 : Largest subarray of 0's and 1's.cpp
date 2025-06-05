class Solution {
  public:
    int maxLen(vector<int> &arr) {
        //wow, how about maintaining a map?
        //but before that, what if i turn 0 to -1
        unordered_map<int, int> mp;
        int max_len=0,sum=0;
        for (int i=0; i<arr.size(); i++) {
            sum +=(arr[i]==0) ? -1 : 1;//hehe
            if (sum==0){
                max_len =i+1; 
            }

            if (mp.find(sum)!=mp.end()){
                max_len=max(max_len,i-mp[sum]);
            }
            else {
                mp[sum]=i;
            }
        }
        return max_len;
    }
};
