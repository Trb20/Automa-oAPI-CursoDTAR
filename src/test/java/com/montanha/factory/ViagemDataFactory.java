package com.montanha.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.montanha.pojo.Viagem;
import java.io.FileInputStream;
import java.io.IOException;

public class ViagemDataFactory {
    public static Viagem cadastraViagem() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Viagem viagem = objectMapper.readValue(new FileInputStream("src/test/resources/requestBody/postV1Viagens.json"), Viagem.class);
        return viagem;
    }

    public static Viagem cadastraViagemValida() throws IOException {
        Viagem cadastraViagem = cadastraViagem();
        return cadastraViagem;
    }

    public static Viagem cadViagemSemLocalDestino() throws IOException {
        Viagem cadastrarViagemSemLocalDeDestino = cadastraViagem();
        cadastrarViagemSemLocalDeDestino.setLocalDeDestino("");
        return cadastrarViagemSemLocalDeDestino;
    }
}