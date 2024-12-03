import java.sql.Connection;

public class Naive {
    private static Integer shortest(Graph.City from, Graph.City to, Integer max) {
        if (max < 0) return null;
        if (from == to) return 0;
        Integer shrt = null;
        for (int i = 0; i < from.connections.length; i++) {
            if (from.connections[i] != null) {
                Graph.Connection conn = from.connections[i];
                Integer curr_dist = shortest(conn.to, to, max-conn.length);
                if (curr_dist!=null){
                    if (shrt==null || shrt>curr_dist+conn.length){
                        shrt = curr_dist+conn.length;
                    }
                }
                //if it is, we just need to move on
            }
        }
        return shrt;
    }
    public static void main(String[] args) {
        Graph map = new Graph("trains.csv");

        String from = args[0];
        String to = args[1];
        Integer max = Integer.valueOf(args[2]);

        long t0 = System.nanoTime();
        Integer dist = shortest(map.find(from), map.find(to), max);
        long time = (System.nanoTime() - t0)/1_000_000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)");
    }
}