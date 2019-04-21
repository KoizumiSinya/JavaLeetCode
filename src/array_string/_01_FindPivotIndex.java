package array_string;

/**
 * @author Koizumi Sinya
 * @date 2019-04-21. 17:47
 * @edithor
 * @date
 */

/**
 * Given an array of integers nums, write a method that returns the "pivot" index of this array.
 * We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of the numbers to the right of the index.
 * If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.
 * Example 1:
 * Input:
 * nums = [1, 7, 3, 6, 5, 6]
 * Output: 3
 * Explanation:
 * The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
 * Also, 3 is the first index where this occurs.
 * Example 2:
 * Input:
 * nums = [1, 2, 3]
 * Output: -1
 * Explanation:
 * There is no index that satisfies the conditions in the problem statement.
 * Note:
 * The length of nums will be in the range [0, 10000].
 * Each element nums[i] will be an integer in the range [-1000, 1000].
 */
public class _01_FindPivotIndex {
    public static void main(String[] args) {
        int[] numbers = new int[]{1, 7, 3, 6, 5, 6};
        System.out.println("result = " + pivotIndex1(numbers));
    }


    public static int pivotIndex1(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int leftSum = 0;
        int rightSum = 0;

        //先获取所有的总和
        for (int i = 0; i < nums.length; i++) {
            rightSum += nums[i];
        }

        //rightSum依次减去左边的值，然后判断是否和左侧的总和相等
        for (int i = 0; i < nums.length; i++) {
            rightSum -= nums[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }

        return -1;
    }

    public static int pivotIndex2(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        int last = nums.length - 1;
        if (nums[last] - nums[0] == 0) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[last] - nums[i] == nums[i - 1]) {
                return i;
            }
        }
        return -1;
    }

    public static int pivotIndex3(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }

        int[] prefix = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < nums.length; i++) {
            if (prefix[i] == prefix[prefix.length - 1] - prefix[i] - nums[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int pivotIndex4(int[] nums) {
        int total = 0;
        int sum = 0;
        for (int num : nums) total += num;
        for (int i = 0; i < nums.length; sum += nums[i++]) {
            if (sum * 2 == total - nums[i]) {
                return i;
            }
        }
        return -1;
    }
}
