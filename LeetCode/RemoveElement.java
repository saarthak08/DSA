class Solution {
  public int removeElement(int[] nums, int val) {
    int count = nums.length;
    int len = count;
    for (int i = 0; i < len; i++) {
      if (nums[i] == val) {
        count--;
        for (int j = len - 1; j > i; j--) {
          if (nums[j] != val) {
            len--;
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
            break;
          }
        }
      }
    }
    return count;
  }
}