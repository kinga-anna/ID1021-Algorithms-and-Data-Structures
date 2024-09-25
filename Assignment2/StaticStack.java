public class StaticStack {
    int[] stack;
    int top;
    public StaticStack(int size) {
        this.stack = new int[size];
        this.top = 0;
    }
    public void push(int val) {
        if (top<stack.length) stack[top++]=val;
        else System.out.println("Stackoverflow");
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
    StaticStack s = new StaticStack(16);
    s.push(32);
    s.push(33);
    s.push(34);
    s.print(s.pop());
    s.print(s.pop());
    s.print(s.pop());
    s.print(s.pop());
    /*System.out.println("pop : " + s.pop());
    System.out.println("pop : " + s.pop());
    System.out.println("pop : " + s.pop());*/
    }
}
    
