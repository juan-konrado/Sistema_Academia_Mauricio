package model;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Pessoa;

public class PessoaTest {

    @Test(expected = IllegalArgumentException.class)
    public void nomeMaiorQue100Test() {
        new Pessoa("A".repeat(101), 20, 1999);
    }

    @Test
    public void nomeValidoTest() {
        Pessoa p = new Pessoa("Joao", 20, 1999);
        assertEquals("Joao", p.getNome());
    }

    @Test(expected = IllegalArgumentException.class)
    public void idadeInvalidaTest() {
        new Pessoa("Maria", 150, 1990);
    }

    @Test(expected = IllegalArgumentException.class)
    public void anoNascimentoInvalidoTest() {
        new Pessoa("Ana", 20, 1500);
    }

    @Test
    public void imprimirPDFTest() {
        Pessoa p = new Pessoa("Pedro", 40, 1984);
        p.imprimirPDF();
    }
}
