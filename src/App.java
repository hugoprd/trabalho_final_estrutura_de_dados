public class App{
    public static void main(String[] args) throws Exception{
        // CÓDIGO FEITO COM BASE NO ENUNCIADO DA DOCUMENTAÇÃO DADA PELO PROFESSOR PEDRO MOURA    	
    	//OrdenacaoTopologica oT = new OrdenacaoTopologica();
        //String entradaArquivo = "src\\entrada.txt";
        //
        //oT.realizaLeitura(entradaArquivo);
    	//
    	//oT.executa();
        
        System.out.println("--- INICIANDO ORDENAÇÃO TOPOLÓGICA ---");

        int[] tamanhosVertices = {10, 20, 30, 40, 50, 100, 200, 500, 1000, 5000, 10000, 20000, 30000, 50000, 100000};
        String nomeArquivo = "grafo_tmp.txt";
        int numRodadas = 10;
        long[] tempos = new long[tamanhosVertices.length];
        int k = 0;

        for(int V : tamanhosVertices){
            int E = V * 4;

            System.out.println("\n---------------------------------------------------------");
            System.out.printf("Iniciando teste para V = %d, E = %d%n", V, E);
            
            OrdenacaoTopologica.geraGrafoAciclico(nomeArquivo, V, E);
            System.out.println("Grafo artificial gerado em '" + nomeArquivo + "'.");

            long tempoTotal = 0;

            long inicio = System.currentTimeMillis();

            System.out.print("Executando rodadas: ");
            for(int i = 0; i < numRodadas; i++){    
                OrdenacaoTopologica ot = new OrdenacaoTopologica();

                ot.realizaLeitura(nomeArquivo);
                ot.executa();
                
                System.out.print((i + 1) + "... ");
            }

            long fim = System.currentTimeMillis();
            tempoTotal += (fim - inicio);

            System.out.println("Concluído.");

            long tempoMedio = tempoTotal / numRodadas;

            tempos[k] = tempoMedio;

            System.out.println("RESULTADO FINAL PARA V = " + V + ": Tempo médio = " + tempoMedio + "ms");

            k++;
        }

        System.out.println("\nTempos médio de todos os tamanhos de vértices:");
        for(int l = 0; l < tempos.length; l++){
            System.out.print(tempos[l] + "ms, ");
        }
    }
}
