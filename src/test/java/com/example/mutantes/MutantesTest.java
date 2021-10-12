package com.example.mutantes;

import com.example.mutantes.services.MutantService;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MutantesTest {

    MutantService ms;
    @BeforeAll
    public void creacionService(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.example.mutantes.services");
        context.refresh();
        ms = context.getBean(MutantService.class);
    }
    @BeforeEach
    public void beforeEach(TestInfo info){
        System.out.println("Inicia: " + info.getDisplayName());
    }

    @Test
    @Order(1)
    public void testMutante(){ //test con el ejemplo de meli (diagonal y vertical)
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        boolean resultado;
        resultado = ms.isMutant(dna);

        Assertions.assertTrue(resultado);
        System.out.println(resultado);
    }

    @Test
    @Order(2)
    public void testMutante2(){ //test para horizontales y diagonal
        String[] dna = {"ACGTA","CGTGA","TTTTG","TGACA","CCCAC"};
        boolean resultado;
        resultado = ms.isMutant(dna);

        Assertions.assertTrue(resultado);
        System.out.println(resultado);
    }

    @Test
    @Order(3)
    public void testMutante3(){ //test de doble diagonales inversas
        String[] dna = {"ACGTA","CGTGT","ATTTG","TGTCT","CTCAC"};
        boolean resultado;
        resultado = ms.isMutant(dna);

        Assertions.assertTrue(resultado);
        System.out.println(resultado);
    }

    @Test
    @Order(4)
    public void testMutante4(){ //test de doble horizontales
        String[] dna = {"CCCCGA","CAGTGA","TAATCG","AAAAGG","CCCGTT","TCCCTG"};
        boolean resultado;
        resultado = ms.isMutant(dna);

        Assertions.assertTrue(resultado);
        System.out.println(resultado);
    }

    @Test
    @Order(5)
    public void testMutante5(){
        String[] dna = {"ATGTA","CGTGA","TTTTG","AGACT","CCCAC"};
        boolean resultado;
        resultado = ms.isMutant(dna);

        Assertions.assertTrue(resultado);
        System.out.println(resultado);
    }

    @Test
    @Order(6)
    public void testMutante6(){
        String[] dna = {"TTTTGA","CAGTGG","TTATGA","AGACAG","CCGATA","GGAGTG"};
        boolean resultado;
        resultado = ms.isMutant(dna);

        Assertions.assertTrue(resultado);
        System.out.println(resultado);
    }


    @Test
    @Order(7)
    public void testMutante7(){ //test de fallo
        String[] dna = {"TTATGA","CAGTGG","TTATGA","AGACAG","CCGATA","GGAGTG"};
        boolean resultado;
        resultado = ms.isMutant(dna);

        Assertions.assertFalse(resultado);
        System.out.println(resultado);
    }


    @Test
    @Order(8)
    public void testMutante8(){
        String[] dna = {"ATGTA","AGTGC","ATTTC","AGACC","CCCAC"};
        boolean resultado;
        resultado = ms.isMutant(dna);

        Assertions.assertTrue(resultado);
        System.out.println(resultado);
    }

}
