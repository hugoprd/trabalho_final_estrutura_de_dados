public class OrdenacaoTopologica{
	private class Elo{
		public int chave;
		public int contador;
		public Elo prox;
		public EloSuc listaSuc;
		
		public Elo(){
			prox = null;
			contador = 0;
			listaSuc = null;
		}
		
		public Elo(int chave, int contador, Elo prox, EloSuc listaSuc){
			this.chave = chave;
			this.contador = contador;
			this.prox = prox;
			this.listaSuc = listaSuc;
		}
	}
	
	private class EloSuc{
		public Elo id;
		public EloSuc prox;
		
		public EloSuc()
		{
			id = null;
			prox = null;
		}
		
		public EloSuc(Elo id, EloSuc prox)
		{
			this.id = id;
			this.prox = prox;
		}
	}

	private Elo prim;
	private int n;
		
	public OrdenacaoTopologica(){
		prim = null;
		n = 0;
	}
	
	/* Método responsável pela leitura do arquivo de entrada. */
	public void realizaLeitura(String nomeEntrada){
		/* Preencher. */
	}
	
	/* Método para impressão do estado atual da estrutura de dados. */
	private void debug(){
		/* Preencher. */
	}
	
	/* Método responsável por executar o algoritmo. */
	public boolean executa(){
		/* Preencher. */
		
		return false;
	}
}