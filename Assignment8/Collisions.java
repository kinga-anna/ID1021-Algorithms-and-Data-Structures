import java.io.BufferedReader;
import java.io.FileReader;



public class Collisions {
    Area[] postnr;
    int[] keys;
    int max = 100000;
    public class Area {
        String name;
        Integer population;

        public Area(String name, Integer population){
            this.name=name;
            this.population = population;
        }
    }
    public Collisions(String file) {
        this.postnr = new Area[this.max];
        this.keys = new int[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < this.max) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                postnr[code] = new Area(row[1], Integer.valueOf(row[2]));
                keys[i]=code;
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

    public void collisions(int mod) {
        int mx = 20;
        int[] data = new int[mod];
        int[] cols = new int[mx];

        // keys[] are the zip codes
        for (int i = 0; i < this.max; i++) {
            Integer index = keys[i]%mod;
            data[index]++;
        }
        for(int i = 0; i < mod; i++) {
            if (data[i] < mx)
            cols[data[i]]++;
        }
        System.out.print(mod + ": ");
        for (int i = 1; i < mx; i++) {
            System.out.print("\t" +i+": " + cols[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Collisions zip = new Collisions("postnummer.csv");
        int[] sizes = {10000,15000,20000,12345,17389,10007,13513,13600,14000,18013,20021,20323};
        for (int n : sizes){
            zip.collisions(n);
            System.out.println();
        }
    }
}
