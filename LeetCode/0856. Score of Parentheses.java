class Solution {
    public int scoreOfParentheses(String s) {
        //stack?
        //my idea is maybe like we get ( then if ) then score is 1
        //if we get ( after ( then whatever the score var is multiply by 2
        //when the ) closes, the score is verified
        //when we get ( after ) then add new score
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // base score for the whole string
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0); // start a new nesting level
            } else {
                int inner = stack.pop();
                // If inner == 0, it was "()", score = 1
                // Otherwise, score = 2 * inner
                int score = Math.max(2 * inner, 1);
                // Add this score to the parent level
                stack.push(stack.pop() + score);
            }
        }
        
        return stack.pop();
    }
}
