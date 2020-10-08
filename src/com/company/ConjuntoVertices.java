package com.company;

public class ConjuntoVertices {
    private int[] conj;		//the set field


    public int[] getConjunto(){
        return conj;
    }
    /* metodo construtor, monta o vetor de conjuntos */
    public ConjuntoVertices(int qtdElementos ) {
        conj = new int [qtdElementos];
        for( int i = 0; i < conj.length; i++ )
            conj[ i ] = -1;
    }
    /* faz a uniao de conjuntos*/
    public void union( Integer raiz1, Integer raiz2 ) {
        if( conj[raiz2] < conj[raiz1] )
            conj[raiz1] = raiz2;
        else {
            if( conj[raiz1] == conj[raiz2] )
                conj[raiz1]--;
            conj[raiz2] = raiz1;
        }
    }
    /* retorna o elemento do conjunto*/
    public int find( int elemento ) {
        if( conj[elemento] < 0 )
            return elemento;
        else
            return conj[elemento] = find(conj[elemento]);
    }
}

