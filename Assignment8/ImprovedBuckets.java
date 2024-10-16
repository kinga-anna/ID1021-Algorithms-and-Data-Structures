import java.io.BufferedReader;
import java.io.FileReader;

import javafx.scene.Node;


public class ImprovedBuckets {
    Area[] postnr;
    int[] keys;
    int max = 20323;
    
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
    public ImprovedBuckets(String file) {
        this.postnr = new Area[this.max];
        this.keys = new int[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            int count = 0;
            while ((line = br.readLine()) != null && i < this.max) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                if (postnr[code%max]==null){
                    postnr[code%max] = new Area(code,row[1], Integer.valueOf(row[2]));
                    keys[i] = code;
                }
                else{
                    int j;
                    for (j = code%max+1; j<max; j++){
                        if (postnr[j%max]==null){
                            postnr[j%max] = new Area(code,row[1], Integer.valueOf(row[2]));
                            keys[i] = code;
                            break;
                        }
                    }
                    if (j==this.max) {count++;keys[i]=code;}
                }
                i++;
            }
            System.out.println("count: "+count);
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }/* 
        this.keys = new int[maxi];
        int j = 0;
        for (int i=0; i<max; i++){
            if (this.postnr[i]!=null){
                this.keys[j++]=this.postnr[i].code;
            }
        }
        for (int i=0; i<keys.length; i++){
            System.out.print(keys[i]+", ");
        }*/
    }
    
    public Integer linear_lookup(Integer key){
        if (this.postnr[key%max]!=null){
            int i = key%max;
            int r = 1;
            while (i<this.max && this.postnr[i]!=null){
                if (this.postnr[i].code.equals(key)) return r;
                else{r++;}
                i++;
            }
            return r;
        }
        return null;
    }

    public static void main(String[] args) {
        ImprovedBuckets zip = new ImprovedBuckets("postnummer.csv");
        //System.out.println(zip.postnr[0].code);
        System.out.println(zip.linear_lookup(11115));
        System.out.println(zip.linear_lookup(98499));

        int k = 200;
        for (int i=0; i<1000; i++){
            zip.linear_lookup(29312);
        }
        /*long min = Long.MAX_VALUE;
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
        System.out.println("111 15: "+min+", 984 99: "+min2);*/

        Integer max = Integer.MIN_VALUE;
        Integer sum = 0;
        for (int i=0; zip.keys[i]!=0; i++){
            Integer r = zip.linear_lookup(zip.keys[i]);
            if (r==null) System.out.println("h");
            if (r>max) max=r;
            sum+=r;
        }
        System.out.println("max: "+max+"\tavg: "+sum/zip.max);
    }
}