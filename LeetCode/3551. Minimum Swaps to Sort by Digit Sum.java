class Solution {
    public int minSwaps(int[] nums) {
        //ok so i shouldnt overwrite the array bcoz we ne to check the og
        //vals for tie breking & for permutations
        int n=nums.length;
        Integer[] order = new Integer[n];
        for (int i=0;i<nums.length;i++){
            order[i]=i;
        }
        Arrays.sort(order,(a,b)->{
            int sa = digSum(nums[a]);
            int sb = digSum(nums[b]);
            if(sa!=sb) return Integer.compare(sa,sb);
            return Integer.compare(nums[a],nums[b]);
        });
        //the swap part remains
        //tarpos[val]=pos where val shall end up
        Map<Integer, Integer> tarpos = new HashMap<>();
        for(int pos =0;pos<n;pos++){
            tarpos.put(nums[order[pos]],pos);
        }
        boolean[] visited = new boolean[n];
        int cyc = 0;
        for(int i=0;i<n;i++){
            if(visited[i]) continue;
            cyc++;
            int cur=i;
            while(!visited[cur]){
                visited[cur] = true;
                //nums[cur] shall mov here
                cur = tarpos.get(nums[cur]);
            }
        }
        return n-cyc;
    }
    public int digSum(int n){
        int s=0;
        while (n>0){
            int r = n%10;
            s+=r;
            n/=10;
        }
        return s;
    }
}
