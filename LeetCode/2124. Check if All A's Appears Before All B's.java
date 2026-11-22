class Solution {
    public boolean checkString(String s) {
        //cases: ab, b, a
        //fail: ba
        int n=s.length();
        if (n==1) return true;
        for (int i=0;i<n-1;i++){
            //so we look for 'ba' cases
            if (s.charAt(i)=='b' && s.charAt(i+1)=='a'){
                return false;
            }
        }
        return true;
    }
}
