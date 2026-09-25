class Solution {
    String s;
    int idx;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        idx = 0;
        return new ArrayList<>(parse());
    }

    // Parse concatenated expressions
    Set<String> parse() {
        Set<String> res = new TreeSet<>();
        res.add("");

        while (idx < s.length() && s.charAt(idx) != '}' && s.charAt(idx) != ',') {
            Set<String> cur;

            if (s.charAt(idx) == '{') {
                idx++; // skip {
                cur = parseUnion();
                idx++; // skip }
            } else {
                cur = new TreeSet<>();
                cur.add(String.valueOf(s.charAt(idx++)));
            }

            res = combine(res, cur);
        }

        return res;
    }

    // Parse expressions separated by commas
    Set<String> parseUnion() {
        Set<String> res = new TreeSet<>();

        while (true) {
            res.addAll(parse());

            if (idx < s.length() && s.charAt(idx) == ',') {
                idx++; // skip comma
            } else {
                break;
            }
        }

        return res;
    }

    // Cartesian product = concatenation
    Set<String> combine(Set<String> a, Set<String> b) {
        Set<String> res = new TreeSet<>();

        for (String x : a) {
            for (String y : b) {
                res.add(x + y);
            }
        }

        return res;
    }
}