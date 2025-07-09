import java.io.IOException;
import java.util.*;

public class geradorDAG{
    public static void gerarDAG(int numVertices, int numArestas, String nomeArq){
        //verificando a quantidade de arestas para nao ter ciclos
        if (numArestas > numVertices * (numVertices - 1) / 2){
            throw new IllegalArgumentException("Numero de arestas excede o maximo aceitavel");
        }

        //criando uma lista com os vertices
        ArrayList<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < numVertices; i++){
            vertices.add(i);
        }

        //embaralhando os dados para gerar aleatoriedade
        Collections.shuffle(vertices);

        // para evitar repetir
        Set<String> arestas = new HashSet<>();
        Random random = new Random();
        // algoritmo para conetar arestas sem erros de predecessor
        while (arestas.size() <  numArestas){
            int i = random.nextInt(numVertices - 1);
            int j = i + 1 + random.nextInt(numVertices - i -1);

            int de = vertices.get(i);
            int para = vertices.get(j);

            String aresta = de + " < " + para;
            arestas.add(aresta);
        }
    }
}
