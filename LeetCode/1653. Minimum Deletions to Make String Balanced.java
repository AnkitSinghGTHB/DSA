class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        int totalA = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                totalA++;
            }
        }
        int leftB = 0;
        int rightA = totalA;
        int minDeletions = leftB + rightA; //split at 0
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                rightA--;
            } 
            else {
                leftB++;
            }
            minDeletions = Math.min(minDeletions, leftB + rightA);
        }
        return minDeletions;
    }
}
