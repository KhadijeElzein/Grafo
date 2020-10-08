package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**classe de escolha de vertice**/
public class EscolhaVertice extends JFrame{

    private JPanel escolhaVertice;
    private JButton escolherButton;
    private JTextField rotulo;

    public EscolhaVertice(Grafo grafo) {
        setTitle("Busca em Largura");
        setContentPane(escolhaVertice);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        escolherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String indice = rotulo.getText();
                Vertice vertice = new Vertice(Integer.parseInt(indice));
                ArrayList<Vertice> vertices = grafo.getVertices();
                for(int i=0;i < vertices.size();i++){
                    if(vertices.get(i).getIndex() == vertice.getIndex()){
                        Busca buscar = new Busca(grafo);
                        buscar.BFS(grafo,vertices.get(i));
                        break;
                    }
                }
                EscolhaVertice.super.dispose();
            }
        });
    }
}
