package model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

public class GeradorDocxTest {

    private GeradorDocx gerador = new GeradorDocx();

    // TU04: Teste Unitário - Sanitarização remove tags HTML/XML
    @Test
    public void sanitarizarRemoveTagsHtmlTest() {
        String textoSujeira = "O texto possui <b>formatação</b> e <script>código malicioso</script> para o DOCX.";
        String esperado = "O texto possui formatação e código malicioso para o DOCX.";
        
        assertEquals(esperado, gerador.sanitizarTexto(textoSujeira));
    }

    // TU04: Teste Unitário - Sanitarização substitui quebras de linha
    @Test
    public void sanitarizarSubstituiQuebraDeLinhaTest() {
        String textoSujeira = "Linha 1\nLinha 2\nLinha 3.";
        String esperado = "Linha 1 Linha 2 Linha 3.";
        
        assertEquals(esperado, gerador.sanitizarTexto(textoSujeira));
    }
    
    // TU04: Teste Unitário - Sanitarização em texto limpo
    @Test
    public void sanitarizarTextoLimpoTest() {
        String textoLimpo = "Este é um texto normal, sem problemas.";
        assertEquals(textoLimpo, gerador.sanitizarTexto(textoLimpo));
    }
    
    // TF01: Teste Funcional (Com verificação de saída) - Geração correta do template
    @Test
    public void gerarDocumentoTipoATest() {
        // 1. Configura para capturar a saída do console (Para verificar o System.out)
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        // Helper para criar um Perito e Laudo Tipo A
        Perito p = new Perito("Cientista", "001", "PC");
        Laudo laudo = new Laudo("Tipo A", p);
        laudo.setCorpoLaudo("Corpo do laudo tipo A.");
        
        // 2. Executa o método
        gerador.gerarDocumento(laudo);

        // 3. Verifica se as mensagens corretas foram impressas (indicando o template usado)
        String saida = outContent.toString();
        assertTrue(saida.contains("Usando Template A. Inserindo dados..."));
        assertTrue(saida.contains("Documento DOCX gerado com sucesso para o Laudo Tipo: Tipo A"));
        
        // 4. Limpa: Restaura a saída original do sistema
        System.setOut(originalOut); 
    }
}
