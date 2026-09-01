import java.util.*;

class Solution {
    static class State {
        int r, c, mask, energy, moves;

        State(int r, int c, int mask, int energy, int moves) {
            this.r = r;
            this.c = c;
            this.mask = mask;
            this.energy = energy;
            this.moves = moves;
        }
    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = -1, sc = -1;
        int[][] litterIdx = new int[m][n];
        for (int[] row : litterIdx) {
            Arrays.fill(row, -1);
        }

        int totalLitters = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    sr = r;
                    sc = c;
                } else if (ch == 'L') {
                    litterIdx[r][c] = totalLitters++;
                }
            }
        }

        // Edge case: No litter to collect
        if (totalLitters == 0) {
            return 0;
        }

        int targetMask = (1 << totalLitters) - 1;

        // maxEnergy[r][c][mask] stores the highest remaining energy seen for that state
        int[][][] maxEnergy = new int[m][n][1 << totalLitters];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(maxEnergy[i][j], -1);
            }
        }

        Queue<State> queue = new ArrayDeque<>();
        queue.offer(new State(sr, sc, 0, energy, 0));
        maxEnergy[sr][sc][0] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            State curr = queue.poll();

            // Cannot move further if energy is depleted
            if (curr.energy == 0) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];

                // Check bounds and obstacles
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X') {
                    int nextEnergy = curr.energy - 1;
                    int nextMask = curr.mask;
                    char cell = classroom[nr].charAt(nc);

                    // Collect litter
                    if (cell == 'L') {
                        nextMask |= (1 << litterIdx[nr][nc]);
                    }

                    // Goal reached
                    if (nextMask == targetMask) {
                        return curr.moves + 1;
                    }

                    // Refuel energy
                    if (cell == 'R') {
                        nextEnergy = energy;
                    }

                    // State dominance check
                    if (nextEnergy > maxEnergy[nr][nc][nextMask]) {
                        maxEnergy[nr][nc][nextMask] = nextEnergy;
                        queue.offer(new State(nr, nc, nextMask, nextEnergy, curr.moves + 1));
                    }
                }
            }
        }

        return -1;
    }
}