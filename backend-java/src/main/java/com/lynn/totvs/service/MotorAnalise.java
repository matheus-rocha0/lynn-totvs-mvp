package com.lynn.totvs.service;

import com.lynn.totvs.enums.NivelRisco;
import com.lynn.totvs.enums.TipoInsight;
import com.lynn.totvs.model.Transcricao;

import java.util.HashMap;
import java.util.Map;

public class MotorAnalise {

    private static final Map<String, TipoInsight> PALAVRAS_CHAVE = new HashMap<>();

    static {
        PALAVRAS_CHAVE.put("cancelar", TipoInsight.CHURN);
        PALAVRAS_CHAVE.put("concorrente", TipoInsight.CHURN);
        PALAVRAS_CHAVE.put("insatisfeito", TipoInsight.CHURN);
        PALAVRAS_CHAVE.put("rescisão", TipoInsight.CHURN);
        PALAVRAS_CHAVE.put("erro", TipoInsight.BUG_SISTEMA);
        PALAVRAS_CHAVE.put("lento", TipoInsight.BUG_SISTEMA);
        PALAVRAS_CHAVE.put("travando", TipoInsight.BUG_SISTEMA);
        PALAVRAS_CHAVE.put("bug", TipoInsight.BUG_SISTEMA);
        PALAVRAS_CHAVE.put("difícil", TipoInsight.FRICCAO_UX);
        PALAVRAS_CHAVE.put("complicado", TipoInsight.FRICCAO_UX);
        PALAVRAS_CHAVE.put("confuso", TipoInsight.FRICCAO_UX);
        PALAVRAS_CHAVE.put("módulo", TipoInsight.UPSELL);
        PALAVRAS_CHAVE.put("precisaria", TipoInsight.UPSELL);
        PALAVRAS_CHAVE.put("integração", TipoInsight.CROSS_SELL);
        PALAVRAS_CHAVE.put("automatizar", TipoInsight.CROSS_SELL);
    }

    public TipoInsight detectarTipo(Transcricao transcricao) {
        String texto = transcricao.getTextoOriginal().toLowerCase();

        Map<TipoInsight, Integer> contagem = new HashMap<>();
        for (Map.Entry<String, TipoInsight> entrada : PALAVRAS_CHAVE.entrySet()) {
            if (texto.contains(entrada.getKey())) {
                contagem.merge(entrada.getValue(), 1, Integer::sum);
            }
        }

        return contagem.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(TipoInsight.UPSELL);
    }

    public NivelRisco calcularRisco(Transcricao transcricao, TipoInsight tipo) {
        String texto = transcricao.getTextoOriginal().toLowerCase();
        long ocorrencias = PALAVRAS_CHAVE.entrySet().stream()
                .filter(e -> texto.contains(e.getKey()))
                .count();

        if (tipo == TipoInsight.CHURN) {
            if (ocorrencias >= 3) return NivelRisco.CRITICO;
            if (ocorrencias == 2) return NivelRisco.ALTO;
            return NivelRisco.MEDIO;
        }
        if (tipo == TipoInsight.BUG_SISTEMA) return NivelRisco.ALTO;
        if (tipo == TipoInsight.FRICCAO_UX) return NivelRisco.MEDIO;
        return NivelRisco.BAIXO;
    }

    public String extrairTrecho(Transcricao transcricao, TipoInsight tipo) {
        String texto = transcricao.getTextoOriginal();
        for (Map.Entry<String, TipoInsight> entrada : PALAVRAS_CHAVE.entrySet()) {
            if (entrada.getValue() == tipo && texto.toLowerCase().contains(entrada.getKey())) {
                int idx = texto.toLowerCase().indexOf(entrada.getKey());
                int inicio = Math.max(0, idx - 30);
                int fim = Math.min(texto.length(), idx + 60);
                return "..." + texto.substring(inicio, fim) + "...";
            }
        }
        return texto.substring(0, Math.min(80, texto.length()));
    }
}
