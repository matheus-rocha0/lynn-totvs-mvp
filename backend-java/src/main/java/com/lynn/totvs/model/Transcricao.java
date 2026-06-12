package com.lynn.totvs.model;

import java.time.LocalDateTime;

public class Transcricao {

    private int id;
    private String textoOriginal;
    private Metadado metadado;
    private LocalDateTime dataEnvio;
    private String status;

    public Transcricao(int id, String textoOriginal, Metadado metadado) {
        this.id = id;
        this.textoOriginal = textoOriginal;
        this.metadado = metadado;
        this.dataEnvio = LocalDateTime.now();
        this.status = "AGUARDANDO_ANALISE";
    }

    @Override
    public String toString() {
        return "Transcricao{id=" + id
                + ", status=" + status
                + ", usuario=" + metadado.getUsuarioLogado().getNome()
                + ", cliente=" + metadado.getClienteVisualizado().getRazaoSocial() + "}";
    }

    public int getId() { return id; }
    public String getTextoOriginal() { return textoOriginal; }
    public Metadado getMetadado() { return metadado; }
    public LocalDateTime getDataEnvio() { return dataEnvio; }
    public String getStatus() { return status; }

    public void setId(int id) { this.id = id; }
    public void setTextoOriginal(String textoOriginal) { this.textoOriginal = textoOriginal; }
    public void setMetadado(Metadado metadado) { this.metadado = metadado; }
    public void setStatus(String status) { this.status = status; }
}
