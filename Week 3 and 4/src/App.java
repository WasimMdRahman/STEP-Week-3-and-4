import java.util.*;

public class RiskThresholdSystem {

    // 🔍 Linear Search (unsorted)
    public static int linearSearch(int[] arr, int target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear Found at index: " + i);
                System.out.println("Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear: Not Found | Comparisons: " + comparisons);
        return -1;
    }

    // ⚡ Binary Search (exact match)
    public static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] == target) {
                System.out.println("Binary Found at index: " + mid);
                System.out.println("Comparisons: " + comparisons);
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary: Not Found | Comparisons: " + comparisons);
        return -1;
    }

    // 📍 Lower Bound (first index ≥ target)
    public static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] < target)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    // 📍 Upper Bound (first index > target)
    public static int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= target)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    // 🔽 Floor (largest ≤ target)
    public static Integer floor(int[] arr, int target) {
        int idx = lowerBound(arr, target);

        if (idx < arr.length && arr[idx] == target)
            return arr[idx];
        if (idx == 0)
            return null;

        return arr[idx - 1];
    }

    // 🔼 Ceiling (smallest ≥ target)
    public static Integer ceiling(int[] arr, int target) {
        int idx = lowerBound(arr, target);

        if (idx == arr.length)
            return null;

        return arr[idx];
    }

    // 📌 Insertion Point
    public static int insertionPoint(int[] arr, int target) {
        return lowerBound(arr, target);
    }

    public static void main(String[] args) {

        // 🔹 Unsorted input
        int[] unsorted = {50, 10, 100, 25};
        int target = 30;

        System.out.println("Unsorted Risks: " + Arrays.toString(unsorted));

        // 🔍 Linear Search
        System.out.println("\n--- Linear Search ---");
        linearSearch(unsorted, target);

        // 🔹 Sort for binary operations
        int[] sorted = {10, 25, 50, 100};
        System.out.println("\nSorted Risks: " + Arrays.toString(sorted));

        // ⚡ Binary Search
        System.out.println("\n--- Binary Search ---");
        binarySearch(sorted, target);

        // 📍 Floor & Ceiling
        System.out.println("\n--- Floor & Ceiling ---");
        System.out.println("Floor(" + target + "): " + floor(sorted, target));
        System.out.println("Ceiling(" + target + "): " + ceiling(sorted, target));

        // 📌 Insertion Point
        System.out.println("\nInsertion Index for " + target + ": " + insertionPoint(sorted, target));
    }
}