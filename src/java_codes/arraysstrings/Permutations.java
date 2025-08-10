package java_codes.arraysstrings;

// Comments in permutations.cpp file.

// Time Complexity: O(n*n!);

// String x="abc";

// substring(int initial index, int last index); //last index is one increased.

//	x.substring(0,0)="";
//  x.substring(1)="bc";

// Permutations of a string are calculated by removing a character from the main string & adding it
// to prefix string & repeating this step again & again.

// Time Complexity of this algorithm is O(n*n!);

/*
		Input String: "abc";
		Recursion Tree:

									 "abc"
					___________________|____________________
				   |     		       |					|
			  ("bc","a")     	  ("ac","b")			("ab","c")
		   _____|_____            ____|_____			____|_______
		  |			  |          |          |		   |			|
	 ("ab","c")   ("b","ac")  ("c","ba")  ("a","bc") ("b","ca")    ("a","cb")
		  |			  |			 |			|            |             |
	 ("","abc")	  ("","acb")  ("","bac")   ("","bca") ("","cab")	("","cba")



substr(index,length);
string x="abc";
x.substr(0,0)="";
x.substr(1,x.length())="bc;

*/

public class Permutations {

  public static void main(String[] args) {
    String x = "abc";
    perm(x, "");
  }

  static void perm(String str, String prefix) {
    if (str.isEmpty()) {
      System.out.println(prefix);
    } else {
      for (int i = 0; i < str.length(); i++) {
        String rem = str.substring(0, i) + str.substring(i + 1);
        perm(rem, prefix + str.charAt(i));
      }
    }
  }
}
