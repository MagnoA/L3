import java.io.FileWriter;
import java.io.IOException;
import java.time.chrono.MinguoEra;

public class GravaDados extends Thread {
    private FileWriter fw;
    private Merge merge;

    /**
     * Le linha a linha cada arquivo separado
     * @param merge calsse que vai unir os arquivos
     * @param nome nome do arquivo organizado
     * @throws IOException
     */
    GravaDados(Merge merge, String nome) throws IOException {
        this.merge = merge;
        fw = new FileWriter(nome);
    }

    public void run() {
        while (merge.getFlag()) {
            try {
                merge.grava(fw);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            merge.finaliza(fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
