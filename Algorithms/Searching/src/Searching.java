/* This file contains all searching algorithms. Made by Anirudh Lakra
 */
import java.util.ArrayList;

public class Searching<T extends Comparable<T>> {

    /* @params = array : array to search through.
                 value : value searching for.
       @returns = index of if found, else -1 to indicate failure.
     */
    public int binary_search(ArrayList<T> array, T value) {

        int size = array.size();
        int index_at = 0;

        while (index_at <= size) {
            int mid = (size + index_at) / 2;

            // If value is found at mid point of index_at and size.
            if (array.get(mid).compareTo(value) == 0)
                return mid;

                // Mid > value, discard values greater than mid.
            else if (array.get(mid).compareTo(value) < 0)
                size = mid - 1;

            else
                index_at = mid + 1;
        }

        // Not found.
        return -1;
    }

    /* Bounds the search by putting last element = value.
       @params = array : array to search through.
                 value : value looking for.
                 size : size of the array you are looking for.
       @returns = index of if found, -1 if not found.
     */
    public int sentinel_linear_search(ArrayList<T> array, T value, int size) {

        // Bound search by making the last value = target value.
        T save_last = array.get(size - 1);
        array.set(size - 1, value);
        int index_at = 0;

        // Loop through until you reach last element or target value.
        while (array.get(index_at).compareTo(value) != 0)
            index_at++;

        // Reset last value.
        array.set(size - 1, save_last);
        if ((index_at < size) || (save_last == value))
            return index_at;

        return -1;
    }
}
