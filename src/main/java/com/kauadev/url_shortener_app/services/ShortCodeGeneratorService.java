package com.kauadev.url_shortener_app.services;

import org.hashids.Hashids;

public class ShortCodeGeneratorService {

    // chave secreta pro salt e numero de caracteres minimo
    private static final Hashids hashIds = new Hashids("my-secret-salt", 5);

    // biblioteca hashIds -> decodifica com base em um id

    public static String genShortCode(Long id) {
        return hashIds.encode(id); // permite decodificar varios
    }

    public static long decodeShortCode(String shortCode) {
        // por permitir fazer o hash de varios, o que retorna é um array em que cada
        // indice contém a decodificação de um indice.
        // nesse caso, só temos id, logo tá no indice 0
        long[] numbers = hashIds.decode(shortCode);

        return numbers[0];
    }

}
