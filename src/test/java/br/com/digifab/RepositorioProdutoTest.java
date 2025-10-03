package br.com.digifab;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RepositorioProdutoTest {

    private RepositorioProduto repositorioProduto;

    @BeforeEach
    public void init() {
        repositorioProduto = new RepositorioProduto();
    }

    @Test
    void deveCadastrarUmProdutoValido() {
        var produto = ProdutoFactory.criarProduto("Polipropileno granulado");

        Assertions.assertDoesNotThrow(() -> repositorioProduto.cadastrarProduto(produto));

        List<Produto> produtosCadastrados = repositorioProduto.listarProdutos();
        Assertions.assertNotNull(produtosCadastrados);
        Assertions.assertEquals(produto, produtosCadastrados.getLast());
    }

    @Test
    void naoDeveCadastrarUmProdutoSemDadosObrigatorios() {
        var produto = ProdutoFactory.criarProdutoVazio();

        var exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> repositorioProduto.cadastrarProduto(produto));

        MatcherAssert.assertThat(exception.getMessage(), CoreMatchers.containsString("e obrigatorio"));
    }

    @Test
    void naoDeveCadastrarUmProdutoDuplicado() {
        var produto1 = ProdutoFactory.criarProduto("Polipropileno granulado");
        Assertions.assertDoesNotThrow(() -> repositorioProduto.cadastrarProduto(produto1));

        var produto2 = ProdutoFactory.criarProduto("PET");
        var exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> repositorioProduto.cadastrarProduto(produto2));
        Assertions.assertEquals(String.format("Produto %s já cadastrado.", produto2.getCodigo()), exception.getMessage());
    }

    @Test
    void deveEditarProdutoCadastrado() {
        var produto = ProdutoFactory.criarProduto("Polipropileno granulado");
        Produto finalProduto = produto;
        Assertions.assertDoesNotThrow(() -> repositorioProduto.cadastrarProduto(finalProduto));

        produto = ProdutoFactory.criarProdutoEditado();

        Assertions.assertTrue(repositorioProduto.editarProduto(produto));
        List<Produto> produtosCadastrados = repositorioProduto.listarProdutos();
        Assertions.assertNotNull(produtosCadastrados);
        Assertions.assertEquals(produto, produtosCadastrados.getLast());
    }

    @Test
    void deveExcluirProdutoCadastrado() {
        var produto = ProdutoFactory.criarProduto("Polipropileno granulado");
        Assertions.assertDoesNotThrow(() -> repositorioProduto.cadastrarProduto(produto));
        Assertions.assertEquals(1, repositorioProduto.listarProdutos().size());
        Assertions.assertTrue(repositorioProduto.excluirProduto("001"));

        List<Produto> produtosCadastrados = repositorioProduto.listarProdutos();
        Assertions.assertNotNull(produtosCadastrados);
        Assertions.assertEquals(0, repositorioProduto.listarProdutos().size());
    }
}
