package model;

public class Perito {
    private String nome;
    private String matricula;
    private String credencial;

    public Perito(String nome, String matricula, String credencial) {
        this.nome = nome;
        this.matricula = matricula;
        this.credencial = credencial;
    }

    public String getNomeCompleto() {
        return nome;
    }

    // TU01: Método para formatar a credencial (foco do Teste Unitário)
    public String formatarCredencial() {
        // Simplesmente retorna o formato para inserção no laudo
        return "Dr(a). " + nome + " - Matrícula: " + matricula + " (" + credencial + ")";
    }
}
