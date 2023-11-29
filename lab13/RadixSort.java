import java.util.Arrays;

public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1
     * length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // Find the length of the longest string
        int maxLength = 0;
        for (String s : asciis) {
            maxLength = Math.max(maxLength, s.length());
        }

        // Perform LSD sort, starting from the least significant digit
        String[] res = Arrays.copyOf(asciis, asciis.length);
        for (int index = maxLength - 1; index >= 0; index--) {
            sortHelperLSD(res, index);
        }

        return res;
    }

    /**
     * LSD helper method that performs a destructive counting sort on the array of
     * Strings based off characters at a specific index.
     * 
     * @param asciis Input array of Strings
     * @param index  The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        int n = asciis.length;
        int R = 256; // ASCII range
        String[] aux = new String[n];
        int[] count = new int[R + 1];

        // Count frequencies of each character at index
        for (String s : asciis) {
            int charIndex = index < s.length() ? (int) s.charAt(index) : 0;
            count[charIndex + 1]++;
        }

        // Compute cumulates
        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }

        // Move items
        for (String s : asciis) {
            int charIndex = index < s.length() ? (int) s.charAt(index) : 0;
            aux[count[charIndex]++] = s;
        }

        // Copy back
        for (int i = 0; i < n; i++) {
            asciis[i] = aux[i];
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        String[] asciis = { "9", "111", "11111", "11111111", "1111111" };
        String[] sorted = sort(asciis);
        System.out.println(Arrays.toString(sorted));
    }
}
