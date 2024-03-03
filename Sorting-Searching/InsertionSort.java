import java.util.Scanner;

/*
 * The basic approach in Insertion Sort is that we start from second element and check that all the elements before
 * it are smaller than it. If not, we will insert the current element before the greater element to ensure every element before
 * current element is smaller. 
 * The only catch here is that if an element is smaller than the current element we don't need to go forward and check the 
 * elements before it since they will be already smaller than the element so we can break the loop there itself.
 * That's why insertion sort can give O(n) complexity.
 *
 */
public class InsertionSort {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        sort(arr, n);

        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void sort(int[] arr, int n) {
        int key = 1;
        while (key < n) {
            int x = key;
            while (x >= 1 && arr[x] < arr[x - 1]) {
                int temp = arr[x];
                arr[x] = arr[x - 1];
                arr[x - 1] = temp;
                x--;
            }
            key++;
        }
    }
}
