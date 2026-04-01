import java.util.*;

// Client class
class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    public String toString() {
        return name + " (Risk: " + riskScore + ", Balance: " + accountBalance + ")";
    }
}

public class ClientRiskSystem {

    // 🔁 Bubble Sort (Ascending by riskScore)
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {

                    // Swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;

                    // Visualization
                    System.out.println("Swap: " + arr[j] + " <-> " + arr[j + 1]);
                }
            }

            if (!swapped) break; // Early stop
        }

        System.out.println("\nBubble Sort Result (Ascending Risk):");
        printArray(arr);
        System.out.println("Total Swaps: " + swaps);
    }

    // 📥 Insertion Sort (Descending riskScore + balance tie-break)
    public static void insertionSort(Client[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        System.out.println("\nInsertion Sort Result (Descending Risk):");
        printArray(arr);
    }

    // Comparator logic
    public static int compare(Client c1, Client c2) {
        if (c1.riskScore != c2.riskScore) {
            return Integer.compare(c1.riskScore, c2.riskScore);
        }
        return Double.compare(c1.accountBalance, c2.accountBalance);
    }

    // 🔝 Top N high-risk clients
    public static void topRiskClients(Client[] arr, int topN) {
        System.out.println("\nTop " + topN + " High-Risk Clients:");
        for (int i = 0; i < Math.min(topN, arr.length); i++) {
            System.out.println(arr[i]);
        }
    }

    // Utility print
    public static void printArray(Client[] arr) {
        for (Client c : arr) {
            System.out.println(c);
        }
    }

    public static void main(String[] args) {

        // Sample Input
        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        System.out.println("Original Data:");
        printArray(clients);

        // Bubble Sort (Ascending)
        Client[] bubbleArr = clients.clone();
        bubbleSort(bubbleArr);

        // Insertion Sort (Descending)
        Client[] insertionArr = clients.clone();
        insertionSort(insertionArr);

        // Top 3 risks
        topRiskClients(insertionArr, 3);
    }
}