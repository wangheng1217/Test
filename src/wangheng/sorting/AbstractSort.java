package wangheng.sorting;

public abstract class AbstractSort {

    public abstract int[] sort(int[] array);

    protected void swap(int[] array, int a, int b) {
        if (a == b)
            return;
        array[b] = array[a] + array[b];
        array[a] = array[b] - array[a];
        array[b] = array[b] - array[a];
    }

    protected static void print(int[] array) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        for (int i = 0; i < array.length; i++) {
            if (i > 0)
                stringBuffer.append(", ");
            stringBuffer.append(array[i]);
        }
        stringBuffer.append("]");
        System.out.println(stringBuffer.toString());
    }
}
