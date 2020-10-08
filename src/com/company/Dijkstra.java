package com.company;

import org.graphstream.graph.Graph;
import java.util.*;
/** Classe do metodo Dijkstra**/
public class Dijkstra {
    private Grafo grafo;
    private Vertice origem;
    private Graph graph;
    private DesenharGrafo desenharGrafo;
    private PriorityQueue<Vertice> filaPrioridade;

    /**Metodo construtor
     *
     * @param grafo Grafo
     * @param origem vertice de origem
     */
    public Dijkstra(Grafo grafo, Vertice origem) {
        this.grafo = grafo;
        this.origem = origem;
        desenharGrafo = new DesenharGrafo(grafo);
        graph = desenharGrafo.desenhar();
    }

    /**
     * Executa Dijkstra de acordo com as entradas
     */
    public void executarAlgoritmo() {
        int i=0;
        /** fila de prioridade de acordo com as distancias**/
            filaPrioridade= new PriorityQueue<Vertice>(grafo.getQtdVertice(), new Comparator<Vertice>() {
                @Override
                public int compare(Vertice o1, Vertice o2) {
                    if (o1.getD() < o2.getD()) return -1;
                    if (o1.getD() > o2.getD()) return 1;
                    return 0;
                }
            });
            /**inicializa os vertices**/
        for (Vertice vertice: grafo.getVertices()) {
            vertice.setD(Integer.MAX_VALUE);
            vertice.setPi(null);
        }
        /** add os vertices a fila**/
        filaPrioridade.addAll(grafo.getVertices());
        /**inicializa o vertice de origem**/
        filaPrioridade.remove(origem);
        origem.setD(0);
        filaPrioridade.add(origem);
        List<Vertice> S = new ArrayList<>();
        while (!filaPrioridade.isEmpty()) { /**Enquanto a fila de prioridade não for vazia**/
            /** pega a cabeca da fila**/
            Vertice atual = filaPrioridade.poll();
            S.add(atual);
            for (Aresta aresta: atual.getAdjacente()) {
                if(aresta.getPeso() < 0){
                    System.out.println("Nao da pra fazer Dijkstra");
                    return;
                }
                /** relaxa as arestas**/
                Relax(aresta);
            }
            /**imprime os menores caminhos no grafo**/
            if(atual.getPi()!=null){
                Aresta a = new Aresta(atual.getPi(),atual);
                for(Aresta arestas:grafo.getArestas()){
                    if(arestas.getRotulo() == null) arestas.setRotulo(String.valueOf(i));
                    if(grafo.getDirecionado()) {
                        if ((arestas.getOrigem() == a.getOrigem() && arestas.getDestino() == a.getDestino()))
                            graph.getEdge(arestas.getRotulo()).setAttribute("ui.class", "menorescaminhos");

                    }else{
                            if((arestas.getOrigem() == a.getOrigem() && arestas.getDestino() == a.getDestino())|| (arestas.getDestino() == a.getOrigem() && arestas.getOrigem() == a.getDestino()))
                                graph.getEdge(arestas.getRotulo()).setAttribute("ui.class","menorescaminhos");
                    }
                    i++;
                }
            }
        }

    }

    /**
     * Relaxaçao do Dijkstra
     * @param uv    Aresta
     */
    private void Relax(Aresta uv) {
        Vertice U = uv.getOrigem();
        Vertice V = uv.getDestino();
        if (V.getD() > (U.getD() + uv.getPeso())) {
            //Reorganiza a fila de prioridade
            filaPrioridade.remove(V);
            V.setD(U.getD() + uv.getPeso());
            V.setPi(uv.getOrigem());
            filaPrioridade.add(V);
        }
    }
}

