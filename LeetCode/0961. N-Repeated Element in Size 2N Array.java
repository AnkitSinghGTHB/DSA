import java.util.HashSet;//this i learnt new
//this soln is smarter than what i originally thought
class Solution {
    public int repeatedNTimes(int[] nums) {
        HashSet<Integer> set = new HashSet<>(); //so this is how we do it lol
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        return -1;
    }
}
