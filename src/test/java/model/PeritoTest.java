package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PeritoTest {

    @Test
    public void formatarCredencialTest() {
        Perito p = new Perito("Alice Mendes", "123456", "Perito Criminal");
        String esperado = "Dr(a). Alice Mendes - Matrícula: 123456 (Perito Criminal)";
        
        // Verifica se o resultado do método é igual ao formato esperado
        assertEquals(esperado, p.formatarCredencial());
    }

    // Teste simples para garantir que o nome seja recuperado corretamente
    @Test
    public void getNomeCompletoTest() {
        Perito p = new Perito("Roberto Silva", "7890", "Perito Criminal");
        assertEquals("Roberto Silva", p.getNomeCompleto());
    }
}
