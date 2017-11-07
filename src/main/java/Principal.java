import java.io.*;
import java.time.Duration;
import java.time.Instant;

public class Principal{

    public static void main(String[] args) throws InterruptedException, IOException {

        String delim = args[0];
        int coluna = Integer.parseInt(args[1]);

        for(int i = 2; i < args.length; i++) {
            Kway kway = new Kway(args[i], coluna, delim);
            Instant inicio = Instant.now();
            kway.mergeSorted();
            Instant fim = Instant.now();
            Duration d = Duration.between(inicio, fim);
            System.out.println("Tempo decorrido em milissegundos: " + d.toMillis());
        }
    }
}
