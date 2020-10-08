package com.company;

import org.graphstream.graph.Graph;

import java.util.*;
/** Classe dos algoritmos de busca**/
public class Busca {
    private Integer timestamp;
    private Graph graph;
    private Grafo grafo;
    public Busca(Grafo grafo){
        this.grafo = grafo;
        DesenharGrafo desenharGrafo= new DesenharGrafo(grafo);
        graph =  desenharGrafo.desenhar();
    }
    /** Algoritmo de busca em profundidade**/
    public void DFS(){
        /**Lista de vertices do Grafo**/
        ArrayList<Vertice> vertices = grafo.getVertices();
        /**percorre a lista de vertices inicializando eles**/
        for (int i=0;i<vertices.size();i++){
            vertices.get(i).setCor("branco");
            vertices.get(i).setPi(null);
        }
        /** inicializa o timestamp**/
        timestamp = 0;
        ArrayList<Vertice> lista = new ArrayList<>();
        System.out.println("Vertice|Pi|D|F|COR");
        for (int i=0;i<vertices.size();i++){
            /** se vertice for da cor branca chama o procedimento de visita**/
            if(vertices.get(i).getCor().equals("branco")){
                DFS_Visit(vertices.get(i));
            }
        }

    }
    public void DFS_Visit(Vertice u){
        /**seta a cor cinza, pois vertice foi visitado**/
        u.setCor("cinza");
        /** itera o timestamp**/
        timestamp ++;
        /** seta o tempo de descoberta**/
        u.setD(timestamp);
        List<Aresta> a  = u.getAdjacente();
        /**percorre todas as arestas adjacentes ao vertice passado**/
            for (int i = 0; i < a.size(); i++) {
                if(a.get(i).getRotulo() == null) a.get(i).setRotulo(String.valueOf(i));
                    if(a.get(i).getDestino().getCor().equals("branco")){
                        a.get(i).getDestino().setPi(u);
                        DFS_Visit(a.get(i).getDestino());
                    }
            }
            /**seta a cor do vertice como preto, pois percorreu as adjacencias**/
            u.setCor("preto");
            timestamp++;
            /**seta o tempo final do vertice**/
            u.setF(timestamp);
            if(u.getPi() == null) System.out.println(u.getIndex()+" "+ "null"+" " + u.getD()+" "+u.getF()+" " +u.getCor());
            else System.out.println(u.getIndex()+" "+ u.getPi().getIndex()+" "+ u.getD()+" "+ u.getF()+" " +u.getCor());
    }

    /** faz a busca em largura
     * @param grafo Grafo
     * vertice inicial**/
    public void BFS(Grafo grafo, Vertice s){
        Vertice u = null;
        ArrayList<Vertice> verticesSemS = grafo.getVertices();
        verticesSemS.remove(s);
        System.out.println("Vertice|Pi|D|COR");
        /** inicializa os vertices que nao sao de origem**/
        for(int i=0; i< verticesSemS.size();i++){
            verticesSemS.get(i).setCor("branco");
            verticesSemS.get(i).setD(Integer.MAX_VALUE);
            verticesSemS.get(i).setPi(null);
        }
        /** seta cor do vertice inicial como cinza**/
        s.setCor("cinza");
        /** inicializa o vertice inicial**/
        s.setPi(null);
        s.setD(0);
        /**cria uma fila de vertices e add o vertice inicial**/
        Queue<Vertice> filaVertices = new LinkedList<Vertice>();
        filaVertices.add(s);
        /** enquanto a fila nao for vazia**/
        while (!filaVertices.isEmpty()){
            /**pega a cabeca da fila**/
            u = filaVertices.peek();
            /**percorre as adjacencias**/
            List<Aresta> adjacente = u.getAdjacente();
            for(int i=0;i<adjacente.size();i++){
                /** se o adjacente for de cor branca seta o tempo de descoberta, a cor como cinza e o pai**/
                if(adjacente.get(i).getDestino().getCor().equals("branco")){
                    adjacente.get(i).getDestino().setCor("cinza");
                    adjacente.get(i).getDestino().setD(u.getD()+1);
                    adjacente.get(i).getDestino().setPi(u);
                    /**adiciona a fila de vertices o vertice adjacente**/
                    filaVertices.add(adjacente.get(i).getDestino());
                }
            }
            /**remove o vertice da fila e seta sua cor como preto**/
            filaVertices.poll();
            u.setCor("preto");
            if(u.getPi() == null) System.out.println(u.getIndex()+" "+ "null"+" " + u.getD()+" "+u.getF()+" " +u.getCor());
            else System.out.println(u.getIndex()+" "+ u.getPi().getIndex()+" "+ u.getD()+" "+u.getCor());
        }
    }
}
