package Lista2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;

public class Algorithms {
    private static int c = 0;
    private static int s = 0;

/******************************************************SELECTSORT******************************************************/
    private void selectSort(int[] arr, int n, boolean x) {
        for(int i = 0; i < n - 1; i++) {
            int index = i;
            for(int j = i + 1; j < n; j++) {
//			    System.err.println("Porównanie: " + arr[j] + " " + arr[index]);
                c++;
                if(arr[j] < arr[index] && x) {
                    index = j;
                } else if(arr[j] > arr[index] && !x) {
                    index = j;
                }
            }
            if(index != i) {
//				System.err.println("Przestawienie: " + arr[index] + " " + arr[i]);
                s++;
                swap(arr, index, i);
            }
        }
    }

/******************************************************INSERTSORT******************************************************/
    private void insertSort(int[] arr, int n, boolean x) {
        for(int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
//			System.err.println("Porównanie: " + arr[j] + " " + key);
            c++;
            while(j >= 0 && arr[j] > key && x) {
//				System.err.println("Przestawienie: " + arr[j + 1] + " " + arr[j]);
                s++;
                arr[j + 1] = arr[j];
                j--;
            }
            while(j >= 0 && arr[j] < key && !x) {
//				System.err.println("Przestawienie: " + arr[j + 1] + " " + arr[j]);
                s++;
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

/*******************************************************HEAPSORT*******************************************************/
    private void heapSort(int[] arr, int n, boolean x) {
        buildHeap(arr, n, x);
        for(int i = n - 1; i >= 0; i--) {
//		    System.err.println("Przestawienie: " + arr[j + 1] + " " + arr[j]);
            s++;
            swap(arr, 0, i);
            heapify(arr, i, 0, x);
        }
    }

    private void buildHeap(int[] arr, int n, boolean x) {
        for(int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, x);
        }
    }

    private void heapify(int[] arr, int n, int i, boolean x) {
        int largest = i;
        int smallest = i;
        int l = left(i);
        int r = right(i);
        if(x) {
//			System.err.println("Porównanie: " + arr[l] + " " + arr[largest]);
            c++;
            if(l < n && arr[l] > arr[largest]) {
                largest = l;
            } else {
                largest = i;
            }
//			System.err.println("Porównanie: " + arr[r] + " " + arr[largest]);
            c++;
            if(r < n && arr[r] > arr[largest]) {
                largest = r;
            }
            if(largest != i) {
//				System.err.println("Przestawienie: " + arr[i] + " " + arr[largest]);
                s++;
                swap(arr, i, largest);
                heapify(arr, n, largest, x);
            }
        } else {
//			System.err.println("Porównanie: " + arr[l] + " " + arr[smallest]);
            c++;
            if(l < n && arr[l] < arr[smallest]) {
                smallest = l;
            }
//			System.err.println("Porównanie: " + arr[r] + " " + arr[smallest]);
            c++;
            if(r < n && arr[r] < arr[smallest]) {
                smallest = r;
            }
            if(smallest != i) {
//				System.err.println("Przestawienie: " + arr[i] + " " + arr[smallest]);
                s++;
                swap(arr, i, smallest);
                heapify(arr, n, smallest, x);
            }
        }

    }

    private int left(int i) {
        return (2 * i + 1);
    }

    private int right(int i) {
        return (2 * i + 2);
    }

/*******************************************************QUICKSORT******************************************************/
    private void quickSort(int[] arr, int p, int r, boolean x) {
        if(p < r) {
            int q = partition(arr, p, r, x);
            quickSort(arr, p, q - 1, x);
            quickSort(arr, q + 1, r, x);
        }
    }

    private int partition(int[] arr, int low, int high, boolean x) {
        int pivot = arr[high];
        int i = low - 1;
        for(int j = low; j < high; j++) {
//			System.err.println("Porównanie: " + arr[j] + " " + pivot);
            c++;
            if(arr[j] <= pivot && x) {
                i++;
//				System.err.println("Przestawienie: " + arr[i] + " " + arr[j]);
                s++;
                swap(arr, i, j);
            } else if(arr[j] >= pivot && !x) {
                i++;
//				System.err.println("Przestawienie: " + arr[i] + " " + arr[j]);
                s++;
                swap(arr, i, j);
            }
        }
//		System.err.println("Przestawienie: " + arr[i + 1] + " " + arr[high]);
        s++;
        swap(arr, i + 1, high);
        return (i + 1);
    }

/*******************************************************QUICKSORT******************************************************/
    private void mQuickSort(int[] arr, int p, int r, boolean x) {
        if(p < r) {
            if((r - p) < 16) {
                insertSort(arr, r + 1, x);
            } else {
                int q = medianPartition(arr, p, r, x);
                quickSort(arr, p, q - 1, x);
                quickSort(arr, q + 1, r, x);
            }
        }
    }

    private int medianPartition(int[] arr, int low, int high, boolean x) {
        int middle = low + (high - low) / 2;
//      System.err.println("Porównanie: " + arr[low] + " " + arr[middle]);
        c++;
        if(arr[low] > arr[middle]) {
//			System.err.println("Przestawienie: " + arr[low] + " " + arr[middle]);
            s++;
            swap(arr, low, middle);
        }
//		System.err.println("Porównanie: " + arr[low] + " " + arr[high]);
        c++;
        if(arr[low] > arr[high]) {
//			System.err.println("Przestawienie: " + arr[low] + " " + arr[high]);
            s++;
            swap(arr, low, high);
        }
//	    System.err.println("Porównanie: " + arr[middle] + " " + arr[low]);
        c++;
        if(arr[middle] > arr[low]) {
//			System.err.println("Przestawienie: " + arr[middle] + " " + arr[high]);
            s++;
            swap(arr, middle, high);
        }
//		System.err.println("Przestawienie: " + arr[middle] + " " + arr[low]);
        s++;
        swap(arr, middle, low);
        return partition(arr, low, high, x);
    }

/**********************************************************************************************************************/
    private void swap(int[] arr, int a, int b) {
        int temporary = arr[a];
        arr[a] = arr[b];
        arr[b] = temporary;
    }

    private static void check(int[] arr, int n, boolean x) {
        for (int i = 0; i < (n - 1); i++) {
            if(x) {
                if(arr[i] > arr[i + 1]) {
                    System.err.println("Program źle posortował tablicę!");
                    System.exit(0);
                }
            } else {
                if(arr[i] < arr[i + 1]) {
                    System.err.println("Program źle posortował tablicę!");
                    System.exit(0);
                }
            }
        }
        System.err.println("Program dobrze posortował tablicę!");
    }

    private static void cs() {
        System.err.println("Liczba porównań: " + c + "\nLiczba przestawień: " + s);
    }

    private int getc() {
        return c;
    }

    private int gets() {
        return s;
    }

    private String getcs() {
        String text = "\tLiczba porównań: " + c + "\t\tLiczba przestawień: " + s;
        c = 0;
        s = 0;
        return text;
    }

    private static void print(int[] arr, int n) {
        System.out.println("Liczba elementów posortowanej tablicy: \n" + n);
        System.out.println("Posortowana tablica: ");
        for(int i = 0; i < n; i++) {
            System.out.println(arr[i] + " ");
        }
        System.out.println();
    }

    private void coppy(int[] arra, int[] arrb) {
        System.arraycopy(arra, 0, arrb, 0, arra.length);
    }

    private void generator(int[] arr){
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < arr.length; i++){
            arr[i] = random.nextInt()%500;
        }
    }

/*********************************************************MAIN*********************************************************/
    public static void main(String[] args) throws FileNotFoundException {
        String type = "select";
        String stat = "";
        boolean x = true;
        int k = 0;
        int n = 0;
        int[] arr = null;
        long start, stop;
        double total = 0;
        double total1, total2, total3, total4, total5;
        Algorithms a = new Algorithms();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--type":
                    type = args[i + 1];
                    i++;
                    break;
                case "--asc":
                    x = true;
                    i++;
                    break;
                case "--desc":
                    x = false;
                    break;
                case "--stat":
                    stat = args[i + 1];
                    k = Integer.parseInt(args[i + 2]);
                    i += 2;
                    break;
                default:
                    System.out.println("Podano błędne parametry!");
                    System.exit(0);
            }
        }
        if(stat.equals("")) {
            switch (type) {
                case "select":
                    System.out.println("SelectSort");
                    System.out.println("Podaj ilość elementów tablicy: ");
                    n = scanner.nextInt();
                    System.out.println("Podaj elementy tablicy: ");
                    arr = new int[n];
                    for (int j = 0; j < n; j++) {
                        arr[j] = scanner.nextInt();
                    }
                    start = System.nanoTime();
                    a.selectSort(arr, n, x);
                    stop = System.nanoTime();
                    total = (stop - start);
                    break;
                case "insert":
                    System.out.println("InsertSort");
                    System.out.println("Podaj ilość elementów tablicy: ");
                    n = scanner.nextInt();
                    System.out.println("Podaj elementy tablicy: ");
                    arr = new int[n];
                    for (int j = 0; j < n; j++) {
                        arr[j] = scanner.nextInt();
                    }
                    start = System.nanoTime();
                    a.insertSort(arr, n, x);
                    stop = System.nanoTime();
                    total = (stop - start);
                    break;
                case "heap":
                    System.out.println("HeapSort");
                    System.out.println("Podaj ilość elementów tablicy: ");
                    n = scanner.nextInt();
                    System.out.println("Podaj elementy tablicy: ");
                    arr = new int[n];
                    for (int j = 0; j < n; j++) {
                        arr[j] = scanner.nextInt();
                    }
                    start = System.nanoTime();
                    a.heapSort(arr, n, x);
                    stop = System.nanoTime();
                    total = (stop - start);
                    break;
                case "quick":
                    System.out.println("QuickSort");
                    System.out.println("Podaj ilość elementów tablicy: ");
                    n = scanner.nextInt();
                    System.out.println("Podaj elementy tablicy: ");
                    arr = new int[n];
                    for (int j = 0; j < n; j++) {
                        arr[j] = scanner.nextInt();
                    }
                    start = System.nanoTime();
                    a.quickSort(arr, 0, n - 1, x);
                    stop = System.nanoTime();
                    total = (stop - start);
                    break;
                case "mquick":
                    System.out.println("MQuickSort");
                    System.out.println("Podaj ilość elementów tablicy: ");
                    n = scanner.nextInt();
                    System.out.println("Podaj elementy tablicy: ");
                    arr = new int[n];
                    for (int j = 0; j < n; j++) {
                        arr[j] = scanner.nextInt();
                    }
                    start = System.nanoTime();
                    a.mQuickSort(arr, 0, n - 1, x);
                    stop = System.nanoTime();
                    total = (stop - start);
                    break;
                default:
                    System.out.println("Podano błędne parametry! Parametr --type może przyjmowa wartości select/insert/heap/quick");
                    System.exit(0);
            }
            check(arr, n, x);
            print(arr, n);
            cs();
            System.err.println("Całkowity czas: " + total + " ns");
            System.exit(0);
        } else if(k > 0) {
            System.err.close();
            PrintWriter printWriter = new PrintWriter(stat);
            /*PrintWriter algorytmyWriter = new PrintWriter(new File(stat + ".csv"));
            StringBuilder algorytmyBuilder = new StringBuilder();
            algorytmyBuilder.append("n");
            algorytmyBuilder.append(',');
            algorytmyBuilder.append("k");
            algorytmyBuilder.append(',');
            algorytmyBuilder.append("porównaniaSelect");
            algorytmyBuilder.append(',');
            algorytmyBuilder.append("przestawieniaSelect");
            algorytmyBuilder.append(',');
            algorytmyBuilder.append("czasSelect");
            algorytmyBuilder.append(',');
            algorytmyBuilder.append("porównaniaInsert");
            algorytmyBuilder.append(',');
            algorytmyBuilder.append("przestawieniaInsert");
            algorytmyBuilder.append(',');
            algorytmyBuilder.append("czasInsert");
            algorytmyBuilder.append(',');
            algorytmyBuilder.append("porównaniaHeap");
            algorytmyBuilder.append(',');
            algorytmyBuilder.append("przestawieniaHeap");
            algorytmyBuilder.append(',');
            algorytmyBuilder.append("czasHeap");
            algorytmyBuilder.append(',');
            algorytmyBuilder.append("porównaniaQuick");
            algorytmyBuilder.append(',');
            algorytmyBuilder.append("przestawieniaQuick");
            algorytmyBuilder.append(',');
            algorytmyBuilder.append("czasQuick");
            algorytmyBuilder.append(',');
            algorytmyBuilder.append("porównaniaMQuick");
            algorytmyBuilder.append(',');
            algorytmyBuilder.append("przestawieniaMQuick");
            algorytmyBuilder.append(',');
            algorytmyBuilder.append("czasMQuick");
            algorytmyBuilder.append('\n');*/
            for (int i = 1; i <= 100; i++) {
                for(int j = 1; j <= k; j++) {
                    System.out.println(i);
                    printWriter.println("n = " + i);
                    printWriter.println("k = " + j);
                    /*algorytmyBuilder.append(i);
                    algorytmyBuilder.append(',');
                    algorytmyBuilder.append(j);
                    algorytmyBuilder.append(',');*/
                    int[] t1 = new int [i];
                    int[] t2 = new int [i];
                    int[] t3 = new int [i];
                    int[] t4 = new int [i];
                    int[] t5 = new int [i];
                    a.generator(t1);
                    a.coppy(t1, t2);
                    a.coppy(t1, t3);
                    a.coppy(t1, t4);
                    a.coppy(t1, t5);
                    printWriter.println("SelectSort: ");
                    start = System.nanoTime();
                    a.selectSort(t1, t1.length, x);
                    stop = System.nanoTime();
                    total1 = (stop - start);
                    /*algorytmyBuilder.append(a.getc());
                    algorytmyBuilder.append(',');
                    algorytmyBuilder.append(a.gets());
                    algorytmyBuilder.append(',');
                    algorytmyBuilder.append(total1);
                    algorytmyBuilder.append(',');
                    printWriter.print(a.getcs());
                    printWriter.print("\t\tCzas: " + total1 + " ns");*/
                    printWriter.println("\nInsertSort: ");
                    start = System.nanoTime();
                    a.insertSort(t2, t2.length, x);
                    stop = System.nanoTime();
                    total2 = (stop - start);
                    /*algorytmyBuilder.append(a.getc());
                    algorytmyBuilder.append(',');
                    algorytmyBuilder.append(a.gets());
                    algorytmyBuilder.append(',');
                    algorytmyBuilder.append(total2);
                    algorytmyBuilder.append(',');*/
                    printWriter.print(a.getcs());
                    printWriter.print("\t\tCzas: " + total2 + " ns");
                    printWriter.println("\nHeapSort: ");
                    start = System.nanoTime();
                    a.heapSort(t3, t3.length, x);
                    stop = System.nanoTime();
                    total3 = (stop - start);
                    /*algorytmyBuilder.append(a.getc());
                    algorytmyBuilder.append(',');
                    algorytmyBuilder.append(a.gets());
                    algorytmyBuilder.append(',');
                    algorytmyBuilder.append(total3);
                    algorytmyBuilder.append(',');*/
                    printWriter.print(a.getcs());
                    printWriter.print("\t\tCzas: " + total3 + " ns ");
                    printWriter.println("\nQuickSort: ");
                    start = System.nanoTime();
                    a.quickSort(t4, 0, (t4.length - 1), x);
                    stop = System.nanoTime();
                    total4 = (stop - start);
                    /*algorytmyBuilder.append(a.getc());
                    algorytmyBuilder.append(',');
                    algorytmyBuilder.append(a.gets());
                    algorytmyBuilder.append(',');
                    algorytmyBuilder.append(total4);
                    algorytmyBuilder.append(',');*/
                    printWriter.print(a.getcs());
                    printWriter.print("\t\tCzas: " + total4 + " ns ");
                    printWriter.println("\nmQuickSort: ");
                    start = System.nanoTime();
                    a.mQuickSort(t5, 0, (t5.length - 1), x);
                    stop = System.nanoTime();
                    total5 = (stop - start);
                    /*algorytmyBuilder.append(a.getc());
                    algorytmyBuilder.append(',');
                    algorytmyBuilder.append(a.gets());
                    algorytmyBuilder.append(',');
                    algorytmyBuilder.append(total5);
                    algorytmyBuilder.append('\n');*/
                    printWriter.print(a.getcs());
                    printWriter.print("\t\tCzas: " + total5 + " ns ");
                    printWriter.println("\n");
                }
                printWriter.println("\n");
            }
            /*algorytmyWriter.write(algorytmyBuilder.toString());
            algorytmyWriter.close();*/
            printWriter.close();
        } else {
            System.out.println("Podano błędne parametry! Parametr --stat musi zawierac nazwę pliku i ilo powtórzeń większš od 0!");
            System.exit(0);
        }
    }
}
