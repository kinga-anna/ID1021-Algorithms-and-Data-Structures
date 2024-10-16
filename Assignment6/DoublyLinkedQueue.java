public class DoublyLinkedQueue{
    Node head;
    Node tail;
    private class Node{
        Integer item;
        Node prev;
        Node next;
        private Node(Integer item, Node prev, Node next){
            this.item = item;
            this.prev=prev;
            this.next=next;
        }
    }

    public DoublyLinkedQueue(){
        head=null;
        tail=null;
    }

    public void enqueue(Integer item){
        if (this.tail==null) this.head = this.tail = new Node(item, null, null);
        else {
            Node new_node = new Node(item,this.tail,null);
            this.tail.next = new_node;
            this.tail = new_node;  
        }
    }

    public Integer dequeue(){
        if (this.head==null) return null;
        Integer r = this.head.item;
        if (this.head.next==null){
            this.head=this.tail=null;
        }
        else{
            this.head.next.prev=null;
            this.head=this.head.next;
        }
        return r;
    }


    public void print(){
        if(this.head==null){
            System.out.println("[]");
            return;
        }
        Node current = this.head;
        System.out.print("[");
        while(current.next!=null){
            System.out.print(current.item+",");
            current=current.next;
        }
        System.out.print(current.item);
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[] sizes = {100000,100,200,400,800,1600,3200};
        int k = 200;

        for (int n:sizes){
            long min = Long.MAX_VALUE;
            for (int i=0; i<k; i++){
                DoublyLinkedQueue q = new DoublyLinkedQueue();
                for (int j = 0; j<n; j++){
                    q.enqueue(i);
                }
                long t0=System.nanoTime();
                for (int h=0; h<1000; h++){
                    q.enqueue(n);
                }
                long t1=System.nanoTime();
                if (t1-t0<min) min=t1-t0;
            }
            System.out.println(n+" "+min/1000+" ns");
        }
    }
}
