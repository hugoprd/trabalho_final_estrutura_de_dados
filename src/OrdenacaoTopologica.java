import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
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
		
		System.out.println("Debug");
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
        debug();

        System.out.println("\nOrdenacao topologica:");
        
        Elo fila = null;
        Elo p = this.prim;
        
        while(p != null){
            Elo proximoOriginal = p.prox;
            if(p.contador == 0){
                p.prox = fila;
                fila = p;
            }

            p = proximoOriginal;
        }

        Elo fimDaFila = fila;
        if(fimDaFila != null){
            while(fimDaFila.prox != null){
                fimDaFila = fimDaFila.prox;
            }
        }
        
        int elementosProcessados = 0;
        
        while(fila != null){
            Elo q = fila;
            fila = fila.prox;

            if(fila == null){
                fimDaFila = null;
            }

            System.out.print(q.chave + " ");
            elementosProcessados++;

            for (EloSuc t = q.listaSuc; t != null; t = t.prox) {
                Elo sucessor = t.id;
                sucessor.contador--;

                if(sucessor.contador == 0){
                    sucessor.prox = null;
                    
                    if(fila == null){
                        fila = sucessor;
                        fimDaFila = sucessor;
                    }
					else{
                        fimDaFila.prox = sucessor;
                        fimDaFila = sucessor;
                    }
                }
            }
        }

        System.out.println();

        if(elementosProcessados == n){
            System.out.println("Conjunto é parcialmente ordenado.");

            return true;
        }
		else{
            System.out.println("Erro: O grafo possui um ciclo.");

            return false;
        }
	}

	public static void geraGrafoAciclico(String nomeArquivo, int numVertices, int numArestas) throws IOException {
        Random random = new Random();

        int[] vertices = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            vertices[i] = i;
        }

        for(int i = numVertices - 1; i > 0; i--){
            int j = random.nextInt(i + 1);
            
            int temp = vertices[i];
            vertices[i] = vertices[j];
            vertices[j] = temp;
        }

        if(numVertices > 50000){
            System.err.println("Aviso: Número de vértices muito grande. Possibilidade de causar OutOfMemoryError.");
        }
        
        boolean[][] arestasCriadas = new boolean[numVertices][numVertices];
        int arestasContadas = 0;

        try(PrintWriter out = new PrintWriter(new FileWriter(nomeArquivo))){
            long maxTentativas = (long) numArestas * 10;
            long tentativas = 0;
            
            while(arestasContadas < numArestas && tentativas < maxTentativas){
                int i = random.nextInt(numVertices);
                int j = random.nextInt(numVertices);
                
                tentativas++;
                if (i == j) continue;

                int indiceDe = Math.min(i, j);
                int indicePara = Math.max(i, j);

                int A = vertices[indiceDe];
                int B = vertices[indicePara];

                if(!arestasCriadas[A][B]){
                    out.println(A + " < " + B);
                    arestasCriadas[A][B] = true;
                    arestasContadas++;
                }
            }
        }
    }
}