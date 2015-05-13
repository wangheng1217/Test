package wangheng.sorting;

/**
 * in-place, unstable
 * 
 * @author hwan4805
 *
 */
public class QuickSort extends AbstractSort {

    public static void main(String[] args) {
        QuickSort s = new QuickSort();
        int[] input = { 1, 10, 2, 6, 8, 4, 7, 5, 3, 9 };
        int[] output = s.sort(input);
        print(input);
        print(output);
    }

    @Override
    public int[] sort(int[] array) {
        // sort(array, 0, array.length - 1);
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void sort(int[] array, int begin, int end) {
        if (begin >= end)
            return;
        int key = array[begin];
        int i, j;
        for (i = begin + 1, j = begin + 1; i <= end; i++) {
            if (array[i] < key) {
                swap(array, j, i);
                j++;
            }
        }
        swap(array, begin, j - 1);
        sort(array, begin, j - 2);
        sort(array, j, end);
    }

    private void quickSort(int[] array, int begin, int end) {
        if (begin >= end)
            return;
        // choose any pivotIndex such that begin <= pivotIndex <= end
        int pivotIndex = (begin + end) / 2;
        int pivotNewIndex = partition(array, begin, end, pivotIndex);
        quickSort(array, begin, pivotNewIndex - 1);
        quickSort(array, pivotNewIndex + 1, end);
    }

    private int partition(int[] array, int begin, int end, int pivotIndex) {
        int key = array[pivotIndex];
        swap(array, begin, pivotIndex);
        int i, j;
        for (i = begin + 1, j = begin + 1; i <= end; i++) {
            if (array[i] < key) {
                swap(array, j, i);
                j++;
            }
        }
        swap(array, begin, j - 1);
        return j - 1;
    }

}
