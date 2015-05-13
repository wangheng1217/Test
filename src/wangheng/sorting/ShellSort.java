package wangheng.sorting;

/**
 * Shellsort is a generalization of insertion sort that allows the exchange of
 * items that are far apart. 
 * effective for mostly sorted array 
 * in-place, but unstable
 * time complexity depends on the cap
 * 
 * @author hwan4805
 * 
 */
public class ShellSort extends AbstractSort {

    public static void main(String[] args) {
        ShellSort s = new ShellSort();
        int[] input = { 1, 10, 2, 6, 8, 4, 7, 5, 3, 9 };
        int[] output = s.sort(input);
        print(input);
        print(output);
    }

    @Override
    public int[] sort(int[] array) {
        int d = array.length / 2;

        while (d > 0) {
            for (int i = d; i < array.length; i++) {
                int key = array[i];
                int j;
                for (j = i - d; j >= 0 && array[j] > key; j = j - d) {
                    array[j + d] = array[j];
                }
                array[j + d] = key;
            }
            System.out.print(d + " - ");
            print(array);

            d = d / 2;
        }

        return array;
    }

}
