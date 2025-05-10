class Solution {
public:
    int minTimeToReach(vector<vector<int>>& moveTime) {
        //waht the hell, yesterday we did the part 1 T-T
        int n = moveTime.size(), m = moveTime[0].size();
        //dist[x][y][parity] — where parity: 0 means next move is 1s, 1 means 2s
        vector<vector<vector<int>>> dist(n, vector<vector<int>>(m, vector<int>(2, INT_MAX)));
        int dx[4] = {-1, 1, 0, 0}; //directions up down left right
        int dy[4] = {0, 0, -1, 1};
        priority_queue<tuple<int, int, int, int>, vector<tuple<int, int, int, int>>, greater<>> pq;// priority_queue<time, x, y, parity>
        pq.push({0, 0, 0, 0}); // time, x, y, parity (0: next move is 1s)
        dist[0][0][0] = 0;
        while (!pq.empty()) {
            auto [time, x, y, parity] = pq.top();
            pq.pop();
            if (x == n - 1 && y == m - 1)
                return time;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;
                int wait = max(moveTime[nx][ny] - time, 0);
                int moveCost = (parity == 0) ? 1 : 2;
                int newTime = time + wait + moveCost;
                int newParity = 1 - parity;
                if (newTime < dist[nx][ny][newParity]) {
                    dist[nx][ny][newParity] = newTime;
                    pq.push({newTime, nx, ny, newParity});
                }
            }
        }
        return min(dist[n-1][m-1][0], dist[n-1][m-1][1]);
    }
};
