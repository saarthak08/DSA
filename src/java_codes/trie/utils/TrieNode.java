package java_codes.trie.utils;

/**
 * Represents a node in the Trie data structure. Each node contains an array of children nodes and a
 * flag to indicate the end of a word.
 */
public class TrieNode {

  private final TrieNode[] children;
  private boolean isEndOfWord;
  private static final int ALPHABET_SIZE = 26;

  public TrieNode() {
    this.children = new TrieNode[ALPHABET_SIZE];
    this.isEndOfWord = false;
  }

  public boolean containsKey(char ch) {
    return children[ch - 'a'] != null;
  }

  public TrieNode get(char ch) {
    return children[ch - 'a'];
  }

  public void put(char ch, TrieNode node) {
    children[ch - 'a'] = node;
  }

  public void setEndOfWord(boolean isEndOfWord) {
    this.isEndOfWord = isEndOfWord;
  }

  public boolean isEndOfWord() {
    return isEndOfWord;
  }
}
