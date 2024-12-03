import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Graph{
    City[] cities;
    private final int mod;

    public class City{
        String name;
        Connection[] connections;

        public City(String name){
            this.name = name;
            connections = new Connection[4];
        }

        public void connect(City to, int length){
            for (int i = 0; i<4; i++){
                if(this.connections[i]==null){
                    this.connections[i] = new Connection(to, length);
                    return;
                }
            }
            System.out.println("problem");
        }
    }

    public class Connection{
        City to;
        int length;

        public Connection(City to, int length){
            this.to = to;
            this.length = length;
        }
    }

    public City find(String name){
        Integer index = hash(name, mod);
        if (this.cities[index]==null){
            this.cities[index] = new City(name);
            return this.cities[index];
        }
        while(cities[index%cities.length]!=null){
            if(this.cities[index%this.cities.length].name.equals(name)) return this.cities[index%this.cities.length];
            //System.out.println("collision!: "+name+" + "+index);
            index++;
        }
        this.cities[index%this.cities.length] = new City(name);
        return this.cities[index%this.cities.length];
    }

    private static Integer hash(String name, int mod) {
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash*31 + name.charAt(i)) % mod;
        }
        return hash;
    }
    
    public Graph(String file){
        this.mod = 223;
        this.cities = new City[mod];
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))){
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                City one = this.find(row[0]);
                City two = this.find(row[1]);
                one.connect(two, Integer.parseInt(row[2]));
                two.connect(one,Integer.parseInt(row[2]));
                count++;
            }
            //System.out.println(count);
        }catch (UnsupportedEncodingException e) {
            System.out.println(e.toString());
        }catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /*public static void main(String[] args) {
        Graph gr = new Graph("trains.csv");
        int count = 0;
        for (City c : gr.cities){
            if (c!=null){
                for (Connection con : c.connections){
                    if (con!=null){
                        System.out.println(c.name +" -> "+ con.to.name);
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }*/
}
