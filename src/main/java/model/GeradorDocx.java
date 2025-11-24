package model;

public class GeradorDocx {

    public String sanitizarTexto(String texto) {
        if (texto == null) return "";
        // Remove comandos perigosos, tags HTML e substitui quebras de linha
        return texto
            .replaceAll("<.*?>", "") // Remove tags HTML/XML
            .replaceAll("\\n", " "); // Substitui quebras de linha por espaço
    }

    // Método que simula a injeção de dados no template DOCX
    public boolean gerarDocumento(Laudo laudo) {
        // 1. Processar dados (Ex: Sanitizar)
        String textoFormatado = sanitizarTexto(laudo.getCorpoLaudo());
        
        // 2. Selecionar o template
        if (laudo.getTipoLaudo().equals("Tipo A")) {
            // Lógica para usar Template A
            System.out.println("Usando Template A. Inserindo dados...");
        }
        
        if (laudo.getTipoLaudo().equals("Tipo B")) {
            // Lógica para usar Template A
            System.out.println("Usando Template B. Inserindo dados...");
        }
        
        if (laudo.getTipoLaudo().equals("Tipo C")) {
            // Lógica para usar Template A
            System.out.println("Usando Template C. Inserindo dados...");
        }
        
        // 3. Simula a escrita do arquivo DOCX
        System.out.println("Documento DOCX gerado com sucesso para o Laudo Tipo: " + laudo.getTipoLaudo());
        return true;
    }
}
