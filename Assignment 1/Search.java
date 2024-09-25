import java.util.Random;

class Search{
    private static long search(int n, int loop) {
        Random rnd = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(n*2);
        }

        int[] keys = new int[loop];
        for (int k = 0; k < loop; k++) {
            keys[k] = rnd.nextInt(n*2);
        }
        int sum = 0;
        long t0 = System.nanoTime();
        for (int i = 0; i < loop; i++) {
            int key = keys[i];
            for (int j = 0; j < n; j++) {
                if (key == array[j]) {
                    sum++;
                    break;
                }
            }
        }
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    public static void main(String[] arg) {
        int[] sizes = {100, 200, 400, 800, 1600, 3200};
        // JIT warmup
        search(100,1000000);
        //System.out.println("done");
        
        int loop = 1000;
        int k = 150;
        for(int n : sizes) {
            long min = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t = search(n, loop);
                if (t < min) min = t;
            }
            System.out.println(n + " " + min + " ns");
        }
    }
}
