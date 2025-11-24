package model;

import org.junit.Test;
import model.Aluno;

public class AlunoTest {

    @Test
    public void imprimirPDFAlunoTest() {
        Aluno a = new Aluno("Carlos", 25, 1998);
        a.imprimirPDF();
    }
}
