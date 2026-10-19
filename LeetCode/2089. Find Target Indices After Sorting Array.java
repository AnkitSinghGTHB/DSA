class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        int n =nums.length;
        //i have to use lowerbound bin search
        int idx=-1;
        int low=0;
        int high=n-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            if (nums[mid]==target){
                idx=mid;
                high=mid-1;
            }
            else if (nums[mid]<target){
                low=mid+1;
            }
            else{
                high=mid-1;
            }
        }
        //i forgot to see if idx=-1
        if (idx==-1){
            return ans;//empty
        }
        else{
            for(int i=idx;i<n;i++){
                if(nums[i]!=target){
                    break;
                }
                ans.add(i);
            }
            return ans;
        }
    }
}
