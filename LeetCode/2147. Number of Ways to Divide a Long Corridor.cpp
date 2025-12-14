class Solution {
public:
    int numberOfWays(string corridor) {
        const int MOD = 1e9 + 7;        
        //find all seat positions
        vector<int> seatPositions;
        for (int i = 0; i < corridor.length(); i++) {
            if (corridor[i] == 'S') {
                seatPositions.push_back(i);
            }
        }                
        int totalSeats = seatPositions.size();//edge cases
        if (totalSeats == 0 || totalSeats % 2 != 0) {
            return 0;
        }        
        if (totalSeats == 2) {
            return 1;
        }        
        //count ways to place dividers between segments
        //between each pair of segments, count placement options
        long long ways = 1;        
        for (int i = 1; i < totalSeats / 2; i++) {
            int endPrevSegment = seatPositions[2 * i - 1];//end of previous segment (2nd seat of segment i-1)
            int startNextSegment = seatPositions[2 * i];//start of next segment (1st seat of segment i)          
            int gap = startNextSegment - endPrevSegment;//gap represents number of ways to place divider            
            ways = (ways * gap) % MOD;
        }        
        return ways;
    }
};
