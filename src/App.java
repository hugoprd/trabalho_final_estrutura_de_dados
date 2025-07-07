public class App{
    public static void main(String[] args) throws Exception{
        // CÓDIGO FEITO COM BASE NO ENUNCIADO DA DOCUMENTAÇÃO DADA PELO PROFESSOR PEDRO MOURA    	
    	OrdenacaoTopologica oT = new OrdenacaoTopologica();
        String entradaArquivo = "src\\entrada.txt";

        oT.realizaLeitura(entradaArquivo);
    	
    	oT.executa();
    }
}
