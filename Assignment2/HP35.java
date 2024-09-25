import java.io.*;

public class HP35 {
    public static void main(String[] args) throws IOException {
        DynamicStack stack = new DynamicStack(4);
        System.out.println("HP-35 pocket calculator");
        boolean run = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(run) {
            System.out.print(" > ");
            String input = br.readLine();
            int one, two;
            switch (input) {
                case "+":
                    one = stack.pop();
                    two = stack.pop();
                    stack.push(one + two);
                    break;
                case "-":
                    one = stack.pop();
                    two = stack.pop();
                    stack.push(two-one);
                    break;
                case "*":
                    one = stack.pop();
                    two = stack.pop();
                    stack.push(one*two);
                    break;
                case "/":
                    one = stack.pop();
                    two = stack.pop();
                    stack.push(two/one);
                    break;
                case "":
                    run = false;
                    break;
                default:
                    try{
                        Integer nr = Integer.parseInt(input);
                        stack.push(nr);
                    }
                    catch(NumberFormatException e){
                        System.out.println("Not a number or operand(+,-,*,/). Please enter a single digit or operand");
                    }
                    break;
            }
        }
        if (stack.top==1){
            System.out.printf("the result is: %d\n\n", stack.pop());
            System.out.printf("I love reversed polish notation, don't you?\n");
        }
        else System.out.println("Sorry, something went wrong.");
    }
}


