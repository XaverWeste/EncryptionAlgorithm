import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

public class Encrypter {

    private final Scanner scanner=new Scanner(System.in);
    private final int[] key =new int[6];
    private String[] alphabet=new String[]{
            "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"," ","1","2","3","4","5","6","7","8","9","0",".",",",":","!","?","/","-","ü","ö","ä","ß",")","("
    };
    private final String[] alphabetO=new String[]{
            "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"," ","1","2","3","4","5","6","7","8","9","0",".",",",":","!","?","/","-","ü","ö","ä","ß",")","("
    };
    private String e="";

    public Encrypter(){

    }

    public void work(int i){
        while(i>0){
            print("encrypt?:");
            boolean encrypt=scanner.nextBoolean();
            String s= scanner.nextLine();
            if(encrypt){
                print("encrypt:");
                encrypt();
            }else{
                print("decrypt:");
                decrypt();
            }
            i--;
        }

    }

    private void encryptAlphabet(int s){
        String[] encryptAlphabet=new String[alphabet.length];
        Arrays.fill(encryptAlphabet, "~");
        for(int i=0;i< encryptAlphabet.length;i++){
            if(!alphabet[i].equals("~")&&encryptAlphabet[i].equals("~")) {
                int j = i + s;
                while (j < 0 || j > alphabet.length-1) {
                    if (j > alphabet.length - 1) j -= alphabet.length;
                    if (j < 0) j += alphabet.length;
                }
                while (alphabet[j].equals("~")||!encryptAlphabet[j].equals("~")){
                    j++;
                    if(j > alphabet.length - 1) j -= alphabet.length;
                }
                encryptAlphabet[i]=alphabet[j];
                encryptAlphabet[j]=alphabet[i];
                alphabet[i]=alphabet[j]="~";
            }
        }
        alphabet=encryptAlphabet;
    }

    private void decrypt(){
        print("Key:");
        String s= scanner.nextLine();
        String[] sr = s.split("/");
        for(int i=0;i< key.length;i++) key[i] = Integer.parseInt(sr[i]);
        for(int i=0;i<key[3];i++) encryptAlphabet(key[0]*key[4]*i);
        decryptText(scanner.nextLine());
        alphabet=alphabetO;
    }

    private void encrypt(){
        print("Key:");
        String s= scanner.nextLine();
        if(s.equals("random")){
            for(int i=0;i< key.length;i++) key[i] = (int)((new SecureRandom().nextInt())*0.00000001);
            print("key: ");
            for (int j : key) System.out.print(j+"/");
            print("");
        }else {
            String[] sr = s.split("/");
            for(int i=0;i< key.length;i++) key[i] = Integer.parseInt(sr[i]);
        }
        for(int i=0;i<Math.sqrt(Math.pow(key[3],2));i++) encryptAlphabet(key[0]*key[4]*i);
        encryptText(scanner.nextLine());
        alphabet=alphabetO;
    }

    private void decryptText(String string){
        char[] c = string.toCharArray();
        String s="";
        for(char value : c){
            s=s+decryptChar(decryptChar(value).charAt(0));
        }
        print(s);
    }

    private void encryptText(String string){
        char[] c = string.toCharArray();
        String s="";
        for(char value : c){
            s=s+encryptChar(encryptChar(value).charAt(0));
        }
        print(s);
    }

    private String decryptChar(char s){
        int i=0;
        while(!alphabet[i].equals(String.valueOf(s)))
            i++;
        i-= key[1];
        i=putInRange(i);
        e=alphabet[i];
        key[1]+= key[2];
        return e;
    }

    private String encryptChar(char s){
        int i=0;
        while(!alphabet[i].equals(String.valueOf(s)))
            i++;
        i+= key[1];
        i=putInRange(i);
        e=alphabet[i];
        key[1]+= key[2];
        return e;
    }

    private int putInRange(int i){
        while(i<0||i> alphabet.length-1) {
            if (i > alphabet.length - 1) i -= alphabet.length;
            if (i < 0) i += alphabet.length;
        }
        return i;
    }

    public void print(String s){ System.out.println(s); }
}
