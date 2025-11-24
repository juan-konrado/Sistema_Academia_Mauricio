package model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.List;

public class LaudoTest {

    private Perito criarPerito() {
        return new Perito("Perito Teste", "999", "Qualificado");
    }

    // TF01: Data de ocorrência futura inválida
    @Test(expected = IllegalArgumentException.class)
    public void setDataOcorrenciaFuturaInvalidaTest() {
        Laudo laudo = new Laudo("Tipo A", criarPerito());
        LocalDate amanha = LocalDate.now().plusDays(1);
        laudo.setDataOcorrencia(amanha);
    }

    // TF02: Limite de caracteres excedido
    @Test(expected = IllegalArgumentException.class)
    public void corpoLaudoExcedeLimiteTest() {
        Laudo laudo = new Laudo("Tipo B", criarPerito());
        String textoMuitoLongo = "b".repeat(20001);
        laudo.setCorpoLaudo(textoMuitoLongo);
    }

    // TF03: Laudo finalizado não pode ser excluído
    @Test
    public void naoPodeExcluirFinalizadoTest() {
        Laudo laudo = new Laudo("Tipo A", criarPerito());
        laudo.setStatus("Finalizado");
        assertFalse(laudo.podeExcluir());
    }

    // TF04: Permissão de acesso
    @Test
    public void temPermissaoDeAcessoTest() {
        Perito peritoPrincipal = criarPerito();
        Perito tecnico = new Perito("Tecnico", "888", "Assistente");
        Laudo laudo = new Laudo("Tipo C", peritoPrincipal);
        laudo.getTecnicosDePericia().add(tecnico);

        assertTrue(laudo.temPermissaoDeAcesso(peritoPrincipal));
        assertTrue(laudo.temPermissaoDeAcesso(tecnico));
        assertFalse(laudo.temPermissaoDeAcesso(new Perito("Outro", "000", "Nada")));
    }

    // TF05: Adição de evidências
    @Test
    public void adicionarMultiplasEvidenciasTest() {
        Laudo laudo = new Laudo("Tipo C", criarPerito());
        laudo.adicionarEvidencia("Evidência 1");
        laudo.adicionarEvidencia("Evidência 2");
        assertEquals(2, laudo.getEvidencias().size());
        assertTrue(laudo.getEvidencias().contains("Evidência 1"));
        assertTrue(laudo.getEvidencias().contains("Evidência 2"));
    }

    // Sistema Test 01: Fluxo completo Tipo A
    @Test
    public void fluxoCompletoLaudoTipoATest() {
        Perito p = criarPerito();
        Laudo laudo = new Laudo("Tipo A", p);
        laudo.setCorpoLaudo("Corpo do laudo Tipo A.");
        laudo.adicionarEvidencia("Evidência 1");
        assertEquals("Tipo A", laudo.getTipoLaudo());
        assertEquals(1, laudo.getEvidencias().size());
    }

    // Sistema Test 02: Fluxo completo Tipo B com técnico
    @Test
    public void fluxoCompletoLaudoTipoBTest() {
        Perito p = criarPerito();
        Perito tecnico = new Perito("Técnico", "101", "Assistente");
        Laudo laudo = new Laudo("Tipo B", p);
        laudo.getTecnicosDePericia().add(tecnico);
        laudo.setCorpoLaudo("Corpo do laudo Tipo B.");
        assertEquals(1, laudo.getTecnicosDePericia().size());
        assertTrue(laudo.temPermissaoDeAcesso(tecnico));
    }

    // Sistema Test 03: Integração evidências e data
    @Test
    public void integracaoEvidenciasLaudoTest() {
        Laudo laudo = new Laudo("Tipo D", criarPerito());
        laudo.adicionarEvidencia("Evidência X");
        laudo.setCorpoLaudo("Texto curto");
        assertEquals(1, laudo.getEvidencias().size());
        assertEquals("Texto curto", laudo.getCorpoLaudo());
    }

    // Sistema Test 04: Acesso negado
    @Test
    public void acessoNegadoLaudoTest() {
        Laudo laudo = new Laudo("Tipo E", criarPerito());
        Perito outro = new Perito("Outro", "555", "Nenhum");
        assertFalse(laudo.temPermissaoDeAcesso(outro));
    }

    // Sistema Test 05: Conversão de data no sistema
    @Test
    public void conversaoDataSistemaTest() {
        Laudo laudo = new Laudo("Tipo F", criarPerito());
        String dataExtenso = laudo.converterDataParaExtenso(LocalDate.of(2025, 11, 24));
        assertEquals("24 de novembro de 2025", dataExtenso);
    }
}
