class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        //sort indices by position to process collisions in order
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;
        Arrays.sort(indices, (a, b) -> Integer.compare(positions[a], positions[b]));
a
        boolean[] alive = new boolean[n];
        Arrays.fill(alive, true);
        Deque<Integer> stack = new ArrayDeque<>();  // stores indices of right-moving robots

        for (int idx : indices) {
            char dir = directions.charAt(idx);
            int health = healths[idx];  // current health of this robot
            if (dir == 'R') {
                stack.push(idx);
            } 
            else { // direction 'L'
                while (!stack.isEmpty()) {
                    int topIdx = stack.peek();
                    int topHealth = healths[topIdx];
                    if (topHealth > health) {
                        // top robot survives, current robot dies
                        healths[topIdx]--;
                        alive[idx] = false;
                        health = 0;   // mark current as dead
                        break;
                    } 
                    else if (topHealth < health) {
                        // current robot survives, top robot dies
                        alive[topIdx] = false;
                        stack.pop();
                        health--;     // current health decreases by 1
                    } 
                    else {
                        // equal health: both die
                        alive[topIdx] = false;
                        stack.pop();
                        alive[idx] = false;
                        health = 0;
                        break;
                    }
                }
                if (health > 0) {
                    // current robot (moving left) survived all collisions
                    healths[idx] = health;
                }
            }
        }

        //collect survivors in the original order
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (alive[i]) {
                result.add(healths[i]);
            }
        }
        return result;
    }
}
