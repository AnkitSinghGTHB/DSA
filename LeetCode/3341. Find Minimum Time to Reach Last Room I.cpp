class Solution {
public:
    int minTimeToReach(vector<vector<int>>& moveTime) {
        //first read: i didnt understand a single thing
        //so basically maze solving algo? can i use dijkstra?
        /*can move only after the room is open, and moving takes
        1 second between adjacent rooms

        so we use dijkstra algo with a min-heap to simulate movement
        we only enter a room after its moveTime
        keeping a visited matrix or dist[i][j] to store 
        the min time to reach each cell.
        finally return the time once you reach the bottom-right cell.
        */

        int n = moveTime.size(), m =moveTime[0].size();
        vector<vector<int>> dist(n, vector<int>(m, INT_MAX));
        int dx[4] ={-1,1,0,0}; //up, down, left, right
        int dy[4] ={0,0,-1,1};  
        priority_queue<vector<int>,vector<vector<int>>,greater<vector<int>>> pq;
        pq.push({0,0,0});//{time, x, y}
        while(!pq.empty()){
            auto top =pq.top();
            int time =top[0];
            int x =top[1];
            int y =top[2];
            pq.pop();
            moveTime[x][y] = -1;//visited
            if(x==n-1 && y==m-1) { //reached the dest
                return time;
            }
            for(int d=0; d<4; d++){//4 directional traversing
                int n_x = x+dx[d]; //adjacent node
                int n_y = y+dy[d];
                if(n_x<0 || n_y<0 || n_x>=n || n_y>=m || moveTime[n_x][n_y]==-1) {
                        continue;//valid and visit check
                }
                int time_n=max(moveTime[n_x][n_y], time);
                //new time
                if(dist[n_x][n_y]>time_n+1){
                    dist[n_x][n_y]=time_n+1;
                    pq.push({time_n+1,n_x,n_y});//push to cell
                }
            }
        }
        return dist[n-1][m-1]; //safe exit
    }
};
