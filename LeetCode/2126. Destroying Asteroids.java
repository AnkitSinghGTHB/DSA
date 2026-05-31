import java.util.Arrays;

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        //sort asteroids in increasing order
        Arrays.sort(asteroids);
        long currentMass = mass; //use long to avoid overflow
        for (int asteroid : asteroids) {
            if (currentMass >= asteroid) {
                currentMass += asteroid; //gain mass
            } 
            else {
                return false; //cant destroy this asteroid
            }
        }
        return true;
    }
}
