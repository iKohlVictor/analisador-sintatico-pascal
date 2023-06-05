package analisador_sintatico.com.br;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        System.out.println("começou o arquivo");

        String nomeDoArquivo = "test.pas";

        substituirTabulacao(nomeDoArquivo);

        Sintatico sintatico = new Sintatico(nomeDoArquivo);

        sintatico.analisar();

    }

    private static void substituirTabulacao(String nomeDoArquivo) {

        Path caminhoDoArquivo = Paths.get(nomeDoArquivo);
        int quantidadeDeEspacosPorTabulacao = 4;
        StringBuilder juntaTexto = new StringBuilder();
        String espacos;

        for (int contador = 0; contador < quantidadeDeEspacosPorTabulacao; contador++) {
            juntaTexto.append(" ");
        }

        espacos = juntaTexto.toString();

        String conteudo;

        try {
            conteudo = new String(Files.readAllBytes(caminhoDoArquivo), StandardCharsets.UTF_8);
            conteudo = conteudo.replace("\t", espacos);
            Files.write(caminhoDoArquivo, conteudo.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
