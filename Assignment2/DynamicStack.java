public class DynamicStack {
    int[] stack;
    int top = 0;
    //int size = 1;
    public DynamicStack(int size) {
        stack = new int[size];
    }
    public void push(int val) {
        if (top == stack.length) {
            int [] newstack = new int[stack.length*2];
            copy(newstack);
            //System.out.println("new");
            stack = newstack;
        }
        stack[top++] = val;
    }
    private void copy(int[] newstack){
        for (int i = 0; i<stack.length; i++){
            newstack[i]=stack[i];
        }
    }

    public int pop() {
        if (top>0){
            /*for (int i = 0; i<this.stack.length; i++){ //For testing purposes I added a print and removed popped elements (set to 0) to be able to see how it works
                System.out.println(this.stack[i]);
            }
            int tmp = stack[top];
            stack[top] = 0;
            return tmp;*/
            return stack[--top];
        }
        else return Integer.MIN_VALUE;
    }

    public void print(int nr){
        if (nr!=Integer.MIN_VALUE) System.out.println("pop : " + nr);
        else System.out.println("Nothing to pop:(");
    }

    public static void main(String[] args) {
        DynamicStack s = new DynamicStack(2);
        s.push(32);
        s.push(33);
        s.push(34);
        s.push(35);
        s.push(36);
        s.push(37);
        s.push(38);
        s.push(39);
        s.push(40);
        s.push(41);
        s.push(42);
        s.push(43);
        s.print(s.pop());
        s.print(s.pop());
        s.print(s.pop());
        s.print(s.pop());
    }
}