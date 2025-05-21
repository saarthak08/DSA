import java.util.Scanner;

// Time Complexity: O(n*(2^n)).
// Approach Bit Masking. 
// 1<<j this is a left shift bitwise operator. It will shift bits of 1 to left by j. For eg: 1<<3=> 1000=>8. 
// 1<<j also means multiplying 1 with 2^j.
// 1>>j means dividing 1 with 2^j.
// Then, this value will get AND with the bits of i.
// If there is a 1 at same positions in the bits of i as well as (1<<j), then that bit will remain 1 after AND operation.
// Then, the result will be greater than 1.
// This is called bit setting/masking.

public class PowerSet {

  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    int n;
    System.out.println("Enter the number of elements:");
    n = sc.nextInt();
    char[] arr = new char[n];
    System.out.println("Enter the elements: ");
    //Taking input.
    for (int i = 0; i < n; i++) {
      arr[i] = sc.next().charAt(0);
    }
    printPowerSet(arr, n);
  }

  private static void printPowerSet(char[] arr, int n) {
    int count = (int) Math.pow(2, n);
    // For each no in the power set i.e. in for each no: i in 2^n elements.
    for (int i = 0; i < count; i++) {
      // For each number j.
      for (int j = 0; j < n; j++) {
        // setting the bits.
        if ((i & (1 << j)) > 0) {
          System.out.print(arr[j]);
        }
      }
      System.out.println();
    }
  }
}