package br.com.digifab;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class RepositorioProduto {
    private final List<Produto> listaProdutos;
    private final ValidatorFactory factory;
    private final Validator validator;

    public RepositorioProduto() {
        this.listaProdutos = new ArrayList<>();
        this.factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public void cadastrarProduto(final Produto produto) {
        validar(produto);

        validarExistencia(produto);

        listaProdutos.add(produto);
    }

    private void validarExistencia(final Produto produto) {
        if (listaProdutos.stream()
                .anyMatch(validarProduto(produto.getCodigo()))) {
            throw new IllegalArgumentException(String.format("Produto %s já cadastrado.", produto.getCodigo()));
        }
    }

    private static Predicate<Produto> validarProduto(final String produto) {
        return p -> p.getCodigo().equals(produto);
    }

    private void validar(final Produto produto) {
        var violations = validator.validate(produto);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException(violations.toString());
        }
    }

    public List<Produto> listarProdutos() {
        return listaProdutos;
    }

    public boolean editarProduto(final Produto produto) {
        listaProdutos.stream()
                .filter(validarProduto(produto.getCodigo()))
                .findFirst()
                .ifPresent(p -> {
                    p.setNome(produto.getNome());
                    p.setFornecedor(produto.getFornecedor());
                    p.setCategoria(produto.getCategoria());
                });
        return true;
    }

    public boolean excluirProduto(final String codigo) {
        return listaProdutos.removeIf(validarProduto(codigo));
    }
}
