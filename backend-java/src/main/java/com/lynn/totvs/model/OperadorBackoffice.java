package com.lynn.totvs.model;

public class OperadorBackoffice extends Usuario {

    private String moduloERP;
    private int nivelAcesso;

    public OperadorBackoffice(int id, String nome, String email, String moduloERP, int nivelAcesso) {
        super(id, nome, email);
        this.moduloERP = moduloERP;
        this.nivelAcesso = nivelAcesso;
    }

    @Override
    public String getTipoUsuario() {
        return "OPERADOR_BACKOFFICE";
    }

    @Override
    public String resumo() {
        return super.resumo() + " | Módulo ERP: " + moduloERP + " | Nível de Acesso: " + nivelAcesso;
    }

    public String getModuloERP() { return moduloERP; }
    public int getNivelAcesso() { return nivelAcesso; }

    public void setModuloERP(String moduloERP) { this.moduloERP = moduloERP; }
    public void setNivelAcesso(int nivelAcesso) { this.nivelAcesso = nivelAcesso; }
}
