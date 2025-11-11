import java.io.BufferedWriter; // Dosyaya verimli (hızlı) yazmak için
import java.io.FileWriter; // Dosyaya yazmak için
import java.io.IOException; // Dosya hatalarını yakalamak için
import java.util.Random; // Rastgele sayı üretmek için

// Bu sınıf, ödevi test etmek için gereken 'Source.txt' ve 'Search.txt'
// dosyalarını oluşturan bir yardımcı sınıftır.
public class FileMaker{
    
    // 'static' metot, böylece 'Assignment2.java' içinden 
    // 'FileMaker.MakeFilesNow()' şeklinde çağırabiliriz.
    public static void MakeFilesNow(){
        
        System.out.println("Dosya olusturma islemi basliyor...");
        
        // --- Ayarlar ---
        String sourceFileName = "Source.txt"; // (a) bölümü için kaynak dosya
        String searchFileName = "Search.txt"; // (b) ve (c) bölümleri için arama dosyası
        int valueCount = 10000; // Dosyalara yazılacak toplam sayı adedi
        int maxValue = 200; // Üretilecek en büyük sayı (hocanın isteği [cite: image_392ffa.png])
        int minValue = 1;  // Üretilecek en küçük sayı
        
        // Rastgele sayı üretici nesnesi
        Random random = new Random();
        
        // --- 1. Source.txt Dosyasını Oluşturma ---
        
        // 'try-with-resources' bloğu, 'writer' işi bitince dosyayı otomatik kapatır
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(sourceFileName))){
            
            // 10.000 kez dön
            for(int i = 0; i<valueCount; i++){
                // 1 ile 200 (dahil) arasında rastgele bir sayı üret
                int randomNumber = random.nextInt(maxValue - minValue + 1) + minValue;
                
                // Sayıyı 'String'e çevirip dosyaya yaz
                writer.write(String.valueOf(randomNumber));
                
                // Eğer son sayı değilse, formatın bozulmaması için araya virgül koy
                if(i < valueCount - 1){
                    writer.write(",");
                }
            }
            System.out.println(sourceFileName + " basariyla olusturuldu.");
        } catch (IOException e){
            // Dosya yazarken bir hata olursa (örn: disk dolu) burası çalışır
            System.err.println(sourceFileName + " yazilirken bir hata olustu: " + e.getMessage());
        }
        
        // --- 2. Search.txt Dosyasını Oluşturma (Popüler Sayılarla) ---
        
        // 'Search.txt' için de 'try' bloğu aç
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(searchFileName))){
            
            // 10.000 kez dön
            for(int i = 0; i<valueCount; i++){
                
                int randomNumber2;
                
                // === YENİ MANTIK (80/20 Kuralı) ===
                // (c) yönteminin (b)'den iyi çalıştığını göstermek için
                // bazı sayıları "popüler" yapmamız gerekti.
                
                // 0-9 arası rastgele bir sayı üret, eğer 8'den küçükse (%80 ihtimal)
                if (random.nextInt(10) < 8) { 
                    // "Popüler" sayıları (1-40 arası) seç.
                    // Bu, aramaların %80'inin, tüm sayıların %20'sine (40/200) gitmesi demek.
                    randomNumber2 = random.nextInt(40) + 1;
                } else { // Kalan %20 ihtimalle
                    // "Popüler olmayan" (41-200 arası) sayılardan seç
                    randomNumber2 = random.nextInt(160) + 41;
                }
                
                // Seçilen sayıyı (popüler veya değil) dosyaya yaz
                writer.write(String.valueOf(randomNumber2));
                
                // Son sayı değilse virgül koy
                if(i < valueCount - 1){
                    writer.write(",");
                }
            }
            System.out.println(searchFileName + " basariyla olusturuldu (Popüler sayılarla).");
        }catch (IOException e){
            System.err.println(searchFileName + " yazilirken bir hata olustu:" + e.getMessage());
        }
        
        System.out.println("Dosya olusturma islemi bitti.");
    }
}
