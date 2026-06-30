class Solution {

    public boolean exist(char[][] board, String word) {

        int m = board.length;
        int n = board[0].length;

        // Optional optimization:
        // If the board doesn't contain enough occurrences of any character,
        // return false immediately.
        int[] freq = new int[128];
        for (char[] row : board) {
            for (char c : row) {
                freq[c]++;
            }
        }

        for (char c : word.toCharArray()) {
            if (--freq[c] < 0) {
                return false;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (board[i][j] == word.charAt(0)
                        && dfs(board, word, i, j, 0)) {
                    return true;
                }

            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word,
                        int i, int j, int index) {

        if (index == word.length())
            return true;

        if (i < 0 || j < 0 ||
            i >= board.length || j >= board[0].length ||
            board[i][j] != word.charAt(index))
            return false;

        char temp = board[i][j];
        board[i][j] = '#';

        boolean found =
                dfs(board, word, i + 1, j, index + 1) ||
                dfs(board, word, i - 1, j, index + 1) ||
                dfs(board, word, i, j + 1, index + 1) ||
                dfs(board, word, i, j - 1, index + 1);

        board[i][j] = temp;

        return found;
    }
}