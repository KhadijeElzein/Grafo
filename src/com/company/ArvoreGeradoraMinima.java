package com.company;

import org.graphstream.graph.Graph;

import java.util.*;

public class ArvoreGeradoraMinima {

    /* Algoritmo de Kruskal, Pega o grafo como inicio*/
    public ArrayList<Aresta> Kruskal(Grafo grafo){
        DesenharGrafo desenharGrafo= new DesenharGrafo(grafo);
        Graph graph =  desenharGrafo.desenhar();
        ArrayList<Aresta> arestas = grafo.getArestas();
        ArrayList<Aresta> arestaArvoreGeradora = new ArrayList<Aresta>();
        ConjuntoVertices cojuntovertice = new ConjuntoVertices(grafo.getQtdVertice()+1);
        Integer pesoTotal= 0;
        /*organiza a lista por pesos das arestas*/
        Collections.sort(arestas, new Comparator<Aresta>() {
            @Override
            public int compare(Aresta o1, Aresta o2) {

                return o1.getPeso().compareTo(o2.getPeso());
            }
        });
        /* percorre as arestas do grafo, gerando a arvore */
        for(int i=0;i<arestas.size() && arestaArvoreGeradora.size() < (grafo.getQtdVertice()-1);i++){
            Aresta atual = arestas.get(i);
            /* encontra os elementos no conjunto e coloca nas raizes*/
            Integer raiz1 = cojuntovertice.find(atual.getOrigem().getIndex());
            Integer raiz2 = cojuntovertice.find(atual.getDestino().getIndex());
            if(raiz1!=raiz2){
                /*add a arvore geradora o vertice*/
                arestaArvoreGeradora.add(atual);
                /* faz uniao de conjuntos*/
                cojuntovertice.union(raiz1,raiz2);
            }
        }
        /*determina o peso total da arvore geradora*/
        for(Aresta aresta: arestaArvoreGeradora){
            pesoTotal += aresta.getPeso();
        }
        /*metodo pra saber quais arestas formam a arvore geradora*/
        int i=0;
        for(Aresta aresta:arestaArvoreGeradora){
            if(aresta.getRotulo()==null) aresta.setRotulo(String.valueOf(i));
            graph.getNode(aresta.getOrigem().getRotulo()).setAttribute("ui.class", "origem");
            graph.getNode(aresta.getDestino().getRotulo()).setAttribute("ui.class","origem");
            graph.getEdge(aresta.getRotulo()).setAttribute("ui.class","menorescaminhos");
            i++;
        }
        /*retorna a arvore geradora*/
        return arestaArvoreGeradora;
    }

    /**Metodo Prim, parametro de entrada é um grafo e um vertice inicial**/
    public void Prim(Grafo grafo, Vertice s) {
        DesenharGrafo desenharGrafo = new DesenharGrafo(grafo);
        Graph graph = desenharGrafo.desenhar();
        /*determina o vertice de origem*/
        graph.getNode(s.getRotulo()).setAttribute("ui.class", "origem");
        Integer peso = null;
        ArrayList<Vertice> vertices = grafo.getVertices();
        Vertice u;
        Queue<Vertice> Q = new LinkedList<>();
        /** inicia os pesos dos vertices**/
        for(Vertice vertice:vertices){
            vertice.setD(Integer.MAX_VALUE);
            vertice.setPi(null);
        }
        /** seta o peso do vertice de origem**/
        s.setD(0);
        s.setPi(null);
        /** ordena a lista por distancias**/
        Collections.sort(vertices, new Comparator<Vertice>() {
            @Override
            public int compare(Vertice o1, Vertice o2) {
                return o1.getD().compareTo(o2.getD());
            }
        });
        /** adiciona lista de vertices a fila**/
        Q.addAll(vertices);
        /**enquanto a fila nao é vazia**/
        while (!Q.isEmpty()) {
            /**pega a cabeca da lista**/
            u = Q.poll();
            /**monta uma lista de arestas adjacentes**/
            List<Aresta> adjacente = u.getAdjacente();
            /**percorre as arestas adjacentes**/
            for (int i = 0; i < adjacente.size(); i++) {
                peso = adjacente.get(i).getPeso();
                /** se o vertice de destino esta na fila e o peso da aresta é menor do que o peso do vertice**/
                if (Q.contains(adjacente.get(i).getDestino()) && peso < adjacente.get(i).getDestino().getD()) {
                    /**o pai é o vertice de origem**/
                    adjacente.get(i).getDestino().setPi(u);
                    /** o peso do vertice é o peso da aresta**/
                    adjacente.get(i).getDestino().setD(peso);
                    if(adjacente.get(i).getRotulo() == null) adjacente.get(i).setRotulo(String.valueOf(i));
                    /** para imprimir as arestas da arvore minima**/
                    graph.getEdge(adjacente.get(i).getRotulo()).setAttribute("ui.class","menorescaminhos");
                }
            }
        }
    }
}
