class Solution {
    public String processStr(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                res.append(c);
            } 
            else if (c == '*') {
                if (res.length() > 0) {
                    res.deleteCharAt(res.length() - 1);
                }
            } 
            else if (c == '#') {
                //duplicate current result
                res.append(res.toString());
            } 
            else if (c == '%') {
                res.reverse();
            }
        }
        return res.toString();
    }
}
