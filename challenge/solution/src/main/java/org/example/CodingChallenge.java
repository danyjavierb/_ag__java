package org.example;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Dados los String "a" y "b", se debe retornar el número de subsecuencias de "a" que pueden formar a "b"
 *
 * Una subsecuencia es un nuevo String formado por el original al que se le han borrado
 * algunos (puede ser ninguno) caracteres sin alterar las posiciones relativas de los
 * caracteres restantes (es decir "ADF" es una subsecuencia de "ABCDEF" mientras que "AFD" no).
 *
 * Ejemplo 1:
 *
 * Entrada: a = "apppgate", b = "appgate"
 * Salida: 3
 * Explicación:
 * Existen 3 maneras en que se puede formar "appgate" de "apppgate".
 * app gate (suprimiendo el cuarto caracter)
 * ap pgate (suprimiendo el tercer caracter)
 * a ppgate (suprimiendo el segundo caracter)
 *
 * Ejemplo 2:
 *
 * Entrada: a = "vovzvoz", b = "voz"
 * Salida: 5
 * Explicación:
 * Existen 5 maneras en que se puede formar "voz" de "vovzvoz".
 * vo z    (suprimiendo tercero, quinto, sexto y séptimo caracter)
 * vo    z (suprimiendo tercero, cuarto, quinto y sexto caracter)
 * v    oz (suprimiendo segundo, tercero, cuarto y quinto caracter)
 *   v  oz (suprimiendo primero, segundo, cuarto y quinto caracter)
 *     voz (suprimiendo primero, segundo, tercero y cuarto caracter)
 *
 * Restricciones:
 * "a" tiene al menos 1 caracter, "b" tiene máximo 1000 caracteres
 * "a" y "b" contienen caracteres entre a y z
 */


public class CodingChallenge {

    @FunctionalInterface
    public interface FourParameterFunction<T, U, V, W, R> {
        public R apply(T t, U u, V v, W w);
    }
    @FunctionalInterface
    public interface TwoParameterFunction<T, U, R> {
        public R apply(T t, U u);
    }

    static FourParameterFunction<String,String,Integer,Integer,Integer> aux = (String a, String b, Integer m , Integer n ) -> {

       int[] subs = new int[n];
       Arrays.fill(subs,0);
        for (int i = 0; i < m; i++)
        {

            for (int j = n - 1; j >= 0; j--)
            {
                if (a.charAt(i) == b.charAt(j))
                {
                   if (j == 0)   subs[j] ++;
                   else  subs[j] += subs[j - 1];
                }
            }
        }

        return Arrays.stream(subs).max().getAsInt();
    };


    public int solution(String first, String second) {

       return CodingChallenge.aux.apply(first,second,first.length(),second.length());

    }


    public static void main(String[] args) {
        System.out.println(new CodingChallenge().solution("vovzvoz","voz"));
    }



}
