public class Main(String args[]) throws Exception{
  public static void main(String[] args) throws Exception {
        Stack liste = new Stack(10); 
        liste.push(5);
        liste.push(10);
        liste.push(15);

        boolean bulundu Mu = search(liste, 10);
        System.out.println("10 sayısı bulundu mu? " + bulunduMu);
    }

  public static boolean search(Stack stk, int val) throws Exception{
        Stack tempStk=new Stack(stk.Size());
        boolean result=false;
        while(!stk.isEmpty()){
            int temp=stk.pop();
            tempStk.push(temp);
            if(temp==val){
                result=true;
                break;
            }
        }
        while(!tempStk.isEmpty())
            stk.push(tempStk.pop());
 
        return result;
    }
     public static int findMin(Stack stk ) throws Exception{
        Stack tempStk=new Stack(stk.Size());
        int min=stk.pop();
        tempStk.push(min);
        while(!stk.isEmpty()){
            int temp=stk.pop();
            if(temp<min)
                min=temp;
            tempStk.push(temp);
        }
        while(!tempStk.isEmpty())
            stk.push(tempStk.pop());
        return min;
    }
     public static void delete(Stack stk, int val) throws Exception{
        Stack tempStk=new Stack(stk.Size());
         
        while(!stk.isEmpty()){
            int temp=stk.pop();
            
            if(temp==val){
                 
                break;
            }
            tempStk.push(temp);
        }
        while(!tempStk.isEmpty())
            stk.push(tempStk.pop());
 
         
    }
}
