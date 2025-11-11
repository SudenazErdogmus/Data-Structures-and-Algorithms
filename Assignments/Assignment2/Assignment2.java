import java.io.File; // Dosya işlemleri için
import java.io.FileNotFoundException; // Dosya bulunamadı hatası için
import java.util.Scanner; // Dosya okumak için

public class Assignment2 {

    public static void main(String[] args) {
        
        // =======================================================
        // ADIM 1: Dosyaları Oluşturma
        // =======================================================
        // FileMaker.MakeFilesNow(); 
        // Not: Bu satırı 'Search.txt'yi (popüler sayılarla) 
        // yeniden oluşturmak için bir kez çalıştırdım ve sonra
        // tekrar yoruma aldım.
        
        
        // =======================================================
        // BÖLÜM (a): 'list' nesnesini (Standart Arama için) oluştur
        // =======================================================
        
        // (b) bölümünde kullanmak için 'list' adında bir liste oluşturuyorum
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        String fileName = "Source.txt"; // Okunacak dosya adı
        
        // Dosya okuma işlemi hata verebileceği için try-catch bloğu kullanıyorum
        try (Scanner scanner = new Scanner(new File(fileName))) {
            
            // Dosya formatı "2,3,45" şeklinde olduğu için ayırıcıyı virgül yapıyorum
            scanner.useDelimiter(",");
            
            // Dosyada okunacak tamsayı olduğu sürece döngüye devam et
            while(scanner.hasNextInt()){
                int number = scanner.nextInt(); // Sayıyı oku
                
                // Ödev kuralı: Sayı listede yoksa...
                if(!list.search(number)){
                    // ...listenin sonuna ekle (benzersiz liste için)
                    list.addToEnd(number);
                }
            }
            System.out.println("Source.txt okundu ve 'list' nesnesi oluşturuldu.");
        } catch(FileNotFoundException e){
            // Dosya bulunamazsa hata mesajı ver
            System.err.println("HATA: " + fileName + " bulunamadı!");
            e.printStackTrace();
            return; // Programı durdur
        }
        System.out.println("-------------------------------------");
        
        // =======================================================
        // BÖLÜM (b): 'list' üzerinde Standart Arama
        // =======================================================
        System.out.println("Ödev (b) Bölümü Başlıyor: Standart Arama...");
        
        // Toplam bellek erişimini saymak için long tipinde bir sayaç
        long totalAccess_B = 0; 
        
        // Süreyi ölçmek için kronometreyi başlatıyorum
        long startTime_B = System.nanoTime(); 
        
        // Şimdi Search.txt dosyasını okuyorum
        try(Scanner searchScanner = new Scanner(new File("Search.txt"))){
            searchScanner.useDelimiter(","); // Yine ayırıcı virgül
            
            // Dosyadaki her bir sayıyı ara
            while (searchScanner.hasNextInt()) {
                int numberToSearch = searchScanner.nextInt();
                
                // 'searchAndCountAccess' metodunu çağır ve dönen erişim sayısını toplama ekle
                totalAccess_B += list.searchAndCountAccess(numberToSearch);
            }
            
            // Arama işlemi bitti, kronometreyi durdur
            long endTime_B = System.nanoTime(); 
            // Geçen süreyi nanosaniyeden milisaniyeye çevir
            long duration_B = (endTime_B - startTime_B) / 1000000; 

            // Sonuçları yazdır
            System.out.println("BÖLÜM (b) TAMAMLANDI");
            System.out.println("Toplam Bellek Erisimi (Standart Arama): " + totalAccess_B);
            System.out.println("Toplam Süre (Standart Arama): " + duration_B + " milisaniye");

        }catch (FileNotFoundException e) {
             System.err.println("HATA: Search.txt bulunamadı!");
             e.printStackTrace();
        } 
        System.out.println("-------------------------------------");
        
        // =======================================================
        // BÖLÜM (a) TEKRARI: 'list_c' nesnesini (Başa Taşıma için) oluştur
        // =======================================================
        
        // (c) bölümünün (b)'den etkilenmemesi ve adil bir karşılaştırma
        // yapabilmek için listeyi 'list_c' adıyla sıfırdan oluşturuyorum.
        System.out.println("Ödev (c) için liste sıfırlanıyor (yeniden oluşturuluyor)...");
        DoublyLinkedList<Integer> list_c = new DoublyLinkedList<>();
        
        // (a) bölümündeki kodun aynısı, sadece 'list_c' için çalışıyor
        try (Scanner scanner = new Scanner(new File(fileName))) {
            scanner.useDelimiter(",");
            while (scanner.hasNextInt()) {
                int sayi = scanner.nextInt();
                if (!list_c.search(sayi)) {
                    list_c.addToEnd(sayi);
                }
            }
            System.out.println("Source.txt okundu ve 'list_c' nesnesi oluşturuldu.");
        } catch (FileNotFoundException e) {
            System.err.println("HATA: " + fileName + " bulunamadı!");
            e.printStackTrace();
            return;
        }
        System.out.println("-------------------------------------");
        
        // =======================================================
        // BÖLÜM (c): 'list_c' üzerinde Başa Taşıma ile Arama
        // =======================================================
        System.out.println("Ödev (c) Bölümü Başlıyor: Başa Taşıma ile Arama...");
        
        // (c) bölümü için ayrı bir toplam erişim sayacı
        long totalAccess_C = 0;
        
        // (c) bölümü için kronometreyi başlat
        long startTime_C = System.nanoTime(); 
        
        // Search.txt dosyasını TEKRAR okuyorum
        try (Scanner searchScanner_C = new Scanner(new File("Search.txt"))){
            searchScanner_C.useDelimiter(",");
            
            while (searchScanner_C.hasNextInt()){
                int numberToSearch = searchScanner_C.nextInt();
                
                // Bu kez 'searchAndMoveToFront' metodunu çağırıyorum
                // Bu metot hem sayıyı bulup başa taşıyor hem de erişim sayısını döndürüyor
                totalAccess_C += list_c.searchAndMoveToFront(numberToSearch);  
            }
            
            // Arama bitti, kronometreyi durdur
            long endTime_C = System.nanoTime(); 
            long duration_C = (endTime_C - startTime_C) / 1000000; // Milisaniyeye çevir
            
            // Sonuçları yazdır
            System.out.println("BÖLÜM (c) TAMAMLANDI");
            System.out.println("Toplam Bellek Erişimi (Basa Tasima): " + totalAccess_C);
            System.out.println("Toplam Süre (Basa Tasima): " + duration_C + " milisaniye"); 

        }catch (FileNotFoundException e) {
             System.err.println("HATA: Search.txt bulunamadı!");
             e.printStackTrace();
        }
        System.out.println("-------------------------------------");
    }
}
