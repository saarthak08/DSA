package in.sg.dsa.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Implements the Huffman coding algorithm for data compression.
 *
 * <p>Huffman coding is a lossless data compression algorithm that assigns variable-length codes to
 * input characters, lengths of the assigned codes are based on the frequencies of corresponding
 * characters. The most frequent character gets the smallest code and the least frequent character
 * gets the largest code.
 *
 * <p>Algorithm Steps: 1. Calculate the frequency of each character in the input string. 2. Create a
 * leaf node for each unique character and build a min-heap (PriorityQueue) of all leaf nodes. The
 * priority is determined by the frequency of the character. 3. Repeatedly extract the two nodes
 * with the minimum frequency from the heap. 4. Create a new internal node with a frequency equal to
 * the sum of the two nodes' frequencies. Make the first extracted node its left child and the
 * second extracted node its right child. 5. Add this new node back to the min-heap. 6. Repeat steps
 * 3-5 until the heap contains only one node, which is the root of the Huffman Tree. 7. Traverse the
 * tree from the root to generate the prefix codes for each character. (0 for left, 1 for right).
 *
 * <p>Time Complexity: O(n log n), where n is the number of unique characters. - Building the
 * frequency map: O(L) where L is the length of the input string. - Building the priority queue: O(n
 * log n) - Building the tree: O(n log n) as each extraction and insertion takes O(log n). -
 * Generating codes: O(n) to traverse the final tree.
 *
 * <p>Space Complexity: O(n) for the frequency map, priority queue, and the Huffman tree itself.
 */
public class HuffmanTree {

  private Node root;
  private final String text;
  private final Map<Character, String> huffmanCodes;

  // Inner node class
  private static class Node implements Comparable<Node> {
    char character;
    int frequency;
    Node left;
    Node right;

    Node(char character, int frequency, Node left, Node right) {
      this.character = character;
      this.frequency = frequency;
      this.left = left;
      this.right = right;
    }

    // A leaf node has a character, an internal node does not.
    boolean isLeaf() {
      return this.left == null && this.right == null;
    }

    @Override
    public int compareTo(Node other) {
      return this.frequency - other.frequency;
    }
  }

  public HuffmanTree(String text) {
    this.text = text;
    this.huffmanCodes = new HashMap<>();
    buildTree();
  }

  private void buildTree() {
    // 1. Calculate frequencies
    Map<Character, Integer> freqMap = new HashMap<>();
    for (char character : text.toCharArray()) {
      freqMap.put(character, freqMap.getOrDefault(character, 0) + 1);
    }

    // 2. Initialize the priority queue with leaf nodes
    PriorityQueue<Node> pq = new PriorityQueue<>();
    for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
      pq.add(new Node(entry.getKey(), entry.getValue(), null, null));
    }

    // 3. Build the tree
    while (pq.size() > 1) {
      Node left = pq.poll();
      Node right = pq.poll();
      // Internal node has a dummy character and sum of frequencies
      Node parent = new Node('\0', left.frequency + right.frequency, left, right);
      pq.add(parent);
    }

    this.root = pq.poll();
    // 4. Generate codes
    generateCodes(root, "");
  }

  // 5. Recursive helper to generate codes
  private void generateCodes(Node node, String code) {
    if (node == null) {
      return;
    }
    // If it's a leaf node, it's one of our original characters
    if (node.isLeaf()) {
      huffmanCodes.put(node.character, code);
    }
    generateCodes(node.left, code + "0");
    generateCodes(node.right, code + "1");
  }

  public void printCodes() {
    System.out.println("\nHuffman Codes:");
    for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
      System.out.println("'" + entry.getKey() + "': " + entry.getValue());
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the string to be compressed: ");
    String input = sc.nextLine();

    if (input == null || input.isEmpty()) {
      System.out.println("Input string is empty.");
      return;
    }

    HuffmanTree huffmanTree = new HuffmanTree(input);
    huffmanTree.printCodes();

    sc.close();
  }
}
