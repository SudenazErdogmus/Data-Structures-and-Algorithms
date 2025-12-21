public class Stack{
    private int[] values;
    private int top;
    
    public Stack(int Size){ //Constructer methodu
        this.values = new int[Size];
        this.top = -1;
    }
    
    public int Size(){
        return this.values.length;
    }
    
    public void clear(){
        this.top = -1;
    }
    
    public boolean isFull(){
        return this.top == this.values.length-1;
    }
    
    public boolean isEmpty(){
        return this.top == -1;
    }
    
    public int pop() throws Exception{
        if(isEmpty()){
            throw new Exception("Stack is empty!");
        }else{
            int temp = this.values[this.top];
            this.top--;
            return temp;
            // return this.values[this.top--];
        }  
    }
    
    /* 
    NOT: Java'da (Post-Decrement) Sondan Azaltma Mantığı
    Bir değişkenin yanındaki -- veya ++ işaretlerinin konumu işlemin sırasını belirler.
        1) variable-- (Post-Decrement): Önce değişkenin o anki değeriyle işlem yapılır, işlem bittikten sonra değişkenin değeri 1 
           azaltılır.
          *Örnek: return values[top--],
           Adım 1: values[top] değerini getir.
           Adım 2: top değerini 1 eksilt.
           Adım 3: Getirilen değeri döndür.
           Kısaca önce kullan sonra azalt.
    !! Stack için ipucu: 
       *pop() yaparken top-- kullanılır çünkü mevcut veriyi alıp sonra indisi düşürmek gerekir.
       *push() yaparken ++top kullanılır çünkü önce boş olan bir üst indise çıkıp sonra veriyi araya eklemek gerekir.
    */
    
    public void push(int val){
        if(isFull()){
            System.out.println("Stack is full!");
            return;
        }else{
            this.values[++this.top] = val;
            //this.top += 1;
            //this.values[this.top] = val;
        }
    }
    
    public int peek() throws Exception{
        if(isEmpty()){
            throw new Exception("Stack is Empty!");
        }else{
            return this.values[this.top];
        }
    }
    
    public void display(){
        if(isEmpty()){
            System.out.println("Stack is empty!");
            return;
        }else{
            int temp = this.top;
            while(temp != -1){
                System.out.println(this.values[temp]);
                temp--;
            }
        }
    }
    
    // Data Structers Hafta 2 Laboratuvar kodları:
    
    public static boolean search(Stack stk, int val) throws Exception{
        Stack tempStk = new Stack(stk.Size());
        boolean result = false;
        while(!stk.isEmpty()){
            int temp = stk.pop();
            tempStk.push(temp);
            if(temp == val){
                result = true;
                break;
            }
        }
        while(!tempStk.isEmpty()){
            stk.push(tempStk.pop());
        }
        return result;
    }
    
    public static int findMin(Stack stk) throws Exception{
        Stack tempStk = new Stack(stk.Size());
        int min = stk.pop();
        tempStk.push(min);
        while(!stk.isEmpty()){
            int temp = stk.pop();
            tempStk.push(temp);
            if(temp < min){
                min = temp;
            }
        }
        while(!tempStk.isEmpty()){
            stk.push(tempStk.pop());
        }
        return min;
    }
    
    public static void delete(Stack stk, int val) throws Exception{
        Stack tempStk = new Stack(stk.Size());
        while(!stk.isEmpty()){
            int temp = stk.pop();
            if(temp == val){
                break;
            }
            tempStk.push(temp);
        }
        while(!tempStk.isEmpty()){
            stk.push(tempStk.pop());
        }
    }
}
