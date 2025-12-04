class Solution {
public:
    int countCollisions(string directions) {
        //remove leading 'L's (they leave without colliding)
        //remove trailing 'R's (they leave without colliding)
        //every non-stationary car in the middle will eventually collide
        int n = directions.size();
        int left = 0, right = n - 1;
        while (left < n && directions[left] == 'L') left++;
        while (right >= 0 && directions[right] == 'R') right--;
        int ans = 0;
        for (int i = left; i <= right; i++) {
            if (directions[i] != 'S') ans++;
        }
        return ans;
    }
};
