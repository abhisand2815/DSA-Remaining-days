class Solution {
    public int findTheWinner(int n, int k) {
        int winner = 0;  // 0-based

        for (int i = 2; i <= n; i++)
            winner = (winner + k) % i;

        return winner + 1;  // convert to 1-based
    }
}