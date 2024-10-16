import java.io.BufferedReader;
import java.io.FileReader;

import javafx.scene.Node;


public class Buckets {
    Bucket[] postnr;
    int max = 17389;

    public class Bucket{
        Area[] elements;
        public Bucket(Integer code, String name, Integer population){
            this.elements = new Area[1];
            elements[0] = new Area(code, name, population);
        }
        public void add(Integer code, String name, Integer population){
            Area[] new_array = new Area[elements.length+1];
            for (int i=0; i<elements.length; i++){
                new_array[i]=elements[i];
            }
            new_array[elements.length]=new Area(code, name, population);
            this.elements = new_array;
        }
    }
    
    public class Area {
        Integer code;
        String name;
        Integer population;

        public Area(Integer code, String name, Integer population){
            this.code = code;
            this.name=name;
            this.population = population;
        }
    }
    public Buckets(String file) {
        this.postnr = new Bucket[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < this.max) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                if (postnr[code%max]==null){
                    postnr[code%max] = new Bucket(code,row[1], Integer.valueOf(row[2]));
                }
                else{
                    postnr[code%max].add(code,row[1], Integer.valueOf(row[2]));
                }
                i++;
            }
            //this.max = i;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }
    
    public boolean linear_lookup(Integer key){
        if (this.postnr[key%max]!=null){
            for (int i=0; i<this.postnr[key%max].elements.length; i++){
                if (this.postnr[key%max].elements[i].code.equals(key)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Buckets zip = new Buckets("postnummer.csv");
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