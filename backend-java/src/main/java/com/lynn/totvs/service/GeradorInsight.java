package com.lynn.totvs.service;

import com.lynn.totvs.enums.NivelRisco;
import com.lynn.totvs.enums.ProdutoTotvs;
import com.lynn.totvs.enums.TipoInsight;
import com.lynn.totvs.model.Insight;
import com.lynn.totvs.model.Transcricao;

import java.util.ArrayList;
import java.util.List;

public class GeradorInsight {

    private final MotorAnalise motor;
    private int contadorId = 1;
    private final List<Insight> insightsGerados = new ArrayList<>();

    public GeradorInsight() {
        this.motor = new MotorAnalise();
    }

    public Insight processar(Transcricao transcricao) {
        transcricao.setStatus("EM_ANALISE");

        TipoInsight tipo = motor.detectarTipo(transcricao);
        NivelRisco risco = motor.calcularRisco(transcricao, tipo);
        String trecho = motor.extrairTrecho(transcricao, tipo);
        String descricao = gerarDescricao(tipo, transcricao);
        String oportunidade = gerarOportunidade(tipo, transcricao.getMetadado().getProdutoContexto());

        Insight insight = new Insight(contadorId++, transcricao, tipo, risco, descricao, trecho, oportunidade);
        insightsGerados.add(insight);
        transcricao.setStatus("CONCLUIDO");

        return insight;
    }

    public void listarTodos() {
        if (insightsGerados.isEmpty()) {
            System.out.println("Nenhum insight gerado ainda.");
            return;
        }
        for (Insight i : insightsGerados) {
            i.exibir();
            System.out.println();
        }
    }

    public List<Insight> filtrarPorTipo(TipoInsight tipo) {
        return insightsGerados.stream()
                .filter(i -> i.getTipo() == tipo)
                .toList();
    }

    private String gerarDescricao(TipoInsight tipo, Transcricao transcricao) {
        String cliente = transcricao.getMetadado().getClienteVisualizado().getRazaoSocial();
        return switch (tipo) {
            case CHURN -> "Risco de cancelamento detectado para o cliente " + cliente;
            case BUG_SISTEMA -> "Relato de instabilidade técnica identificado para " + cliente;
            case FRICCAO_UX -> "Dificuldade de uso relatada pelo cliente " + cliente;
            case UPSELL -> "Oportunidade de expansão de contrato para " + cliente;
            case CROSS_SELL -> "Oportunidade de venda cruzada identificada para " + cliente;
        };
    }

    private String gerarOportunidade(TipoInsight tipo, ProdutoTotvs produto) {
        return switch (tipo) {
            case UPSELL -> "Oferecer módulo complementar ao " + produto;
            case CROSS_SELL -> "Apresentar integração entre " + produto + " e outro produto TOTVS";
            case CHURN -> "Acionar CS para reunião de retenção urgente";
            case BUG_SISTEMA -> "Abrir chamado prioritário no suporte técnico";
            case FRICCAO_UX -> "Encaminhar relato ao time de Produto e Universidade TOTVS";
        };
    }

    public List<Insight> getInsightsGerados() { return insightsGerados; }
}
