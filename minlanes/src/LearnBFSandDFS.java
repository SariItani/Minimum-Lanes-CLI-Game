// import java.util.List;
// import java.util.Queue;
// import java.util.Stack;
// import java.util.LinkedList;

// class Node {
//     public int data;
//     public List<Node> children;
//     public boolean visited = false;
    
//     public Node() {}

//     public Node(int _data, List<Node> _children)
//     {
//         data = _data;
//         children = _children;
//     }
// };

// public class LearnBFSandDFS
// {
//     public static void BFS(Node node)
//     {
//         Queue<Node> queue = new LinkedList<>();

//         queue.offer(node);
//         node.visited = true;
        
//         while (!queue.isEmpty())
//         {
//             int size = queue.size();
//             for(int i = 0; i < size; i++)
//             {
//                 Node current = queue.poll();
//                 System.out.println(current.data);
//                 if (current.children != null)
//                 {
//                     for (Node child: current.children)
//                     {
//                         if (!child.visited)
//                         {
//                             queue.offer(child);
//                             child.visited=true;
//                         }
//                     }
//                 }
//             }
//         }
//     }

//     public static void DFS(Node node)
//     {
//         Stack<Node> stack = new Stack<>();

//         stack.push(node);
        
//         while (!stack.isEmpty())
//         {
//             Node current = stack.pop();
//             if (!current.visited)
//             {
//                 current.visited = true;
//                 System.out.println(current.data);
//             }
//             if (current.children != null)
//             {
//                 for (Node child : current.children)
//                 {
//                     stack.push(child);
//                 }
//             }
//         }
//     }

//     public static void main(String[] args) 
//     {
//         List<Node> allNodes = new LinkedList<Node>();

//         Node node1 = new Node(1, List.of());
//         allNodes.add(node1);
//         Node node2 = new Node(2, List.of());
//         allNodes.add(node2);
//         Node node3 = new Node(3, List.of());
//         allNodes.add(node3);
//         Node node4 = new Node(4, List.of());
//         allNodes.add(node4);
//         Node node5 = new Node(5, List.of());
//         allNodes.add(node5);
//         Node node6 = new Node(6, List.of());
//         allNodes.add(node6);
//         Node node7 = new Node(7, List.of());
//         allNodes.add(node7);
//         Node node8 = new Node(8, List.of());
//         allNodes.add(node8);

//         node1.children = List.of(node3, node4, node2);
//         node2.children = List.of(node4);
//         node3.children = List.of(node7, node5);
//         node4.children = List.of(node6, node5);
//         node5.children = List.of(node8);
//         node6.children = List.of(node8);
//         node7.children = List.of(node8);

//         System.out.println("BFS Order:");
//         BFS(node1);
//         for (Node node : allNodes)
//             node.visited = false;
//         System.out.println("DFS Order:");
//         DFS(node1);
//     }

// }
