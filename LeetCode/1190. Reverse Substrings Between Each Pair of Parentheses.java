class Solution {
    public String reverseParentheses(String s) {
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder current = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(current.toString());
                current.setLength(0);
            } else if (c == ')') {
                current.reverse();
                current = new StringBuilder(stack.pop()).append(current);
            } else {
                current.append(c);
            }
        }
        return current.toString();
    }
}
