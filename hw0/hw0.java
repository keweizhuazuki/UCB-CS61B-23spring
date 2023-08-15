/** cretive exercise 1A

package Homework;

public class hw0 {
    public static void main(String[] args) {
        int row = 0;
        int size = 5;

        while (row < size){
            int col = 0;
            while (col <= row){
                System.out.print('*');
                col = col + 1;
            }
            System.out.println();
            row = row + 1;
        }
}
}

Creative Exercise 1b

public class ClassNameHere {
   public static void drawTriangle(int N){
        int row = 0;

        while (row < N){
            int col = 0;
            while (col <= row){
                System.out.print('*');
                col = col + 1;
            }
            System.out.println();
            row = row + 1;
        }
   }
   public static void main(String[] args) {
      drawTriangle(10);
      
   }
}

Exercise 2

public class ClassNameHere {
   public static int max(int[] m) {
      int x = 0;
      int maxVal = m[0];
      while (x < m.length){
        if (m[x] > maxVal){
          maxVal = m[x];
          }
        x++;
        }
      return maxVal;
   }
   public static void main(String[] args) {
      int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
      System.out.println(max(numbers));
   }
}

Exercise 3
public class ClassNameHere {
   public static int max(int[] m) {
      int maxVal = m[0];
         for (int x = 0; x < m.length; x++){
         if (m[x] > maxVal){
             maxVal = m[x];
         }
          
      }
      return maxVal;
         
   }
   public static void main(String[] args) {
      int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
      System.out.println(max(numbers));
   }
}

Exercise 4

public class BreakContinue {
  public static void windowPosSum(int[] a, int n) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] < 0) { // If the current value is negative, skip to the next iteration
        continue;
      }
      
      // Inner loop to add next n positive values
      for (int j = 1; j <= n; j++) {
        if (i + j >= a.length) { // Break if we have reached the end of the array
          break;
        }
        a[i] += a[i + j]; // Add the next value to the current one
      }
    }
  }

  public static void main(String[] args) {
    int[] a = {1, 2, -3, 4, 5, 4};
    int n = 3;
    windowPosSum(a, n);

    // Should print 4, 8, -3, 13, 9, 4
    System.out.println(java.util.Arrays.toString(a));
  }
}
 */