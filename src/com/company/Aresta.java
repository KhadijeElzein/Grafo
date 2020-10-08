package com.company;
/*
    Classe da aresta
 */
public class Aresta {
        String rotulo; //rotulo da aresta
        Integer peso; //peso da aresta
        Vertice origem; //vertice de origem
        Vertice destino; //vertice de destino
        //construtor da aresta
        Aresta(Vertice origem, Vertice destino) {
            this.origem = origem;
            this.destino = destino;
        }

    /*Getters e Setters*/
    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Vertice getOrigem() {
        return origem;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }
}
