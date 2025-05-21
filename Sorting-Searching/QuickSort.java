import java.util.Scanner;

/*
 * The idea behind Quick Sort is that we have to find an element which is called pivot and ensure that all the elements
 * before the pivot are smaller than it and all the elements after it are greater than it. This is done recursively.
 * So, what we do is we will have a pivotal element. Pivotal element and pivot are two separate things.
 * Basic function is a parition function which finds a pivot and then we divide the array against pivot and then again find the
 * pivot recursively.
 * So in the partition function, we will start with a pivotal element
 * and take the first element of array as pivotal element. Now, we will go from
 * right to left checking whether any element is greater than pivot and same way we will go from left to right checking whether
 * any element is less than pivot. If the left element index is less than right index, then we will swap those elements.
 * And then again we will continue going from right to left and simultaneously left to right. Now, we will again find
 * two right and left indices. Now, in this case if left index is less than right index, swap the elements if not, then we
 * can be ensured that the all the right elements are bigger and all the left elements are smaller. Now we will swap the right index
 * with the pivotal element and send the index back from the paritition function. This will be the index of the pivot.
 */
public class QuickSort {
  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
    sort(0, n - 1, arr);

    for (int i : arr) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  private static void sort(int l, int r, int[] arr) {
    if (l < r) {
      int pivot = partition(l, r, arr);
      sort(l, pivot - 1, arr);
      sort(pivot + 1, r, arr);
    }
  }

  private static int partition(int l, int r, int[] arr) {
    int pivotalElement = arr[l];
    int i = l;
    int j = r;
    while (i < j) {
      while (i <= r && arr[i] <= pivotalElement) {
        i++;
      }
      while (j >= l && arr[j] > pivotalElement) {
        j--;
      }
      if (i < j) {
        int temp = arr[i];
        arr[j] = arr[i];
        arr[i] = temp;
      }
    }
    int temp = arr[j];
    arr[j] = pivotalElement;
    arr[l] = temp;
    return j;
  }
}
