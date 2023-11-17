import java.util.*;

public class BubbleSort {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Integer numberOfInputs;
        Integer[] inputArray;
        numberOfInputs = sc.nextInt();
        inputArray = new Integer[numberOfInputs];
        for (int i = 0; i < numberOfInputs; i++) {
            inputArray[i] = sc.nextInt();
        }
        for (int i = 0; i < numberOfInputs; i++) {
            for (int j = 0; j < numberOfInputs - i - 1; j++) {
                if (inputArray[j] > inputArray[j + 1]) {
                    // inputArray[j] = inputArray[j] + inputArray[j + 1];
                    // inputArray[j + 1] = inputArray[j] - inputArray[j + 1];
                    // System.out.println(inputArray[j] + " hello");
                    System.out.println((inputArray[j] + inputArray[j + 1]) / 2);
                    // inputArray[j] = (inputArray[j] - inputArray[j + 1]) / 2;
                    // inputArray[j + 1] = (inputArray[j] + inputArray[j + 1]) / 2;
                    int x = inputArray[j + 1];
                    inputArray[j + 1] = inputArray[j];
                    inputArray[j] = x;
                }
            }
        }
        for (int i = 0; i < numberOfInputs; i++) {
            System.out.println(inputArray[i]);
        }

    }
}
