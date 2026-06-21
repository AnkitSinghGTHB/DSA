class Solution {
    public int maxIceCream(int[] costs, int coins) {
        //what is counting sort? can i not just use some O(nlogn) sort?
        /*
        Arrays.sort(costs);//O(n logn)
        int count=0;
        for(int i=0;i<costs.length;i++){
            if(coins>=costs[i]){
                coins-=costs[i];
                count++;
            }
        }
        return count;
        */
        //i still dont know counting sort
        //let me learn that before submitting

        //okay i learned that if i use counting sort here
        //O(n + maxCost) will be faster than O(n log n) if maxCost ≤ 1e5
        int maxCost = 0;
        for (int c : costs) maxCost = Math.max(maxCost, c);
        int[] freq = new int[maxCost + 1];
        for (int c : costs) freq[c]++;
        
        int count = 0;
        for (int price = 0; price <= maxCost && coins >= price; price++) {
            int available = freq[price];
            if (available == 0) continue;
            int canBuy = Math.min(available, coins / price);
            count += canBuy;
            coins -= canBuy * price;
        }
        return count;
    }
}
