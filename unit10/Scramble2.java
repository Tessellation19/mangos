package unit10;

import java.util.Arrays;

public class Scramble2 {
    /**
     * Sorts the array using the Merge Sort algorithm.
     * Modified code from https://www.baeldung.com/java-merge-sort
     * 
     * @param arr the array to sort
     */
    public static void mergeSort(String[] arr) {
        int n = arr.length;
        // base case: no need to sort a single element (or empty) array
        if (n < 2) {
            return;
        }
        // split the array to sort recursively.
        int mid = n / 2;
        String[] l = new String[mid];
        String[] r = new String[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = arr[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = arr[i];
        }
        mergeSort(l);
        mergeSort(r);

        // MERGE
        int left = mid;
        int right = n - mid;
        // pointers for left array (i), right array (j), and combined array (k)
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            //if (l[i] <= r[j]) {
            if(l[i].compareTo(r[j])<= 0){
                arr[k++] = l[i++];
            } else {
                arr[k++] = r[j++];
            }
        }
        // if we finish one array, complete the other
        while (i < left) {
            arr[k++] = l[i++];
        }
        while (j < right) {
            arr[k++] = r[j++];
        }
    }

    /**
     * Sorts the array using the Merge Sort algorithm.
     * Modified code from https://www.baeldung.com/java-merge-sort
     * 
     * @param arr the array to sort
     */
    public static void mergeSort(int[] arr) {
        int n = arr.length;
        // base case: no need to sort a single element (or empty) array
        if (n < 2) {
            return;
        }
        // split the array to sort recursively.
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = arr[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = arr[i];
        }
        mergeSort(l);
        mergeSort(r);

        // MERGE
        int left = mid;
        int right = n - mid;
        // pointers for left array (i), right array (j), and combined array (k)
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                arr[k++] = l[i++];
            } else {
                arr[k++] = r[j++];
            }
        }
        // if we finish one array, complete the other
        while (i < left) {
            arr[k++] = l[i++];
        }
        while (j < right) {
            arr[k++] = r[j++];
        }
    }

    static boolean DEBUG = false;

    /**
     * Sorts the provided array (from index left to index right) using the Quicksort
     * algorithm.
     * Modified code from https://www.baeldung.com/java-quicksort
     * 
     * @param arr   the array to sort
     * @param left  starting index of the array subset to sort
     * @param right ending index of the array subset to sort
     */
    public static void quickSort(int arr[], int left, int right) {
        if (left < right) {
            if (DEBUG) {
                System.out.println("sorting(" + left + "," + right + ")");
                System.out.println(Arrays.toString(arr));
            }
            int pivot = arr[right];
            if (DEBUG) {
                System.out.println("pivot=" + pivot);
            }

            // PARTITION!
            int i = left;
            for (int j = left; j < right; j++) {
                if (arr[j] <= pivot) {
                    // swap left and right
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                }
            }
            // move pivot to i
            int temp = arr[i];
            arr[i] = arr[right];
            arr[right] = temp;
            // DONE PARTITIONING!

            if (DEBUG) {
                System.out.println("result:" + Arrays.toString(arr));
                System.out.println();
            }

            if (left < i - 1)
                quickSort(arr, left, i - 1);
            if (right > i + 1)
                quickSort(arr, i + 1, right);
        } else {
            if (DEBUG) {
                System.out.println("no sort(" + left + "," + right + ")");
                System.out.println();
            }
            // else done (base case)
        }
    }

    public static void main(String args[]) {

        String[] arr1 = {"Hello", "My friend", "How are you"};
        // end up with { items < 4}, 4, {items > 4}
        mergeSort(arr1);
        System.out.println("Merge Sorted: " + Arrays.toString(arr1));
    }
}