import java.util.*;

class Solution {

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null; // store full word at end
    }

    private TrieNode root = new TrieNode();
    private List<String> result = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {

        // Step 1: Build Trie
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
            }
            node.word = word;
        }

        int m = board.length, n = board[0].length;

        // Step 2: DFS from every cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int i, int j, TrieNode node) {

        // boundary
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length)
            return;

        char c = board[i][j];

        // visited or no prefix
        if (c == '#' || !node.children.containsKey(c)) return;

        node = node.children.get(c);

        // word found
        if (node.word != null) {
            result.add(node.word);
            node.word = null; // avoid duplicates
        }

        // mark visited
        board[i][j] = '#';

        // explore 4 directions
        dfs(board, i + 1, j, node);
        dfs(board, i - 1, j, node);
        dfs(board, i, j + 1, node);
        dfs(board, i, j - 1, node);

        // backtrack
        board[i][j] = c;

        // 🔥 pruning optimization
        if (node.children.isEmpty()) {
            node = null;
        }
    }
}