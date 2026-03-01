class Solution {
    public int minPartitions(String n) {
        //lmao
        char m= n.charAt(0);
        for (int i=0;i<n.length();i++){
            if (n.charAt(i)>m){
                m=n.charAt(i);
            }
        }
        return (int) (m - '0');
    }
}
