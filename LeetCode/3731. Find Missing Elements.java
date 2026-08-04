class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        HashSet<Integer> m = new HashSet<>();
        int n=nums.length;
        int mn=101, mx=0;
        for (int i=0;i<n;i++){
            int t = nums[i];
            if (t<mn){
                mn=t;
            }
            if (t>mx){
                mx=t;
            }
            m.add(t);
        }
        ArrayList<Integer> res = new ArrayList<>(); 
        for (int i=mn;i<=mx;i++){
            if(!m.contains(i)){
                res.add(i);
            }
        }
        return res; //oh we can return arraylist as list directly
    }
}
