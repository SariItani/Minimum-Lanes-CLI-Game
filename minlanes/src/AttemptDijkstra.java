// import java.util.List;
// import java.util.Map;
// import java.util.PriorityQueue;
// import java.util.Comparator;
// import java.util.HashMap;
// import java.util.LinkedList;

// class Edge 
// {
//     public Node child;
//     public int weight;

//     public Edge(Node _child, int _weight) 
//     {
//         child = _child;
//         weight = _weight;
//     }
// }

// class Node 
// {
//     public int[] data = new int[2]; // no longer needed if we want to used a hashmap
//     public List<Edge> children;
//     public boolean visited = false;

//     public Node() {}

//     public Node(int[] _data, List<Edge> _children) 
//     {
//         data = _data;
//         children = _children;
//     }
// }

// public class AttemptDijkstra
// {
//     public static HashMap<String, Node> nodeMap = new HashMap<>();

//     public static int dijkstra_minpath(Node source, Node endpoint, List<Node> graph) 
//     {
//     // Initialize distances with infinity
//     Map<Node, Integer> distances = new HashMap<>();
//     for (Node node : graph)
//         distances.put(node, Integer.MAX_VALUE);

//     // Set distance of source node to 0
//     distances.put(source, 0);

//     // Priority queue to store nodes based on their distances
//     PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
//     priorityQueue.add(source);

//     while (!priorityQueue.isEmpty()) {
//         Node current = priorityQueue.poll();

//         if (current.equals(endpoint)) {
//             // Reached the destination, return the distance
//             return distances.get(current);
//         }

//         // Relax edges (update distances)
//         for (Edge edge : current.children) {
//             Node neighbor = edge.child;
//             int newDistance = distances.get(current) + edge.weight;

//             if (newDistance < distances.get(neighbor)) {
//                 distances.put(neighbor, newDistance);
//                 priorityQueue.add(neighbor);
//             }
//         }
//     }

//     // If no path found
//     return Integer.MAX_VALUE;
// }


//     public static List<Node> getNodes(int[][] matrix) 
//     { // WHY DID IT NOT GET THE ONES AT rows-1 ROW?!
//         List<Node> allNodes = new LinkedList<>();
    
//         int rows = matrix.length;
//         int cols = matrix[0].length;
    
//         for (int col = 0; col < cols; col++) 
//         {
//             if (matrix[0][col] == 0) 
//             {
//                 Node node = new Node(new int[]{0, col}, new LinkedList<>());
//                 String key = 0 + "-" + col;
//                 nodeMap.put(key, node);
//                 allNodes.add(node);
//             }
//             if (matrix[rows - 1][col] == 0)
//             {
//                 Node node = new Node(new int[]{rows - 1, col}, new LinkedList<>());
//                 String key = (rows - 1) + "-" + col;
//                 nodeMap.put(key, node);
//                 allNodes.add(node);
//             }
//         }
    
//         for (int row = 1; row < rows - 1; row++)
//         {
//             for (int col = 0; col < cols; col++) 
//             {
//                 if (matrix[row][col] == 1)
//                 {
//                     if (matrix[row - 1][col] == 0)
//                     {
//                         Node node = new Node(new int[]{row - 1, col}, new LinkedList<>());
//                         String key = (row - 1) + "-" + col;
//                         nodeMap.put(key, node);
//                         allNodes.add(node);
//                     }
//                     if (col > 0 && matrix[row - 1][col - 1] == 0) 
//                     {
//                         Node leftNode = new Node(new int[]{row - 1, col - 1}, new LinkedList<>());
//                         String leftKey = (row - 1) + "-" + (col - 1);
//                         nodeMap.put(leftKey, leftNode);
//                         allNodes.add(leftNode);
//                     }
//                     if (col < cols - 1 && matrix[row - 1][col + 1] == 0) 
//                     {
//                         Node rightNode = new Node(new int[]{row - 1, col + 1}, new LinkedList<>());
//                         String rightKey = (row - 1) + "-" + (col + 1);
//                         nodeMap.put(rightKey, rightNode);
//                         allNodes.add(rightNode);
//                     }
//                 }
//             }
//         }
//         return allNodes;
//     }
    
//     public static void connectNodes(int[][] matrix) 
//     {
//         int rows = matrix.length;
//         int cols = matrix[0].length;

//         for (Map.Entry<String, Node> entry : nodeMap.entrySet()) 
//         {
//             String key = entry.getKey();
//             Node node = nodeMap.get(key);
//             String[] parts = key.split("-");
//             int row = Integer.parseInt(parts[0]);
//             int col = Integer.parseInt(parts[1]);

//             for (int offset = 1; offset < cols; offset++) 
//             {
//                 // Check left
//                 int targetCol = col - offset;
//                 if (targetCol >= 0 && matrix[row][targetCol] == 0) 
//                 {
//                     Node targetNode = fetchNode(row, targetCol);
//                     if (targetNode != null) 
//                     {
//                         node.children.add(new Edge(targetNode, offset));
//                         break;
//                     }
//                 }
//             }
//             for (int offset = 1; offset < cols; offset++) 
//             {
//                 // Check right
//                 int targetCol = col + offset;
//                 if (targetCol < cols && matrix[row][targetCol] == 0) 
//                 {
//                     Node targetNode = fetchNode(row, targetCol);
//                     if (targetNode != null) 
//                     {
//                         node.children.add(new Edge(targetNode, offset));
//                         break;
//                     }
//                 }
//             }
//             for (int offset = 1; offset < rows; offset++) 
//             {
//                 // Connect vertically upwards
//                 int targetRow = row + offset;
//                 if (targetRow < rows && matrix[targetRow][col] == 0) 
//                 {
//                     Node targetNode = fetchNode(targetRow, col);
//                     if (targetNode != null) 
//                     {
//                         node.children.add(new Edge(targetNode, 0));
//                         break;
//                     }
//                 }
//             }
//         }
//     }

//     public static Node fetchNode(int x, int y) 
//     {
//         String key = x + "-" + y;
//         return nodeMap.get(key);
//     }

//     public static int findMin(int[][] matrix)
//     {
//         List<Node> allNodes = getNodes(matrix);
//         connectNodes(matrix);

//         int laneLength = matrix.length;
//         int totalLanes = matrix[0].length;

//         int min = Integer.MAX_VALUE;

//         for (int i = 0; i < totalLanes; i++) 
//         {
//             Node startNode = fetchNode(i, laneLength - 1);
//             for (int j = 0; j < totalLanes; j++) 
//             {
//                 Node endNode = fetchNode(j, totalLanes - 1);
//                 if (startNode != null && endNode != null) 
//                 {
//                     int temp = dijkstra_minpath(startNode, endNode, allNodes);
//                     min = Math.min(temp, min);
//                 }
//             }
//         }

//         return min;
//     }

//     public static void main(String[] args)
//     {
//         int[][] cars = 
//         {                 // cost =     0 0 0 0 => min = 0
//             {1, 0, 0, 0}, // . . . . => . . . .
//             {0, 0, 1, 1}, //         => |0|0|0|0
//             {0, 1, 0, 0}, //         => |0|0|0|0
//             {0, 0, 0, 1}  // . . . . => . . . . 
//         };

//         int min = findMin(cars);
//         System.out.println("Min: " + min);
//     }
// }
