package model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class LaudoTest {

    // Helper para criar um Perito
    private Perito criarPerito() {
        return new Perito("Perito Teste", "999", "Qualificado");
    }

    // TF02: Teste Funcional - Data de Ocorrência futura é inválida
    @Test(expected = IllegalArgumentException.class)
    public void setDataOcorrenciaFuturaInvalidaTest() {
        Laudo laudo = new Laudo("Tipo A", criarPerito());
        LocalDate amanha = LocalDate.now().plusDays(1);
        laudo.setDataOcorrencia(amanha); 
    }

    // TF04: Teste Funcional - Deve suportar 10.000 caracteres
    @Test
    public void corpoLaudoSuporta10000CaracteresTest() {
        Laudo laudo = new Laudo("Tipo D", criarPerito());
        String textoLongo = "a".repeat(10000); 
        laudo.setCorpoLaudo(textoLongo);
        assertEquals(10000, laudo.getCorpoLaudo().length());
    }
    
    // TF04: Teste Funcional - Exceder o limite máximo
    @Test(expected = IllegalArgumentException.class)
    public void corpoLaudoExcedeLimiteTest() {
        Laudo laudo = new Laudo("Tipo D", criarPerito());
        String textoMuitoLongo = "b".repeat(20001); 
        laudo.setCorpoLaudo(textoMuitoLongo);
    }

    // TF05: Teste Funcional - Laudo Finalizado NÃO pode ser excluído
    @Test
    public void naoPodeExcluirFinalizadoTest() {
        Laudo laudo = new Laudo("Tipo A", criarPerito());
        laudo.setStatus("Finalizado");
        assertFalse(laudo.podeExcluir());
    }

    // TU02: Teste Unitário - Validação de CPF ou CNPJ (Sucesso)
    @Test
    public void validarCPFouCNPJValidoTest() {
        assertTrue(ValidadorUtil.validarCPFouCNPJ("123.456.789-01"));
        assertTrue(ValidadorUtil.validarCPFouCNPJ("12.345.678/0001-99"));
    }
    
    // TU03: Teste Unitário - Adição de Múltiplas Evidências
    @Test
    public void adicionarMultiplasEvidenciasTest() {
        Laudo laudo = new Laudo("Tipo C", criarPerito());
        laudo.adicionarEvidencia("Evidência 1");
        laudo.adicionarEvidencia("Evidência 2");
        assertEquals(2, laudo.getEvidencias().size());
        assertTrue(laudo.getEvidencias().contains("Evidência 1"));
    }
    
    // TU05: Teste Unitário - Conversão de Data para Extenso
    @Test
    public void converterDataParaExtensoTest() {
        Laudo laudo = new Laudo("Tipo E", criarPerito());
        LocalDate data = LocalDate.of(2025, 11, 24); // 24/11/2025
        String esperado = "24 de novembro de 2025";
        
        // O teste é sensível ao Locale, mas a implementação simplificada deve funcionar
        assertEquals(esperado, laudo.converterDataParaExtenso(data)); 
    }
}
