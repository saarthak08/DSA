import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanTree {
  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Enter the string to be compressed: ");
    String input = sc.nextLine();
    HashMap<Character, Integer> frequenciesMap = buildFrequencyMap(input);
    TreeNode head = buildHuffmanTree(frequenciesMap);
    System.out.println("\nPre-Order Traversal of the Huffman Tree is :");
    preOrderTraversal(head);
    System.out.println("\n\nPrefix Codes of the Characters :");
    printPrefixCodes(head, frequenciesMap);
  }

  private static HashMap<Character, Integer> buildFrequencyMap(String input) {
    HashMap<Character, Integer> frHashMap = new HashMap<>();
    for (int i = 0; i < input.length(); i++) {
      char a = input.charAt(i);
      if (frHashMap.containsKey(a)) {
        int frequency = frHashMap.get(a);
        frHashMap.put(a, ++frequency);
      } else {
        frHashMap.put(a, 1);
      }
    }
    return frHashMap;
  }

  private static TreeNode buildHuffmanTree(HashMap<Character, Integer> frHashMap) {
    PriorityQueue<TreeNode> queue = new PriorityQueue<>(new TreeNodeComparator());
    Iterator<Character> hashMapKeySet = frHashMap.keySet().iterator();
    while (hashMapKeySet.hasNext()) {
      char a = hashMapKeySet.next();
      TreeNode treeNode = new TreeNode();
      treeNode.data = a;
      treeNode.priority = frHashMap.get(a);
      queue.offer(treeNode);
    }
    TreeNode head = null;
    while (!queue.isEmpty()) {
      TreeNode a = queue.poll();
      TreeNode b = queue.poll();
      TreeNode newNode = new TreeNode();
      newNode.priority = a.priority + b.priority;
      newNode.data = '-';
      newNode.leftChild = a;
      newNode.rightChild = b;
      if (queue.isEmpty()) {
        head = newNode;
      } else {
        queue.offer(newNode);
      }
    }
    return head;
  }

  private static void printPrefixCodes(TreeNode head, HashMap<Character, Integer> hashMap) {
    Iterator<Character> kIterator = hashMap.keySet().iterator();
    while (kIterator.hasNext()) {
      Character a = kIterator.next();
      String prefixedCode = getPrefixCodeForCharacter(head, a);
      System.out.print("Prefix code for " + a + ": " + prefixedCode.substring(0, prefixedCode.length() - 1));
      System.out.println();
    }
  }

  private static String getPrefixCodeForCharacter(TreeNode head, Character a) {
    if (head == null) {
      return "";
    }
    if (head.data == a) {
      return "-";
    }
    String x = getPrefixCodeForCharacter(head.leftChild, a);
    String y = getPrefixCodeForCharacter(head.rightChild, a);
    if (x.endsWith("-")) {
      return "0" + x;
    } else {
      return "1" + y;
    }
  }

  private static void preOrderTraversal(TreeNode head) {
    if (head == null) {
      return;
    }
    System.out.print(head.data + "=" + head.priority + "\t");
    preOrderTraversal(head.leftChild);
    preOrderTraversal(head.rightChild);
  }
}

class TreeNodeComparator implements Comparator<TreeNode> {
  @Override
  public int compare(TreeNode a, TreeNode b) {
    return a.priority - b.priority;
  }
}

class TreeNode {
  Character data;
  int priority;
  TreeNode leftChild;
  TreeNode rightChild;
}