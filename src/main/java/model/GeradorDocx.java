package model;

public class GeradorDocx {
    
    // TU04: Sanitarização de Entrada
    public String sanitizarTexto(String texto) {
        if (texto == null) return "";
        return texto
            .replaceAll("<.*?>", "") // Remove tags HTML/XML
            .replaceAll("\\n", " "); // Substitui quebras de linha por espaço
    }

    // TF01: Simula a injeção de dados no template DOCX
    public boolean gerarDocumento(Laudo laudo) {
        String textoFormatado = sanitizarTexto(laudo.getCorpoLaudo());
        
        if (laudo.getTipoLaudo().equals("Tipo A")) {
            System.out.println("Usando Template Tipo A. Inserindo dados formatados...");
        } else if (laudo.getTipoLaudo().equals("Tipo B")) {
            System.out.println("Usando Template Tipo B. Inserindo dados formatados...");
        }
        
        System.out.println("Documento DOCX gerado com sucesso para o Laudo Tipo: " + laudo.getTipoLaudo());
        return true;
    }
}
