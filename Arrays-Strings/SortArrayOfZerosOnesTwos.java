import java.util.*;

public class SortArrayOfZerosOnesTwos {

	public static Scanner sc=new Scanner(System.in);

	public static void main(String[] args) {
		int n;
		n=sc.nextInt();
		int[] arr=new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=sc.nextInt();
		} 
		int index=0;
		for(int j=0;j<3;j++) {
			while(index<n&&arr[index]==j) {
				index++;
			}
			for(int i=index;i<n;i++) {
				if(arr[i]==j) {
					if(index!=i) {
						int temp=arr[i];
						arr[i]=arr[index];
						arr[index]=temp;
						index++;
					}
				}
			}
		}
		for(int i=0;i<n;i++) {
			System.out.print(arr[i]+" ");
		} 
	}
}