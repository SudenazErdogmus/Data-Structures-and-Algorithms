public class DoublyLinkedList<T extends Comparable>{
    private DNode<T> head;
    
    public void addToFront(T val){
        DNode<T> newNode =new DNode(val);
        newNode.next=head;
        head=newNode;
        if(head.next!=null)
            head.next.prev=newNode;
    }
    public void addToEnd(T val){
        DNode<T> newNode =new DNode(val);
        if(head==null)
            head=newNode;
        else{
            DNode<T> iterator=head;
            while(iterator.next!=null)
                iterator=iterator.next;
            iterator.next=newNode;
            newNode.prev=iterator;
        }
    }
    public void delete(T val){
        while(search(val)){
            int counter=0;
            while( head!=null && head.value.compareTo(val)==0 ){
                this.deleteHead();
                counter++;
            }
            if(counter!=0)
                continue;
            DNode<T> iterator=head;
            while(iterator.value.compareTo(val)!=0)
                iterator=iterator.next;
            iterator.prev.next=iterator.next;
            if(iterator.next!=null) 
                iterator.next.prev=iterator.prev;
        }
    }
    public int count() {
        int counter = 0;
        DNode<T> iterator = head;
        while (iterator != null) {
            iterator = iterator.next;
            counter++;
        }
        return counter;
    }
    public void insertAtGivenIndex(T val, int index){
        if(index>count())
            return;
        if(index<=0)
            this.addToFront(val);
        else{
            DNode<T> iterator=head;
            for (int i = 0; i < index; i++) {
                iterator=iterator.next;
            }
            DNode<T> newNode=new DNode(val);
            iterator.prev.next=newNode;
            newNode.prev=iterator.prev;
            newNode.next=iterator;
            iterator.prev=newNode;
        }
    }

    public boolean search(T val){
        DNode<T> iterator = head;
        while (iterator != null) {
            if (iterator.value.compareTo(val) == 0) {
                return true;
            }
            iterator = iterator.next;
        }
        return false;
    }

    public void deleteHead(){
        if(head==null)
            return;
        head=head.next;
        if(head!=null)
            head.prev=null;
    }
     public void display() {
        if (head == null) {
            return;
        }
        DNode<T> iterator = head;
        while (iterator.next != null) {
            System.out.print(iterator + "<-->");
            iterator = iterator.next;
        }
        System.out.println(iterator);
    }
    
    /*
      BÖLÜM b için Standart Arama Metodu.
      Bir değeri (val) listede arar ve arama sırasında kaç düğüme
      erişildiğini (bellek erişimi) sayar.
     */
    public int searchAndCountAccess(T val){
        
        // Bellek erişim sayacını başlat
        int accessCount = 0; 
        
        // Aramaya baştan (head) başla
        DNode<T> iterator = head; 
        
        // Liste sonuna (null) gelene kadar devam et
        while(iterator != null){
            
            // Düğüme eriştiğimiz an sayacı artırırız.
            // Ödev kuralı: "Her düğüme erişim bir bellek erişimi sayılır."
            accessCount++; 
            
            // Değeri bulduk mu diye kontrol et
            if(iterator.value.compareTo(val) == 0){
                // Değeri bulduk, o anki erişim sayısını döndür.
                return accessCount;
            }
            // Bulamadıysak bir sonraki düğüme geç
            iterator = iterator.next;
        }
        
        // Döngü bittiyse (değeri bulamadıysak),
        // listenin sonuna kadar gittiğimiz erişim sayısını döndürürüz.
        return accessCount;
    }
     
    /*
      Bölüm c için başa taşıma metodu.
      Bir değeri (val) listede arar, kaç düğüme erişildiğini sayar
      ve EĞER bulursa o düğümü listenin başına (head) taşır.
    */
    public int searchAndMoveToFront(T val){
        
        // Bellek erişim sayacını başlat
        int accessCount = 0;
        DNode<T> iterator = head;
        
        // Liste sonuna (null) gelene kadar devam et
        while(iterator != null){
            
            // Düğüme eriştiğimiz an sayacı artırırız
            accessCount++;
            
            // Değeri bulduk mu?
            if(iterator.value.compareTo(val) == 0){
                
                // === DEĞER BULUNDU -> BAŞA TAŞIMA MANTIĞI ===
                
                // 1. Durum: Değer zaten listenin başındaysa (head)
                if (iterator == head) {
                    // Hiçbir şey yapmaya gerek yok, sadece erişim sayısını döndür
                    return accessCount;
                }
                
                // 2. Durum: Değer listenin ortasında veya sonundaysa
                
                // 2a. Düğümü listeden "kesip" çıkarıyoruz.
                // Önceki düğümü, bir sonrakine bağlıyoruz.
                iterator.prev.next = iterator.next; 
                
                // Eğer kestiğimiz düğüm listenin sonunda değilse
                // (yani 'next'i null değilse)
                if (iterator.next != null) {
                    // Sonraki düğümün 'prev'ini, öncekine bağlıyoruz.
                    iterator.next.prev = iterator.prev;
                }
                
                // 2b. Kestiğimiz düğümü (iterator) başa taşıyoruz.
                iterator.next = head; // Düğümün 'next'i, eski 'head' oldu.
                iterator.prev = null; // Başa geldiği için 'prev'i null olmalı.
                head.prev = iterator; // Eski 'head'in 'prev'i, yeni 'head' (iterator) oldu.
                head = iterator;      // Listenin 'head'ini resmen bu düğüm yap.
                
                // Toplam erişim sayısını döndür.
                return accessCount;
            }
            
            // Bulamadıysak bir sonraki düğüme geç
            iterator = iterator.next;
        }
        
        // Değeri listede hiç bulamadıysak,
        // listenin sonuna kadar gittiğimiz erişim sayısını döndürürüz.
        return accessCount;
    }
}
