package com.company;

import java.util.ArrayList;
import java.util.List;
/** classe do grafo**/
public class Grafo {
        Boolean isDirecionado; //grafo é direcionado?
        ArrayList<Vertice> vertices; //lista de vertices do grafo
        ArrayList<Aresta> arestas; //lista de arestas do grafo
        Integer qtdVertice; //qtd de vertices do grafo
        //metodo construtor
        public Grafo() {
            vertices = new ArrayList<Vertice>();
            arestas = new ArrayList<Aresta>();
        }
        //add vertice a lista
        Vertice addVertice(Vertice v) {
            vertices.add(v);
            return v;
        }
        //getters e setters
        public void setQtdVertice(Integer qtdVertice) {
            this.qtdVertice = qtdVertice;
        }

        public void setDirecionado(Boolean direcionado) {
            isDirecionado = direcionado;
        }

        public Boolean getDirecionado() {
            return isDirecionado;
        }

        public Integer getQtdVertice() {
            return qtdVertice;
    }
        //add aresta a lista
        Aresta addAresta(Vertice origem, Vertice destino) {
            Aresta e = new Aresta(origem, destino);
            origem.addAdjacente(e);
            arestas.add(e);
            return e;
        }
        //para imprimir o grafo no terminal
        public String toString() {
            String r = "";
            for (Vertice u : vertices) {
                r += u.getRotulo() + " -> ";
                for (Aresta e : u.getAdjacente()) {
                    Vertice v = e.destino;
                    r += v.getRotulo() + ", ";
                }
                r += "\n";
            }
            return r;
        }
        //imprime grafo no terminal
        public void printGrafo(Grafo g) {
                  System.out.println(g);
        }
    //mais getters
    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }
}


