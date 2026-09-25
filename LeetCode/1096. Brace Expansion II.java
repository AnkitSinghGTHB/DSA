class Solution {
    private String expr;
    private int i;

    public List<String> braceExpansionII(String expression) {
        this.expr = expression;
        this.i = 0;
        Set<String> result = parse();
        List<String> list = new ArrayList<>(result);
        Collections.sort(list);
        return list;
    }

    // Parses a concatenation of terms until it hits ',' or '}' or end.
    private Set<String> parse() {
        Set<String> current = new HashSet<>();
        current.add(""); // identity for concatenation
        while (i < expr.length() && expr.charAt(i) != ',' && expr.charAt(i) != '}') {
            char c = expr.charAt(i);
            Set<String> next;
            if (c == '{') {
                i++; // skip '{'
                next = parseUnion();
            } else {
                // letter
                next = new HashSet<>();
                next.add(String.valueOf(c));
                i++;
            }
            // concatenate current with next
            current = concatenate(current, next);
        }
        return current;
    }

    // Parses a union of expressions separated by commas, inside a '{' ... '}'
    // Assumes we are just after '{'. Consumes the closing '}'.
    private Set<String> parseUnion() {
        Set<String> unionSet = new HashSet<>();
        unionSet.addAll(parse());
        while (i < expr.length() && expr.charAt(i) == ',') {
            i++; // skip ','
            unionSet.addAll(parse());
        }
        // now we should see '}'
        if (i < expr.length() && expr.charAt(i) == '}') {
            i++; // skip '}'
        }
        return unionSet;
    }

    private Set<String> concatenate(Set<String> a, Set<String> b) {
        Set<String> res = new HashSet<>();
        for (String s1 : a) {
            for (String s2 : b) {
                res.add(s1 + s2);
            }
        }
        return res;
    }
}
