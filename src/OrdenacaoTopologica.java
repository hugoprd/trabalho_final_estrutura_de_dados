import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

	private Elo criaElo(int chave){
		Elo q = new Elo(chave, 0, null, null);
		n++;

		if(prim == null){
			prim = q;
		}
		else{
			Elo pUlt = prim;

			while(pUlt.prox != null){
				pUlt = pUlt.prox;
			}

			pUlt.prox = q;
		}

		return q;
	}

	private Elo buscaElo(int chave){
		for(Elo p = prim; p != null; p = p.prox){
			if(p.chave == chave){
				return p;
			}
		}

		return null;
	}
	
	/* Método responsável pela leitura do arquivo de entrada. */
	public void realizaLeitura(String nomeEntrada){
		
		File arquivo = new File(nomeEntrada);
		
		try(Scanner scan = new Scanner(arquivo)){
			while(scan.hasNextInt()){
				int chaveAnt = scan.nextInt();

				if(scan.hasNext("<")){
					scan.next();
				}

				int chaveSuc = scan.nextInt();
				
				Elo noAnt = buscaElo(chaveAnt);
				if(noAnt == null){
					noAnt = criaElo(chaveAnt);
				}
				
				Elo noSuc = buscaElo(chaveSuc);
				if(noSuc == null){
					noSuc = criaElo(chaveSuc);
				}
	
				EloSuc novoSuc = new EloSuc(noSuc, noAnt.listaSuc);
				noAnt.listaSuc = novoSuc;

				noSuc.contador++;
			}
		}
		catch(FileNotFoundException e){
			System.err.println("Erro: Arquivo '" + nomeEntrada + "' não encontrado.");
            e.printStackTrace();
		}
	}
	
	/* Método para impressão do estado atual da estrutura de dados. */
	private void debug(){
		Elo p = prim;
		
		while(p != null)
		{
			System.out.printf(p.chave + " predecessores: " + p.contador + ", sucessores: ");

			EloSuc q = p.listaSuc;
			
			while(q != null)
			{
				System.out.print(q.id.chave + " -> ");
					
				q = q.prox;
			}
			
			System.out.print(" NULL");
			
			System.out.println();
			
			p = p.prox;
		}
	}
	
	/* Método responsável por executar o algoritmo. */
	public boolean executa(){
		String entradaArquivo = "src\\entrada.txt";
		realizaLeitura(entradaArquivo);
		
		debug();
		
		return false;
	}
}