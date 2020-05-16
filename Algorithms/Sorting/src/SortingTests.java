/* This program contains tests to test the sorting algorithms, made by Anirudh Lakra.
 */

import java.util.ArrayList;


public class SortingTests {

    Sorting sorting = new Sorting();

    // Helper method used to initialise testing arraylists.
    private ArrayList<Double> init_array() {

        ArrayList<Double> my_array = new ArrayList<>();
        my_array.add(32.3);
        my_array.add(-202.1);
        my_array.add(6.0);
        my_array.add(230.03);
        my_array.add(0.0);

        return my_array;
    }

    // prints unsorted array. Helps for testing.
    private void print_unsorted(ArrayList<Double> my_array, String algorithm) {

        System.out.println("\nTesting " + algorithm);
        System.out.print("Unsorted: ");
        for (Double elem : my_array)
            System.out.print(elem + " ");
        System.out.println();
    }

    // prints sorted array. Helps for testing.
    private void print_sorted(ArrayList<Double> my_array) {

        System.out.print("Sorted: ");
        for (Double elem : my_array)
            System.out.print(elem + " ");
        System.out.println();
    }

    private void test_insertion_sort() {

        ArrayList<Double> my_array = init_array();
        print_unsorted(my_array, "Insertion Sort.");
        sorting.insertion_sort(my_array);
        print_sorted(my_array);
    }

    private void test_quick_sort() {

        ArrayList<Double> my_array = init_array();
        print_unsorted(my_array, "Quick Sort.");
        sorting.quick_sort(my_array, 0, my_array.size() - 1);
        print_sorted(my_array);
    }

    private void test_selection_sort() {

        ArrayList<Double> my_array = init_array();
        print_unsorted(my_array, "Selection Sort.");
        sorting.selection_sort(my_array);
        print_sorted(my_array);
    }

    private void test_merge_sort() {

        ArrayList<Double> my_array = init_array();
        print_unsorted(my_array, "Merge Sort.");
        sorting.merge_sort(my_array, my_array.size());
        print_sorted(my_array);
    }

    private void run_tests() {

        this.test_insertion_sort();
        this.test_selection_sort();
        this.test_quick_sort();
        this.test_merge_sort();
    }

    public static void main(String[] args) {

        SortingTests tests = new SortingTests();
        tests.run_tests();
    }
}
