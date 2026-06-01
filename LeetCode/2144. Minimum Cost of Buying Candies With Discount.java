class Solution {
    public int minimumCost(int[] cost) {
        //sort in desc order
        Integer[] costDesc = Arrays.stream(cost).boxed().toArray(Integer[]::new);
        Arrays.sort(costDesc, (a, b) -> b - a);
        int total = 0;
        for (int i = 0; i < costDesc.length; i++) {
            //pay for every third candy 
            //idx 0,1 are paid, idx 2 is free, then 3,4 paid, 5 free so on
            if (i % 3 != 2) {
                total += costDesc[i];
            }
        }
        return total;
    }
}
