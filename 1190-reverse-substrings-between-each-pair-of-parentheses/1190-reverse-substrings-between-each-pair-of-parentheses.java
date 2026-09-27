class Solution {
    public String reverseParentheses(String s) {
        Stack<StringBuilder> st = new Stack<>();
        st.push(new StringBuilder());

        for (char c : s.toCharArray()) {
            if (c == '(') {
                st.push(new StringBuilder());
            } 
            else if (c == ')') {
                StringBuilder cur = st.pop();
                cur.reverse();
                st.peek().append(cur);
            } 
            else {
                st.peek().append(c);
            }
        }

        return st.pop().toString();
    }
}