class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // store full word
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        TrieNode root = buildTrie(words);

        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root, result);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int i, int j,
                     TrieNode node, List<String> result) {

        char c = board[i][j];

        // boundary + pruning
        if (c == '#' || node.children[c - 'a'] == null) return;

        node = node.children[c - 'a'];

        // found word
        if (node.word != null) {
            result.add(node.word);
            node.word = null; // avoid duplicates 🔥
        }

        board[i][j] = '#'; // mark visited

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];

            if (ni >= 0 && nj >= 0 && ni < board.length && nj < board[0].length) {
                dfs(board, ni, nj, node, result);
            }
        }

        board[i][j] = c; // backtrack
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode node = root;

            for (char c : word.toCharArray()) {
                int index = c - 'a';

                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }

                node = node.children[index];
            }

            node.word = word; // store word
        }

        return root;
    }
}