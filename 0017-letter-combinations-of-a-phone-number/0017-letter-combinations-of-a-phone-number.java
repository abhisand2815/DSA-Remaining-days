class Solution {

    private static final String[] MAP = {
        "", "", "abc", "def", "ghi",
        "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();

        if (digits == null || digits.length() == 0)
            return result;

        char[] path = new char[digits.length()];

        backtrack(digits, 0, path, result);

        return result;
    }

    private void backtrack(String digits, int index,
                           char[] path, List<String> result) {

        if (index == digits.length()) {
            result.add(new String(path));
            return;
        }

        String letters = MAP[digits.charAt(index) - '0'];

        for (int i = 0; i < letters.length(); i++) {
            path[index] = letters.charAt(i);
            backtrack(digits, index + 1, path, result);
        }
    }
}