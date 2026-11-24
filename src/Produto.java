import java.util.Locale;

public class Produto {

    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    private final String nome;
    private final Double preco;

    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return String.format(PT_BR, "%s - R$ %,.2f", nome, preco);
    }
}