public class CircularQueue <T extends Comparable> {
    private T [] values;
    private int front;
    private int rear;
    
    public CircularQueue(Class <T> dataType,int size){
        //values = new T[size] olmalı ama java da olmuyor.
        this.values = (T[]) java.lang.reflect.Array.newInstance(dataType, size);
    }
    
    public void clear(){
        this.rear = this.front = 0;
    }
    
    public boolean isEmpty(){
        return this.rear == this.front;
    }
    
    public boolean isFull(){
        return (this.rear + 1) % this.values.length == this.front;
    }
    
    public void enQueue(T val){
        if(isFull()){
            System.out.println("Queue is full!");
            return;
        }
        this.rear = (this.rear + 1) % this.values.length;
        this.values[this.rear] = val;
    }
    
    public T deQueue() throws Exception{
        if(isEmpty()){
            throw new Exception("Queue is empty!");
        }else{
            this.front = (this.front + 1) % this.values.length;
            return this.values[this.front];
        }
    }
    
    public int Size(){
        return this.values.length;
    }
    
    public void display(){
        for(int i=(this.front +1) % Size(); i != (this.rear + 1) % Size(); i = (i + 1) % Size()){
            System.out.println(this.values[i]);
        }
    }
    
    public int count(){
        int count = 0;
        for(int i=(this.front+1)%Size(); i!=(this.rear+1)%Size(); i=(i+1)%Size()){
            count++;
        }
        return count;
    }
}
