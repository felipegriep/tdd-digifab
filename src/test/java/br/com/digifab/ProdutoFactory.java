package br.com.digifab;

public class ProdutoFactory {

    static Produto criarProduto(final String nome) {
        return new Produto("001",
                nome,
                new Fornecedor("001", "Cooperativa de Catadores Tavares"),
                new Categoria("001", "Insumo"));
    }

    static Produto criarProdutoVazio() {
        return new Produto();
    }

    static Produto criarProdutoEditado() {
        return new Produto(
                "001",
                "Garrafa PET",
                new Fornecedor("002", "Cooperativa XPTO"),
                new Categoria("002", "Produto Final"));
    }
}
