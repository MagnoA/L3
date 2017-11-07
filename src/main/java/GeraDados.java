import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GeraDados extends Thread{
    private int numeroArq;
    private Merge merge;
    private int coluna;
    private String delimitador;

    /**
     * Gera linha a linha de cada arquivo
     * @param n numero identificador do arquivo temporario
     * @param m classe que ira unir os arquivos
     * @param c coluna de referencia
     * @param d delimitador
     */
    public GeraDados(int n, Merge m, int c, String d){
        numeroArq = n;
        merge = m;
        coluna = c;
        delimitador = d;
    }

    public void run() {
        File arq1 = new File("temp" + numeroArq + ".csv");
        while(arq1.exists()){
            try {
                Scanner l = new Scanner (arq1);
                while(l.hasNext()){
                    merge.recebeDados(new Dados(coluna, l.nextLine(), delimitador));
                }
                arq1.delete();
                merge.decrementa();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
