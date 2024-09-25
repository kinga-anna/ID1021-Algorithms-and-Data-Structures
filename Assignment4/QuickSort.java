import java.util.Random;

public class QuickSort{
    public static long sort(int[] array) {
        long t0 = System.nanoTime();
        sort(array, 0, array.length);
        long t1 = System.nanoTime();
        long t = t1-t0;
        return t;
    }

    public static void sort(int[] array, int from, int to){
        if (from<to) {
            //System.out.println("hello darling (but in a british accent)");
            int pivot = partition(array, from, to);
            sort(array, from, pivot);
            sort(array, pivot+1, to);
        }
    }

    public static int partition(int[] array, int from, int to){
        int pivot = array[from];
        int i = from - 1;
        int j = to;
        while (true){
            do {
                i++;
            } while (array[i]<pivot);
            do{
                j--;
            }while (array[j] > pivot);
            if (i>=j) return j;
            swap(array,i,j);
        }
    }

    public static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i]=array[j];
        array[j]=temp;
    }

    public static void print(int[] array){
        System.out.print("[");
        for (int i = 0; i<array.length-1; i++){
            System.out.print(array[i]+", ");
        }
        System.out.print(array[array.length-1]+"]");
        System.out.println();
    }

    public static void main(String[] args) {
        /*int[] array = {2,6,3,1,5,12,8,9,20,0};
        sort(array);
        print(array);*/
        int[] sizes = {10000,100,200,400,800,1600,3200,6400};
        int k = 1000;
        Random rnd = new Random();
        long t;
        long t2;
        //int next=0;
        //System.out.print("Ignore 1st!!!");
        for (int n : sizes){
            long min = Long.MAX_VALUE;
            long min2=Long.MAX_VALUE;
            for (int i = 0; i<k; i++){
                int[] array = new int[n];
                for (int j = 0; j < n; j++) {
                    //next += rnd.nextInt(10)+1;
                    array[j] = rnd.nextInt(n*2);
                    //array[j]=next;
                }
                t = sort(array);
                if (t<min) min=t;
                t2 = sort(array);
                if (t2<min2) min2=t2;
            }
            System.out.println(n + " " + min + " ns");
            System.out.println(n + " " + min2 + " ns");
        }
    }
}