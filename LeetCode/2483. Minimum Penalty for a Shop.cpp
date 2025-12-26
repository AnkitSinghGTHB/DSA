class Solution {
public:
    int bestClosingTime(string customers) {
        //its not easy to understand bruv
        int n = customers.size();
        int totalY = 0;
        for (char c : customers) {
            if (c == 'Y') totalY++;
        }        
        int penalty = totalY;
        int minPenalty = penalty;
        int bestHour = 0;        
        for (int j = 1; j <= n; j++) {
            if (customers[j-1] == 'N') {
                penalty += 1;
            } 
            else {
                penalty -= 1;
            }
            if (penalty < minPenalty) {
                minPenalty = penalty;
                bestHour = j;
            }
        }        
        return bestHour;
    }
};
