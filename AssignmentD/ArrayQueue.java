import java.util.Random;

public class ArrayQueue{
    int[] q;
    int first=0;
    int last=0;
    int count=0;

    public ArrayQueue(int n){
        q=new int[n];
    }

    public void lengthen(){
        int[] new_q = new int[2*this.q.length];
        for (int i = first; i<last; i++){
            new_q[i-first]=q[i%this.q.length];
        }
        last=last-first;
        first=0;
        q=new_q;
    }
    public void shrink(){
        int[] new_q = new int[this.q.length/2];
        for (int i = first; i<last; i++){
            new_q[i-first]=q[i%this.q.length];
        }
        last=last-first;
        first=0;
        q=new_q;
    }

    public void enqueue(int item){
        if(this.count==this.q.length){
            lengthen();
        }
        this.q[last%this.q.length]=item;
        last++;
        count++;
    }

    public Integer dequeue(){
        if(count<this.q.length/3&&this.q.length>6){
            shrink();
        }
        if (first!=last){
            //we can dequeue
            int r = this.q[first%this.q.length];
            first++;
            count--;
            return r;
        }
        else return null;
    }

    public void print(){
        for (int i=0; i<this.q.length; i++){
            System.out.print(this.q[i]+",");
        }
        System.out.println("first: "+this.first+" last: "+last+" count: "+count);
    }

    public void print_queue(){
        for (int i=this.first%this.q.length; i<this.first%this.q.length+count; i++){
            System.out.print(this.q[i%this.q.length]+",");
        }
        System.out.println("first: "+this.first+" last: "+last+" count: "+count);
    }

    public static void main(String[] args){
        int[] sizes = {100000,100,200,400,800,1600,3200,6400,12800};
        int k = 500;
        Random rnd = new Random();
        for (int n:sizes){
            long min = Long.MAX_VALUE;
            long min2 = Long.MAX_VALUE;
            long min3 = Long.MAX_VALUE;
            for (int i=0; i<k; i++){
                ArrayQueue q = new ArrayQueue(100);
                int[] op = new int[n];
                for (int j=0; j<n; j++){
                    op[j] = rnd.nextInt(2);
                }
                long t0=System.nanoTime();
                for (int j=0; j<n; j++){
                    if(op[j]==0){
                        q.enqueue(j);
                    }
                    else{
                        q.dequeue();
                    }
                }
                long t1=System.nanoTime();
                if (t1-t0<min) min=t1-t0;
                //ArrayQueue q2 = new ArrayQueue(10);
                //ArrayQueue q3 = new ArrayQueue(2);
                /*for (int j = 0; j<n; j++){
                    q.enqueue(j);
                }
                long t0=System.nanoTime();
                for (int h=0; h<n; h++){
                    //q.enqueue(h);
                    q.dequeue();
                }
                long t1=System.nanoTime();
                if (t1-t0<min) min=t1-t0;
                for (int h=0; h<n; h++){
                    //q.dequeue();
                    q.enqueue(h);
                }*/
                /*long t2=System.nanoTime();
                for (int h=0; h<n; h++){
                    q2.enqueue(h);
                    //q.dequeue();
                }
                long t3=System.nanoTime();
                if (t3-t2<min2) min2=t3-t2;
                for (int h=0; h<n; h++){
                    q2.dequeue();
                }long t4=System.nanoTime();
                for (int h=0; h<n; h++){
                    q3.enqueue(h);
                    //q.dequeue();
                }
                long t5=System.nanoTime();
                if (t5-t4<min3) min3=t5-t4;
                for (int h=0; h<n; h++){
                    q3.dequeue();
                }*/
            }
            System.out.println(n+" "+min+" ns");
            //System.out.println(n+" "+min+"  "+min2+"    "+min3+" ns");
        }
    }
}
