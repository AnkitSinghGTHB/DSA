import java.util.*;
//used deepseek
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;
        
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        visited.add(s);
        boolean found = false;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (isValid(cur)) {
                    result.add(cur);
                    found = true;
                }
                // If we already found valid strings at this level, do not expand further
                if (found) continue;
                
                // Generate all strings by removing one parenthesis
                for (int j = 0; j < cur.length(); j++) {
                    char c = cur.charAt(j);
                    if (c != '(' && c != ')') continue;
                    String next = cur.substring(0, j) + cur.substring(j + 1);
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
            if (found) break; // stop after finishing this level
        }
        return result;
    }
    
    private boolean isValid(String s) {
        int balance = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') balance++;
            else if (c == ')') {
                balance--;
                if (balance < 0) return false;
            }
        }
        return balance == 0;
    }
}
