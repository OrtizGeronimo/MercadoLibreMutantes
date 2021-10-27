package com.example.mutantes.services;

import com.example.mutantes.domain.Mutant;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mutantes.repositories.MutantRepository;

import java.util.List;

@Service
public class MutantService {

    @Autowired
    private MutantRepository mutantRepository;

    public MutantService(MutantRepository mutantRepository) {
        this.mutantRepository = mutantRepository;
    }

    public boolean comprobarMutante(Mutant m){
        String[] dna = new String[m.getDna().size()];
        dna = m.getDna().toArray(dna);
        boolean resultado = this.isMutant(dna);
        if (resultado){
            m.setMutant(true);
            m.setDnaPersistido(dna.toString());
            mutantRepository.save(m);
            return true;
        } else {
            m.setMutant(false);
            m.setDnaPersistido(dna.toString());
            mutantRepository.save(m);
            return false;
        }

    }

    public Object getCount(){
        List<Mutant> mutantes = mutantRepository.findAll();
        double count_mutant = 0;
        double count_human = mutantes.size();
        double ratio = 0;
        for (Mutant mutant: mutantes) {
            if (mutant.isMutant()){
                count_mutant++;
            }
        }
        ratio = count_mutant/count_human;
        JSONObject json = new JSONObject();
        json.put("count_mutant_dna", count_mutant);
        json.put("count_human_dna", count_human);
        json.put("ratio", ratio);
        return json;
    }

    public boolean isMutant(String[] dna) {
        int cantTrue = 0;
        String letra;
        int dimension = dna.length;
        String[][] matriz = new String[dimension][dimension];
        rellenarMatriz(dna, matriz);
        String letraA;
        String letraB;
        int contadorA;
        int contadorB;
        //comprobamos verticales
        for (int i = 0; i < dimension-3; i++) {
            //verticales
            loop:
            for (int j = 0; j < dimension; j++) {
                letra = matriz[i][j];
                if (letra.equals(matriz[i + 1][j])) {
                    for (int k = i + 2; k < i + 4; k++) {
                        if (!letra.equals(matriz[k][j])) {
                            break;
                        }
                        if (k == (i + 3)) {
                            cantTrue++;

                            break loop;
                        }
                    }
                }
            }
            if (cantTrue >= 2) {
                return true;
            }
            // diagonales
            contadorA = 0;
            contadorB = 0;
            for (int j = 0; j < dimension - i - 1; j++) {
                if (cantTrue >= 2 || contadorA > 3 || contadorB > 3) {
                    return true;
                }
                //diagonales izquierdas
                letraA = matriz[i + j][j];
                if (letraA.equals(matriz[i + j + 1][j + 1])) {
                    contadorA++;
                } else {
                    contadorA = 0;
                }
                if (contadorA == 3) {
                    cantTrue++;
                }
                //diagonales derechas
                if (i > 0) {
                    letraB = matriz[j][i + j];
                    if (letraB.equals(matriz[j + 1][i + j + 1])) {
                        contadorB++;
                    } else {
                        contadorB = 0;
                    }
                    if (contadorB == 3) {
                        cantTrue++;
                    }

                }
            }
        }
        //horizontales
        String[] condicion = {"AAAA", "TTTT", "CCCC", "GGGG"};
        int firstIndex;
        for (String cadenaAux : dna) {
            if (cantTrue >= 2) {
                return true;
            }
            loop:
            for (String cond : condicion) {
                firstIndex = cadenaAux.indexOf(cond);
                do {
                    if (firstIndex != -1) {
                        cantTrue++;
                        break loop;
                    }
                    firstIndex = cadenaAux.indexOf(cond, firstIndex + 1);
                } while (firstIndex != -1);
            }
        }
        int contador;
        //diagonales de derecha a izq desde fila 0
        for (int i = 3; i < dimension; i++) {
            contador = 0;
            if (cantTrue >= 2) {
                return true;
            }
            for (int j = i; j > 0; j--) {
                letra = matriz[j][i - j];
                if (letra.equals(matriz[j - 1][i - (j - 1)])) {
                    contador++;
                } else {
                    contador = 0;
                }
                if (contador == 3) {
                    cantTrue++;
                   // contador = 0;
                    break;
                }
            }
        }
        //diagonales de derecha a izq desde fila maxima
        for (int i = 1; i < dimension - 3; i++) {
            letra = matriz[dimension - 1][i];
            contador = 0;
             for (int j = 1; j < ((dimension - i)); j++) {
                if (letra.equals(matriz[dimension - j - 1][j + i])) {
                    contador++;
                } else {
                    letra = matriz[dimension - j - 1][j + i];
                    contador = 0;
                }
                if (contador == 3) {
                    cantTrue++;
                    if (cantTrue >= 2){
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }
    public void rellenarMatriz(String[] dna, String[][] matriz) {
        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna.length; j++) {
                matriz[i][j] = Character.toString(dna[i].charAt(j));
            }
        }
    }
}
