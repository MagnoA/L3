public class Dados implements Comparable<Dados>{
    private String comparador;
    private String[] dados;

    /**
     * Construtor reponsável pela organização dos dados obtidos através do arquivo CSV
     * @param comp É a coluna de referência para comparação
     * @param data Todos as informações contidas em uma string
     * @param delim Delimitador que será empregado
     */
    public Dados(int comp, String data, String delim){
        dados = data.split(delim);
        comparador = dados[comp];
    }

    @Override
    public int compareTo(Dados dados) {
        return comparador.compareTo(dados.comparador);
    }

    @Override
    public String toString() {
        String result = "";
        for(String temp : dados){
            result += temp + ";";
        }

        return result.substring(0, result.length()-1);
    }

    public void run(){

    }
}
