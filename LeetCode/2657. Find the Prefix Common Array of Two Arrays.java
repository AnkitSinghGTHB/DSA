class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n=A.length;
        int[] freq = new int[n+1]; //numbers from 1 to n, here was my mistake
        int[] ans = new int[n];
        int common = 0;
        for (int i = 0; i < n; i++) {
            freq[A[i]]++;// process A[i]
            if (freq[A[i]] == 2) {
                common++;
            }            
            freq[B[i]]++;// process B[i]
            if (freq[B[i]] == 2){
                common++;
            }
            ans[i] = common;
        }
        return ans;
    }
}
