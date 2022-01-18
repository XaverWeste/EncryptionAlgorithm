
public class MainController {

    public static void main(String[] args) {
        new MainController();
    }

    public MainController(){
        Encrypter e=new Encrypter();
        e.work(10);
    }
}