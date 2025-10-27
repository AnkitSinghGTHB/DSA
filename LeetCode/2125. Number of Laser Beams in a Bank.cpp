class Solution {
public:
    int numberOfBeams(vector<string>& bank) {
        int prevDevices = 0;
        int totalBeams = 0;
        
        for (const string& row : bank) {
            int currDevices = 0;
            for (char cell : row) {
                if (cell == '1') currDevices++;
            }
            
            if (currDevices > 0) {
                totalBeams += prevDevices * currDevices;
                prevDevices = currDevices;
            }
        }
        
        return totalBeams;
    }
};
