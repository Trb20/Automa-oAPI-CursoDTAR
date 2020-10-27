package com.montanha.pojo;

public class Viagem {
    private String acompanhante;
    private String dataPartida;
    private String dataRetorno;
    private String localDeDestino;
    private String regiao;

    public void setAcompanhante(String acompanhante) {
        this.acompanhante = acompanhante;
    }

    public String getAcompanhante() {
        return acompanhante;
    }

    public void setDataPartida(String dataPartida) {
        this.dataPartida = dataPartida;
    }

    public String getDataPartida() {
        return dataPartida;
    }

    public void setDataRetorno(String dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public String getDataRetorno() {
        return dataRetorno;
    }

    public void setLocalDeDestino(String localDeDestino) {
        this.localDeDestino = localDeDestino;
    }

    public String getLocalDeDestino() {
        return localDeDestino;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getRegiao() {
        return regiao;
    }
}
