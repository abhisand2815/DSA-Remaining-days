class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        int top = 0;
        int bottom = m - 1;

        while (top <= bottom) {

            int row = top + (bottom - top) / 2;

            if (target > matrix[row][n - 1])
                top = row + 1;
            else if (target < matrix[row][0])
                bottom = row - 1;
            else {

                int left = 0;
                int right = n - 1;

                while (left <= right) {
                    int mid = left + (right - left) / 2;

                    if (matrix[row][mid] == target)
                        return true;

                    if (matrix[row][mid] < target)
                        left = mid + 1;
                    else
                        right = mid - 1;
                }

                return false;
            }
        }

        return false;
    }
}