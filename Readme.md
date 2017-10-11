### Programação Orientada a Objetos - POO29004

#### Engenharia de Telecomunicações

##### Instituto Federal de Santa Catarina



## Ordenação de arquivos com threads

O [Portal Brasileiro de Dados Abertos](http://dados.gov.br) é a ferramenta disponibilizada pelo governo para que todos possam encontrar e utilizar os dados e as informações públicas. O portal preza pela simplicidade e organização para que você possa encontrar facilmente os dados e informações que precisa. O portal também tem o objetivo de promover a interlocução entre atores da sociedade e com o governo para pensar a melhor utilização dos dados em prol de uma sociedade melhor.

A [Agência Nacional de Telecomunicações](http://dados.gov.br/dataset?tags=Anatel&q=anatel) disponibiliza um conjunto de dados abertos relacionados à homologação de produtos de telecomunicação, arrecadação das contribuições, estações que possuem licença para operar no serviço de comunicação multímidia, entre outros. 

Nesse projeto deve-se desenvolver em Java um ordenador de arquivos textos grandes que estejam no formato [CSV](https://pt.wikipedia.org/wiki/Comma-separated_values).  O aplicativo deve receber como [argumentos de linha de comando](https://docs.oracle.com/javase/tutorial/essential/environment/cmdLineArgs.html): 

- Caracter delimitador. Ex: `;`
- Número da coluna pela qual se deseja fazer a ordenação. Ex: `2`
- Nomes dos arquivos que serão ordenados separados por espaço. Ex: `arq1.csv arq2.csv`

Como resultado, o aplicativo deverá criar um arquivo ordenado correspondente para cada arquivo de entrada. O nome de cada arquivo resultante deverá ter como sufixo `.ordenado`. Ex: `arq1.ordenado.csv`

Exemplo de execução do programa a ser desenvolvido, passando como parâmetro 3 arquivos que deverão ser ordenados pela 2 coluna, tendo o caracter `; ` como delimitador:

```Shell
java OrdenacaoComThreads ";" 2 "arquivo1.csv" "arquivo2.csv" "arquivo3.csv"
```



Foi disponibilizado uma cópia do conjunto de dados [Produtos de Telecomunicações Homologados pela Anatel](http://dados.gov.br/dataset/produtos-de-telecomunicacoes-homologados-pela-anatel) que fora obtido no Portal Brasileiro de Dados Abertos. Esse [arquivo](data/produtos-homologados-anatel-2017-10-10.csv.zip) foi convertido para do formato [Windows-1252](https://en.wikipedia.org/wiki/Windows-1252) para o formato [UTF-8](https://pt.wikipedia.org/wiki/UTF-8) (`iconv -f cp-1252 -t utf-8 entrada > saída`). A estrutura de cada linha do arquivo é:

```
"Número de Homologação";"Nome do Fabricante";"Tipo do Produto";"Modelo do Equipamento";"Nome Comercial";"Data de Extração";"Validade"
```



O aplicativo a ser desenvolvido deverá ser capaz de ordenar arquivo cujo tamanho seja maior que o total de memória RAM disponível para a JVM. Não seria possível pensar em uma abordagem que carregaria todas as linhas de um arquivo texto em uma estrutura em memória como a [TreeSet](https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html), pois isso consumiria toda memória RAM e faria o aplicativo quebrar (i.e. *OutOfMemory exception*).  Sendo assim, a solução a ser desenvolvida deverá fazer uso do [algoritmo de ordenação externa](https://en.wikipedia.org/wiki/External_sorting) (sugestão: *merge sort k-way*).  A classe `java.io.File` possui o método `length()` que retorna o tamanho do arquivo em bytes.

Como o programa deverá ordenar vários arquivos, então é provável que o uso de múltiplas *Threads* possa melhorar o desempenho, principalmente nos computadores atuais que possuem múltiplos núcleos. Sendo assim, tem-se como requisito deste projeto fazer uso de *Threads*.

A fim de verificar a eficiência do algoritmo, o programa deverá imprimir o tempo (em milisegundos) que demorou para efetuar a ordenação. As classes [Instant](https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html) e [Duration](https://docs.oracle.com/javase/8/docs/api/java/time/Duration.html) do Java 8 podem ser usadas para isso. Por exemplo:

```java
Instant inicio = Instant.now();
ordenandoArquivos();
Instant fim = Instant.now();
Duration d = Duration.between(inicio, fim);
System.out.println("Tempo decorrido em milissegundos: " + d.toMillis());
```

### Requisitos

- Deve-se limitar a memória RAM da JVM para 64MBytes. Isso deve ser feito passando argumentos para JVM. Exemplo: `java -Xmx64M OrdenacaoComThreads... `



### Data para entrega: 05/11/2017
