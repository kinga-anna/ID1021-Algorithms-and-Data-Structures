import java.util.Random;
import java.util.Stack;

public class BinaryTree{
    public Node root;

    public class Node{
        public Integer value;
        public Node left, right;

        public Node(Integer value) {
            this.value = value;
            this.left = this.right = null;
        }
        
        private void print(){
            if(left != null)
            left.print();
            System.out.println(value);
            if(right != null)
            right.print();
        }
    }

    public BinaryTree() {
        root = null;
    }

    public void add(Integer value){
        if (this.root==null){
            this.root=new Node(value);
            return;
        }
        else add(value, root);
    }

    private void add(Integer value, Node current){
        if (current.value==value){
            return;
        }
        if (current.value>value){
            if (current.left==null){
                current.left=new Node(value);
                return;
            }
            else add(value,current.left);
            return;
        }
        else{
            if (current.right==null){
                current.right=new Node(value);
                return;
            }
            else add(value,current.right);
            return;
        }
    }

    public boolean lookup(Integer key){
        if (this.root==null) return false;
        return lookup (key, root);
    }

    public boolean lookup(Integer key, Node current){
        if (current.value==key){
            return true;
        }
        if (current.value>key){
            if (current.left==null){
                return false;
            }
            else return lookup(key,current.left);
        }
        else{
            if (current.right==null){
                return false;
            }
            else return lookup(key,current.right);
        }
    }
    
    public void print() {
        DoublyLinkedQueue q = new DoublyLinkedQueue();
        Node current = this.root;
        while(current != null){
            System.out.println(current.value);
            if(current.left!=null) {
                q.enqueue(current.left);
            }
            if(current.right!=null){
                q.enqueue(current.right);
            }
            current=q.dequeue();
        }
    }

    /*public Sequence sequence(){
        return new Sequence(this.root);
    }*/

    public Sequence sequence(){
        return new Sequence(this.root);
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.add(8);
        bt.add(3);
        bt.add(12);
        bt.add(1);
        bt.add(2);
        bt.add(4);
        bt.add(7);
        bt.add(27);
        bt.add(18);
        bt.add(23);
        bt.add(5);
        bt.add(9);
        bt.add(20);
        bt.add(17);
        bt.add(15);
        bt.add(0);

        Sequence seq = bt.sequence();
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        
        //bt.print();

        /*Sequence seq = bt.sequence();
        System.out.println(seq.next());
        System.out.println(seq.next());*/
        
    }
}
