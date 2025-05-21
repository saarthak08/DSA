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

class WordDictionary {
  private final TrieNode root;

  /**
   * Initialize your data structure here.
   */
  public WordDictionary() {
    root = new TrieNode();
  }

  /**
   * Adds a word into the data structure.
   */
  public void addWord(String word) {
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
   * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
   */
  public boolean search(String word) {
    return searchWord(word, root, 0);
  }

  public boolean searchWord(String word, TrieNode x, int i) {
    if (word.charAt(i) == '.') {
      for (int j = 0; j < 26; j++) {
        if (x.nodes[j] != null) {
          if (i == word.length() - 1) {
            if (x.nodes[j].isEndOfWord) {
              return true;
            }
          } else {
            if (searchWord(word, x.nodes[j], i + 1)) {
              return true;
            }
          }
        }
      }
      return false;
    } else {
      int c = word.charAt(i) - 'a';
      if (x.nodes[c] == null) {
        return false;
      }
      x = x.nodes[c];
      if (i == word.length() - 1 && x.isEndOfWord) {
        return true;
      } else if (i == word.length() - 1 && !x.isEndOfWord) {
        return false;
      }
      return searchWord(word, x, i + 1);
    }
  }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */