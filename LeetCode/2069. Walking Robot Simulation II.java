class Robot {
    private int width, height;
    private int totalSteps;         //total number of steps taken so far
    private int period;             //length of the repeating cycle (T)
    private List<int[]> cycle;      //states after steps 1..period: [x, y, dir]
    private int[] dx = {1, 0, -1, 0}; // e, n, w, s
    private int[] dy = {0, 1, 0, -1};
    private String[] dirNames = {"East", "North", "West", "South"};

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.totalSteps = 0;
        this.period = 2 * (width + height - 2);
        cycle = new ArrayList<>(period);

        //simulate the first 'period' steps starting from (0,0) facing East
        int x = 0, y = 0, dir = 0; // 0 = East
        for (int step = 1; step <= period; step++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            //if next cell is out of bounds, turn left (counter‑clockwise) and retry
            if (nx < 0 || nx >= width || ny < 0 || ny >= height) {
                dir = (dir + 1) % 4; // left turn
                nx = x + dx[dir];
                ny = y + dy[dir];
            }
            x = nx;
            y = ny;
            // after moving, the direction is the one we just used
            cycle.add(new int[]{x, y, dir});
        }
    }

    public void step(int num) {
        totalSteps += num;
    }

    public int[] getPos() {
        if (totalSteps == 0) {
            return new int[]{0, 0};
        }
        int idx = (totalSteps - 1) % period;
        int[] state = cycle.get(idx);
        return new int[]{state[0], state[1]};
    }

    public String getDir() {
        if (totalSteps == 0) {
            return "East";
        }
        int idx = (totalSteps - 1) % period;
        int[] state = cycle.get(idx);
        return dirNames[state[2]];
    }
}
