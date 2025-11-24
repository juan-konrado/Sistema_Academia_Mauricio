package model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class LaudoTest {

    // Helper para criar um Perito
    private Perito criarPerito() {
        return new Perito("Perito Teste", "999", "Qualificado");
    }

    // TF02: Teste Funcional - Data de Ocorrência não pode ser futura (Cenário de Falha)
    @Test(expected = IllegalArgumentException.class)
    public void setDataOcorrenciaFuturaInvalidaTest() {
        Perito p = criarPerito();
        Laudo laudo = new Laudo("Tipo A", p);
        
        // Tenta definir a data para amanhã
        LocalDate amanha = LocalDate.now().plusDays(1);
        laudo.setDataOcorrencia(amanha); 
    }

    // TF02: Teste Funcional - Data de Ocorrência válida (Cenário de Sucesso)
    @Test
    public void setDataOcorrenciaValidaTest() {
        Perito p = criarPerito();
        Laudo laudo = new Laudo("Tipo B", p);
        LocalDate ontem = LocalDate.now().minusDays(1);
        
        // Define a data para ontem (válida)
        laudo.setDataOcorrencia(ontem);
        // Não deve lançar exceção
    }

    // TU03: Teste Unitário - Adição de Múltiplas Evidências
    @Test
    public void adicionarMultiplasEvidenciasTest() {
        Laudo laudo = new Laudo("Tipo C", criarPerito());
    
        laudo.adicionarEvidencia("Câmera de Segurança 01");
        laudo.adicionarEvidencia("Depoimento da Testemunha X");
    
        // Verifica se o tamanho da lista é o esperado
        assertEquals(2, laudo.getEvidencias().size());
    
        // Verifica se um item específico foi adicionado corretamente
        assertTrue(laudo.getEvidencias().contains("Câmera de Segurança 01"));
    }

    // TU03: Teste Unitário - Não deve adicionar evidências nulas ou vazias
    @Test
    public void naoAdicionarEvidenciaNulaOuVaziaTest() {
        Laudo laudo = new Laudo("Tipo C", criarPerito());
    
        laudo.adicionarEvidencia(null);
        laudo.adicionarEvidencia("");
        laudo.adicionarEvidencia("  "); // Espaços em branco
    
        // Verifica se o tamanho da lista permanece zero
        assertEquals(0, laudo.getEvidencias().size());
    }

    // TF05: Teste Funcional - Laudo em Rascunho pode ser excluído
    @Test
    public void podeExcluirRascunhoTest() {
        Laudo laudo = new Laudo("Tipo A", criarPerito());
        // Status padrão é "Rascunho"
        assertTrue(laudo.podeExcluir());
    }

    // TF05: Teste Funcional - Laudo Finalizado NÃO pode ser excluído
    @Test
    public void naoPodeExcluirFinalizadoTest() {
        Laudo laudo = new Laudo("Tipo A", criarPerito());
        laudo.setStatus("Finalizado");
        assertFalse(laudo.podeExcluir());
    }
}
