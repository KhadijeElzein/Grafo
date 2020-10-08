package com.company;

import java.util.ArrayList;
import java.util.List;
/** classe do vertice**/
public class Vertice {
        private String rotulo; //rotulo do vertice
        private Integer index; //indice do vertice
        List<Aresta> adjacente; //lista de arestas adjacentes
        /** estruturas adicionais para os algoritmos**/
        private String cor;
        private Vertice pi;
        private  Integer d;
        private Integer f;

    /**
     * metodo construtor
     * @param rotulo rotulo do vertice
     * @param index indice do vertice
     */
        Vertice(String rotulo,Integer index) {
            this.rotulo = rotulo;
            this.index = index;
            this.adjacente = new ArrayList<Aresta>();
        }

    /**
     * construtor com so o indice
      * @param index
     */
    public Vertice(Integer index) {
            this.index = index;
            this.adjacente = new ArrayList<Aresta>();
            this.rotulo = null;
        }
    /**
     * construtor padrao
     */
    public Vertice() {

    }

    /**
     * add aresta adjacente
     * @param aresta
     */
    void addAdjacente(Aresta aresta) {
            adjacente.add(aresta);
        }
        //getters e setters
        public String getRotulo() {
            return rotulo;
        }

        public Integer getIndex() {
        return index;
    }

        public void setRotulo(String rotulo) {
            this.rotulo = rotulo;
        }

        public List<Aresta> getAdjacente() {
            return adjacente;
        }

        public String getCor() { return cor; }

        public void setCor(String cor) { this.cor = cor; }
        public Vertice getPi() {    return pi; }
        public void setPi(Vertice pi) { this.pi = pi; }
        public Integer getD() { return d; }
        public void setD(Integer d) { this.d = d; }
        public Integer getF() { return f; }
        public void setF(Integer f) { this.f = f; }
}
