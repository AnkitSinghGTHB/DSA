class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        //dirs: north, east, south, west
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int dir = 0; // start facing north
        int x = 0, y = 0;
        int maxDistSq = 0;
        
        //store obstacles in a set for O(1) lookup
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            obstacleSet.add(obs[0] + "," + obs[1]);
        }
        
        for (int cmd : commands) {
            if (cmd == -2) {
                // turn left
                dir = (dir + 3) % 4;
            } 
            else if (cmd == -1) {
                // turn right
                dir = (dir + 1) % 4;
            } 
            else {
                // move forward step by step
                for (int i = 0; i < cmd; i++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    if (obstacleSet.contains(nx + "," + ny)) {
                        break; // obstacle encountered, stop moving
                    }
                    x = nx;
                    y = ny;
                    maxDistSq = Math.max(maxDistSq, x * x + y * y);
                }
            }
        }
        return maxDistSq;
    }
}
