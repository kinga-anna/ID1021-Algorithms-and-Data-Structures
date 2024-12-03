public class DynamicMax {
    Graph.City[] path;
    int sp;

    public DynamicMax() {
        path = new Graph.City[52];
        sp = 0;
    }

    private Integer shortest(Graph.City from, Graph.City to, Integer max) {
        if (max!=null && max<0) return null;
        if (from==to) return 0;
        Integer shrt = null;
        for (int i = 0; i < sp; i++) {
            if (path[i] == from)
            return null;
        }
        path[sp++] = from;
        for (int i = 0; i < from.connections.length; i++) {
            if (from.connections[i] != null) {
                Graph.Connection conn = from.connections[i];
                Integer curr_dist;
                if (max==null) curr_dist = shortest(conn.to,to,max);
                else curr_dist = shortest(conn.to, to, max-conn.length);

                if (curr_dist!=null){
                    if (shrt==null || shrt>curr_dist+conn.length){
                        shrt = curr_dist+conn.length;
                        max = shrt;
                    }
                }
            }
        }
        path[sp--] = null;
        return shrt;
    }
    
    public static void main(String[] args) {
        Graph map = new Graph("trains.csv");
        DynamicMax dm = new DynamicMax();

        String from = args[0];
        String to = args[1];

        long t0 = System.nanoTime();
        Integer dist = dm.shortest(map.find(from), map.find(to), null);
        long time = (System.nanoTime() - t0)/1_000_000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)");
    }
}
