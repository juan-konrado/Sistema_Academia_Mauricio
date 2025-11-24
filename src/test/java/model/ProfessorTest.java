package model;

import org.junit.Test;
import model.Professor;

public class ProfessorTest {

    @Test
    public void imprimirPDFProfessorTest() {
        Professor p = new Professor("Marcos", 45, 1978);
        p.imprimirPDF();
    }
}
