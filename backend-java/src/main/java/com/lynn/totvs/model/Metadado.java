package com.lynn.totvs.model;

import com.lynn.totvs.enums.ProdutoTotvs;

public class Metadado {

    private int id;
    private Usuario usuarioLogado;
    private Cliente clienteVisualizado;
    private ProdutoTotvs produtoContexto;
    private String sistemaOrigem;

    public Metadado(int id, Usuario usuarioLogado, Cliente clienteVisualizado, ProdutoTotvs produtoContexto, String sistemaOrigem) {
        this.id = id;
        this.usuarioLogado = usuarioLogado;
        this.clienteVisualizado = clienteVisualizado;
        this.produtoContexto = produtoContexto;
        this.sistemaOrigem = sistemaOrigem;
    }

    @Override
    public String toString() {
        return "Metadado{usuário=" + usuarioLogado.getNome()
                + ", cliente=" + clienteVisualizado.getRazaoSocial()
                + ", produto=" + produtoContexto
                + ", sistema=" + sistemaOrigem + "}";
    }

    public int getId() { return id; }
    public Usuario getUsuarioLogado() { return usuarioLogado; }
    public Cliente getClienteVisualizado() { return clienteVisualizado; }
    public ProdutoTotvs getProdutoContexto() { return produtoContexto; }
    public String getSistemaOrigem() { return sistemaOrigem; }

    public void setId(int id) { this.id = id; }
    public void setUsuarioLogado(Usuario usuarioLogado) { this.usuarioLogado = usuarioLogado; }
    public void setClienteVisualizado(Cliente clienteVisualizado) { this.clienteVisualizado = clienteVisualizado; }
    public void setProdutoContexto(ProdutoTotvs produtoContexto) { this.produtoContexto = produtoContexto; }
    public void setSistemaOrigem(String sistemaOrigem) { this.sistemaOrigem = sistemaOrigem; }
}
