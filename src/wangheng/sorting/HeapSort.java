package wangheng.sorting;

/**
 * Heapsort is a much more efficient version of selection sort.
 * worst case O(nlogn)
 * in-place, but not stable
 * 
 * @author hwan4805
 *
 */
public class HeapSort extends AbstractSort {

    public static void main(String[] args) {
        HeapSort s = new HeapSort();
        int[] input = { 1, 10, 2, 6, 8, 4, 7, 5, 3, 9 };
        int[] output = s.sort(input);
        print(input);
        print(output);
    }

    @Override
    public int[] sort(int[] array) {
        // construct the MAX-heap
        for (int i = 1; i < array.length; i++) {
            int curr = i;
            while (curr > 0 && array[curr] > array[(curr - 1) / 2]) {
                swap(array, curr, (curr - 1) / 2);
                curr = (curr - 1) / 2;
            }
        }

        // move the max item to the sorted array one by one, and adjust the heap
        int i = array.length - 1;
        while (i >= 0) {
            swap(array, 0, i);

            i--;

            int curr = 0;
            while (curr <= i) {
                int leftIndex = (curr + 1) * 2 - 1;
                int rightIndex = (curr + 1) * 2;
                int maxChildIndex;
                if (leftIndex > i)
                    maxChildIndex = -1;
                else if (rightIndex > i)
                    maxChildIndex = leftIndex;
                else
                    maxChildIndex = array[leftIndex] > array[rightIndex] ? leftIndex
                            : rightIndex;

                if (maxChildIndex == -1)
                    break;

                if (array[curr] < array[maxChildIndex]) {
                    swap(array, curr, maxChildIndex);
                    curr = maxChildIndex;
                } else
                    break;
            }
        }

        return array;
    }

}
