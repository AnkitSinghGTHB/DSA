class Solution {
    public boolean isValid(String s) {
        //add to left to stack, if right is for left of top of stack, then go ahead
        //anywhere discrepancy then false
        Stack<Character> k = new Stack<>();
        for (char c:s.toCharArray()){
            if (c=='[' || c=='{' || c=='('){
                k.push(c);
            }
            if (c==']' || c=='}'||c==')'){
                if (k.isEmpty()) {
                    return false;
                }
                if ((k.peek()=='[' && c==']')
                ||(k.peek()=='{' && c=='}')
                ||(k.peek()=='(' && c==')')
                ){
                    k.pop();
                }
                else{
                    return false;
                }
            }
        }
        return k.isEmpty();
    }
}
