class Solution {
    public String getHappyString(int n, int k) {
        List<String> list = new ArrayList<>();
        generate("", n, list);
        if (k > list.size()) return "";
        return list.get(k - 1);
    }

    private void generate(String current, int n, List<String> list) {
        if (current.length() == n) {
            list.add(current);
            return;
        }
        for (char c : new char[]{'a', 'b', 'c'}) {
            if (current.length() == 0 || current.charAt(current.length() - 1) != c) {
                generate(current + c, n, list);
            }
        }
    }
}
