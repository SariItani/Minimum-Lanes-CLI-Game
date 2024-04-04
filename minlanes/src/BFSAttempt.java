// import java.util.LinkedList;
// import java.util.Queue;

// public class BFSAttempt {

//     // Direction vectors for movement: left, right, up
//     private static final int[] dx = {-1, 1, 0};
//     private static final int[] dy = {0, 0, 1};

//     public static int minLaneSwitches(int[][] cars) {
//         int m = cars.length; // Number of lanes
//         int n = cars[0].length; // Length of each lane

//         // Create a 2D array to store the minimum lane-switches needed for each cell
//         int[][] minSwitches = new int[m][n];
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 minSwitches[i][j] = Integer.MAX_VALUE;
//             }
//         }

//         // Initialize the BFS queue
//         Queue<int[]> queue = new LinkedList<>();

//         // Start BFS from all possible starting positions at cars[i][0]
//         for (int i = 0; i < m; i++) {
//             if (cars[i][0] == 0) {
//                 queue.offer(new int[]{i, 0, 0}); // {lane, column, switches}
//                 minSwitches[i][0] = 0;
//             }
//         }

//         // Perform BFS
//         while (!queue.isEmpty()) {
//             int[] current = queue.poll();
//             int currentLane = current[0];
//             int currentColumn = current[1];
//             int currentSwitches = current[2];

//             // Explore all possible directions (left, right, up)
//             for (int k = 0; k < 3; k++) {
//                 int newLane = currentLane + dx[k];
//                 int newColumn = currentColumn + dy[k];

//                 // Check if the new position is within bounds
//                 if (newLane >= 0 && newLane < m && newColumn >= 0 && newColumn < n) {
//                     // Check if the cell is empty and hasn't been visited before
//                     if (cars[newLane][newColumn] == 0 && currentSwitches + (k != 2 ? 1 : 0) < minSwitches[newLane][newColumn]) {
//                         minSwitches[newLane][newColumn] = currentSwitches + (k != 2 ? 1 : 0);
//                         queue.offer(new int[]{newLane, newColumn, minSwitches[newLane][newColumn]});
//                     }
//                 }
//             }
//         }

//         // Find the minimum switches needed to reach the end in the last column
//         int result = Integer.MAX_VALUE;
//         for (int i = 0; i < m; i++) {
//             if (cars[i][n - 1] == 0) {
//                 result = Math.min(result, minSwitches[i][n - 1]);
//             }
//         }

//         return result == Integer.MAX_VALUE ? -1 : result;
//     }

//     public static void main(String[] args) {
//         // Example usage
//         int[][] cars = {
//             {1, 0, 0, 1},
//             {0, 1, 0, 0},
//             {0, 0, 0, 1},
//             {1, 0, 1, 0}
//         };

//         int result = minLaneSwitches(cars);
//         System.out.println("Minimum Lane Switches: " + result);
//     }
// }