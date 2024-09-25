public class Bench{
    public static void main(String[] args) {
        int[] sizes = {100,200,400,800,1600,3200,6400};
        int k = 200;
        //StaticStack s = new StaticStack(k);
        DynamicStack s = new DynamickStack(k);

        //JIT warmup
        for (int i = 0; i<100000; i++){
            s.push(i);
            s.pop();
        }

        for (int n : sizes){
            //s = new StaticStack(n);
            s= new DynamicStack(100);
            long min = Long.MAX_VALUE;
            for (int i = 0; i<k; i++){
                long t0 = System.nanoTime();
                for (int j = 0; j<n; j++){
                    s.push(j);
                }
                long t1 = System.nanoTime();
                long t = (t1-t0);
                if (t<min) min=t;
                for (int l = 0; l<n; l++){
                    s.pop();
                }
            }
            System.out.println(n + " " + min + " ns");
        }

    }
}