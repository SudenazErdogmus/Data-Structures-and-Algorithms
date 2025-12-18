public class MyArray {
    private int[] array;
    
    public MyArray(int[] array){
        this.array = array;
    }
    
    public void display(){
        for(int i=0; i<array.length-1; i++){
            System.out.print(array[i]+" ");
        }
    }
    
    public int findMin(){
        int minimumNumber = array[0];
        for(int i=1; i<array.length-1; i++){
            if(minimumNumber > array[i]){
                minimumNumber = array[i];
            }
        }
        return minimumNumber;
    }
    
    public int lineerSearch(int number){
        for(int i=0; i<array.length-1; i++){
            if(number == array[i]){
                return i; //sayıyı değil indisini döndürmemiz isteniyor 
            }
        }
        return -1;
    }
    
    public void bubbleSort(){
   
      for(int i=0; i<array.length-1; i++){
          for(int j=0; j<array.length-1; j++){
              if(array[j+1] < array[j]){
                  int temp = array[j];
                  array[j] = array[j+1];
                  array[j+1] = temp;
              }
          }
          
          System.out.println((i+1) + ". tur sonucu");
          display();
          
      }
    }
    
    public int binarySearch(int number){
       
        int start = 0;
        int middle ;
        int end = array.length-1;
        
        while(start <= end){
            middle = (start + end) / 2;
            if(number == array[middle]){
                System.out.println("Tebrikler, tek attiniz <333");
                return number;
            }
            else if(number < array[middle]){
                end = middle - 1;
            }
            else{
                start = middle + 1;
            }
        }  
        return -1;
    }
    
}
 
