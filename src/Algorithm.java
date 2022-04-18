import java.util.Scanner;

public class Algorithm {

    private final Scanner scanner=new Scanner(System.in);
    private final int[] key =new int[2];
    private final char[] alphabetOriginal= new char[]{
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' ','1','2','3','4','5','6','7','8','9','0','.',',',':','!','?','/','-','ü','ö','ä','ß',')','('
    };
    private char[] alphabet;

    public Algorithm(){
        encryptAlphabet(4);
        while(1==1){
            String s=scanner.nextLine();
            System.out.println(encrypt(s));
            System.out.println(decrypt(encrypt(s)));
        }
    }

    private void encryptAlphabet(int k){
        alphabet=new char[alphabetOriginal.length];
        char[] encryptAlphabet=new char[alphabetOriginal.length];
        for(int i=0;i< encryptAlphabet.length;i++){
            encryptAlphabet[i]=alphabetOriginal[putInRange(next(k+i))];
        }
        System.arraycopy(encryptAlphabet,0,alphabet,0,encryptAlphabet.length);
    }

    private String encrypt(String s){
        char[] c=s.toCharArray();
        StringBuilder sb=new StringBuilder();
        for(char value:c) sb.append(encrypt(value));
        return sb.toString();
    }

    private String decrypt(String s){
        char[] c=s.toCharArray();
        StringBuilder sb=new StringBuilder();
        for(char value:c) sb.append(decrypt(value));
        return sb.toString();
    }
    
    private char encrypt(char c){
        for(int i=0;i<alphabetOriginal.length;i++){
            if(alphabetOriginal[i]==c) return alphabet[i];
        }
        return '§';
    }

    private char decrypt(char c){
        for(int i=0;i<alphabet.length;i++){
            if(alphabet[i]==c) return alphabetOriginal[i];
        }
        return '§';
    }

    private int next(int i){
        return (41*i+23)%alphabet.length;
    }

    private int putInRange(int i){
        if (i >= alphabetOriginal.length || i < 0) {
            while (i > alphabetOriginal.length) {
                i -= alphabetOriginal.length;
            }
            while (i < 0) {
                i += alphabetOriginal.length;
            }
        }
        return i;
    }
}