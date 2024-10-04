public class Queue{
    Node head;
    private class Node{
        Integer item;
        Node next;
        private Node(Integer item, Node list){
            this.item = item;
            this.next = list;
        }
    }
    public Queue(){
        head = null;
    }
    public void enqueue(Integer item){
        Node new_node = new Node(item, null);
        if (this.head==null){
            this.head=new_node;
            return;
        }
        Node current = this.head;
        while(current.next!=null){
            current=current.next;
        }
        current.next = new_node;
    }
    public Integer dequeue(){
        if (this.head==null) return null;
        Integer r = head.item;
        head=head.next;
        return r;
    }
    public void print(){
        if (this.head==null){
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
        int[] sizes = {1000,100,200,400,800,1600,3200};
        int k = 200;

        for (int n:sizes){
            long min = Long.MAX_VALUE;
            for (int i=0; i<k; i++){
                Queue q = new Queue();
                for (int j = 0; j<n; j++){
                    q.enqueue(i);
                }
                long t0=System.nanoTime();
                for (int h=0; h<1000; h++){
                    q.dequeue();
                }
                long t1=System.nanoTime();
                if (t1-t0<min) min=t1-t0;
            }
            System.out.println(n+" "+min/1000+" ns");
        }
    }
}
