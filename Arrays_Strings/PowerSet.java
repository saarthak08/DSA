import java.util.*;

public class PowerSet {

	private static Scanner sc=new Scanner(System.in);

	public static void main(String[] args) {
		String input;
		System.out.println("Enter the string: ");
		input=sc.nextLine();
		ArrayList<String> arr=new ArrayList<String>();
		arr=powerSet(input,0,"",arr);
		System.out.println("\nThe Power Set: ");
		for(String x:arr) {
			System.out.println(x);
		}
		arr.clear();
		arr=powerSet2(input,input.length(),"",arr);
		System.out.println("\nThe Power Set-2: ");
		for(String x:arr) {
			System.out.println(x);
		}
	}

	private static ArrayList<String> powerSet(String input, int index, String curr, ArrayList<String> results) {
		if(index==input.length()) {
			results.add(curr);
			return results;
		}
		results=powerSet(input,index+1,curr+input.charAt(index),results);
		results=powerSet(input,index+1,curr,results);
		return results;
	}

	private static ArrayList<String> powerSet2(String input, int index, String curr, ArrayList<String> results) {
		if(index==0) {
			results.add(curr);
			return results;
		}
		results=powerSet2(input,index-1,curr+input.charAt(index-1),results);
		results=powerSet2(input,index-1,curr,results);
		return results;
	}
}