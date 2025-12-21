public class GenericStack <T extends Comparable>{
    T [] values;
    int top;
    
    public GenericStack(Class <T> dataType, int size){
        //values = T[size] olmalı ama javada olmuyor.
        this.values = (T[]) java.lang.reflect.Array.newInstance(dataType, size);
        this.top = -1;
    }
    
    public int Size(){
        return this.values.length;
    }
    
    public void clear(){
        this.top = -1;
    }
    
    public boolean isEmpty(){
        return this.top == -1;
    }
    
    public boolean isFull(){
        return this.top == this.values.length - 1;
    }
    
    public T pop() throws Exception{
        if(isEmpty()){
            throw new Exception("Stack is Empty!");
        }else{
            T temp = this.values[this.top];
            this.top--;
            return temp;
          //return this.values[this.top--];
        }
    }
    
    public void push(T val){
        if (isFull()){
            System.out.println("Stack is full!");
        }else{
            this.values[++this.top] = val;
        }
    }
    
    public T peek() throws Exception{
        if(isEmpty()){
            throw new Exception("Stack is empty!");
        }else{
            return this.values[this.top];
        }
    }
    
    public void display(){
        if(isEmpty()){
            System.out.println("Stack is Empty!");
        }else{
            int temp = this.top;
            while(temp != -1){
                System.out.println(this.values[temp]);
                temp--;
             // System.out.println(this.values[temp--]);
            }
        }
    }
    
    public boolean search(T val){
        if(isEmpty()){
            return false;
        }
      /*else{
            int temp = this.top;
            while(temp != -1){
                if(this.values[temp].compareTo(val) == 0){
                    return true;
                }
                temp--;
            }
        }
        return false;
      */
        for(int i=0; i <= this.top; i++){
            if(this.values[i].compareTo(val) == 0){
                return true;
            }
        }
        return false;
    }
}
