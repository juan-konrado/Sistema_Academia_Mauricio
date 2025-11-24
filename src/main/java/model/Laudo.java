package model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Laudo {
    private String tipoLaudo; // Ex: Tipo A, Tipo B, etc.
    private String corpoLaudo;
    private LocalDate dataOcorrencia;
    private Perito peritoResponsavel;
    private String status = "Rascunho"; // Ex: Rascunho, Finalizado
    private List<String> evidencias = new ArrayList<>();
    private final int LIMITE_MAXIMO_CORPO = 20000; // Constante para o limite

    public Laudo(String tipoLaudo, Perito peritoResponsavel) {
        this.tipoLaudo = tipoLaudo;
        this.peritoResponsavel = peritoResponsavel;
        // TF01: A lógica de validação de tipo de laudo ocorreria aqui ou na camada de serviço
    }

    // TF05: Validação de Status (Exclusão)
    public boolean podeExcluir() {
        return status.equals("Rascunho");
    }

    // TF02: Validação de Data (Exemplo de regra de negócio)
    public void setDataOcorrencia(LocalDate dataOcorrencia) {
        if (dataOcorrencia.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data da ocorrência não pode ser futura.");
        }
        this.dataOcorrencia = dataOcorrencia;
    }

    // TU03: Método para ser testado unitariamente
    public void adicionarEvidencia(String evidencia) {
        if (evidencia != null && !evidencia.trim().isEmpty()) {
            this.evidencias.add(evidencia);
        }
    }
    
    public void setCorpoLaudo(String corpoLaudo) {
        if (corpoLaudo != null && corpoLaudo.length() > LIMITE_MAXIMO_CORPO) {
            throw new IllegalArgumentException("O corpo do laudo excede o limite máximo de " + LIMITE_MAXIMO_CORPO + " caracteres.");
        }
        this.corpoLaudo = corpoLaudo;
    }
    
    // Métodos Getters e Setters
    public String getTipoLaudo() { 
        return tipoLaudo;
    }
    
    public String getCorpoLaudo() { 
        return corpoLaudo; 
    }
    
    public void setCorpoLaudo(String corpoLaudo) { 
        this.corpoLaudo = corpoLaudo; 
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
