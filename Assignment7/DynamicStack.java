public class DynamicStack {
    BinaryTree.Node[] stack;
    int top = 0;
    //int size = 1;
    public DynamicStack(int size) {
        stack = new BinaryTree.Node[size];
    }
    public void push(BinaryTree.Node val) {
        if (top == stack.length) {
            BinaryTree.Node[] newstack = new BinaryTree.Node[stack.length*2];
            copy(newstack);
            //System.out.println("new");
            stack = newstack;
        }
        stack[top++] = val;
    }
    private void copy(BinaryTree.Node[] newstack){
        for (int i = 0; i<stack.length; i++){
            newstack[i]=stack[i];
        }
    }

    public BinaryTree.Node pop() {
        if (top>0){
            /*for (int i = 0; i<this.stack.length; i++){ //For testing purposes I added a print and removed popped elements (set to 0) to be able to see how it works
                System.out.println(this.stack[i]);
            }
            int tmp = stack[top];
            stack[top] = 0;
            return tmp;*/
            return stack[--top];
        }
        else return null;
    }

    /*public void print(int nr){
        if (nr!=Integer.MIN_VALUE) System.out.println("pop : " + nr);
        else System.out.println("Nothing to pop:(");
    }*/

    /*public static void main(String[] args) {
        DynamicStack s = new DynamicStack(2);
        s.push(new BinaryTree.Node(2));
    }*/
}