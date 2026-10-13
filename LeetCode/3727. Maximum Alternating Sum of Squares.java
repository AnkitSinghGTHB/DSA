class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];
        for (int i=0;i<n;i++){
            arr[i]=nums[i]*nums[i]; //had to fix integer overflow
        }
        Arrays.sort(arr);
        long score = 0;
        for(int i=0;i<n/2;i++){
            score-=arr[i];
        }
        for(int i=n/2;i<n;i++){
            score+=arr[i];
        }
        return score;
    }
}
