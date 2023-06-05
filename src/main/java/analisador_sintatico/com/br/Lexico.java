package analisador_sintatico.com.br;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Lexico {

    private String caminhoArquivo;
    private String nomeDoArquivo;
    private int c;
    BufferedReader bufferedReader;
    PushbackReader br;

    private ArrayList<String> palavrasReservadas = new ArrayList<String>(
            Arrays.asList(
                    "and", "array", "begin", "case", "const", "div",
                    "do", "downto", "else", "end", "file", "for",
                    "function", "goto", "if", "in", "label", "mod",
                    "nil", "not", "of", "or", "packed", "procedure",
                    "program", "record", "repeat", "set", "then",
                    "to", "type", "until", "var", "while", "with",
                    "read", "write", "real", "integer"));

    public Lexico(String nomeDoArquivo) {
        this.caminhoArquivo = Paths.get(nomeDoArquivo).toAbsolutePath().toString();
        this.nomeDoArquivo = nomeDoArquivo;

        try {
            this.bufferedReader = new BufferedReader(new FileReader(this.caminhoArquivo, StandardCharsets.UTF_8));
            this.br = new PushbackReader(bufferedReader);
            this.c = this.br.read();
        } catch (IOException error) {
            System.err.println("Nao é possível abrir o arquivo: " + this.nomeDoArquivo);
            error.printStackTrace();
        }
    }

    public Token getToken(int linha, int coluna) {
        StringBuilder lexema = new StringBuilder("");
        char caractere;
        Token token = new Token();
        int tamanhoDoToken = 0;
        int quantidadeEspacos = 0;

        try {
            while (c != 1) {
                caractere = (char) c;
                if (!(c == 13 || c == 10)) {
                    if (caractere == ' ') {
                        while (caractere == ' ') {
                            c = this.br.read();
                            quantidadeEspacos++;
                            caractere = (char) c;
                        }
                    } else if (Character.isLetter(caractere)) {
                        while (Character.isLetter(caractere) || Character.isDigit(caractere)) {
                            lexema.append(caractere);
                            c = this.br.read();
                            tamanhoDoToken++;
                            caractere = (char) c;
                        }
                        if (palavrasReservadas.contains(lexema.toString().toLowerCase())) {
                            token.setClasse(TokenEnum.cPalRes);
                        } else {
                            token.setClasse(TokenEnum.cId);
                        }
                        adicionarColunaELinha(token, linha, coluna, tamanhoDoToken, quantidadeEspacos);
                        Valor valor = new Valor(lexema.toString());
                        token.setValor(valor);
                        return token;
                    } else if (Character.isDigit(caractere)) {
                        int quantidadeDePontos = 0;
                        while (Character.isDigit(caractere) || caractere == '.') {
                            if (caractere == '.') {
                                quantidadeDePontos++;
                            }
                            lexema.append(caractere);
                            c = this.br.read();
                            tamanhoDoToken++;
                            caractere = (char) c;
                        }
                        if (quantidadeDePontos <= 1) {
                            if (quantidadeDePontos == 0) {
                                token.setClasse(TokenEnum.cInt);
                                Valor valor = new Valor(Integer.parseInt(lexema.toString()));
                                token.setValor(valor);
                            } else {
                                token.setClasse(TokenEnum.cReal);
                                Valor valor = new Valor(Float.parseFloat(lexema.toString()));
                                token.setValor(valor);
                            }
                            adicionarColunaELinha(token, linha, coluna, tamanhoDoToken,
                                    quantidadeEspacos);
                            return token;
                        }
                    } else {
                        tamanhoDoToken++;
                        if (caractere == ':' | caractere == '>' | caractere == '<') {
                            if (caractere == ':') {
                                int proximo = this.br.read();
                                caractere = (char) proximo;
                                if (caractere == '=') {
                                    tamanhoDoToken++;
                                    token.setClasse(TokenEnum.cAtribuicao);
                                } else {
                                    this.br.unread(proximo);
                                    token.setClasse(TokenEnum.cDoisPontos);
                                }
                            } else if (caractere == '>') {
                                int proximo = this.br.read();
                                caractere = (char) proximo;
                                if (caractere == '=') {
                                    tamanhoDoToken++;
                                    token.setClasse(TokenEnum.cMaiorIgual);
                                } else {
                                    this.br.unread(proximo);
                                    token.setClasse(TokenEnum.cMaior);
                                }
                            } else if (caractere == '<') {
                                int proximo = this.br.read();
                                caractere = (char) proximo;
                                if (caractere == '=') {
                                    tamanhoDoToken++;
                                    token.setClasse(TokenEnum.cMenorIgual);
                                } else if (caractere == '>') {
                                    tamanhoDoToken++;
                                    token.setClasse(TokenEnum.cDiferente);
                                } else {
                                    this.br.unread(proximo);
                                    token.setClasse(TokenEnum.cMenor);
                                }
                            }
                        } else {
                            if (caractere == '+') {
                                token.setClasse(TokenEnum.cMais);
                            } else if (caractere == '-') {
                                token.setClasse(TokenEnum.cMenos);
                            } else if (caractere == '/') {
                                token.setClasse(TokenEnum.cDivisao);
                            } else if (caractere == '*') {
                                token.setClasse(TokenEnum.cMultiplicacao);
                            } else if (caractere == '=') {
                                token.setClasse(TokenEnum.cIgual);
                            } else if (caractere == ',') {
                                token.setClasse(TokenEnum.cVirgula);
                            } else if (caractere == ';') {
                                token.setClasse(TokenEnum.cPontoVirgula);
                            } else if (caractere == '.') {
                                token.setClasse(TokenEnum.cPonto);
                            } else if (caractere == '(') {
                                token.setClasse(TokenEnum.cParEsq);
                            } else if (caractere == ')') {
                                token.setClasse(TokenEnum.cParDir);
                            } else {
                                token.setClasse(TokenEnum.cEOF);
                            }
                        }
                        token.setValor(null);
                        adicionarColunaELinha(token, linha, coluna, tamanhoDoToken, quantidadeEspacos);
                        c = this.br.read();
                        tamanhoDoToken++;
                        return token;
                    }
                } else {
                    c = this.br.read();
                    linha++;
                    quantidadeEspacos = 0;
                    tamanhoDoToken = 0;
                    coluna = 1;
                }
            }
            token.setClasse(TokenEnum.cEOF);
            return token;
        } catch (

        IOException err) {
            System.err.println("Don't possible to open file: " + this.nomeDoArquivo);
            err.printStackTrace();
        }
        return null;
    }

    private void adicionarColunaELinha(Token token, int linha, int coluna, int tamanhoDoToken,
            int quantidadeEspacos) {
        token.setTamanhoDoToken(tamanhoDoToken);
        token.setColuna(coluna + quantidadeEspacos);
        token.setLinha(linha);
    }

}
