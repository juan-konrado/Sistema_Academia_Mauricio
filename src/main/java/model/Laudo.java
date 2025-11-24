package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Laudo {
    private String tipoLaudo; 
    private String corpoLaudo;
    private LocalDate dataOcorrencia;
    private Perito peritoResponsavel;
    private String status = "Rascunho"; 
    private List<String> evidencias = new ArrayList<>(); // TU03
    
    private final int LIMITE_MAXIMO_CORPO = 20000; // TF04

    public Laudo(String tipoLaudo, Perito peritoResponsavel) {
        this.tipoLaudo = tipoLaudo;
        this.peritoResponsavel = peritoResponsavel;
    }

    // TF02: Validação de Data (Não pode ser futura)
    public void setDataOcorrencia(LocalDate dataOcorrencia) {
        if (dataOcorrencia.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data da ocorrência não pode ser futura.");
        }
        this.dataOcorrencia = dataOcorrencia;
    }
    
    // TU03: Adição de Evidências
    public void adicionarEvidencia(String evidencia) {
        if (evidencia != null && !evidencia.trim().isEmpty()) {
            this.evidencias.add(evidencia);
        }
    }
    
    // TF04: Validação de Limite de Caracteres
    public void setCorpoLaudo(String corpoLaudo) {
        if (corpoLaudo != null && corpoLaudo.length() > LIMITE_MAXIMO_CORPO) {
            throw new IllegalArgumentException("O corpo do laudo excede o limite máximo de " + LIMITE_MAXIMO_CORPO + " caracteres.");
        }
        this.corpoLaudo = corpoLaudo;
    }
    
    // TU05: Converte data para extenso
    public String converterDataParaExtenso(LocalDate data) {
        // Implementação simplificada para teste (você pode expandir para idioma pt-br real)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
        return data.format(formatter);
    }

    // TF05: Validação de Status (Exclusão)
    public boolean podeExcluir() {
        return status.equals("Rascunho");
    }
    
    // Getters
    public String getTipoLaudo() { 
        return tipoLaudo; 
    }
    
    public String getCorpoLaudo() {
        return corpoLaudo; 
    }
    
    public String getStatus() { 
        return status; 
    }
    
    public void setStatus(String status) { 
        this.status = status; 
    }
    
    public List<String> getEvidencias() { 
        return evidencias; 
    }
    
}
