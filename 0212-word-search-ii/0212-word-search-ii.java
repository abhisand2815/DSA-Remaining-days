
class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }

    private TrieNode root = new TrieNode();
    private List<String> result = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {

        // Build Trie
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
            node.word = word;
        }

        int m = board.length, n = board[0].length;

        // DFS from each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (root.children[board[i][j] - 'a'] != null) {
                    dfs(board, i, j, root);
                }
            }
        }

        return result;
    }

    private void dfs(char[][] board, int i, int j, TrieNode node) {

        char c = board[i][j];

        if (c == '#' || node.children[c - 'a'] == null) return;

        TrieNode nextNode = node.children[c - 'a'];

        // Found word
        if (nextNode.word != null) {
            result.add(nextNode.word);
            nextNode.word = null; // avoid duplicates
        }

        // mark visited
        board[i][j] = '#';

        // explore
        if (i > 0) dfs(board, i - 1, j, nextNode);
        if (j > 0) dfs(board, i, j - 1, nextNode);
        if (i < board.length - 1) dfs(board, i + 1, j, nextNode);
        if (j < board[0].length - 1) dfs(board, i, j + 1, nextNode);

        // backtrack
        board[i][j] = c;

        // 🔥 PRUNE TRIE (IMPORTANT)
        if (isEmpty(nextNode)) {
            node.children[c - 'a'] = null;
        }
    }

    private boolean isEmpty(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) return false;
        }
        return true;
    }
}