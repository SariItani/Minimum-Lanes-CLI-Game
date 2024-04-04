// import java.util.ArrayList;
// import java.util.List;
// import java.util.Stack;

// public class Attempt3 {

//     public static void sort(int[] arr, int l, int r) 
//     {
//         if (l < r)
//         {
//             int m = (l + r) / 2;
//             sort(arr, l, m);
//             sort(arr, m+1, r);
//             merge(arr, l, m, r);
//         }
//     }

//     public static void merge(int[] arr, int l, int m, int r)
//     {
//         int[] leftArr = new int[m-l+1];
//         int[] rightArr = new int[r-m];

//         for (int i = 0; i < (m-l+1); i++)
//             leftArr[i] = arr[i+l];
//         for (int j = 0; j < (r-m); j++)
//             rightArr[j] = arr[j+m+1];
        
//         int i = 0, j = 0, k = l;
//         while (i < (m-l+1) && j < (r-m))
//         {
//             if (leftArr[i] <= rightArr[j])
//             {
//                 arr[k] = leftArr[i];
//                 i++;
//             }
//             else
//             {
//                 arr[k] = rightArr[j];
//                 j++;
//             }
//             k++;
//         }
//         while (i < (m-l+1)) 
//         {
//             arr[k] = leftArr[i];
//             k++;
//         }
//         while (j < (r-m)) 
//         {
//             arr[k] = rightArr[j];
//             k++;   
//         }
//     }


//     public static int findMin3(List<List<Integer>> cars) 
//     {
//         Stack<int[]> checkpoints = new Stack<>();
//         List<Integer> bestLanes = new ArrayList<>();
//         int left = 0;
//         int right = cars.size() - 1;
//         int elevation = 0;
//         int[] tempPlusPlus = new int[cars.size()];

//         while (elevation < cars.get(0).size())
//         {
//             for (int lane = left; lane <= right; lane++)
//             {
//                 int tempIncrease = elevation;
//                 while (tempIncrease < cars.get(lane).size() && cars.get(lane).get(tempIncrease) != 1)
//                     tempIncrease++; // could be pushed into a pQueue for simplicity of best case selection
//                 tempPlusPlus[lane] = tempIncrease;
//             }
//             int[] laneChoice = new int[tempPlusPlus.length];
//             for (int i = 0; i < tempPlusPlus.length; i++)
//                 laneChoice[i] = tempPlusPlus[i];
//             sort(laneChoice, 0, tempPlusPlus.length - 1);
//             // Arrays.sort(laneChoice);
//             for (int num : laneChoice)
//             {
//                 if (num > 0)
//                     bestLanes.add(binarySearch(laneChoice, num));
//             }
//             int[] state = new int[2]; // i should be able to do this iteratively
//             while(!bestLanes.isEmpty())
//             {
//                 state[0] = bestLanes.get(bestLanes.size()-1); // no, i must chose the closest bestLane choice to me, so i care about it being near me, and i also care about it being the best, so for example if im on lane 1 and i have 3 2 2 then i chose lane 0, but if i am on lane 3 and i have 0 2 1 1 2 then i will surely chose lane 4 instead of lane 1
//                 state[1] = cars.get(bestLanes.get(bestLanes.size()-1)).get(tempPlusPlus[bestLanes.get(bestLanes.size()-1)]);
//                 elevation += state[1];
//                 checkpoints.push(state);
//                 // update left
//                 // update right
//                 // condition for pop()
//                 // actually pop()
//                 // somehow go on with the algorithm
//             }
//         }
//         return -1;
//     }

//     private static int binarySearch(int[] arr, int num) 
//     {
//         // arr is assumed to be sorted
//         int l = 0;
//         int r = arr.length - 1;
//         int m;
//         int guess;
//         do
//         {
//             m = (l+r)/2;
//             guess = arr[m];
//             if (guess == num)
//                 return m;
//             else if (guess < num)
//             {
//                 l = m+1;
//             }
//             else
//             {
//                 r = m-1;
//             }
//         } while (l <= r);
//         return -1;
//     }

//     private static List<List<Integer>> generateTestCase(int length, int density) 
//     {
//         int numLanes = (int) (Math.random() * 3 + 2);
//         List<List<Integer>> lanes = new ArrayList<>();
//         for (int i = 0; i < numLanes; i++) 
//         {
//             List<Integer> lane = new ArrayList<>();
//             for (int j = 0; j < length; j++)
//                 lane.add(Math.random() < (density / 10.0) ? 1 : 0);
//             lanes.add(lane);
//         }
//         return lanes;
//     }


//     public static void main(String[] args)
//     {
//         List<List<List<Integer>>> testCases = new ArrayList<>();
//         for (int i = 0; i < 10; i++) 
//         {
//             int length = (int) (Math.random() * 10 + 5);
//             int density = (int) (Math.random() * 5);
//             List<List<Integer>> testCase = generateTestCase(length, density);
//             testCases.add(testCase);
//         }
    
//         for (List<List<Integer>> cars : testCases)
//         {
//             System.out.println("Test Case: " + cars);
//             long start = System.currentTimeMillis();
//             int min = findMin3(cars);
//             long end = System.currentTimeMillis();
//             System.out.println("Minimum Lane Switches: " + min + "\nFound in " + (end - start) + "ms.");
//         }
//     }
// }
