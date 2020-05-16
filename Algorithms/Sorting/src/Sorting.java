/* This program contains different sorting algorithms, made by Anirudh Lakra.
 */

import java.util.ArrayList;

public class Sorting<T extends Comparable<T>> {

    /* Picks and element and moves it all the way to the left until it is
       in the right space.
       @params = array : array to sort.
     */
    public void insertion_sort(ArrayList<T> array) {

        for (int i = 0; i != array.size(); ++i) {
            T key = array.get(i);
            int j = i - 1;

            // Loop until you reach beginning or find a value smaller than key.
            while ((j >= 0) && (key.compareTo(array.get(j)) < 0)) {

                // Shift element one to the right and move j to the left.
                array.set(j+1, array.get(j));
                j--;
            }

            array.set(j+1, key);
        }
    }

    /*  Works by repeatedly partitioning array into smaller arrays and sorting them.
        @params = array : array to be sorted.
                  l_index : lower index.
                  r_index : upper index.
        Uses partition algorithm from GeeksForGeeks.
     */
    public void quick_sort(ArrayList<T> array, int l_index, int r_index) {

        if (l_index < r_index) {
            int partition_index = partition(array, l_index, r_index);
            quick_sort(array, l_index, partition_index - 1);
            quick_sort(array, partition_index + 1, r_index);
        }
    }

    /* Helper method for quick_sort. Works by picking last element as pivot.
       Finds all elements smaller than pivot and places them before partition index.
       array[partition_index] = last element.
       @params = array : array to sort.
                 l_index : lower index.
                 r_index : upper index.
       @returns = partition_index.
     */
    private int partition(ArrayList<T> array, int l_index, int r_index) {

        int partition_index = l_index;
        T pivot = array.get(r_index);

        for (int i = l_index; i != r_index; ++i) {
            if (array.get(i).compareTo(pivot) <= 0) {
                swap(array, i, partition_index);
                partition_index++;
            }
        }

        swap(array, partition_index, r_index);
        return partition_index;
    }

    // Helper method that swaps elements in array at index and r_index.
    private void swap(ArrayList<T> array, int index, int r_index) {

        T save = array.get(index);
        array.set(index, array.get(r_index));
        array.set(r_index, save);
    }

    /* Simplest sorting algorithm. Picks out smallest element and adds it
       to sorted array.
       @params = array : array to sort.
     */
    public void selection_sort(ArrayList<T> array) {

        for (int i = 0; i != array.size(); ++i) {
            int min_index = i;
            int j = i + 1;

            for (;j < array.size(); ++j) {
                if (array.get(j).compareTo(array.get(min_index)) < 0) {
                    min_index = j;
                }
            }

            swap(array, min_index, i);
        }
    }

    /*  Works by repeatedly breaking array into smaller pieces and merging them
        together in order.
        @params = array : array to sort.
                  size : size of array.
     */
    public void merge_sort(ArrayList<T> array, int size) {

        if (size < 2)
            return;
        else {
            int mid = size / 2;
            ArrayList<T> left = copy(array, 0, mid);
            ArrayList<T> right = copy(array, mid, size);

            merge_sort(left, left.size());
            merge_sort(right, right.size());
            merge(array, left, right);
        }
    }

    /* Helper method for merge_sort. Works by taking in 3 arrays and picking
       smallest element out of left and right and inserting it into array. Does this
       until all elements in left and right have been inserted.
       @params = array : array to insert values into.
                 left : left partition of array.
                 right : right partition of array.
     */
    private void merge(ArrayList<T> array, ArrayList<T> left, ArrayList<T> right) {

        int len_l = left.size();
        int len_r = right.size();

        // i = left index, j = right index, k = array index.
        int i = 0;
        int j = 0;
        int k = 0;

        while ((i < len_l) && (j < len_r)) {

            // If left[i] <= right[j].
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                array.set(k, left.get(i));
                i++;
            }

            // If right[j] < left[i].
            else {
                array.set(k, right.get(j));
                j++;
            }
            k++;
        }

        if (i < len_l) {
            array.set(k, left.get(i));
            i++;
            k++;
        }
        if (j < len_r) {
            array.set(k, right.get(j));
            j++;
            k++;
        }
    }

    /* Helper method for merge_sort. Copies all elements from index_from to index_to.
       Doesnt include element at index_to.
       ret = [index_from,index_to).
       @params = array : array to copy from.
                 index_from : index to start copying from.
                 index_to : index to copy to. (doesnt include element at index_to).
       @returns = copied arraylist.
     */
    private ArrayList<T> copy(ArrayList<T> array, int index_from, int index_to) {

        ArrayList<T> ret = new ArrayList<>();
        for (int i = index_from; i != index_to; ++i) {
            ret.add(array.get(i));
        }
        return ret;
    }
}
