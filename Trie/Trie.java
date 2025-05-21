import java.util.Scanner;

public class Trie {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int decision = 0;
    TrieNode head = new TrieNode();
    while (decision != -1) {
      System.out.println("Enter 1 to insert string to Trie\nEnter 2 to check string in Trie\nEnter -1 to stop");
      decision = scanner.nextInt();
      scanner.nextLine();
      if (decision == 1) {
        System.out.println("Enter the string to add to Trie:");
        String input = scanner.nextLine();
        addStringToTrie(head, input);
      } else if (decision == 2) {
        System.out.println("Enter the string which you need to check in Trie:");
        String input = scanner.nextLine();
        checkStringExistsInTrie(head, input);
      } else if (decision != -1) {
        System.out.println("Wrong input! Please try again");
      }
    }
  }

  private static void addStringToTrie(TrieNode head, String input) {
    for (int i = 0; i < input.length(); i++) {
      int ascii = getAsciiValueOfCharacter(input.charAt(i));
      head.setCharacter(ascii, i == input.length() - 1);
      head = head.getCharacters()[ascii];
    }
  }

  private static void checkStringExistsInTrie(TrieNode head, String input) {
    for (int i = 0; i < input.length(); i++) {
      int ascii = getAsciiValueOfCharacter(input.charAt(i));
      head = head.getCharacters()[ascii];
      if (head == null) {
        System.out.println("The given string doesn't exist in the Trie. " + input.charAt(i));
        return;
      }
    }
    System.out.println("The given string exists in the Trie");
  }

  private static int getAsciiValueOfCharacter(char c) {
    int ascii = c;
    if (ascii >= 97) {
      ascii = ascii - 97;
    } else {
      ascii = ascii - 65;
    }
    return ascii;
  }
}

class TrieNode {
  private final TrieNode[] characters;
  private boolean isEnd = false;

  public TrieNode() {
    characters = new TrieNode[26];
  }

  public boolean getIsEnd() {
    return this.isEnd;
  }

  public void setIsEnd(boolean isEnd) {
    this.isEnd = isEnd;
  }

  public void setCharacter(int index, boolean isEnd) {
    if (index >= 0 && index < 26) {
      if (characters[index] == null) {
        characters[index] = new TrieNode();
      }
      characters[index].setIsEnd(isEnd);
    } else {
      System.out.println("Wrong index provided: " + index);
    }
  }

  public TrieNode[] getCharacters() {
    return this.characters;
  }
}