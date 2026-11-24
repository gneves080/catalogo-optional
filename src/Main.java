import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Produto> produtos = Arrays.asList(
                new Produto("Notebook", 3500.00),
                new Produto("Mouse", 59.90),
                new Produto("Teclado", 149.90),
                new Produto("Monitor", 899.00),
                new Produto("Cabo HDMI", 25.00),
                new Produto("Headset", 100.00),
                new Produto("Webcam", 229.90),
                new Produto("Hub USB", 89.90));

        System.out.println("=== Catálogo completo ===");
        produtos.forEach(System.out::println);

        // Filtro: preço estritamente maior que 100 (o Headset, de R$ 100,00, fica de fora)
        List<Produto> acimaDe100 = produtos.stream()
                .filter(p -> p.getPreco() > 100)
                .collect(Collectors.toList());

        System.out.println("\n=== Produtos com preço acima de R$ 100,00 ===");
        acimaDe100.forEach(System.out::println);

        // Ordenação por nome, em ordem alfabética (sem diferenciar maiúsculas de minúsculas)
        List<Produto> ordenadosPorNome = produtos.stream()
                .sorted(Comparator.comparing(Produto::getNome, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());

        System.out.println("\n=== Produtos em ordem alfabética ===");
        ordenadosPorNome.forEach(System.out::println);

        // Busca segura com Optional
        System.out.println("\n=== Busca por nome ===");
        buscarEExibir(produtos, "Monitor");
        buscarEExibir(produtos, "webcam");
        buscarEExibir(produtos, "Impressora");
    }

    /** Retorna um Optional: vazio quando nenhum produto tem o nome informado. */
    private static Optional<Produto> buscarPorNome(List<Produto> produtos, String nome) {
        return produtos.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    private static void buscarEExibir(List<Produto> produtos, String nome) {
        System.out.print("Buscando \"" + nome + "\": ");
        buscarPorNome(produtos, nome).ifPresentOrElse(
                produto -> System.out.println(String.format(Locale.forLanguageTag("pt-BR"),
                        "Produto encontrado: %s | Preço: R$ %,.2f", produto.getNome(), produto.getPreco())),
                () -> System.out.println("Produto não encontrado."));
    }
}