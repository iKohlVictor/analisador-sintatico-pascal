package analisador_sintatico.com.br;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class Lexico {
    
    private String caminhoArquivo;
    private String nomeArquivo;
    private int c;
    BufferedReader bufferedReader;

    public Lexico(String nomeArquivo){
        this.caminhoArquivo = Paths.get(nomeArquivo).toAbsolutePath().toString();
        this.nomeArquivo = nomeArquivo;

        try {
            this.bufferedReader = new BufferedReader(new FileReader(this.caminhoArquivo, StandardCharsets.UTF_8));
            this.c = this.bufferedReader.read();
        } catch(IOException error){
            System.err.println("Dont Possible open to file with name " + this.nomeArquivo);
            error.printStackTrace();
        }
    }

    public Token getToken(){
        StringBuilder lexema = new StringBuilder("");
        char caractere;
        Token token = new Token();

        try {

            while (c != -1) { 
                caractere = (char) c;
                if (!(c == 13 || c == 10)) {
                    if (caractere == ' ') {
                        while (caractere == ' ') {
                            c = this.bufferedReader.read();
                            caractere = (char) c;
                        }
                    } else if (Character.isLetter(caractere)) {
                        while (Character.isLetter(caractere) || Character.isDigit(caractere)) {
                            lexema.append(caractere);
                            c = this.bufferedReader.read();
                            caractere = (char) c;
                        }
                        Valor valor = new Valor();
                        token.setClasse(TokenEnum.cId);
                        valor.setValorIdentificador(lexema.toString());
                        token.setValor(valor);
                        return token;
                    } else if (Character.isDigit(caractere)) {
                        while (Character.isDigit(caractere) || caractere=='.') {
                            lexema.append(caractere);
                            c = this.bufferedReader.read();
                            caractere = (char) c;
                        }
                        Valor valor = new Valor();
                        token.setClasse(TokenEnum.cInt);
                        valor.setValorInteiro(Integer.parseInt(lexema.toString()));
                        token.setValor(valor);
                        return token;
                        
                    }else {
                        if(caractere==':'){
                            while(caractere == ':'){
                                lexema.append(caractere);
                                c = this.bufferedReader.read();
                                caractere = (char) c;
                            }
                            Valor valor = new Valor();
                            token.setClasse(TokenEnum.cDoisPontos);
                            valor.setValorIdentificador(lexema.toString());
                            token.setValor(valor);
                            return token;
                        }else if(caractere=='+'){
                            while(caractere == '+'){
                                lexema.append(caractere);
                                c = this.bufferedReader.read();
                                caractere = (char) c;
                            }
                            Valor valor = new Valor();
                            token.setClasse(TokenEnum.cMais);
                            valor.setValorIdentificador(lexema.toString());
                            token.setValor(valor);
                            return token;
                        }else if(caractere=='-'){
                            while(caractere == '-'){
                                lexema.append(caractere);
                                c = this.bufferedReader.read();
                                caractere = (char) c;
                            }
                            Valor valor = new Valor();
                            token.setClasse(TokenEnum.cMenor);
                            valor.setValorIdentificador(lexema.toString());
                            token.setValor(valor);
                            return token;
                        }else if(caractere=='/'){
                            while(caractere == '/'){
                                lexema.append(caractere);
                                c = this.bufferedReader.read();
                                caractere = (char) c;
                            }
                            Valor valor = new Valor();
                            token.setClasse(TokenEnum.cDivisao);
                            valor.setValorIdentificador(lexema.toString());
                            token.setValor(valor);
                            return token;
                        }else if(caractere=='*'){
                            while(caractere == '*'){
                                lexema.append(caractere);
                                c = this.bufferedReader.read();
                                caractere = (char) c;
                            }
                            Valor valor = new Valor();
                            token.setClasse(TokenEnum.cMultiplicacao);
                            valor.setValorIdentificador(lexema.toString());
                            token.setValor(valor);
                            return token;
                        }else if(caractere=='>'){
                            while(caractere == '>'){
                                lexema.append(caractere);
                                c = this.bufferedReader.read();
                                caractere = (char) c;
                            }
                            Valor valor = new Valor();
                            token.setClasse(TokenEnum.cMaior);
                            valor.setValorIdentificador(lexema.toString());
                            token.setValor(valor);
                            return token;
                        }else if(caractere=='<'){
                            while(caractere == '<'){
                                lexema.append(caractere);
                                c = this.bufferedReader.read();
                                caractere = (char) c;
                            }
                            Valor valor = new Valor();
                            valor.setValorIdentificador(lexema.toString());
                            token.setClasse(TokenEnum.cMenor);
                            token.setValor(valor);
                            return token;
                        }else if(caractere=='='){
                            while(caractere == '='){
                                lexema.append(caractere);
                                c = this.bufferedReader.read();
                                caractere = (char) c;
                            }
                            Valor valor = new Valor();
                            token.setClasse(TokenEnum.cIgual);
                            valor.setValorIdentificador(lexema.toString());
                            token.setValor(valor);
                            return token;
                        }else if(caractere==','){
                            while(caractere == ','){
                                lexema.append(caractere);
                                c = this.bufferedReader.read();
                                caractere = (char) c;
                            }
                            Valor valor = new Valor();
                            token.setClasse(TokenEnum.cVirgula);
                            valor.setValorIdentificador(lexema.toString());
                            token.setValor(valor);
                            return token;
                        }else if(caractere==';'){
                            while(caractere == ';'){
                                lexema.append(caractere);
                                c = this.bufferedReader.read();
                                caractere = (char) c;
                            }
                            Valor valor = new Valor();
                            token.setClasse(TokenEnum.cPontoVirgula);
                            valor.setValorIdentificador(lexema.toString());
                            token.setValor(valor);
                            return token;
                        }else if(caractere=='.'){
                            while(caractere == '.'){
                                lexema.append(caractere);
                                c = this.bufferedReader.read();
                                caractere = (char) c;
                            }
                            Valor valor = new Valor();
                            token.setClasse(TokenEnum.cPonto);
                            valor.setValorIdentificador(lexema.toString());
                            token.setValor(valor);
                            return token;
                        }else if(caractere=='('){
                            while(caractere == '('){
                                lexema.append(caractere);
                                c = this.bufferedReader.read();
                                caractere = (char) c;
                            }
                            Valor valor = new Valor();
                            token.setClasse(TokenEnum.cParEsq);
                            valor.setValorIdentificador(lexema.toString());
                            token.setValor(valor);
                            return token;
                        }else if(caractere==')'){
                            while(caractere == ')'){
                                lexema.append(caractere);
                                c = this.bufferedReader.read();
                                caractere = (char) c;
                            }
                            Valor valor = new Valor();
                            token.setClasse(TokenEnum.cParDir);
                            valor.setValorIdentificador(lexema.toString());
                            token.setValor(valor);
                            return token;
                        }else{
                            token.setClasse(TokenEnum.cEOF);
                        }
                        
                        token.setValor(null);
                        c = this.bufferedReader.read();
                        return token;
                    }
                }else{
                    c = this.bufferedReader.read();
                }
            }

            token.setClasse(TokenEnum.cEOF);
            return token;
        } catch (IOException err) {
            System.err.println("Dont possible open file this name " + this.nomeArquivo);
            err.printStackTrace();
        }
        return null;
    }
}
