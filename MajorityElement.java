import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;


/**
 * LeetCode 169. Majority Element
 * https://leetcode.com/problems/majority-element/
 */
public class MajorityElement {


    /**
     * Given an array nums of size n, return the majority element.
     * 
     * Execution: O(n) - Space: O(n)
     * 
     * 47 / 47 test cases passed.
     * Status: Accepted
     * Runtime: 8 ms
     * Memory Usage: 44.7 MB
     * @return 
     */
    static public Integer majorityElement0(int[] nums) {
        
        // **** sanity check ****
        if (nums.length == 1) return nums[0];

        // **** initialization ****
        int floor                       = nums.length / 2;
        HashMap<Integer, Integer> freqs = new HashMap<>();

        // **** populate freqs hashmap - O(n) ****
        for (int key : nums) {
            Integer val = freqs.putIfAbsent(key, 1);
            if (val != null) {

                // **** check if done ****
                if (++val > floor) return key;

                // **** ****
                freqs.put(key, val);
            }
        }

        // **** iterate through the entry set populating the list - O(n) ****
        for (Entry<Integer, Integer> e : freqs.entrySet()) {
            if (e.getValue() > floor) return e.getKey();
        }

        // **** something went wrong ****
        return -1;
    }


    /**
     * Given an array nums of size n, return the majority element.
     * 
     * 47 / 47 test cases passed.
     * Status: Accepted
     * Runtime: 2 ms
     * Memory Usage: 54.6 MB
     */
    static public Integer majorityElement1(int[] nums) {
        
        // **** sanity check ****
        if (nums.length == 1) return nums[0];

        // **** initialization ****
        int floor   = nums.length / 2;

        int n1      = Integer.MIN_VALUE;
        int c1      = 0;
     
        int n2      = Integer.MIN_VALUE;
        int c2      = 0;

        // **** ****
        for (int num : nums) {

            // **** increment count for n1 ****
            if (num == n1) {
                c1++;
                if (c1 > floor) return n1;
            }

            // **** increment count for n2 ****
            else if (num == n2) {
                c2++;
                if (c2 > floor) return n2;
            }

            // **** replace n1 with num and start counting ****
            else if (c1 == 0) {
                n1 = num;
                c1 = 1;
            }

            // **** replace n2 with num and start counting ****
            else if (c2 == 0) {
                n2 = num;
                c2 = 1;
            }

            // **** decrement both counters ****
            else {
                c1--;
                c2--;
            }
        }

        // **** ****
        if (c1 > c2) return n1;
        else return n2;
    }


    /**
     * Given an array nums of size n, return the majority element.
     * 
     * 47 / 47 test cases passed.
     * Status: Accepted
     * Runtime: 1 ms
     * Memory Usage: 45.2 MB
     * 
     * Runtime: 1 ms, faster than 99.90% of Java online submissions.
     * Memory Usage: 45.2 MB, less than 32.83% of Java online submissions.
     */
    static public Integer majorityElement(int[] nums) {
        
        // **** number of elements in nums ****
        int n = nums.length;

        // **** sanity check ****
        if (n == 1) return nums[0];

        // **** initialization ****
        int n1      = nums[0];
        int c1      = 1;

        // **** traverse nums array ****
        for (int i = 1; i < n; i++) {

            // **** increment count for n1 ****
            if (nums[i] == n1)
                c1++;

            // **** decrement counter ****
            else
                c1--;

            // **** replace n1 with num and start counting ****
            if (c1 == 0) {
                n1 = nums[i];
                c1 = 1;
            }
        }

        // **** ****
        return n1;
    }


    /**
     * Test scaffold
     * !!! NOT PART OF SOLUTION !!!
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read int[] nums ****
        int[] nums = Arrays.stream(br.readLine().trim().split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< nums: " + Arrays.toString(nums));

        // **** call function of interest and display result ****
        System.out.println("main <<< majorityElement0: " + majorityElement0(nums));

        // **** call function of interest and display result ****
        System.out.println("main <<< majorityElement1: " + majorityElement1(nums));

        // **** call function of interest and display result ****
        System.out.println("main <<<  majorityElement: " + majorityElement(nums));
    }
}