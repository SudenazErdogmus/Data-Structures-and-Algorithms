import java.util.*;

/**
 * Islemler sınıfı içindeki 3 metodun yazılması istenmektedir
 * GenericStack sınıfını kullanınız.
 */
public class Islemler {

    /**
     * Yığıtın elemanlarının sırasını tersine çevirir
     * @param s Tersine çevrilecek yığıt
     * @return Tersine çevrilmiş yığıtı döndürür
     */
    public static <T extends Comparable> GenericStack<T> tersCevir(GenericStack<T> s) {
        /*Bu en basit metottu mantığı çok açıktı çünkü. 
        Bir tempStack(geçici Stack) bir de reverseStack(tersStack) oluşturuyoruz boyutları 
        ve tipleri parametre olarak verilen s stack' i olacak şekilde.
        */
        try{
            Class<T> tip = (Class<T>) s.values.getClass().getComponentType();
            
            GenericStack<T> tempStack = new GenericStack<T>(tip, s.Size());
            GenericStack<T> reverseStack = new GenericStack<T>(tip, s.Size());
            /* s boşalana kadar eleman çıkarıp bu çıkardığımız elemanları temp adındaki bir değişkene atayıp sonrasında da 
            tempStack ve revgerseStack' e push ediyoruz. Bu döngü bittiğinde s boş olur tempStack ve reverseStack ise full olur.
            Buradan sonra aslında soru bitiyor ANCAK s stack' i bozulmamalı şartı var zaten bu yüzden bir tempStack oluşturduk.
            */
            while(!s.isEmpty()){
                T temp = s.pop();
                tempStack.push(temp);
                reverseStack.push(temp);
            }
            /*Burada da tempStack boşalana kadar s stack' ine tüm elemanları ekleriz. Tabii önce tempStack' ten pop ederiz. Bu sayede
            orijinal stack' imiz bozulmaz. En sonda da ters stack i(reverseStack döndürürüz.)
            */
            while(!tempStack.isEmpty()){
                s.push(tempStack.pop());
            }
            return reverseStack;
        }catch (Exception e){
            System.out.println("Hata! " + e.getMessage());
            return null;
        }
    }
    

    /**
     * İki yığıtın eşit olup olmadığını kontrol eder
     * @param s1 Birinci yığıt
     * @param s2 İkinci yığıt
     * @return İki yığıtın eşit olma durumu
     */
    public static <T extends Comparable> boolean esit(GenericStack<T> s1, GenericStack<T> s2) {
         /*ilk başta iki stack' in boyutu eşit değilse direkt false döndürerek programı sonlandırırız ki
        sonraki aşamalara boşu boşuna girmesin. Kodumuz efektif olsun. Ayrıca bu blokla bundan sonra iki stack' in boyutlarının birbirlerine
        eşit olduğunu garanti ederiz. Ayrıca bunu kontrol etmemiz gerekmez.
        */
        if(s1.Size() != s2.Size()){ //ilk başta iki stack' in boyutu eşit değilse direkt false döndürerek programı sonlandırırız ki 
            return false; 
        }
        /*Bu değişken özellikle while döngüsünün içindeki if bloğu için çok önemlidir. ilk önce return false olarak yazmıştım kodumu.
        Sonra main methodunda çağırıp test ettiğimde ve orijinal stacklerim s1 ve s2 yi okumak istediğimde onların bozulmuş olduğunu gördüm.
        Bu sebeple temp1 ve temp2 olarak tanımladığım değişkenlerimin birbirine eşit olmaması halinde while döngüsünün break le durdurulması
        daha doğru bir kullanım. ÇÖünkü eşitse de değilse de orijinal stack' lerin korunuyor olması gerek.
        Bu sayede eşit değilse de program en sonuna kadar çalışacak ve esitMi değişkenini return edecek.
        */
        boolean esitMi = true;
        
        try{
            Class<T> tip1 = (Class<T>) s1.values.getClass().getComponentType();
            Class<T> tip2 = (Class<T>) s2.values.getClass().getComponentType();
            
            GenericStack<T> tempStack1 = new GenericStack<T>(tip1, s1.Size());
            GenericStack<T> tempStack2 = new GenericStack<T>(tip2, s2.Size());
            
            while(!s1.isEmpty() && !s2.isEmpty()){
                T temp1 = s1.pop();
                tempStack1.push(temp1);
                T temp2 = s2.pop();
                tempStack2.push(temp2);
                if(temp1.compareTo(temp2) != 0){
                    esitMi = false;
                    break;
                }        
            }
            
            /*Burada orijinal stacklerimizi eski haline getiriyoruz.*/
            while(!tempStack1.isEmpty() && !tempStack2.isEmpty()){
                s1.push(tempStack1.pop());
                s2.push(tempStack2.pop());
            }
            
        }catch (Exception e){
            System.out.println("Hata! " + e.getMessage());
            return false;
        }
        
        return esitMi;
    }

    /**
     * Postfix ifadeyi değerlendirir.
     * İşlemler tamsayılar üzerinde gerçekleşir.
     * "7 5 /" işleminin sonucu 1'dir.
     * @param girdi Girdi ifadesi
     * @return İfadenin sonucu
     */
    public static int postfixDegerlendir(String girdi) {
         String[] tokens = splitToTokens(girdi); //İlk önce splitToToken metodu yardımıyla girdileri bie string dizisini atıyoruz. 
        try{
            GenericStack<Integer> numberStack = new GenericStack<>(Integer.class, tokens.length);
            for(int i= 0; i<tokens.length; i++){ 
                if(tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/") ){
                    /*Bir postfix ifadede işlem soldan sağa akar. "3 4 +" ifadesi veilmiş olsun,
                    Burada 3 stack e push edilir sonra 4 push edilir. Ve şu an için top 4 elemanını gösterir. Ancak "+" operatörünü görünce
                    bu iki sayıyı pop etmemiz gerekir. Stacklerde son giren ilk çıkar(LIFO) mantığı olduğu için ilkPopEdilenSayi = 4 
                    ikinciPopEdilenSayi = 3 olur. yani 4 3 olarak alırız ancak toplamada bir sorun yaratmaması çıkarmada da yaratmayacağı 
                    anlamına gelmez. Bu sebeple pop edilen sayıların sırasını kontrol etmemiz işlem sonucunun doğruluğu açısından önemlidir.
                    Bu nedenle isimlendirmeleri daha göz önünde tutmak ve mantığını oturtmak açısından bu şekilde gerçekleştirdim.
                    */
                    int ilkPopEdilenSayi = numberStack.pop();  
                    int ikinciPopEdilenSayi = numberStack.pop();
                    int result; //result değişkenini int olarak tanımladım çünkü sonucun bir tamsayı dönemsi bekleniyor.
                    if(tokens[i].equals("+")){
                        result = ikinciPopEdilenSayi + ilkPopEdilenSayi;
                    }
                    else if(tokens[i].equals("-")){
                        result = ikinciPopEdilenSayi - ilkPopEdilenSayi;
                    }
                    else if(tokens[i].equals("*")){
                        result = ikinciPopEdilenSayi * ilkPopEdilenSayi;
                    }
                    else{
                        result = ikinciPopEdilenSayi / ilkPopEdilenSayi; 
                    }
                    numberStack.push(result);
                    
                }else{
                    int number = Integer.parseInt(tokens[i]);//Bu kısımda gemini' den yardım aldım. Çünkü tip dönüşümlerinde bu kısmı bilmiyordum.
                    numberStack.push(number);
                }
            }
            return numberStack.pop();//En son Stack' te kalan tek elemanımızı pop ederek return ederiz.
   
        }catch(Exception e){
            System.out.println("Hata! " + e.getMessage());
            return -1;
        }
    }

    /**
     * Infix veya postfix ifadeyi sembollerine ayıran yardımcı metot.
     * Operatörleri, sayıları ve parantezleri ayrı token’lar halinde döndürür.
     * @param girdi ifadenin kendisi (örnek: "3 4 +")
     * @return Sembol dizisi (örnek: ["3", "4", "+"])
     */
    private static String[] splitToTokens(String girdi)
    {
        StringTokenizer t = new StringTokenizer(girdi, "+-*/^() ", true);
        List<String> tokenList = new ArrayList<>();
        while (t.hasMoreTokens()) {
            String s = t.nextToken().trim();
            if(!s.equals(""))
                tokenList.add(s);
        }
        String [] tl = new String[tokenList.size()];
        tokenList.toArray(tl);
        return tl;
    }
}

