//Comments in permutations.cpp file.

// Time Complexity: O(n*n!);

// String x="abc";

// substring(int initial index, int last index); //last index is one increased.

//	x.substring(0,0)="";
//  x.substring(1)="bc";

public class permutations {

  public static void main(String[] args) {
    String x = "abc";
    perm(x, "");
  }

  static void perm(String str, String prefix) {
    if (str.length() == 0) {
      System.out.println(prefix);
    } else {
      for (int i = 0; i < str.length(); i++) {
        String rem = str.substring(0, i) +
          str.substring(i + 1);
        //System.out.println("Rem: " +rem);
        //System.out.println("Prefix: " +prefix+str.charAt(i));

        perm(rem, prefix + str.charAt(i));
      }
    }
  }
}
