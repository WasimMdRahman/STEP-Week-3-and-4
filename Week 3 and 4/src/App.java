import java.util.*;

// Trade class
class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    public String toString() {
        return id + ": " + volume;
    }
}

public class TradeAnalysisSystem {

    // 🔁 Merge Sort (Ascending)
    public static List<Trade> mergeSort(List<Trade> list) {
        if (list.size() <= 1) return list;

        int mid = list.size() / 2;

        List<Trade> left = mergeSort(list.subList(0, mid));
        List<Trade> right = mergeSort(list.subList(mid, list.size()));

        return merge(left, right);
    }

    public static List<Trade> merge(List<Trade> left, List<Trade> right) {
        List<Trade> result = new ArrayList<>();

        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).volume <= right.get(j).volume) {
                result.add(left.get(i++)); // stable
            } else {
                result.add(right.get(j++));
            }
        }

        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));

        return result;
    }

    // ⚡ Quick Sort (Descending)
    public static void quickSort(List<Trade> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);

            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    public static int partition(List<Trade> list, int low, int high) {
        Trade pivot = list.get(high); // Lomuto pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).volume > pivot.volume) { // DESC
                i++;
                Collections.swap(list, i, j);
            }
        }

        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    // 🔗 Merge two sorted lists
    public static List<Trade> mergeTwoSorted(List<Trade> a, List<Trade> b) {
        List<Trade> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < a.size() && j < b.size()) {
            if (a.get(i).volume <= b.get(j).volume) {
                result.add(a.get(i++));
            } else {
                result.add(b.get(j++));
            }
        }

        while (i < a.size()) result.add(a.get(i++));
        while (j < b.size()) result.add(b.get(j++));

        return result;
    }

    // 📊 Total Volume
    public static int totalVolume(List<Trade> list) {
        int sum = 0;
        for (Trade t : list) {
            sum += t.volume;
        }
        return sum;
    }

    // Utility print
    public static void printList(List<Trade> list) {
        for (Trade t : list) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {

        // Sample Input
        List<Trade> trades = new ArrayList<>();
        trades.add(new Trade("trade3", 500));
        trades.add(new Trade("trade1", 100));
        trades.add(new Trade("trade2", 300));

        System.out.println("Original Trades:");
        printList(trades);

        // 🔁 Merge Sort (Ascending)
        List<Trade> sortedMerge = mergeSort(new ArrayList<>(trades));
        System.out.println("\nMerge Sort (Ascending):");
        printList(sortedMerge);

        // ⚡ Quick Sort (Descending)
        List<Trade> quickList = new ArrayList<>(trades);
        quickSort(quickList, 0, quickList.size() - 1);
        System.out.println("\nQuick Sort (Descending):");
        printList(quickList);

        // 🔗 Merge two sorted lists (example: morning + afternoon)
        List<Trade> morning = new ArrayList<>();
        morning.add(new Trade("m1", 100));
        morning.add(new Trade("m2", 300));

        List<Trade> afternoon = new ArrayList<>();
        afternoon.add(new Trade("a1", 200));
        afternoon.add(new Trade("a2", 400));

        List<Trade> merged = mergeTwoSorted(morning, afternoon);
        System.out.println("\nMerged Trade List:");
        printList(merged);

        // 📊 Total volume
        System.out.println("\nTotal Volume: " + totalVolume(merged));
    }
}