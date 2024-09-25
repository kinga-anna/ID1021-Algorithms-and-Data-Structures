import java.util.Random;

public class Search{
    public static boolean unsorted_search(int[] array, int key) {
        for (int index = 0; index < array.length ; index++) {
            if (array[index] == key) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean sorted_search(int[] array, int key) {
        for (int index = 0; index<array.length; index++){
            if (array[index] == key) {
                return true;
            }
            if (array[index]>key) {
                return false;
            }
        }
        return false;
    }

    public static boolean binary_search(int[] array, int key) {
        int first = 0;
        int last = array.length-1;
        while (true) {
            int index = (first+last)/2 ;
            if (array[index] == key) {
                return true;
            }
            else if (array[index] < key && index < last) {
                first = index+1 ;
            }
            else if (array[index] > key && index > first) {
                last = index-1 ;
            }
            else return false;
        }
    }

    private static boolean rec_binary_search(int[] arr, int key, int min, int max) {
        int mid = min + ((max - min)/2);
        if (arr[mid] == key) {
            return true;
        }
        if ((arr[mid] > key) && (min < mid)) {
            return rec_binary_search(arr, key, min, mid-1);
        }
        if ((arr[mid] < key) && (mid < max)) {
            return rec_binary_search(arr, key, mid+1, max);
        }
        else return false;
    }
        

    public static void main(String[] args) {
        int[] sizes = {1000,1000000};
        int k = 10000;
        //JIT warmup
        for (int n : sizes){
            long min = Long.MAX_VALUE;
            Random rnd = new Random();
            int[] array_k = new int[1000];
            for (int i = 0; i < 1000; i++) {
                array_k[i] = rnd.nextInt(n*2);
            }
            /*int[] array_v = new int[n];
            for (int i = 0; i < n; i++) {
                array_v[i] = rnd.nextInt(n*2);
            }*/
            int[] array_v = new int[n];
            int nxt = 0;
            for (int i = 0; i < n ; i++) {
                nxt += rnd.nextInt(10) + 1;
                array_v[i] = nxt;
            }
            for (int i=0; i<k; i++){
                long t0 = System.nanoTime();
                for (int j = 0; j<1000; j++){
                    rec_binary_search(array_v, array_k[j], 0, array_v.length-1);
                }
                long t1 = System.nanoTime();
                if (t1-t0<min) min=t1-t0;
            }
            System.out.println(n+" "+min+" ns");
        }

    }
}
   
