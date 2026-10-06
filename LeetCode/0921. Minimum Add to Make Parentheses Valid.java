class Solution {
    public int minAddToMakeValid(String s) {
        //can we add anywhere?
        //take a stack
        //clear if getting the opposite one
        //in the end if the stack is nonempty then return length
        //check weird cases like )( 
        Stack<Character> k = new Stack<>();
        int count =0;
        for(char c:s.toCharArray()){
            if (c=='('){
                k.push(c);
                count++;
            }
            else if (c==')'){
                if (k.isEmpty()){
                    count++;
                }
                else{
                    char f = k.peek();
                    if (f=='('){
                        k.pop();
                        count--;
                    }
                    else{
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
