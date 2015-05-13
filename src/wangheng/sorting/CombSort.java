package wangheng.sorting;

public class CombSort extends AbstractSort {

    public static void main(String[] args) {
        CombSort s = new CombSort();
        int[] input = { 1, 10, 2, 6, 8, 4, 7, 5, 3, 9 };
        int[] output = s.sort(input);
        print(input);
        print(output);
    }

    @Override
    public int[] sort(int[] array) {
        double shrink = 1.3d;
        int gap = array.length;
        boolean swapped = false;

        while (gap > 1 || swapped) {
            gap = (int) (gap / shrink);
            if (gap < 1)
                gap = 1;

            swapped = false;

            for (int i = 0; i + gap < array.length; i++) {
                if (array[i] > array[i + gap]) {
                    swap(array, i, i + gap);
                    swapped = true;
                }
            }

            System.out.print(gap + " - ");
            print(array);
        }

        return array;
    }

}
