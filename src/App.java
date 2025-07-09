public class App{
    public static void main(String[] args) throws Exception{
        // CÓDIGO FEITO COM BASE NO ENUNCIADO DA DOCUMENTAÇÃO DADA PELO PROFESSOR PEDRO MOURA    	
    	OrdenacaoTopologica oT = new OrdenacaoTopologica();
        String entradaArquivo = "src\\entrada.txt";

        oT.realizaLeitura(entradaArquivo);
    	
    	oT.executa();

        System.out.println("--- INICIANDO EXPERIMENTO COMPUTACIONAL DE ORDENAÇÃO TOPOLÓGICA ---");

        int[] tamanhosVertices = {10, 20, 30, 40, 50, 100, 200, 500, 1000, 5000, 10000, 20000, 30000, 50000, 100000};
        String nomeArquivo = "grafo_tmp.txt";
        int numRodadas = 10;

        for(int V : tamanhosVertices){
            int E = V * 4;

            System.out.println("\n---------------------------------------------------------");
            System.out.printf("Iniciando teste para V = %d, E = %d%n", V, E);
            
            OrdenacaoTopologica.geraGrafoAciclico(nomeArquivo, V, E);
            System.out.println("Grafo artificial gerado em '" + nomeArquivo + "'.");

            long tempoTotalNano = 0;

            System.out.print("Executando rodadas: ");
            for (int i = 0; i < numRodadas; i++) {
                OrdenacaoTopologica ot = new OrdenacaoTopologica();
                
                long inicio = System.nanoTime();
                
                ot.realizaLeitura(nomeArquivo);
                ot.executa();
                
                long fim = System.nanoTime();
                tempoTotalNano += (fim - inicio);
                System.out.print((i + 1) + "... ");
            }
            System.out.println("Concluído.");

            long tempoMedioNano = tempoTotalNano / numRodadas;
            double tempoMedioMilli = tempoMedioNano / 1_000_000.0;

            System.out.printf(">> RESULTADO FINAL PARA V=%d: Tempo médio = %.4f ms%n", V, tempoMedioMilli);
        }
    }
}
