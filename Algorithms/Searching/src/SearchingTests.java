/* This file contains code to test searching algorithms. Made by Anirudh Lakra.
 */

import java.util.ArrayList;

public class SearchingTests {

    private Searching search = new Searching();


    private void test_linear_search() {

        ArrayList<Integer> my_array = init_array();

        System.out.println("\nTesting linear Search.");
        System.out.print("Unsorted: ");
        for (Integer num : my_array) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Target Value: 3012");

        int index = search.sentinel_linear_search(my_array, 3012, my_array.size());

        if (index == -1 )
            System.out.println("Value was not found.");
        else
            System.out.println("Index of: " + String.valueOf(index));
    }

    private void test_binary_search() {

        ArrayList<Integer> my_array = init_array();

        System.out.println("\nTesting Binary Search.");
        System.out.print("Unsorted: ");
        for (Integer num : my_array) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Target Value: 23");

        int index = search.binary_search(my_array, 23);

        if (index == -1 )
            System.out.println("Value was not found.");
        else
            System.out.println("Index of: " + String.valueOf(index));
    }

    // Helper method to initialise arrays to test.
    private ArrayList<Integer> init_array() {

        ArrayList<Integer> my_array = new ArrayList<>();
        my_array.add(203);
        my_array.add(2);
        my_array.add(-230);
        my_array.add(3012);
        my_array.add(23);
        my_array.add(-50);

        return my_array;
    }

    public static void main(String[] args) {

        SearchingTests tests = new SearchingTests();
        tests.test_binary_search();
        tests.test_linear_search();
    }
}
