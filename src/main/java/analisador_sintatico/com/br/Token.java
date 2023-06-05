package analisador_sintatico.com.br;

public class Token {
    private TokenEnum classe;
    private Valor valor;
    private int linha;
    private int coluna;
    private int tamanhoDoToken;

    public int getTamanhoDoToken() {
        return tamanhoDoToken;
    }

    public void setTamanhoDoToken(int tamanhoDoToken) {
        this.tamanhoDoToken = tamanhoDoToken;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public TokenEnum getClasse() {
        return classe;
    }

    public void setClasse(TokenEnum classe) {
        this.classe = classe;
    }

    public Valor getValor() {
        return valor;
    }

    public void setValor(Valor valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Token { TokenEnum..: " + classe + ", valor..: " + valor + ", linha..:" + linha + ", coluna..:" + coluna
                + ", tamanho do token..: " + tamanhoDoToken + " }";
    }

}
