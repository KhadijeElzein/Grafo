package com.company;

import org.graphstream.graph.Graph;
import java.util.ArrayList;
import java.util.Collections;
/** classe do algoritmo BellmanFord**/
public class BellmanFord {
    private Grafo grafo;
    private Vertice origem;
    private Graph graph;
    private DesenharGrafo desenharGrafo;
    /** Construtor do BellmanFord, inicializa o grafo e o vertice de origem**/
    public BellmanFord(Grafo grafo, Vertice origem) {
        this.grafo = grafo;
        this.origem = origem;
        desenharGrafo = new DesenharGrafo(grafo);
        graph = desenharGrafo.desenhar();
    }
    /** executa o algoritmo de Bellman Ford**/
    public boolean executaAlgoritmo() {
        /** seta as distancias e os pais dos vertices, a disstancia do vertice de origem é zero**/
        for (Vertice vertice: grafo.getVertices()) {
            vertice.setD(Integer.MAX_VALUE);
            vertice.setPi(null);
            if(vertice == origem) vertice.setD(0);
        }
        /** a pesquisa deve comecar pelo vertice de origem**/
        Collections.swap(grafo.getVertices(),0,origem.getIndex());
        ArrayList<Vertice> vertices = grafo.getVertices();
        for (int i = 1; i < grafo.getQtdVertice() - 1; i++) {
            for (Vertice vertice: vertices) {
                /** percorre as arestas do grafo**/
                for (Aresta aresta: vertice.getAdjacente()) {
                    /** relaxa as arestas**/
                        relax(aresta);
                }
        }

    }
        for (Vertice vertice: grafo.getVertices()) {
            /** percorre as arestas testando se ha ciclos negativos**/
        for (Aresta aresta: vertice.getAdjacente()) {
            Vertice u = vertice;
            Vertice v = aresta.getDestino();
            /** se houver ciclos negativos retorna falso**/
            if (v.getD() > u.getD() + aresta.getPeso()) {
                return false;
            }
        }
    }
        return true;
}

    /**
     * Metodom de relaxacao do Belman Ford
     * @param uv Aresta uv Aresta do grafo
     */
    private void relax(Aresta uv) {
        int i=0;
        /**vertices de origem e destino da aresta**/
        Vertice U = uv.getOrigem();
        Vertice V = uv.getDestino();
        /** seta as distancias e pais dos vertices**/
        if (V.getD() > (U.getD() + uv.getPeso())) {
            V.setD(U.getD() + uv.getPeso());
            if(V == origem) V.setPi(null);
            else V.setPi(uv.getOrigem());
            if(V.getPi()!=null){
                /** Monta uma  aresta pra mostrar o menor caminho no grafo**/
                    Aresta a = new Aresta(V.getPi(),V);
                    for(Aresta arestas:grafo.getArestas()){
                        if(arestas.getRotulo() == null) arestas.setRotulo(String.valueOf(i));
                        if(grafo.getDirecionado()) {
                            if ((arestas.getOrigem() == a.getOrigem() && arestas.getDestino() == a.getDestino()))
                                graph.getEdge(arestas.getRotulo()).setAttribute("ui.class", "menorescaminhos");

                        }if ((arestas.getOrigem() == a.getOrigem() && arestas.getDestino() == a.getDestino()) || (arestas.getDestino() == a.getOrigem() && arestas.getOrigem() == a.getDestino()))
                            graph.getEdge(arestas.getRotulo()).setAttribute("ui.class", "menorescaminhos");
                        i++;
                    }
            }
        }
    }

    /*public void imprimirCaminho(int verticeFinal) {
        if (verticeFinal == origem) {
            try {
                grafo.repintarGrafo();
                URL url = (new java.io.File("src/com/unioeste/algoritmos/BellmanFord.css")).toURI().toURL();
                desenho.addAttribute("ui.stylesheet", "url('" + url + "')");
                desenho.getNode(origem).addAttribute("ui.class", "origem");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            Vertice vertice = grafo.getVertice(verticeFinal);
            if (verticeFinal >= grafo.getQuantidadeVertice()) {
                System.out.println("Não existe menor caminho de [s] para [v]\n");
                grafo.repintarGrafo();
            } else {
                imprimirCaminho(tabela.get(vertice).getPi().intValue());
                desenho.getNode(tabela.get(vertice).getRotulo()).setAttribute("ui.class", "visitado");
                desenho.getEdge(grafo.getRotuloAresta(tabela.get(vertice).getPi().intValue(), tabela.get(vertice).getVertice().getVertice())).setAttribute("ui.class", "visitado");
            }*/
        }


