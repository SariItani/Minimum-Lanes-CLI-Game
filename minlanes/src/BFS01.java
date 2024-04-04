// import java.util.List;
// import java.util.LinkedList;

// class Edge {
//     public Node child;
//     public int weight;

//     public Edge(Node _child, int _weight) {
//         child = _child;
//         weight = _weight;
//     }
// }

// class Node {
//     public int[] data = new int[2];
//     public List<Edge> children;

//     public Node() {}

//     public Node(int[] _data, List<Edge> _children) {
//         data = _data;
//         children = _children;
//     }
// }

// public class BFS01 {

//     public static LinkedList<Node> nodeQueue = new LinkedList<>();

//     public static int bfs_minpath(Node source, Node endpoint, List<Node> graph) {
//         for (Node node : graph) {
//             node.data[1] = Integer.MAX_VALUE;
//         }

//         source.data[1] = 0;
//         nodeQueue.add(source);

//         while (!nodeQueue.isEmpty()) {
//             Node current = nodeQueue.poll();

//             if (current.equals(endpoint)) {
//                 return current.data[1];
//             }

//             for (Edge edge : current.children) {
//                 Node neighbor = edge.child;
//                 int newDistance = current.data[1] + edge.weight;

//                 if (newDistance < neighbor.data[1]) {
//                     neighbor.data[1] = newDistance;

//                     if (edge.weight == 0) {
//                         nodeQueue.addFirst(neighbor); // Add at the front for 0 weights (0-1 BFS)
//                     } else {
//                         nodeQueue.addLast(neighbor); // Add at the end for non-zero weights
//                     }
//                 }
//             }
//         }

//         return Integer.MAX_VALUE;
//     }

//     public static List<Node> getNodes(int[][] matrix) {
//         List<Node> allNodes = new LinkedList<>();

//         int rows = matrix.length;
//         int cols = matrix[0].length;

//         for (int row = 0; row < rows; row++) {
//             for (int col = 0; col < cols; col++) {
//                 if (matrix[row][col] == 0) {
//                     Node node = new Node(new int[]{row, col}, new LinkedList<>());
//                     allNodes.add(node);
//                 }
//             }
//         }
//         return allNodes;
//     }

//     public static void connectNodes(int[][] matrix, List<Node> allNodes) {
//         int rows = matrix.length;
//         int cols = matrix[0].length;

//         for (Node node : allNodes) {
//             int row = node.data[0];
//             int col = node.data[1];

//             for (int offset = 1; offset < cols; offset++) {
//                 // Check left
//                 int targetCol = col - offset;
//                 if (targetCol >= 0 && matrix[row][targetCol] == 0) {
//                     Node targetNode = fetchNode(targetCol, row, allNodes);
//                     if (targetNode != null) {
//                         node.children.add(new Edge(targetNode, offset));
//                         break;
//                     }
//                 }
//             }

//             for (int offset = 1; offset < cols; offset++) {
//                 // Check right
//                 int targetCol = col + offset;
//                 if (targetCol < cols && matrix[row][targetCol] == 0) {
//                     Node targetNode = fetchNode(targetCol, row, allNodes);
//                     if (targetNode != null) {
//                         node.children.add(new Edge(targetNode, offset));
//                         break;
//                     }
//                 }
//             }

//             for (int offset = 1; offset < rows; offset++) {
//                 // Connect vertically upwards
//                 int targetRow = row - offset;
//                 if (targetRow >= 0 && matrix[targetRow][col] == 0) {
//                     Node targetNode = fetchNode(col, targetRow, allNodes);
//                     if (targetNode != null) {
//                         node.children.add(new Edge(targetNode, 0));
//                         break;
//                     }
//                 }
//             }
//         }
//     }

//     public static Node fetchNode(int x, int y, List<Node> allNodes) {
//         for (Node node : allNodes) {
//             if (node.data[0] == y && node.data[1] == x) {
//                 return node;
//             }
//         }
//         return null;
//     }

//     public static int findMin(int[][] matrix) {
//         List<Node> allNodes = getNodes(matrix);
//         connectNodes(matrix, allNodes);

//         int rows = matrix.length;
//         int cols = matrix[0].length;

//         int min = Integer.MAX_VALUE;

//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < rows; j++) {
//                 Node startNode = fetchNode(cols - 1, i, allNodes);
//                 Node endNode = fetchNode(0, j, allNodes);
//                 if (startNode != null && endNode != null) {
//                     int temp = bfs_minpath(startNode, endNode, allNodes);
//                     min = Math.min(temp, min);
//                 }
//             }
//         }

//         return min;
//     }

//     public static void main(String[] args) {
//         int[][] cars = {
//             {1, 0, 0, 0},
//             {0, 0, 1, 1},
//             {0, 1, 0, 0},
//             {0, 0, 0, 1}
//         };

//         int min = findMin(cars);
//         System.out.println("Min: " + min);
//     }
// }
