class TrieNode {
  public TrieNode[] nodes;
  public boolean isEndOfWord = false;

  public TrieNode() {
    nodes = new TrieNode[26];
    for (int i = 0; i < 26; i++) {
      nodes[i] = null;
    }
  }
}

class Trie {

  private final TrieNode root;

  /**
   * Initialize your data structure here.
   */
  public Trie() {
    root = new TrieNode();
  }

  /**
   * Inserts a word into the trie.
   */
  public void insert(String word) {
    TrieNode node = root;
    for (int i = 0; i < word.length(); i++) {
      int c = word.charAt(i) - 'a';
      if (node.nodes[c] == null) {
        node.nodes[c] = new TrieNode();
      }
      node = node.nodes[c];
    }
    node.isEndOfWord = true;
  }

  /**
   * Returns if the word is in the trie.
   */
  public boolean search(String word) {
    TrieNode x = root;
    for (int i = 0; i < word.length(); i++) {
      int c = word.charAt(i) - 'a';
      if (x.nodes[c] == null) {
        return false;
      }
      x = x.nodes[c];
    }
    return x.isEndOfWord;
  }

  /**
   * Returns if there is any word in the trie that starts with the given prefix.
   */
  public boolean startsWith(String prefix) {
    TrieNode x = root;
    for (int i = 0; i < prefix.length(); i++) {
      int c = prefix.charAt(i) - 'a';
      if (x.nodes[c] == null) {
        return false;
      }
      x = x.nodes[c];
    }
    return true;
  }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */