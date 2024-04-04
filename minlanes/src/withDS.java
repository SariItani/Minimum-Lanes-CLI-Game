import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Node
{
    public boolean isLeaf;
    public List<Integer> coords;
    public int commulativeWeight;
    public Node left;
    public Node right;
    public Node up;

    public Node ()
    {
        isLeaf = false;
        coords = new ArrayList<>(2);
        commulativeWeight = Integer.MAX_VALUE;
        left = null;
        right = null;
        up = null;
    }

    public Node (boolean _isLeaf, List<Integer> _coords, int _commulativeWeight, Node _left, Node _right, Node _up) 
    {
        isLeaf = _isLeaf;
        coords = _coords;
        commulativeWeight = _commulativeWeight;
        left = _left;
        right = _right;
        up = _up;
    }

    public Node(boolean _isLeaf)
    {
        isLeaf = _isLeaf;
        coords = new ArrayList<>(2);
        commulativeWeight = Integer.MAX_VALUE;
        left = null;
        right = null;
        up = null;
    }

    public Node(List<Integer> _coords)
    {
        isLeaf = false;
        coords = _coords;
        commulativeWeight = Integer.MAX_VALUE;
        left = null;
        right = null;
        up = null;
    }

    public Node(boolean _isLeaf, List<Integer> _coords)
    {
        isLeaf = _isLeaf;
        coords = _coords;
        commulativeWeight = Integer.MAX_VALUE;
        left = null;
        right = null;
        up = null;
    }
};

public class withDS
{   
    public HashMap<List<Integer>, Node> getGraph(int[][] matrix)
    {
        HashMap<List<Integer>, Node> coordsToNode = new HashMap<>();
        List<Integer> coords;
    
        for (int col = 0; col < matrix[0].length; col++)
        {
            if (matrix[0][col] == 0)
            {
                coords = new ArrayList<>(2);
                coords.add(col); // x
                coords.add(0);   // y
                // coords (x=col, y=0)
                Node endNode = new Node(true, coords);
                coordsToNode.put(coords, endNode);
            }
            if (matrix[matrix.length - 1][col] == 0)
            {
                coords = new ArrayList<>(2);
                coords.add(col);               // x
                coords.add(matrix.length - 1); // y
                // coords (x=col, y=max)
                Node startNode = new Node(coords);
                coordsToNode.put(coords, startNode);
            }       
        }

        for (int row = matrix.length - 1; row >= 0; row--)
        {
            for (int col = 0; col < matrix[0].length; col++)
            {
                // this is not optimised since we can directly connect 2 vertical nodes no matter
                // how long the distance is between them with weight = 0
                // but instead, what is happening here is that we're setting each and every single
                // empty cell in the matrix to a traversable node.
                // this allows us to use 0-1 BFS - like algorithm
                coords = new ArrayList<>(2);
                coords.add(col);
                coords.add(row);
                if (matrix[row][col] == 0)
                {
                    Node newNode;
                    if (!coordsToNode.containsKey(coords))
                    {
                        newNode = new Node(coords);
                        // coords (x=col, y=row)
                        coordsToNode.put(coords, newNode);
                    }
                    else
                        newNode = coordsToNode.get(coords);
                    
                    // add upper node connection
                    if (row > 0 && matrix[row-1][col] == 0)
                    {
                        coords = new ArrayList<>(2);
                        coords.add(col);
                        coords.add(row-1);
                        // coords (x=col, y=row-1)
                        Node upNode;
                        if (coordsToNode.containsKey(coords))
                            upNode = coordsToNode.get(coords);
                        else
                        {
                            upNode = new Node(coords); // if row = matrixlength-1 or 0 then node exists
                            coordsToNode.put(coords, upNode);
                        }
                        newNode.up = upNode; 
                    }

                    //add left node connection
                    if (col > 0 && matrix[row][col-1] == 0)
                    {
                        coords = new ArrayList<>(2);
                        coords.add(col-1);
                        coords.add(row);
                        // coords (x=col-1, y=row)
                        Node leftNode;
                        if (coordsToNode.containsKey(coords))
                            leftNode = coordsToNode.get(coords);
                        else
                        {
                            leftNode = new Node(coords); // if row = matrixlength-1 or 0 then node exists
                            coordsToNode.put(coords, leftNode);
                        }
                        newNode.left = leftNode;
                    }

                    //add right node connection
                    if (col < matrix[0].length - 1 && matrix[row][col+1] == 0)
                    {
                        coords = new ArrayList<>(2);
                        coords.add(col+1);
                        coords.add(row);
                        // coords (x=col+1, y=row)
                        Node rightNode;
                        if (coordsToNode.containsKey(coords))
                            rightNode = coordsToNode.get(coords);
                        else
                        {
                            rightNode = new Node(coords); // if row = matrixlength-1 or 0 then node exists
                            coordsToNode.put(coords, rightNode);
                        }
                        newNode.right = rightNode;
                    }
                }
            }
        }
        return coordsToNode;
    }

    // now we need a method that would be called during each iteration of
    // our SSSP algorithm to connect the graph accordingly and distribute the commulative weights
    // however it would just be better to distribute weights and traverse the graph at the same time
    // much like what dijkstra's algorithm does with weight relaxation and adj list / matrix.
    // but i will implement 0-1 BFS since i can due to the way i designed my classes
    // so i dont really need to keep track of the commulative weight, but i will keep
    // it in the class definition perhaps ill somehow need it later
    public int findWeights(int[][] matrix, HashMap<List<Integer>, Node> graph, Node startNode, Node leafNode) 
    {
        Deque<Node> deque = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();

        startNode.commulativeWeight = 0;
        deque.offerLast(startNode);

        while (!deque.isEmpty()) 
        {
            Node current = deque.pollFirst();
            visited.add(current);

            if (current.coords.equals(leafNode.coords))
            {
                drawMatrixWithWeights(matrix, graph, startNode, leafNode);
                System.out.println("Current Minimum Distance is: " + current.commulativeWeight);
                return current.commulativeWeight;
            }

            processNeighbor(current.up, current, deque, visited);
            processNeighbor(current.left, current, deque, visited);
            processNeighbor(current.right, current, deque, visited);
        }

        drawMatrixWithWeights(matrix, graph, startNode, leafNode);
        System.out.println("No Path Found");
        return Integer.MAX_VALUE;
    }

    private void processNeighbor(Node neighbor, Node current, Deque<Node> deque, Set<Node> visited) 
    {
        if (neighbor != null && !visited.contains(neighbor)) 
        {
            int newWeight = current.commulativeWeight + ((neighbor == current.up) ? 0 : 1);

            // If the new weight is less than the neighbor's current weight, update and add to deque
            if (newWeight < neighbor.commulativeWeight) 
            {
                neighbor.commulativeWeight = newWeight;
                deque.offerLast(neighbor);
            }
            else
                deque.offerLast(neighbor);
        }
    }
    
    public void drawMatrixWithWeights(int[][] matrix, HashMap<List<Integer>, Node> graph, Node startNode, Node leafNode)
    {
        System.out.println("Matrix with Cumulative Weights:");

        List<Integer> coords;
        Node currentNode;
        for (int row = 0; row < matrix.length; row++)
        {
            for (int col = 0; col < matrix[0].length; col++)
            {
                coords = new ArrayList<>(2);
                coords.add(col);
                coords.add(row);  
                if (graph.containsKey(coords))
                {
                    currentNode = graph.get(coords);
                    if (currentNode.coords.equals(startNode.coords))
                        System.out.print("S\t");
                    else if (currentNode.coords.equals(leafNode.coords))
                        System.out.print("E\t");
                    else if (currentNode.commulativeWeight == Integer.MAX_VALUE)
                        System.out.print("-1\t");
                    else
                        System.out.print(currentNode.commulativeWeight + "\t");
                }
                else
                System.out.print("X\t");
            }
            System.out.println();
        }
    }

    public int findMin(int[][] matrix)
    {
        HashMap<List<Integer>, Node> graph = getGraph(matrix);

        int min = Integer.MAX_VALUE;

        List<Integer> startCoords, endCoords;
        Node startNode, endNode;

        for (int col = 0; col < matrix[0].length; col++)
        {
            for (int col2 = 0; col2 < matrix[0].length; col2++)
            {
                startCoords = new ArrayList<>(2);
                endCoords = new ArrayList<>(2);
                startCoords.add(col);
                startCoords.add(matrix.length - 1);
                endCoords.add(col2);
                endCoords.add(0);

                if (graph.containsKey(startCoords) && graph.containsKey(endCoords))
                {
                    System.out.println("Start Node: " + startCoords);
                    System.out.println("End Node: " + endCoords);
                    startNode = graph.get(startCoords);
                    endNode = graph.get(endCoords);
                    int currentMin = findWeights(matrix, graph, startNode, endNode);
                    min = (currentMin < min) ? currentMin : min;
                }
            }
        }

        return min;
    }

    public static void main(String[] args) 
    {
        System.out.println();

        int[][] matrix = {
            {0, 0, 0, 1},
            {1, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0}
        };

        withDS graphProcessor = new withDS();

        int minDistance = graphProcessor.findMin(matrix);
        if (minDistance != -1)
            System.out.println("\nThe overall minimum distance is: " + minDistance);
        else
            System.out.println("\nNo path found to any leaf node.");

        System.out.println();

        int[][] matrix2 = {
            {0, 1, 0, 1},
            {0, 0, 0, 0},
            {0, 0, 1, 0},
            {1, 0, 0, 1}
        };

        graphProcessor = new withDS();

        minDistance = graphProcessor.findMin(matrix2);
        if (minDistance != -1)
            System.out.println("\nThe overall minimum distance is: " + minDistance);
        else
            System.out.println("\nNo path found to any leaf node.");
        
        System.out.println();

        int[][] matrix3 ={
            {1,1,0,0,1,0,0,1},
            {0,0,1,0,0,0,1,0},
            {0,0,0,0,1,0,0,1},
            {0,1,0,0,0,1,0,0},
            {1,0,0,1,0,0,1,0},
            {0,0,1,0,0,0,0,0},
            {0,1,0,0,1,1,0,1},
            {0,0,0,1,0,0,0,0},
            {1,0,1,0,1,0,1,0}
        };

        graphProcessor = new withDS();

        minDistance = graphProcessor.findMin(matrix3);
        if (minDistance != -1)
            System.out.println("\nThe overall minimum distance is: " + minDistance);
        else
            System.out.println("\nNo path found to any leaf node.");
    }
}