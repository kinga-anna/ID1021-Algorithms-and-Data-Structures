public class DoublyLinkedQueue{
    Node head;
    Node tail;
    private class Node{
        BinaryTree.Node item;
        Node prev;
        Node next;
        private Node(BinaryTree.Node item, Node prev, Node next){
            this.item = item;
            this.prev=prev;
            this.next=next;
        }
    }

    public DoublyLinkedQueue(){
        head=null;
        tail=null;
    }

    public void enqueue(BinaryTree.Node item){
        if (this.tail==null) this.head = this.tail = new Node(item, null, null);
        else{
            Node new_node = new Node(item,this.tail,null);
            this.tail.next = new_node;
            this.tail = new_node;  
        }
    }

    public BinaryTree.Node dequeue(){
        if (this.head==null) return null;
        BinaryTree.Node r = this.head.item;
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
            System.out.print(current.item.value+",");
            current=current.next;
        }
        System.out.print(current.item.value);
        System.out.println("]");
    }
}