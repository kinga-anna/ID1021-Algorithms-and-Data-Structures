import java.io.BufferedReader;
import java.io.FileReader;

import javafx.scene.Node;


public class Zipv2 {
    Area[] postnr;
    int max = 100000;
    public class Area {
        String name;
        Integer population;

        public Area(String name, Integer population){
            this.name=name;
            this.population = population;
        }
    }
    public Zipv2(String file) {
        this.postnr = new Area[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < this.max) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                postnr[code] = new Area(row[1], Integer.valueOf(row[2]));
                i++;
            }
            this.max = i;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }
    
    public boolean linear_lookup(Integer key){
        if (this.postnr[key]!=null) return true;
        else return false;
    }

    public static void main(String[] args) {
        Zipv2 zip = new Zipv2("postnummer.csv");
        //System.out.println(zip.postnr[0].code);
        System.out.println(zip.linear_lookup(11115));
        System.out.println(zip.linear_lookup(98499));

        int k = 200;
        for (int i=0; i<1000; i++){
            zip.linear_lookup(29312);
        }
        long min = Long.MAX_VALUE;
        long min2 = Long.MAX_VALUE;
        for (int i=0; i<k; i++){
            long t0 = System.nanoTime();
            zip.linear_lookup(11115);
            long t1 = System.nanoTime();
            if (t1-t0<min) min=t1-t0;
            long t2 = System.nanoTime();
            zip.linear_lookup(98499);
            long t3 = System.nanoTime();
            if (t3-t2<min2) min2=t3-t2;
        }
        System.out.println("111 15: "+min+", 984 99: "+min2);
    }
}