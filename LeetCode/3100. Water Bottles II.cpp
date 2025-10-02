class Solution {
public:
    int maxBottlesDrunk(int numBottles, int numExchange) {
        //erm ok
        int totalDrunk = numBottles;
        int empty = numBottles;
        int exchange = numExchange;
        while (empty >= exchange) {
            //xchange
            empty -= exchange;
            exchange++;
            //drink the new bottle immediately
            totalDrunk++;
            empty++;
        }
        
        return totalDrunk;
    }
};
