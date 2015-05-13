package wangheng.sorting;

/**
 * efficient for small lists and mostly sorted lists
 * in-place, and stable
 * 
 * @author hwan4805
 *
 */
public class InsertionSort extends AbstractSort {

    public static void main(String[] args) {
        InsertionSort s = new InsertionSort();
        int[] input = { 1, 10, 2, 6, 8, 4, 7, 5, 3, 9 };
        int[] output = s.sort(input);
        print(input);
        print(output);
    }

    public int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j;
            for (j = i - 1; j >= 0 && array[j] > key; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = key;
        }
        return array;
    }
}
