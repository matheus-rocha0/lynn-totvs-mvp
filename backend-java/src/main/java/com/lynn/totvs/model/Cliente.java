package com.lynn.totvs.model;

import com.lynn.totvs.enums.ProdutoTotvs;

public class Cliente {

    private int id;
    private String razaoSocial;
    private String cnpj;
    private String segmento;
    private ProdutoTotvs produtoAtual;

    public Cliente(int id, String razaoSocial, String cnpj, String segmento, ProdutoTotvs produtoAtual) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.segmento = segmento;
        this.produtoAtual = produtoAtual;
    }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", razaoSocial='" + razaoSocial + "', produto=" + produtoAtual + "}";
    }

    public int getId() { return id; }
    public String getRazaoSocial() { return razaoSocial; }
    public String getCnpj() { return cnpj; }
    public String getSegmento() { return segmento; }
    public ProdutoTotvs getProdutoAtual() { return produtoAtual; }

    public void setId(int id) { this.id = id; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public void setSegmento(String segmento) { this.segmento = segmento; }
    public void setProdutoAtual(ProdutoTotvs produtoAtual) { this.produtoAtual = produtoAtual; }
}
