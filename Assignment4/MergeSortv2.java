import java.util.Random;

public class MergeSortv2{
    public static long sort(int[] org) {
        if (org.length == 0) return Long.MAX_VALUE;
        long t0 = System.nanoTime();
        int[] aux = new int[org.length];
        for (int i = 0; i<org.length; i++){
            aux[i]=org[i];
        }
        sort(org, aux, 0, org.length -1);
        long t1 = System.nanoTime();
        long t = t1-t0;
        return t;
    }
    private static void sort(int[] org, int[] aux, int lo, int hi) {
        if (lo != hi) {
        int mid = (lo + hi)/2;
        // sort the items from lo to mid
        sort(aux, org, lo, mid);
        // sort the items from mid+1 to hi
        sort(aux, org, mid+1, hi);
        // merge the two sections using the additional array
        merge(org, aux, lo, mid, hi);
        }
    }
    private static void merge(int[] org, int[] aux, int lo, int mid, int hi) {
        // copy all items from lo to hi from org to aux
        /*for (int i = lo; i<=hi; i++ ) {
            aux[i]=org[i];
        }*/
        // let's do the merging
        int i = lo; // the index in the first part
        int j = mid+1; // the index in the second part
        // for all indices from lo to hi
        for ( int k = lo; k <= hi; k++) {
        // if i is greater than mid then
        // move the j'th item to the org array, update j
        if (i>mid){
            org[k]=aux[j++];
        }
        // else if j is greate than hi then
        // move the i'th item to the org array, update i
        else if (j>hi){
            org[k]=aux[i++];
        }
        // else if the i'th item is smaller than the j'th item,
        // move the i'th item to the org array, update i
        else if (aux[j]<aux[i]){ //went off-script to ensure stability (?)
            org[k]=aux[j++];
        }
        // else
        // move the j'th item to the org array, update j
        else org[k]=aux[i++];
        }
    }
    public static void print(int[] array){
        for (int i = 0; i<array.length; i++){
            System.out.print(array[i]+", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /*int[] array = {2,6,3,1,5,12,8,9,20};
        sort(array);
        print(array);*/
        int[] sizes = {10000,100,200,400,800,1600,3200,6400,12800,25600};
        int k = 1000;
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