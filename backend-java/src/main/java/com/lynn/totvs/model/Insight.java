package com.lynn.totvs.model;

import com.lynn.totvs.enums.NivelRisco;
import com.lynn.totvs.enums.TipoInsight;

public class Insight {

    private int id;
    private Transcricao transcricao;
    private TipoInsight tipo;
    private NivelRisco nivelRisco;
    private String descricao;
    private String trechoOriginal;
    private String oportunidade;

    public Insight(int id, Transcricao transcricao, TipoInsight tipo, NivelRisco nivelRisco,
                   String descricao, String trechoOriginal, String oportunidade) {
        this.id = id;
        this.transcricao = transcricao;
        this.tipo = tipo;
        this.nivelRisco = nivelRisco;
        this.descricao = descricao;
        this.trechoOriginal = trechoOriginal;
        this.oportunidade = oportunidade;
    }

    public void exibir() {
        System.out.println("=== INSIGHT #" + id + " ===");
        System.out.println("Tipo       : " + tipo);
        System.out.println("Risco      : " + nivelRisco);
        System.out.println("Descrição  : " + descricao);
        System.out.println("Trecho     : \"" + trechoOriginal + "\"");
        System.out.println("Oportunidade: " + oportunidade);
        System.out.println("Cliente    : " + transcricao.getMetadado().getClienteVisualizado().getRazaoSocial());
    }

    public int getId() { return id; }
    public Transcricao getTranscricao() { return transcricao; }
    public TipoInsight getTipo() { return tipo; }
    public NivelRisco getNivelRisco() { return nivelRisco; }
    public String getDescricao() { return descricao; }
    public String getTrechoOriginal() { return trechoOriginal; }
    public String getOportunidade() { return oportunidade; }

    public void setId(int id) { this.id = id; }
    public void setTranscricao(Transcricao transcricao) { this.transcricao = transcricao; }
    public void setTipo(TipoInsight tipo) { this.tipo = tipo; }
    public void setNivelRisco(NivelRisco nivelRisco) { this.nivelRisco = nivelRisco; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setTrechoOriginal(String trechoOriginal) { this.trechoOriginal = trechoOriginal; }
    public void setOportunidade(String oportunidade) { this.oportunidade = oportunidade; }
}
