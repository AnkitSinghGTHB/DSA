class Solution {
    public int reverseDegree(String s) {
        int n=s.length();
        int deg=0;
        for (int i=0;i<n;i++){
            deg+=('z'-s.charAt(i)+1)*(i+1);
        }
        return deg;
    }
}
