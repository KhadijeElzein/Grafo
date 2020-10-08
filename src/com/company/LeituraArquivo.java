package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
/** classe que faz a leitura do grafo do arquivo**/
public class LeituraArquivo {
    protected  Grafo grafo;
    protected File fileIn;

    /**
     * metodo construtor que pega o arquivo
     * @param fileIn
     */
    public LeituraArquivo(File fileIn) {
        grafo = new Grafo();
        this.fileIn = fileIn;
    }

    /**
     * metodo que faz a leitura do arquivo
     * @return grafo
     */
    public Grafo LerArquivo(){
        try {
            ArrayList<Vertice> lista = new ArrayList<Vertice>();
            String[] rotulos;
            String aux;
            String[] verticeArestas;
            Aresta a,a1 = null;
            ArrayList<Aresta> arestas;
            BufferedReader reader = new BufferedReader(new FileReader(fileIn));
            String s = reader.readLine();
            if(s.contains("sim")) grafo.setDirecionado(true);
            else grafo.setDirecionado(false);
            System.out.println("Direcionado: "+grafo.getDirecionado());
            s = reader.readLine();
            if(s.contains("{")) {
                s = s.substring(s.lastIndexOf('{') + 1);
                rotulos = s.split(",");
                grafo.setQtdVertice(rotulos.length);
                for (int i = 0; i < rotulos.length; i++) {
                    if(rotulos[i].contains("}")) rotulos[i] = rotulos[i].substring(0,rotulos[i].indexOf('}'));
                    Vertice v = new Vertice(rotulos[i], i);
                    v = grafo.addVertice(v);
                }
            }else{
                s = s.substring(s.lastIndexOf('=') + 1);
                grafo.setQtdVertice(Integer.parseInt(s));
            }
            s = reader.readLine();
            lista = grafo.getVertices();
            while(s!= null) {
                aux = s.substring(s.lastIndexOf('(') + 1);
                aux = aux.substring(0, s.indexOf(')') - 1);
                verticeArestas = aux.split(",");
                Integer origem = Integer.parseInt(verticeArestas[0]);
                Integer destino = Integer.parseInt(verticeArestas[1]);
                Vertice vorig = new Vertice(verticeArestas[0], origem);
                Vertice vdestino = new Vertice(verticeArestas[1], destino);
                if (lista != null) {
                    for (int i = 0; i < lista.size(); i++) {
                        if ((Objects.equals(lista.get(i).getIndex(), origem))) vorig = lista.get(i);
                        if (Objects.equals(lista.get(i).getIndex(), destino)) vdestino = lista.get(i);
                    }
                }
                if(lista.size() < grafo.getQtdVertice()){
                    if(!lista.contains(vorig)) vorig = grafo.addVertice(vorig);
                    if(!lista.contains(vdestino)) vdestino = grafo.addVertice(vdestino);
                }
                if (!grafo.getDirecionado()) {
                    a1 = grafo.addAresta(vdestino, vorig);
                }
                a = grafo.addAresta(vorig, vdestino);
                aux = s.substring(s.lastIndexOf(':') + 1);
                if (aux.contains(",")) {
                    rotulos = aux.split(",");
                    a.setPeso(Integer.parseInt(rotulos[0]));
                    a.setRotulo(rotulos[1]);
                    if(a1!=null) {
                        a1.setRotulo(rotulos[1]);
                        a1.setPeso(Integer.parseInt(rotulos[0]));
                    }
                } else{
                    a.setPeso(Integer.parseInt(aux));
                    if(a1!=null) a1.setPeso(Integer.parseInt(aux));
                }
                s = reader.readLine();
            }
            lista = grafo.getVertices();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return grafo;
    }
}
