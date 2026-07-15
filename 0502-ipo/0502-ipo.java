
class Solution {

    public int findMaximizedCapital(int k, int w,
                                    int[] profits,
                                    int[] capital) {

        int n = profits.length;

        int[][] projects = new int[n][2];

        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }

        // Sort by required capital
        Arrays.sort(projects, (a, b) ->
                Integer.compare(a[0], b[0]));

        // Max Heap of profits
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        int i = 0;

        while (k-- > 0) {

            // Add all affordable projects
            while (i < n && projects[i][0] <= w) {
                maxHeap.offer(projects[i][1]);
                i++;
            }

            // Cannot do any more projects
            if (maxHeap.isEmpty())
                break;

            // Take the most profitable project
            w += maxHeap.poll();
        }

        return w;
    }
}