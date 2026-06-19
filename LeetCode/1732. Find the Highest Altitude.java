class Solution {
    public int largestAltitude(int[] gain) {
        //i remember doing this ques somewhere, or something like this
        int prefsum = 0;
        int alt = 0;
        for (int i=0; i<gain.length;i++){
            prefsum += gain[i];
            if (alt<prefsum){
                alt = prefsum;
            }
        }
        return alt;
    }
}
