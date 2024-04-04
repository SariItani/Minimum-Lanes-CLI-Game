// import java.util.ArrayList;
// import java.util.List;

// public class MinLanes {
//     // it is required, given lanes of cars, to find the best path to take while switching lanes one at a time, if neccessary, with minumum switches.
//     public static int findMin(List<List<Integer>> cars)
//     {
//         int min = 0;
//         int totalLength = cars.get(0).size();
//         int level = 0;
//         int lastLevel = level;
//         List<Integer> distances = new ArrayList<Integer>(cars.size());

//         for (int lane = 0; lane < cars.size(); lane++)
//         {
//             int temp = lastLevel;
//             while (temp < totalLength && cars.get(lane).get(temp) == 0) 
//                 temp++;
//             distances.add(temp);
//         }
//         int max = 0;
//         for (int num : distances)
//             max = (num > max) ? num : max;
        
//         int lastLane = distances.indexOf(max);

//         while (level < totalLength)
//         {
//             int left = 0;
//             int right = cars.size();
//             int iter = lastLane;
//             while (iter >= 0 && cars.get(iter).get(level) != 1)
//             {
//                 iter--;
//                 left = iter;
//             }
//             iter = lastLane;
//             while (iter <= cars.size() && cars.get(iter).get(level) != 1)
//             {
//                 iter++;
//                 right = iter;    
//             }

//             for (int lane = left; lane < right; lane++)
//             {
//                 int temp = lastLevel;
//                 while (temp < totalLength && cars.get(lane).get(temp) == 0) 
//                     temp++;
//                 distances.add(temp);
//             }
//             max = 0;
//             for (int num : distances)
//                 max = (num > max) ? num : max;
//             int tempLane = distances.indexOf(max);

//             level += max;
//             min += Math.abs(tempLane-lastLane);
//             lastLane = tempLane;
//             lastLevel = level;
//         }
//         return min;
//     }

//     private static List<List<Integer>> generateTestCase(int length, int density) {
//         int numLanes = (int) (Math.random() * 3 + 2);
//         List<List<Integer>> lanes = new ArrayList<>();
//         for (int i = 0; i < numLanes; i++) {
//             List<Integer> lane = new ArrayList<>();
//             for (int j = 0; j < length; j++) {
//                 lane.add(Math.random() < (density / 10.0) ? 1 : 0);
//             }
//             lanes.add(lane);
//         }
//         return lanes;
//     }
    

//     public static void main(String[] args)
//     {
//         List<List<List<Integer>>> testCases = new ArrayList<>();
//         for (int i = 0; i < 10; i++) {
//             int length = (int) (Math.random() * 10 + 5);
//             int density = (int) (Math.random() * 5);
//             List<List<Integer>> testCase = generateTestCase(length, density);
//             testCases.add(testCase);
//         }
    
//         for (List<List<Integer>> cars : testCases)
//         {
//             System.out.println("Test Case: " + cars);
//             long start = System.currentTimeMillis();
//             int min = findMin(cars);
//             long end = System.currentTimeMillis();
//             System.out.println("Minimum Lane Switches: " + min + "\nFound in " + (end - start) + "ms.");
//         }
//     }
// }
