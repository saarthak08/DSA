package java.trie;

import in.sg.dsa.trie.utils.TrieNode;
import java.util.Scanner;

/**
 * Implementation of a Trie data structure for storing and searching strings.
 *
 * <p>A Trie, also known as a prefix tree, is a tree-like data structure that stores a dynamic set
 * of strings. Each node in the Trie represents a single character of a string. A path from the root
 * to a node represents a prefix, and if the node is marked as an end-of-word node, it represents a
 * complete word.
 *
 * <p>This implementation is for lowercase English alphabet characters ('a' through 'z').
 *
 * <p>Time Complexity: Let L be the length of the key (string). - Insertion: O(L) - Search: O(L) -
 * Starts With (prefix search): O(L)
 *
 * <p>Space Complexity: O(ALPHABET_SIZE * L * N) where N is the number of keys in the trie. In the
 * worst case (no shared prefixes), space is the sum of the lengths of all words.
 */
public class Trie {

  private final TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  /**
   * Inserts a word into the trie.
   *
   * @param word The word to insert.
   */
  public void insert(String word) {
    TrieNode current = root;
    for (char ch : word.toLowerCase().toCharArray()) {
      if (!current.containsKey(ch)) {
        current.put(ch, new TrieNode());
      }
      current = current.get(ch);
    }
    current.setEndOfWord(true);
  }

  /**
   * Searches for a word in the trie.
   *
   * @param word The word to search for.
   * @return true if the word is in the trie, false otherwise.
   */
  public boolean search(String word) {
    TrieNode node = searchPrefix(word);
    return node != null && node.isEndOfWord();
  }

  /**
   * Checks if there is any word in the trie that starts with the given prefix.
   *
   * @param prefix The prefix to search for.
   * @return true if there is any word with the given prefix, false otherwise.
   */
  public boolean startsWith(String prefix) {
    return searchPrefix(prefix) != null;
  }

  // Helper method to search for a prefix or a whole word.
  private TrieNode searchPrefix(String word) {
    TrieNode current = root;
    for (char ch : word.toLowerCase().toCharArray()) {
      if (current.containsKey(ch)) {
        current = current.get(ch);
      } else {
        return null;
      }
    }
    return current;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Trie trie = new Trie();
    int decision = 0;

    while (decision != -1) {
      System.out.println("\n1. Insert string");
      System.out.println("2. Search for a full word");
      System.out.println("3. Check for a prefix");
      System.out.println("-1. Stop");
      System.out.print("Enter your choice: ");

      decision = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (decision) {
        case 1:
          System.out.print("Enter the string to insert: ");
          String insertInput = scanner.nextLine();
          trie.insert(insertInput);
          System.out.println("'" + insertInput + "' inserted.");
          break;
        case 2:
          System.out.print("Enter the string to search for: ");
          String searchInput = scanner.nextLine();
          if (trie.search(searchInput)) {
            System.out.println("The string '" + searchInput + "' exists in the Trie.");
          } else {
            System.out.println("The string '" + searchInput + "' does not exist in the Trie.");
          }
          break;
        case 3:
          System.out.print("Enter the prefix to check: ");
          String prefixInput = scanner.nextLine();
          if (trie.startsWith(prefixInput)) {
            System.out.println("Found words starting with the prefix '" + prefixInput + "'.");
          } else {
            System.out.println("No words found with the prefix '" + prefixInput + "'.");
          }
          break;
        case -1:
          System.out.println("Exiting...");
          break;
        default:
          System.out.println("Wrong input! Please try again.");
      }
    }
    scanner.close();
  }
}
