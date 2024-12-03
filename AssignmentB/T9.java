import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.PrintStream;  
import static java.nio.charset.StandardCharsets.UTF_8;  

public class T9{
    Node root;

    public T9(){
        String file = "kelly.txt";
        this.root=new Node();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String word;
            while ((word = br.readLine()) != null) {
                //System.out.print(word + ": \n");
                Node current = this.add(toCode((int)word.charAt(0)));
                
                for(int i = 1; i < word.length()-1; i++) {
                    current = current.add(toCode((int)word.charAt(i)));
                }
                current.addFinal(toCode((int)word.charAt(word.length()-1)));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println(" file " + file + " not found or corrupt");
        }
    }

    public Node add(int i){
        if (this.root.next[i]==null){
            this.root.next[i] = new Node();
        }
        return this.root.next[i];
    }

    private class Node{
        public Node[] next;
        public boolean valid;
        public Node() {
            next = new Node[27];
            valid = false;
        }

        public Node add(int i){
            if (this.next[i]==null){
                this.next[i] = new Node();
            }
            return this.next[i];
        }
        public void addFinal(int i){
            if (this.next[i]==null){
                this.next[i] = new Node();
            }
            this.next[i].valid = true;
        }

        public void collect(String s, int pos, ArrayList<String> result, String path){
            if (pos>=s.length()){ //reached the end of the sequence
                if (this.valid==true){
                    result.add(path);
                    return;
                }
                else return;
            }
            int index = toIndex(s.charAt(pos)-'0');
            //System.out.println(this.next[index*3]);
            pos++;

            String one = path;
            String two = path;
            String three = path;

            if(this.next[index*3]!=null){
                one+=(char)fromCode(index*3);
                //System.out.println(one);
                this.next[index*3].collect(s, pos, result, one);
            }
            if(this.next[index*3+1]!=null){
                two+=(char)fromCode(index*3+1);
                //System.out.println(two);
                this.next[index*3+1].collect(s, pos, result, two);
            }
            if(this.next[index*3+2]!=null){
                three+=(char)fromCode(index*3+2);
                //System.out.println(three);
                this.next[index*3+2].collect(s, pos, result, three);
            }
        }
    }

    public int toCode(int a){
        int[] chars = {97,98,99,100,101,102,103,104,105,106,107,108,109,
            110,111,112,114,115,116,117,118,120,121,122,229,228,246};
        for (int i = 0; i<27; i++){
            if (chars[i]==a) return i;
        }
        return -1;
    }

    public int fromCode(int a){
        int[] chars = {97,98,99,100,101,102,103,104,105,106,107,108,109,
            110,111,112,114,115,116,117,118,120,121,122,229,228,246};
        try{
            return chars[a];
        } catch (IndexOutOfBoundsException e){
            return 0;
        }
    }

    public int toIndex(int k){
        return k-1;
    }

    public int toKey(int k){
        int[] s = {0,3,6,9,12,15,18,21,24};
        int i = 0;
        while (i<s.length && s[i]<=k){
            i++;
        }
        return i;
    }

    public ArrayList<String> decode(String s){
        ArrayList<String> result = new ArrayList<String>();
        String path="";
        int pos=0;
        this.root.collect(s,pos,result,path);
        return result;
        /*args to collect: string s, index to position in string s, ref to list of words?, int array containing path

        check if string.charAt(index)==\0 except Java doesn't nullterminate strings
        {
        if so, we need to add the path to the list and return
        }
        otherwise we go forward to each possible next node with index++ and adding the current char to the path

        //but we need to deep copy the int array so that every path has a different array...


        when we get back to decode: go through list, convert everything to strings and print them
        }*/
    }

    public static void main(String[] args) {
        T9 t = new T9();
        //ArrayList<String> a = t.decode("595126643272");
        //System.out.println(t.decode("595126643272").toString());
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("kelly.txt"), "UTF-8"))) {
            String word;
            while ((word = br.readLine()) != null) {
                System.out.print(word+": ");
                String seq="";
                for (int i = 0; i<word.length(); i++){
                    seq+=t.toKey(t.toCode((int)word.charAt(i)));
                }
                System.out.println(t.decode(seq).toString());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println(" file not found or corrupt");
        }
        System.out.println(t.decode("4341").toString());
        //System.out.println((char)229);
        //System.out.println(t.root.next[0].next[16].next[18].next[14].next[13].valid);
    }
}
