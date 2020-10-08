package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
/** Classe de menu**/
public class MenuView extends JFrame {
    private JPanel mainPanel;
    private JButton carregarGrafoButton;
    private JButton buscaEmProfundidadeButton;
    private JButton buscaEmLarguraButton;
    private JButton dijkstraButton;
    private JButton bellmanFordButton;
    private JButton kruskalButton;
    private JButton primButton;
    private JButton desenharGrafoButton;
    private Grafo grafo;
    public MenuView() {

        setTitle("MENU");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        carregarGrafoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarGrafoButtonButtonActionPerformed(e);
            }
        });
        desenharGrafoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desenharGrafoButtonButtonActionPerformed(e);
            }
        });
        buscaEmProfundidadeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscaEmProfundidadeButtonActionPerformed(e);
            }
        });
        buscaEmLarguraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscaemLarguraButtonActionPerformed(e);
            }
        });
        kruskalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kruskalButtonActionPerformed(e);
            }
        });
        bellmanFordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bellmanFordButtonActionPerformed(e);
            }
        });
        primButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               primButtonActionPerformed(e);
            }
        });
        dijkstraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dijkstraButtonActionPerformed(e);
            }
        });
    }

    private void carregarGrafoButtonButtonActionPerformed(ActionEvent evt) {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setDialogTitle("Escolha o arquivo");
            if (chooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG) {
                File fileSelected = chooser.getSelectedFile();
                LeituraArquivo leituraArquivo = new LeituraArquivo(fileSelected);
                grafo = leituraArquivo.LerArquivo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void desenharGrafoButtonButtonActionPerformed(ActionEvent evt) {
        DesenharGrafo desenhar = new DesenharGrafo(grafo);
        desenhar.desenhar();
    }

    private void buscaEmProfundidadeButtonActionPerformed(ActionEvent evt) {
        Busca buscar = new Busca(grafo);
        buscar.DFS();
    }

    private void buscaemLarguraButtonActionPerformed(ActionEvent evt) {
        EscolhaVertice escolhaVertice = new EscolhaVertice(grafo);
        escolhaVertice.setVisible(true);
    }
    private void kruskalButtonActionPerformed(ActionEvent evt){
        ArvoreGeradoraMinima min = new ArvoreGeradoraMinima();
        min.Kruskal(grafo);
    }
    private void bellmanFordButtonActionPerformed(ActionEvent evt){
       BellmanFord bellmanFord = new BellmanFord(grafo,grafo.getVertices().get(1));
       bellmanFord.executaAlgoritmo();
    }
    private void dijkstraButtonActionPerformed(ActionEvent evt){
        Dijkstra dijkstra = new Dijkstra(grafo,grafo.getVertices().get(1));
        dijkstra.executarAlgoritmo(); //arrumar escolher vertice.
    }
    private void primButtonActionPerformed(ActionEvent evt){
        ArvoreGeradoraMinima arvoreGeradoraMinima = new ArvoreGeradoraMinima();
        arvoreGeradoraMinima.Prim(grafo, grafo.getVertices().get(0));
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuView frame = new MenuView();
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });
    }
}
