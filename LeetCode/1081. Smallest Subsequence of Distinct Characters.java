class Solution {
    public String smallestSubsequence(String s) {
        //erm example soln is cringe
        //nah my soln is cringe
        //this new soln is assisted by deepseek
        int n = s.length();
        int[] last = new int[26];
        for (int i = 0; i < n; i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        boolean[] inStack = new boolean[26];
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (inStack[c - 'a']) continue;

            while (!stack.isEmpty() && stack.peek() > c && last[stack.peek() - 'a'] > i) {
                inStack[stack.peek() - 'a'] = false;
                stack.pop();
            }

            stack.push(c);
            inStack[c - 'a'] = true;
        }

        // Build result from stack (bottom to top)
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }
}
