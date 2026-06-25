import java.util.*;

class Solution {

    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();

        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[] map = {
            "", "",
            "abc", "def",
            "ghi", "jkl",
            "mno", "pqrs",
            "tuv", "wxyz"
        };

        Queue<String> queue = new LinkedList<>();
        queue.offer("");

        for (char digit : digits.toCharArray()) {

            int size = queue.size();
            String letters = map[digit - '0'];

            for (int i = 0; i < size; i++) {

                String current = queue.poll();

                for (char c : letters.toCharArray()) {
                    queue.offer(current + c);
                }
            }
        }

        result.addAll(queue);
        return result;
    }
}