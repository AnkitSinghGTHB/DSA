class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        long even =1;//pref sum0 is eve
        long odd =0;
        int prefP =0;
        long ans=0;
        for(int x:arr){
            prefP ^= (x&1);
            if (prefP==1){
                ans+=even;
                odd++;
            }
            else{
                ans+=odd;
                even++;
            }
            ans%=MOD;
        }
        return (int) ans;
    }
}
