package wangheng.sorting;

public class MergeSort extends AbstractSort {

    public static void main(String[] args) {
        MergeSort s = new MergeSort();
        int[] input = { 1, 10, 2, 6, 8, 4, 7, 5, 3, 9 };
        int[] output = s.sort(input);
        print(input);
        print(output);
    }

    @Override
    public int[] sort(int[] array) {
        array = sort(array, 0, array.length - 1);
        return array;
    }

    private int[] sort(int[] array, int begin, int end) {
        if (begin == end) {
            int[] result = { array[begin] };
            return result;
        }
        int mid = (begin + end) / 2;
        int[] sortedArray1 = sort(array, begin, mid);
        int[] sortedArray2 = sort(array, mid + 1, end);
        int[] result = merge(sortedArray1, sortedArray2);
        return result;
    }

    private int[] merge(int[] sortedArray1, int[] sortedArray2) {
        int[] mergedArray = new int[sortedArray1.length + sortedArray2.length];
        for (int i = 0, j = 0, k = 0; k < mergedArray.length; k++) {
            if (i == sortedArray1.length) {
                mergedArray[k] = sortedArray2[j];
                j++;
            } else if (j == sortedArray2.length) {
                mergedArray[k] = sortedArray1[i];
                i++;
            } else if (sortedArray1[i] < sortedArray2[j]) {
                mergedArray[k] = sortedArray1[i];
                i++;
            } else {
                mergedArray[k] = sortedArray2[j];
                j++;
            }
        }
        return mergedArray;
    }

}
