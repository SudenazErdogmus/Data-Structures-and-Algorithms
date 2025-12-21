public class Main {

    public static void main(String[] args) {
        //Burada test edilebilir.
    }
    
    public static boolean isPalindrom(String str) throws Exception{
        CircularQueue<Character> queue = new CircularQueue(Character.class, str.length()+1);
        GenericStack<Character> stk = new GenericStack(Character.class, str.length()+1);
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            queue.enQueue(c);
            stk.push(c);
        }
        for(int i=0; i<str.length(); i++){
            if(queue.deQueue().compareTo(stk.pop()) == -1){
                return false;
            }
        }
        return true;
    }
    
    public static double avarage(CircularQueue<Integer> queue) throws Exception{
        CircularQueue<Integer> tempQueue = new CircularQueue(Integer.class, queue.Size());
        int sum = 0;
        while(!queue.isEmpty()){
            int temp = queue.deQueue();
            tempQueue.enQueue(temp);
            sum += temp;
        }
        while(!tempQueue.isEmpty()){
            queue.enQueue(tempQueue.deQueue());
        }
        return (double) sum/queue.count();
    }

    private static char charAt(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
