import java.io.FileWriter;
import java.io.IOException;


public class Merge{
    private boolean compare1;
    private boolean compare2;
    private Dados dados1;
    private Dados dados2;
    private int numArqs;
    private boolean flag;

    /**
     * Classe responsavel por unir os arquivos csv
     * @param n numero de arquivos que serão unidos
     */
    public Merge(int n){
        compare1 = false;
        compare2 = false;
        flag = true;
        numArqs = n;
    }

    public synchronized void grava(FileWriter fw) throws InterruptedException, IOException {
        while(compare1 == false || compare2 == false){
            if(numArqs == -1){
                flag = false;
                break;
            }else
                wait();
        }

        if(dados1.compareTo(dados2) < 0){
            fw.append(dados1.toString()+"\n");
            compare1 = false;
            notifyAll();
        }else{
            fw.append(dados2.toString()+"\n");
            compare2 = false;
            notifyAll();
        }
    }

    public synchronized void recebeDados(Dados d) throws InterruptedException {
        if(compare1 == true){
            dados2 = d;
            compare2 = true;
            notifyAll();

        }else{
            dados1 = d;
            compare1 = true;
            notifyAll();
        }
    }

    public void finaliza(FileWriter fw) throws IOException {
        if(compare1) fw.append(dados1.toString()+"\n");
        if(compare2) fw.append(dados2.toString()+"\n");
    }

    public synchronized void decrementa(){
        --numArqs;
        notifyAll();
    }

    public boolean getFlag(){
        return flag;
    }

}
