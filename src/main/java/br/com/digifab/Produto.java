package br.com.digifab;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class Produto {
    @NotBlank(message = "O atributo codigo e obrigatorio")
    private String codigo;

    @NotBlank(message = "O atributo nome e obrigatorio")
    private String nome;

    @NotNull(message = "O atributo fornecedor e obrigatorio")
    private Fornecedor fornecedor;

    @NotNull(message = "O atributo categoria e obrigatorio")
    private Categoria categoria;

    public Produto() {}

    public Produto(final String codigo, final String nome, final Fornecedor fornecedor, final Categoria categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(final Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(final Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(codigo, produto.codigo) &&
                Objects.equals(nome, produto.nome) &&
                Objects.equals(fornecedor, produto.fornecedor) &&
                Objects.equals(categoria, produto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nome, fornecedor, categoria);
    }
}
