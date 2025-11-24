package model;

public class Aluno extends Pessoa {

    public Aluno(String nome, int idade, int anoNascimento) {
        super(nome, idade, anoNascimento);
    }

    @Override
    public void imprimirPDF() {
        System.out.println("Imprimindo PDF do Aluno...");
    }
}
