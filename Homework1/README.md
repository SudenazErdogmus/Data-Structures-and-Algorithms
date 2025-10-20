Bu klasör, Veri Yapıları dersinin 1. ödevinin çözümünü içermektedir. (Stack, Postfix)

📘 ÖDEV AÇIKLAMASI

Ders sırasında paylaşılan GenericStack sınıfını kullanarak, Islemler.java dosyasındaki üç metodu tamamlamanız gerekmektedir:

1. tersCevir()
Verilen yığıttaki elemanların sırasını tersine çevirir.

Yeni bir yığıt oluşturup elemanları bu yığıta ters sırayla eklemelidir.

Orijinal yığıt bozulmamalıdır.

Fonksiyon, ters çevrilmiş yeni bir GenericStack döndürmelidir.

2. esit()
İki yığıtın içeriklerinin sıra ve değer olarak eşit olup olmadığını kontrol eder.

Yığıtların eleman sayısı farklıysa doğrudan false döndürülmelidir.

Tüm elemanlar sırayla karşılaştırılmalıdır.

Yığıtların içerikleri değişmemelidir (bozulmamalıdır).

Eşitse true, değilse false döndürmelidir.

3. postfixDegerlendir()
Postfix (ters Polonya gösterimi) biçimindeki bir aritmetik ifadeyi değerlendirir.

İşlemler tamsayılar üzerinde yapılacaktır.

Örnek: "10 5 /" işleminin sonucu 2 olmalıdır.

+, -, *, / operatörleri desteklenmelidir.

Girdi ifadesindeki sayılar ve operatörler boşluklarla ayrılmıştır.

Değerlendirme sonucunda elde edilen tek tamsayı değer döndürülmelidir.

Bu metotları yazarken LAB derslerinde gösterilen örneklerdeki yaklaşım ve yöntemleri takip ediniz.
Kodunuzu GenericStack sınıfındaki push, pop, peek, isEmpty gibi metotları kullanarak tamamlayınız ve gerekli yerlerde yorum satırları ekleyiniz.

💡 Örnek: Generic sınıf kullanımı gösteren basit bir fonksiyon
Aşağıdaki örnek, GenericStack sınıfında tip bilgisinin (Class<T>) nasıl kullanılacağını gösterir.

/**
 * Verilen yığıtı bozmadan yeni bir kopyasını oluşturur.
 * @param s Kopyalanacak yığıt
 * @return Orijinal yığıtla aynı elemanlara sahip yeni bir yığıt
 */
public static <T extends Comparable> GenericStack<T> kopyala(GenericStack<T> s) {
    try {
        // Stack'in tuttuğu veri tipini alıyoruz (örnek: Integer.class)
        Class<T> tip = (Class<T>) s.values.getClass().getComponentType();

        // Aynı boyutta geçici yığıtlar oluşturuyoruz
        GenericStack<T> gecici = new GenericStack<>(tip, s.Size());
        GenericStack<T> yeni = new GenericStack<>(tip, s.Size());

        // Elemanları geçici olarak ters sırada aktarıyoruz
        while (!s.isEmpty()) {
            T val = s.pop();
            gecici.push(val);
        }

        // Hem orijinal stack'i geri yüklüyoruz hem yeni kopyayı oluşturuyoruz
        while (!gecici.isEmpty()) {
            T val = gecici.pop();
            s.push(val);
            yeni.push(val);
        }

        // 🔹 Asıl kopyayı döndür
        return yeni;

    } catch (Exception e) {
        System.out.println("Hata: " + e.getMessage());
        return null;
    }
}
Yukarıdaki örnek, Class<T> bilgisinin GenericStack içinde nasıl kullanıldığını göstermek amacıyla paylaşılmıştır.
