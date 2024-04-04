// import java.util.LinkedList;
// import java.util.Queue;

// public class WithDp {

//     public static int findMinSwitches(int[][] matrix) {
//         int rows = matrix.length;
//         int cols = matrix[0].length;

//         // Using BFS to explore the paths
//         Queue<int[]> queue = new LinkedList<>();
//         boolean[][] visited = new boolean[rows][cols];

//         // Initial state: row, column, switches
//         queue.offer(new int[]{0, 0, 0});
//         visited[0][0] = true;

//         while (!queue.isEmpty()) {
//             int[] current = queue.poll();
//             int row = current[0];
//             int col = current[1];
//             int switches = current[2];

//             // Check if we reached any bottom row
//             if (row == rows - 1) {
//                 return switches;
//             }

//             // Explore possible moves: right, down, left
//             int[][] moves = {{0, 1}, {1, 0}, {0, -1}};

//             for (int[] move : moves) {
//                 int newRow = row + move[0];
//                 int newCol = col + move[1];

//                 // Check if the new position is within bounds and not visited
//                 if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && !visited[newRow][newCol] && matrix[newRow][newCol] == 0) {
//                     queue.offer(new int[]{newRow, newCol, switches + (move[1] != 0 ? 1 : 0)});
//                     visited[newRow][newCol] = true;
//                 }
//             }
//         }

//         // If we cannot reach any bottom row
//         return -1;
//     }

//     public static void main(String[] args) {
//         int[][] matrix1 = {
//                 {1, 0, 0, 1},
//                 {1, 1, 0, 0},
//                 {0, 0, 0, 0},
//                 {0, 0, 0, 0}
//         };
//         System.out.println("Minimum switches: " + findMinSwitches(matrix1));

//         int[][] matrix2 = {
//                 {1, 0, 0, 1},
//                 {0, 0, 1, 0},
//                 {0, 1, 0, 0},
//                 {0, 0, 0, 1}
//         };
//         System.out.println("Minimum switches: " + findMinSwitches(matrix2));
//     }
// }
