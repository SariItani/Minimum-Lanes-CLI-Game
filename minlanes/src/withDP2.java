public class withDP2 {
    public static int findMinimumSwitches(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows][cols];
        dp[0][0] = matrix[0][0];

        // Fill the first row of dp matrix
        for (int j = 1; j < cols; j++) {
            dp[0][j] = dp[0][j - 1] + (1 - matrix[0][j]);
        }

        // Fill the first column of dp matrix
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }

        // Fill the remaining cells of dp matrix
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }

        return dp[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 0, 0, 1},
            {1, 1, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };

        int minimumSwitches = findMinimumSwitches(matrix);
        System.out.println("Minimum number of lane switches: " + minimumSwitches);
    }
}