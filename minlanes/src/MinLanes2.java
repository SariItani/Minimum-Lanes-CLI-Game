// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;
// import java.util.Stack;

// public class MinLanes2 {
//   public static int findMin2(List<List<Integer>> cars) {
//     int minSwitches = Integer.MAX_VALUE;
//     int level = 0;
//     int totalLength = cars.get(0).size();
//     int lastLane = 0;
//     List<Integer> distances = new ArrayList<>();
//     List<List<Boolean>> visited = new ArrayList<>();
//     Stack<int[]> backtrackingStack = new Stack<>();

//     // Initialization
//     for (int i = 0; i < cars.size(); i++) {
//       int temp = 0;
//       while (cars.get(i).get(temp) == 0) {
//         temp++;
//       }
//       distances.add(temp);
//       visited.add(new ArrayList<>(Collections.nCopies(totalLength, false)));
//     }
//     lastLane = distances.indexOf(Collections.max(distances));

//     // Iteration with backtracking
//     while (!backtrackingStack.isEmpty() || level < totalLength) {
//       if (!backtrackingStack.isEmpty()) { // Backtrack if necessary
//         int[] backtrackState = backtrackingStack.pop();
//         level = backtrackState[0];
//         lastLane = backtrackState[1];
//         visited.get(lastLane).set(level, false); // Unmark visited
//       }

//       int maxDistance = 0, nextLane = 0;
//       for (int lane = Math.max(0, lastLane - 1); lane <= Math.min(cars.size() - 1, lastLane + 1); lane++) {
//         if (!visited.get(lane).get(level) && distances.get(lane) > maxDistance) {
//           maxDistance = distances.get(lane);
//           nextLane = lane;
//         }
//       }

//       if (maxDistance > 0) { // Not stuck
//         level += maxDistance;
//         if (lastLane != nextLane) {
//           minSwitches = Math.min(minSwitches, minSwitches + 1); // Update min with backtracking
//         }
//         lastLane = nextLane;
//         visited.get(lastLane).set(level, true);
//         backtrackingStack.push(new int[]{level, lastLane});
//       } else if (level == totalLength - 1) { // Reached end with backtracking
//         minSwitches = Math.min(minSwitches, minSwitches);
//       }
//     }

//     return minSwitches == Integer.MAX_VALUE ? -1 : minSwitches;
//   }

//   private static List<List<Integer>> generateTestCase(int length, int density) {
//     int numLanes = (int) (Math.random() * 3 + 2);
//     List<List<Integer>> lanes = new ArrayList<>();
//     for (int i = 0; i < numLanes; i++) {
//       List<Integer> lane = new ArrayList<>();
//       for (int j = 0; j < length; j++) {
//         lane.add(Math.random() < (density / 10.0) ? 1 : 0);
//       }
//       lanes.add(lane);
//     }
//     return lanes;
//   }
   

//   public static void main(String[] args)
//   {
//     List<List<List<Integer>>> testCases = new ArrayList<>();
//     for (int i = 0; i < 10; i++) {
//       int length = (int) (Math.random() * 10 + 5);
//       int density = (int) (Math.random() * 5);
//       List<List<Integer>> testCase = generateTestCase(length, density);
//       testCases.add(testCase);
//     }
   
//     for (List<List<Integer>> cars : testCases)
//     {
//       System.out.println("Test Case: " + cars);
//       long start = System.currentTimeMillis();
//       int min = findMin2(cars);
//       long end = System.currentTimeMillis();
//       System.out.println("Minimum Lane Switches: " + min + "\nFound in " + (end - start) + "ms.");
//     }
//   }
// }
