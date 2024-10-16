import java.util.Random;
import java.util.Stack;

public class BinaryTree{
    private Node root;

    public class Node{
        private Integer value;
        private Node left, right;

        private Node(Integer value) {
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

    public void add2(Integer value){
        if(this.root==null) this.root = new Node(value);
        Node current = this.root;
        while(true){
            if (current.value==value) return;
            if (current.value<value){
                if (current.right==null){
                    current.right = new Node(value);
                    return;
                }
                current=current.right;
            }
            else{
                if(current.left==null){
                    current.left= new Node(value);
                    return;
                }
                current=current.left;
            }
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
    /*public void print() {
        if (this.root!=null) root.print();
        else System.out.println("Empty tree!");
    }*/
    public void print() {
        DynamicStack s = new DynamicStack(2);
        Node current = this.root;
        while(current.left!=null){
                s.push(current);
                current=current.left;
        }
        // move to the leftmost node
        while(current != null) {  
            // print value of node
            System.out.println(current.value);
            if(current.right != null) {
                current=current.right;
                // move to the leftmost node, push nodes as you go
                while(current.left!=null){
                    s.push(current);
                    current=current.left;
                }
            } else {
                current=s.pop();// pop a node from the stack
            }
        }
    // done
    }

    public static void main(String[] args) {
        /*BinaryTree bt = new BinaryTree();
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
        bt.print();*/

        int[] sizes = {10000,100,200,400,800,1600,3200,6400};
        int k = 100;
        
        for (int n:sizes){
            Long min = Long.MAX_VALUE;
            Random rnd = new Random();
            BinaryTree bt = new BinaryTree();
            for (int i=0; i<n; i++){
                bt.add(rnd.nextInt(n));
            }
            int[] a = new int[1000];
            for (int i = 0; i < 1000; i++) {
                a[i] = rnd.nextInt(n*2);
            }
            for (int j=0; j<k; j++){
                long t0 = System.nanoTime();
                for (int h=0; h<1000; h++){
                    bt.lookup(a[h]);
                }
                long t1 = System.nanoTime();
                if (t1-t0<min) min = t1-t0;
            }
            System.out.println(n+" "+min+" ns");
        }
        
        /*BinaryTree bt = new BinaryTree();
        bt.add(8);
        bt.add(12);
        bt.add(6);
        bt.add(7);
        bt.add(11);
        //bt.add(20);
        //bt.add(8);
        bt.print();
        System.out.println(bt.lookup(9));*/
        
    }
}
