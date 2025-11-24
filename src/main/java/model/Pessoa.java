package model;

public class Pessoa {
	private String nome;
	private int idade;
	private int anoNascimento;

	public Pessoa(String nome, int idade, int anoNascimento) {
		if (nome.length() > 100) {
			throw new IllegalArgumentException("Nome com mais de 100 caracteres.");
		}
		if (idade > 120 || idade < 0) {
			throw new IllegalArgumentException("Idade inválida.");
		}
		if (anoNascimento < 1900) {
			throw new IllegalArgumentException("Ano de nascimento inválido.");
		}

		this.nome = nome;
		this.idade = idade;
		this.anoNascimento = anoNascimento;
	}

	public void imprimirPDF() {
		System.out.println("Imprimindo PDF de Pessoa...");
	}

	public String getNome() {
		return nome;
	}
}