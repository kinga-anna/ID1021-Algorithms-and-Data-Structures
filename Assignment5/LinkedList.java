import java.util.Random;

public class LinkedList {
    Cell first;
    public class Cell {
        int head;
        Cell tail;
        Cell(int val, Cell tl) {
            head = val;
            tail = tl;
        }
    }
    public LinkedList(){
        first = null;
    }
    public LinkedList(int n) {
        Cell last = null;
        for (int i = 0; i < n; i++) {
            last = new Cell(i, last);
        }
        first = last;
    }
        
    /*ADD TO END OF LIST 
    public void add(int item){
        Cell new_cell = new Cell(item, null);
        if (this.first==null){
            this.first=new_cell;
            return;
        }
        Cell current = this.first;
        while(current.tail!=null){
            current=current.tail;
        }
        current.tail = new_cell;
    }*/
    public void add(int item){
        Cell new_cell = new Cell(item, this.first);
        this.first = new_cell;
    }

    public int length(){
        int length = 0;
        Cell current = this.first;
        while(current!=null){
            length++;
            current=current.tail;
        }
        return length;
    }

    public boolean find(int item){
        Cell current = this.first;
        while(current!=null){
            if(current.head==item){
                return true;
            }
            current=current.tail;
        }
        return false;
    }

    public void remove(int item){
        if (this.first==null) return;
        if (this.first.head==item){
            this.first=this.first.tail;
            return;
        }
        Cell current=this.first;
        while(current.tail!=null){
            if (current.tail.head==item){
                current.tail=current.tail.tail;
                return;
            }
            current=current.tail;
        }
    }

    public void remove2(int item) {
        if (this.first==null) return;
        if (this.first.head==item){
            this.first=this.first.tail;
            return;
        }
        Cell current=this.first.tail;
        Cell prev=this.first;
        while(current!=null){
            if(current.head==item){
                prev.tail=current.tail;
            }
            prev=current;
            current=current.tail;
        }
    }

    public void append(LinkedList b) {
        if (this.first==null){
            first=b.first;
            return;
        }
        Cell current = this.first;
        while (current.tail != null) {
            current=current.tail;
        }
        current.tail=b.first;
        b.first=null;
    }

    public void print(){
        Cell current = this.first;
        System.out.print("[");
        while(current!=null){
            System.out.print(current.head+",");
            current=current.tail;
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[] sizes = {100000,100,200,400,800,1600,3200,6400,12800};
        int k = 10000;

        for (int n:sizes){
            long min = Long.MAX_VALUE;
            Random rnd = new Random();
            LinkedList list_a = new LinkedList(100);
            LinkedList list_b = new LinkedList(n);
            for (int i=0; i<k; i++){
                long t0 = System.nanoTime();
                list_a.append(list_b);
                long t1 = System.nanoTime();
                if (t1-t0<min) min=t1-t0;
            }
            System.out.println(n+" "+min+" ns");
        }

        /*LinkedList list = new LinkedList();
        list.add(8);
        list.add(12);
        list.add(2);
        list.add(1);
        list.add(2);
        list.print();
        list.remove2(9);
        list.print();
        LinkedList list2 = new LinkedList();
        list2.add(3);
        list2.add(7);
        list2.add(19);
        list2.print();
        list.append(list2);
        list.print();
        LinkedList list3 = new LinkedList();
        list3.append(list);
        list.print();
        list.remove(3);
        list3.print();
        System.out.println(list.length());
        System.out.println(list.find(8));
        System.out.println(list.find(9));*/
    }
}
