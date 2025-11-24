package model;

public class Professor extends Pessoa {

    public Professor(String nome, int idade, int anoNascimento) {
        super(nome, idade, anoNascimento);
    }

    @Override
    public void imprimirPDF() {
        System.out.println("Imprimindo PDF do Professor...");
    }
}