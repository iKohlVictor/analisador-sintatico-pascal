package analisador_sintatico.com.br;

public class Token {
    private TokenEnum classe;
    private Valor valor;


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
        return "Token { classe..: " + classe + ", valor..: " + valor + " }";
    }
    

}
