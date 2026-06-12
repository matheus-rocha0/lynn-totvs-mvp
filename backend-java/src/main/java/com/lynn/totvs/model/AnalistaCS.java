package com.lynn.totvs.model;

public class AnalistaCS extends Usuario {

    private int carteiraClientes;
    private double taxaRetencao;

    public AnalistaCS(int id, String nome, String email, int carteiraClientes, double taxaRetencao) {
        super(id, nome, email);
        this.carteiraClientes = carteiraClientes;
        this.taxaRetencao = taxaRetencao;
    }

    @Override
    public String getTipoUsuario() {
        return "ANALISTA_CS";
    }

    @Override
    public String resumo() {
        return super.resumo() + " | Carteira: " + carteiraClientes + " clientes | Retenção: " + taxaRetencao + "%";
    }

    public int getCarteiraClientes() { return carteiraClientes; }
    public double getTaxaRetencao() { return taxaRetencao; }

    public void setCarteiraClientes(int carteiraClientes) { this.carteiraClientes = carteiraClientes; }
    public void setTaxaRetencao(double taxaRetencao) { this.taxaRetencao = taxaRetencao; }
}
