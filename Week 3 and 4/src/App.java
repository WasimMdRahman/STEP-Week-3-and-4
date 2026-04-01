import java.util.*;

public class AccountSearchSystem {

    // 🔍 Linear Search: First Occurrence
    public static int linearFirst(String[] arr, String target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear First Found at index: " + i);
                System.out.println("Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Not Found | Comparisons: " + comparisons);
        return -1;
    }

    // 🔍 Linear Search: Last Occurrence
    public static int linearLast(String[] arr, String target) {
        int comparisons = 0;
        int lastIndex = -1;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                lastIndex = i;
            }
        }

        System.out.println("Linear Last Found at index: " + lastIndex);
        System.out.println("Comparisons: " + comparisons);
        return lastIndex;
    }

    // ⚡ Binary Search: Find any occurrence
    public static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid].equals(target)) {
                System.out.println("Binary Found at index: " + mid);
                System.out.println("Comparisons: " + comparisons);
                return mid;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Not Found | Comparisons: " + comparisons);
        return -1;
    }

    // 🔢 Count occurrences using Binary Search
    public static int countOccurrences(String[] arr, String target) {
        int first = firstOccurrence(arr, target);
        int last = lastOccurrence(arr, target);

        if (first == -1) return 0;
        return last - first + 1;
    }

    // First occurrence (Binary Search)
    private static int firstOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1; // go left
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    // Last occurrence (Binary Search)
    private static int lastOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1; // go right
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        // Sample Input
        String[] logs = {"accB", "accA", "accB", "accC"};

        System.out.println("Original Logs:");
        System.out.println(Arrays.toString(logs));

        // 🔍 Linear Search
        System.out.println("\n--- Linear Search ---");
        linearFirst(logs, "accB");
        linearLast(logs, "accB");

        // ⚠️ Binary Search requires sorting
        Arrays.sort(logs);

        System.out.println("\nSorted Logs for Binary Search:");
        System.out.println(Arrays.toString(logs));

        // ⚡ Binary Search
        System.out.println("\n--- Binary Search ---");
        binarySearch(logs, "accB");

        int count = countOccurrences(logs, "accB");
        System.out.println("Total Occurrences of accB: " + count);
    }
}