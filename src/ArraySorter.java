import java.util.Random;

import static java.util.Arrays.copyOfRange;

public class ArraySorter {
    public static void main(String[] args) {
        int[] array = arrayGenerator(10, 100);
        printArray(array);
        System.out.println();
        sortQuick(array);
        printArray(array);
    }

    public static void printArray (int[] array) {
        for (int elem: array) {
            System.out.print(elem+" , ");
        }
    }

    public static int[] arrayGenerator (int arraySize, int maxValue) {
        Random random = new Random();
        int[] newArray = new int[random.nextInt(arraySize)];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = random.nextInt(maxValue);
        }
        return newArray;
    }

    public static void sortBubbleUp (int[] array) {
        for (int i = array.length-1; i >= 0; i--) {
            for (int k = 0; k < i; k++) {
                if (array[k] > array[k+1]) {
                    int buff = array[k+1];
                    array[k+1] = array[k];
                    array[k] = buff;
                }
            }
        }
    }

    public static void sortBubbleDown (int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            for (int k = array.length-1; k > i; k--) {
                if (array[k] > array[k-1]) {
                    int buff = array[k-1];
                    array[k-1] = array[k];
                    array[k] = buff;
                }
            }
        }
    }

    public static void sortInsertionUp (int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            if (array[i] > array[i+1]) {
                for (int j = i; ((j >= 0) && (array[j] > array[j+1])); j--) {
                    int buff = array[j+1];
                    array[j+1] = array[j];
                    array[j] = buff;
                }
            }
        }
    }

    public static void sortInsertionDown (int[] array) {
        for (int i = array.length-1; i > 0; i--) {
            if (array[i] > array[i-1]) {
                for (int j = i; ((j <= array.length-1) && (array[j] > array[j-1])); j++) {
                    int buff = array[j-1];
                    array[j-1] = array[j];
                    array[j] = buff;
                }
            }
        }
    }

    public static void sortInsertionUpModified (int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            if (array[i] > array[i+1]) {
                int j = i;
                int buff = array[i+1];
                for (; ((j >= 0) && (array[j] > buff)); j--) {
                    array[j+1] = array[j];
                }
                array[j+1] = buff;
            }
        }
    }

    public static void sortSelectionUp (int[] array) {
        for (int i = 0; i < array.length; i++) {
            int maxIndex = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < array[maxIndex]) {
                    maxIndex = j;
                }
            }
            int buff = array[maxIndex];
            array[maxIndex] = array[i];
            array[i] = buff;
        }
    }

    public static void sortSelectionDown (int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] > array[minIndex]) {
                    minIndex = j;
                }
            }
            int buff = array[minIndex];
            array[minIndex] = array[i];
            array[i] = buff;
        }
    }

    public static int[] sortMerge (int[] array) {
        if (array.length <= 1) {
            return array;
        } else {
            int[] half1 = copyOfRange(array, 0, array.length / 2);
            int[] half2 = copyOfRange(array, array.length / 2, array.length);
            half1 = sortMerge(half1);
            half2 = sortMerge(half2);
            return mergeSortedArrays(half1, half2);
        }
    }

    public static int[] mergeSortedArrays (int[] array, int[] array2) {
        int[] newArray = new int[(array.length + array2.length)];
        int i = 0;
        int j = 0;
        for (int k = 0; k < newArray.length; k++) {
            if (i >= array.length) {
                newArray[k] = array2[j++];
            } else if (j >= array2.length) {
                newArray[k] = array[i++];
            } else if (array[i] <= array2[j]) {
                newArray[k] = array[i++];
            } else newArray[k] = array2[j++];
        }
        return newArray;
    }

    public static void sortQuick (int[] array) {
        if (array.length > 1)
            sortQuick(array, 0, array.length - 1);
    }

    private static void sortQuick (int[] array, int start, int end) {
        int i = start;
        int j = end;
        int medium = ((array[j] + array[(i+j)/2] + array[i])/3);
        do {
            while (array[i] <= medium) {
                i++;
            }
            while (array[j] > medium) {
                j--;
            }
            if (i <= j) {
                int buff = array[i];
                array[i] = array[j];
                array[j] = buff;
            }
        } while (i <= j);
        if (j > start) {
            sortQuick (array, start, j);
        }
        if (i < end) {
            sortQuick (array, i, end);
        }
    }
}