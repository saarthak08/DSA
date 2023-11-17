import java.util.Scanner;

public class QuickSort {
    private static Scanner sc = new Scanner(System.in);

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
