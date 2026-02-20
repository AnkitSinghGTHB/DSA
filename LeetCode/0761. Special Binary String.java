class Solution {
    public String makeLargestSpecial(String s) {
        if (s == null || s.length() == 0) return "";        
        List<String> primitives = new ArrayList<>();
        int balance = 0, start = 0;        
        for (int i = 0; i < s.length(); i++) {
            balance += (s.charAt(i) == '1') ? 1 : -1;
            if (balance == 0) {
                // s[start..i] is a primitive special string
                String inner = s.substring(start + 1, i);
                primitives.add("1" + makeLargestSpecial(inner) + "0");
                start = i + 1;
            }
        }        
        //then ig sort in descending order to get lexicographically largest concatenation
        primitives.sort(Collections.reverseOrder());        
        StringBuilder result = new StringBuilder();
        for (String p : primitives) {
            result.append(p);
        }
        return result.toString();
    }
}
