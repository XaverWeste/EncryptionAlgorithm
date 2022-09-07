import java.util.Scanner;

public class Algorithm {

    private final char[] alphabetOriginal= new char[]{
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' ','1','2','3','4','5','6','7','8','9','0','.',',',':','!','?','/','-','ü','ö','ä','ß',')','('
    };
    private char[] alphabet;
    int h=0;

    public Algorithm(int i){
        //TODO mehrfach verschlüsseln
        while(i>0){
            Scanner scanner = new Scanner(System.in);
            String s=scanner.nextLine();
            String[] sa=s.split("/");
            int[] key = new int[2];
            key[0]=Integer.parseInt(sa[0]);
            key[1]=Integer.parseInt(sa[1]);
            encryptAlphabet(key[0]);
            s=scanner.nextLine();
            h=next(key[1]);
            System.out.println("encrypt: "+encrypt(s));
            h=next(key[1]);
            System.out.println("decrypt: "+decrypt(s));
            i--;
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
        for(char value:c){
            sb.append(encrypt(value,h));
            h=next(h);
        }
        return sb.toString();
    }

    private String decrypt(String s){
        char[] c=s.toCharArray();
        StringBuilder sb=new StringBuilder();
        for(char value:c){
            sb.append(decrypt(value,h));
            h=next(h);
        }
        return sb.toString();
    }
    
    private char encrypt(char c,int j){
        for(int i=0;i<alphabetOriginal.length;i++){
            if(alphabetOriginal[i]==c){
                return alphabet[putInRange(i+j)];
            }
        }
        return '§';
    }

    private char decrypt(char c,int j){
        for(int i=0;i<alphabet.length;i++){
            if(alphabet[i]==c){
                return alphabetOriginal[putInRange(i-j)];
            }
        }
        return '§';
    }

    private int next(int i){
        return (41*i+23)%alphabetOriginal.length;
    }

    private int putInRange(int i){
        if (i >= alphabetOriginal.length || i < 0) {
            while (i >= alphabetOriginal.length) {
                i -= alphabetOriginal.length;
            }
            while (i < 0) {
                i += alphabetOriginal.length;
            }
        }
        return i;
    }
}
