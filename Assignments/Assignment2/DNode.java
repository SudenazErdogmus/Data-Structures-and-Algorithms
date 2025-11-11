public class DNode<T extends Comparable> {
    T value;
    DNode<T> next, prev;

    public DNode(T val){
        this.value=val;
        this.next=null;
        this.prev=null;
    }
    @Override
    public String toString(){
        return this.value.toString();
    }
}

