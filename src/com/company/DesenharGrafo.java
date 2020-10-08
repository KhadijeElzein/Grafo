package com.company;
import org.graphstream.graph.*;
import java.util.ArrayList;
import org.graphstream.graph.implementations.SingleGraph;
/** classe que Desenha o Grafo**/
public class DesenharGrafo {
    private Grafo grafo;
    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> arestasGrafo;
    /**Metodo construtor
     * @param graph Grafo**/
    public DesenharGrafo(Grafo graph){
        grafo = graph;
        vertices = graph.getVertices();
        arestasGrafo = graph.getArestas();
    }
    /** Metodo que desenha o grafo**/
    public Graph desenhar() {
        Graph graph = new SingleGraph("Grafo");
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        graph.addAttribute("ui.stylesheet", "url('C:\\Users\\Khadije\\Desktop\\mestrado\\trab-ia-1\\src\\com\\company\\estiloGrafo.css')");
        int rotulo = 0;
        /** adiciona os vertices do grafo pra desenhar**/
        for (Vertice vertice : vertices) {
            graph.addNode(vertice.getRotulo());
            graph.getNode(vertice.getRotulo()).setAttribute("label", vertice.getRotulo());

        }
        /** evita que saiam arestas duplicadas caso o grafo seja nao direcionado**/
        for(int i=0;i<arestasGrafo.size();i++){
            for(int j=0;j<arestasGrafo.size();j++){
                if(!grafo.getDirecionado())
                    if(arestasGrafo.get(i).getOrigem() == arestasGrafo.get(j).getDestino() && arestasGrafo.get(i).getDestino() == arestasGrafo.get(j).getOrigem())
                    arestasGrafo.remove(arestasGrafo.get(j));
            }
        }
        /**Monta as arestas do grafo para desenhar**/
        for (Aresta aresta: arestasGrafo) {
            if(aresta.getRotulo()!=null) {
                if(grafo.getDirecionado()) graph.addEdge(aresta.getRotulo(), aresta.getOrigem().getRotulo(), aresta.getDestino().getRotulo(), true);
                else graph.addEdge(aresta.getRotulo(), aresta.getOrigem().getRotulo(), aresta.getDestino().getRotulo(), false);
                graph.getEdge(aresta.getRotulo()).setAttribute("label", aresta.getRotulo() + ": " + aresta.getPeso());
            }else{
                aresta.setRotulo(String.valueOf(rotulo));
                if(grafo.getDirecionado()) graph.addEdge(aresta.getRotulo(), aresta.getOrigem().getRotulo(), aresta.getDestino().getRotulo(), true);
                else  graph.addEdge(aresta.getRotulo(), aresta.getOrigem().getRotulo(), aresta.getDestino().getRotulo(), false);
                graph.getEdge(aresta.getRotulo()).setAttribute("label", aresta.getPeso());
                rotulo++;
            }
        }
        /**Desenha o grafo**/
        graph.display();
        return graph;
    }
}
