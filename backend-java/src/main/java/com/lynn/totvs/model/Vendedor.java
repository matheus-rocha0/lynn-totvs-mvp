package com.lynn.totvs.model;

public class Vendedor extends Usuario {

    private String territorioAtendimento;
    private double metaVendasMensal;

    public Vendedor(int id, String nome, String email, String territorioAtendimento, double metaVendasMensal) {
        super(id, nome, email);
        this.territorioAtendimento = territorioAtendimento;
        this.metaVendasMensal = metaVendasMensal;
    }

    @Override
    public String getTipoUsuario() {
        return "VENDEDOR";
    }

    @Override
    public String resumo() {
        return super.resumo() + " | Território: " + territorioAtendimento + " | Meta: R$ " + metaVendasMensal;
    }

    public String getTerritorioAtendimento() { return territorioAtendimento; }
    public double getMetaVendasMensal() { return metaVendasMensal; }

    public void setTerritorioAtendimento(String territorioAtendimento) { this.territorioAtendimento = territorioAtendimento; }
    public void setMetaVendasMensal(double metaVendasMensal) { this.metaVendasMensal = metaVendasMensal; }
}
