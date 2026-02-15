class Solution {
    public String addBinary(String a, String b) {
        //in python i will have a very funny solution lol
        //okay i didnt know we had parseint with base in java lmao
        // int a1 = Integer.parseInt(a, 2);
        // int b1 = Integer.parseInt(b, 2);
        // String sum = Integer.toBinaryString(a1+b1);
        // return sum;
        //bruh i was not expecting it to break :(
        //so finally i have to use the carry approach
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;        
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
            result.append(sum % 2);
            carry = sum / 2;
        }        
        return result.reverse().toString();
    }
}
