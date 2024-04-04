
 
import java.util.Arrays;

public class WithDP3 {
    
    public static int findMinLaneSwitches(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Initialize dp array to store minimum switches for each cell
        int[][] dp = new int[rows][cols];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Base cases: First row needs no switches
        for (int col = 0; col < cols; col++) {
            if (matrix[0][col] == 0) {
                dp[0][col] = 0;
            }
        }

        // Fill the dp table
        for (int row = 1; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 0) {
                    // Consider current lane and adjacent lanes
                    int minSwitches = Math.min(dp[row - 1][col], dp[row][col]); // Same lane priority
                    if (col > 0) {
                        minSwitches = Math.min(minSwitches, dp[row - 1][col - 1] + 1); // Left lane
                    }
                    if (col < cols - 1) {
                        minSwitches = Math.min(minSwitches, dp[row - 1][col + 1] + 1); // Right lane
                    }
                    dp[row][col] = minSwitches;
                }
            }
        }

        // Find the minimum switches in the last row
        int minSwitches = Integer.MAX_VALUE;
        for (int col = 0; col < cols; col++) {
            if (dp[rows - 1][col] != Integer.MAX_VALUE) {
                minSwitches = Math.min(minSwitches, dp[rows - 1][col]);
            }
        }

        return minSwitches;
    }

    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 0, 0, 1},
                {1, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        int[][] matrix2 = {
                {1, 0, 0, 1},
                {0, 0, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 1}
        };

        System.out.println("Minimum lane switches in matrix1: " + findMinLaneSwitches(matrix1));
        System.out.println("Minimum lane switches in matrix2: " + findMinLaneSwitches(matrix2));
    }
}
