public class Sequence{
    private DoublyLinkedQueue q;

    public Sequence(BinaryTree.Node root){
        this.q = new DoublyLinkedQueue();
        this.q.enqueue(root);
    }

    public Integer next(){
        BinaryTree.Node r = q.dequeue();
        if (r==null) return null;
        else{
            if (r.left!=null) q.enqueue(r.left);
            if (r.right!=null) q.enqueue(r.right);
            return r.value;
        }
    }
}