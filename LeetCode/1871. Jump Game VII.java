class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        //if the last char is not 0 we cant reach it.
        if (s.charAt(n - 1) != '0') return false;        
        int[] diff = new int[n + 1];
        int sum = 0;        
        //strt from idx 0 (its always 0)
        int left = minJump;
        int right = maxJump;
        if (left < n) {
            diff[left]++;
            if (right + 1 < n) diff[right + 1]--;
        }        
        //process indices 1 to n-1
        for (int i = 1; i < n; i++) {
            sum += diff[i];
            if (sum > 0 && s.charAt(i) == '0') {
                if (i == n - 1) return true;
                left = i + minJump;
                right = i + maxJump;
                if (left < n) {
                    diff[left]++;
                    if (right + 1 < n) diff[right + 1]--;
                }
            }
        }
        return false;
    }
}
