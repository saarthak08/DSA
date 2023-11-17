import java.util.Scanner;

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
