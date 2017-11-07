import com.sun.corba.se.spi.orbutil.threadpool.ThreadPoolManager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Kway extends Thread{
    protected int coluna;
    private long size;
    private long sizeArq;
    private int numArqs;
    protected String delimitador;
    private String nomeArquivo;
    private Scanner leitor;
    private ArrayList<Dados> lista;

    /**
     * Classe que separa e une os arquivos
     * @param nomeArq nome do arquivo
     * @param col coluna de referencia
     * @param delim delimitador
     * @throws IOException
     */
    Kway(String nomeArq, int col, String delim) throws IOException {
        coluna = col;
        delimitador = delim;
        this.nomeArquivo = nomeArq;
        File arquivo = new File(nomeArquivo);
        int extensao = nomeArquivo.indexOf(".");
        nomeArquivo = nomeArq.substring(0,extensao);
        this.leitor = new Scanner(arquivo, String.valueOf(StandardCharsets.UTF_8));
        lista = new ArrayList<>();
        numArqs = 0;
        sizeArq = arquivo.length();
    }

    public void separa() throws FileNotFoundException {
        while (leitor.hasNextLine()) {
            String linha = leitor.nextLine();
            size += linha.length();
            Dados dados = new Dados(coluna, linha, delimitador);

            if (size > sizeArq/3) {
                size = 0;
                Collections.sort(lista);
                String nome;
                try {
                    nome = "temp"+numArqs + ".csv";
                    FileWriter arq = new FileWriter(new File(nome));
                    for (Dados data : lista) {
                        arq.append(data.toString() + "\n");
                    }
                    lista.clear();
                    arq.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                numArqs++;
            }
            lista.add(dados);
        }

        if (size > 0) {
            Collections.sort(lista);
            try {
                FileWriter arq = new FileWriter(new File("temp" + numArqs + ".csv"));
                for (Dados data : lista) {
                    arq.append(data.toString() + "\n");
                }
                arq.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void mergeSorted() throws IOException {
        try {
            separa();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Merge merge = new Merge(numArqs);
        GravaDados grD = new GravaDados(merge, nomeArquivo+".ordenado.csv");
        grD.start();
        for (int i = 0; i <= numArqs; i++) {
            GeraDados gd = new GeraDados(i, merge, coluna, delimitador);
            gd.start();
        }
    }
}

