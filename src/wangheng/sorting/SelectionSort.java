package wangheng.sorting;

public class SelectionSort extends AbstractSort {

    public static void main(String[] args) {
        SelectionSort s = new SelectionSort();
        int[] input = { 1, 10, 2, 6, 8, 4, 7, 5, 3, 9 };
        int[] output = s.sort(input);
        print(input);
        print(output);
    }

    public int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minValueIndex])
                    minValueIndex = j;
            }
            swap(array, i, minValueIndex);
        }
        return array;
    }
}
