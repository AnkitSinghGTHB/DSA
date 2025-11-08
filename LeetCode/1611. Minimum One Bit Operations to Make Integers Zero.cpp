class Solution {
public:
    int minimumOneBitOperations(int n) {
        //ah a dp ques, but i dont really understand what it is asking for
        //find the most significant bit position
        //adjust to get the actual MSB position
        //recurrence: f(n) = (2^(k+1) - 1) - f(n ^ (1 << k))
        if (n == 0) return 0;
        int k = 0;
        while ((1 << k) <= n) {
            k++;
        }
        k--;        
        return (1 << (k + 1)) - 1 - minimumOneBitOperations(n ^ (1 << k));
    }
};
