// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import java.util.PriorityQueue;

// public class AttemptGraph {

//     // Define directions for moving to left, right, and up
//     private static final int[] dx = {-1, 0, 1};
//     private static final int[] dy = {0, 1, 0};

//     // Function to check if the given coordinates are within the bounds of the matrix
//     private static boolean isValid(int x, int y, int rows, int cols) {
//         return x >= 0 && x < rows && y >= 0 && y < cols;
//     }

//     // Formulate nodes from the matrix
//     public static List<int[]> formulateNodes(int[][] matrix) {
//         List<int[]> nodes = new ArrayList<>();

//         int rows = matrix.length;
//         int cols = matrix[0].length;

//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 if (i == 0 || i == rows - 1) {
//                     // Starting or ending row, check for empty cell
//                     if (matrix[i][j] == 0) {
//                         nodes.add(new int[]{i, j});
//                     }
//                 } else if (matrix[i][j] == 0) {
//                     // Check below a car for valid nodes
//                     nodes.add(new int[]{i, j});

//                     // Check left and right of the node below a car
//                     for (int k = 0; k < 2; k++) {
//                         int nx = i + dx[k];
//                         int ny = j + dy[k];
//                         if (isValid(nx, ny, rows, cols) && matrix[nx][ny] == 0) {
//                             nodes.add(new int[]{nx, ny});
//                         }
//                     }
//                 }
//             }
//         }

//         return nodes;
//     }

//     // Connect the graph and formulate the adjacency matrix
//     public static int[][] connectGraph(List<int[]> nodes, int[][] matrix) {
//         int n = nodes.size();
//         int[][] adjacencyMatrix = new int[n][n];
//         for (int[] row : adjacencyMatrix) {
//             Arrays.fill(row, Integer.MAX_VALUE);
//         }

//         for (int i = 0; i < n; i++) {
//             int[] source = nodes.get(i);

//             for (int j = 0; j < n; j++) {
//                 if (i != j) {
//                     int[] destination = nodes.get(j);

//                     // Check for obstacles in the path
//                     boolean isValidEdge = true;
//                     if (source[0] == destination[0]) {
//                         // Horizontal connection
//                         int minY = Math.min(source[1], destination[1]);
//                         int maxY = Math.max(source[1], destination[1]);

//                         for (int y = minY + 1; y < maxY; y++) {
//                             if (matrix[source[0]][y] == 1) {
//                                 isValidEdge = false;
//                                 break;
//                             }
//                         }
//                     } else {
//                         // Vertical connection
//                         int minX = Math.min(source[0], destination[0]);
//                         int maxX = Math.max(source[0], destination[0]);

//                         for (int x = minX + 1; x < maxX; x++) {
//                             if (matrix[x][source[1]] == 1) {
//                                 isValidEdge = false;
//                                 break;
//                             }
//                         }
//                     }

//                     if (isValidEdge) {
//                         // Update adjacency matrix with distance
//                         int distance = Math.abs(source[0] - destination[0]) + Math.abs(source[1] - destination[1]);
//                         adjacencyMatrix[i][j] = distance;
//                         adjacencyMatrix[j][i] = distance;
//                     }
//                 }
//             }
//         }

//         return adjacencyMatrix;
//     }

//     private static int findMinLaneSwitches(int[][] adjacencyMatrix, List<int[]> nodes, int startNode, int endNode, int[][] memo) {
//         if (memo[startNode][endNode] != -1) {
//             return memo[startNode][endNode];
//         }

//         int n = nodes.size();
//         int[] dist = new int[n];
//         Arrays.fill(dist, Integer.MAX_VALUE);
//         dist[startNode] = 0;

//         PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
//         minHeap.offer(new int[]{startNode, 0});

//         while (!minHeap.isEmpty()) {
//             int[] current = minHeap.poll();
//             int currentNode = current[0];
//             int currentDistance = current[1];

//             if (currentNode == endNode) {
//                 break;
//             }

//             for (int neighbor = 0; neighbor < n; neighbor++) {
//                 if (adjacencyMatrix[currentNode][neighbor] != Integer.MAX_VALUE) {
//                     int newDistance = currentDistance + adjacencyMatrix[currentNode][neighbor];
//                     if (newDistance < dist[neighbor]) {
//                         dist[neighbor] = newDistance;
//                         minHeap.offer(new int[]{neighbor, newDistance});
//                     }
//                 }
//             }
//         }

//         memo[startNode][endNode] = dist[endNode];
//         return dist[endNode];
//     }

//     // Function to find the minimum lane switches for all combinations using memoization
//     public static int findMinLaneSwitchesForAllCombinations(int[][] adjacencyMatrix, List<int[]> nodes) {
//         int n = nodes.size();
//         int[][] memo = new int[n][n];
//         for (int[] row : memo) {
//             Arrays.fill(row, -1);
//         }

//         int minLaneSwitches = Integer.MAX_VALUE;

//         for (int startNode = 0; startNode < n; startNode++) {
//             for (int endNode = 0; endNode < n; endNode++) {
//                 if (startNode != endNode) {
//                     int switches = findMinLaneSwitches(adjacencyMatrix, nodes, startNode, endNode, memo);
//                     minLaneSwitches = Math.min(minLaneSwitches, switches);
//                 }
//             }
//         }

//         return minLaneSwitches;
//     }


//     public static void main(String[] args) {
//         int[][] matrix = {
//             {0, 1, 0, 0},
//             {0, 1, 1, 0},
//             {0, 0, 1, 0},
//             {1, 0, 0, 1}
//         };

//         List<int[]> nodes = formulateNodes(matrix);
//         int[][] adjacencyMatrix = connectGraph(nodes, matrix);

//         // Output nodes
//         System.out.println("Nodes:");
//         for (int[] node : nodes) {
//             System.out.println(Arrays.toString(node));
//         }

//         // Output adjacency matrix
//         System.out.println("\nAdjacency Matrix:");
//         for (int i = 0; i < adjacencyMatrix.length; i++) {
//             System.out.println(Arrays.toString(adjacencyMatrix[i]));
//         }

//         // Output MinLanes, Final Solution
//         int minLaneSwitches = findMinLaneSwitchesForAllCombinations(adjacencyMatrix, nodes);
//         System.out.println("Minimum Lane Switches: " + minLaneSwitches);
//     }
// }

