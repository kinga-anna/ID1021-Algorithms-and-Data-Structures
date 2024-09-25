import java.util.Random;

public class InsertionSort{
    public static long sort(int[] array){
        long t0 = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0 && array[j-1]>array[j] ; j--) {
                swap(array, j, j-1);
            }
        }
        long t1 = System.nanoTime();
        long t = t1-t0;
        return t;
    }
    public static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i]=array[j];
        array[j]=temp;
    }
    public static void print(int[] array){
        for (int i = 0; i<array.length; i++){
            System.out.print(array[i]+", ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        int[] sizes = {10000,100,200,400,800,1600,3200,6400,12800};
        int k = 100;
        Random rnd = new Random();
        long t;
        //System.out.print("Ignore 1st!!!");
        for (int n : sizes){
            long min = Long.MAX_VALUE;
            for (int i = 0; i<k; i++){
                int[] array = new int[n];
                for (int j = 0; j < n; j++) {
                    array[j] = rnd.nextInt(n*2);
                }
                t = sort(array);
                if (t<min) min=t;
            }
            System.out.println(n + " " + min + " ns");
        }
    }
}