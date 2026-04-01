import java.util.*;

// Asset class
class Asset {
    String name;
    double returnRate;
    double volatility;

    public Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    public String toString() {
        return name + ": " + returnRate + "% (Vol: " + volatility + ")";
    }
}

public class PortfolioSortingSystem {

    // 🔁 Merge Sort (Stable - Ascending by returnRate)
    public static List<Asset> mergeSort(List<Asset> list) {
        if (list.size() <= 1) return list;

        int mid = list.size() / 2;
        List<Asset> left = mergeSort(new ArrayList<>(list.subList(0, mid)));
        List<Asset> right = mergeSort(new ArrayList<>(list.subList(mid, list.size())));

        return merge(left, right);
    }

    private static List<Asset> merge(List<Asset> left, List<Asset> right) {
        List<Asset> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            // Stable: <= preserves order
            if (left.get(i).returnRate <= right.get(j).returnRate) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));

        return result;
    }

    // ⚡ Quick Sort (Descending returnRate + Asc volatility)
    public static void quickSort(List<Asset> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    private static int partition(List<Asset> list, int low, int high) {
        // Pivot selection: median-of-3
        int mid = (low + high) / 2;
        Asset pivot = medianOfThree(list, low, mid, high);

        // Move pivot to end
        Collections.swap(list, list.indexOf(pivot), high);

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(list.get(j), pivot) < 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }

        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    // Comparator: DESC returnRate, ASC volatility
    private static int compare(Asset a1, Asset a2) {
        if (a1.returnRate != a2.returnRate) {
            return Double.compare(a2.returnRate, a1.returnRate); // DESC
        }
        return Double.compare(a1.volatility, a2.volatility); // ASC
    }

    // Median-of-3 pivot selection
    private static Asset medianOfThree(List<Asset> list, int low, int mid, int high) {
        Asset a = list.get(low);
        Asset b = list.get(mid);
        Asset c = list.get(high);

        if (compare(a, b) < 0) {
            if (compare(b, c) < 0) return b;
            else if (compare(a, c) < 0) return c;
            else return a;
        } else {
            if (compare(a, c) < 0) return a;
            else if (compare(b, c) < 0) return c;
            else return b;
        }
    }

    // Utility print
    public static void printList(List<Asset> list) {
        for (Asset a : list) {
            System.out.println(a);
        }
    }

    public static void main(String[] args) {

        // Sample Input
        List<Asset> assets = new ArrayList<>();
        assets.add(new Asset("AAPL", 12, 5));
        assets.add(new Asset("TSLA", 8, 9));
        assets.add(new Asset("GOOG", 15, 4));

        System.out.println("Original Assets:");
        printList(assets);

        // 🔁 Merge Sort (Ascending)
        List<Asset> mergeSorted = mergeSort(new ArrayList<>(assets));
        System.out.println("\nMerge Sort (Ascending by Return):");
        printList(mergeSorted);

        // ⚡ Quick Sort (Descending + volatility)
        List<Asset> quickSorted = new ArrayList<>(assets);
        quickSort(quickSorted, 0, quickSorted.size() - 1);

        System.out.println("\nQuick Sort (DESC Return + ASC Volatility):");
        printList(quickSorted);
    }
}