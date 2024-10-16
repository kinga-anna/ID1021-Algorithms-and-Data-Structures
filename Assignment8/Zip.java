import java.io.BufferedReader;
import java.io.FileReader;

import javafx.scene.Node;


public class Zip {
    Area[] postnr;
    int max = 10000;
    public class Area {
        Integer code;
        String name;
        Integer population;

        public Area(Integer code, String name, Integer population){
            this.code=code;
            this.name=name;
            this.population = population;
        }
    }
    public Zip(String file) {
        this.postnr = new Area[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < this.max) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                postnr[i++] = new Area(code, row[1], Integer.valueOf(row[2]));
            }
            this.max = i;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }
    
    public boolean linear_lookup(Integer key){
        for (int i = 0; i<this.max; i++){
            if (this.postnr[i].code.equals(key)) return true;
        }
        return false;
    }

    public boolean binary_lookup(Integer key, int from, int to){
        int mid = (from+to)/2;
        if (this.postnr[mid].code.equals(key)) return true;
        if(this.postnr[mid].code.compareTo(key)>0 && from<mid){
            return binary_lookup(key, from, mid-1);
        }
        if(this.postnr[mid].code.compareTo(key)<0 && mid<to){
            return binary_lookup(key, mid+1, to);
        }
        else return false;
    }

    public static void main(String[] args) {
        Zip zip = new Zip("postnummer.csv");
        //System.out.println(zip.postnr[0].code);
        System.out.println(zip.binary_lookup(11115,0,zip.max-1));
        System.out.println(zip.binary_lookup(98599,0,zip.max-1));

        int k = 200;
        for (int i=0; i<1000; i++){
            zip.binary_lookup(29312,0,zip.max-1);
        }
        long min = Long.MAX_VALUE;
        long min2 = Long.MAX_VALUE;
        for (int i=0; i<k; i++){
            long t0 = System.nanoTime();
            zip.binary_lookup(11115,0,zip.max-1);
            long t1 = System.nanoTime();
            if (t1-t0<min) min=t1-t0;
            long t2 = System.nanoTime();
            zip.binary_lookup(98499,0,zip.max-1);
            long t3 = System.nanoTime();
            if (t3-t2<min2) min2=t3-t2;
        }
        System.out.println("111 15: "+min+", 984 99: "+min2);
    }
}
