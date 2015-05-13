package wangheng.sorting;

// in place
public class BubbleSort extends AbstractSort {

    public static void main(String[] args) {
        BubbleSort s = new BubbleSort();
        int[] input = { 1, 10, 2, 6, 8, 4, 7, 5, 3, 9 };
        int[] output = s.sort(input);
        print(input);
        print(output);
    }

    @Override
    public int[] sort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1])
                    swap(array, j, j + 1);
            }
        }
        return array;
    }
}
