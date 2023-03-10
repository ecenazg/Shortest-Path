//-----------------------------------------------------
// Title: Prim Minimum Spanning Tree class
// Author: Ecenaz Güngör
// ID: 38210050188
// Section: 2
// Assignment: 3
// Description: This class finds minimum spanning tree with using prim's algorithm.
//-----------------------------------------------------
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PrimMinimumSpanningTree {

		private static Scanner sc;
		private static int sourceIndex;
		private static int destinationIndex;
		
		private static Set<String> allPathSet;

		public static void main(String[] args) {
			allPathSet = new HashSet<>();
			Graph graph = readInput();
			findMST(graph);
		}
		
		public static int minKey(int key[], Boolean mstSet[], int vertexCount)
		{
			
			int min = Integer.MAX_VALUE, minIndex = -1;
	
			for (int v = 0; v < vertexCount; v++)
				if (mstSet[v] == false && key[v] < min) {
				min = key[v];
				minIndex = v;
			}
	
			return minIndex;
		}

		public static void printResult(int parent[], Graph graph)
		{
			int total = 0;
			int curIndex = destinationIndex;
			
			for (int i = 1; i < graph.getNumberOfVertices(); i++) {
				String paired1 = ""+i+" "+parent[i];
				String paired2 = ""+parent[i]+" "+i;
				allPathSet.remove(paired1);
				allPathSet.remove(paired2);
			}
			
			
			while(curIndex!=sourceIndex) {
				total+=graph.getDistanceMatrix()[curIndex][parent[curIndex]];
				curIndex = parent[curIndex];
			}
			
			System.out.println(total);
			System.out.println(allPathSet.size());
			
			for(String ss:allPathSet) {
				String[] pairSplit = ss.split(" ");
				System.out.println(graph.getReverseIndexMap().get(Integer.parseInt(pairSplit[0]))+" "
				+graph.getReverseIndexMap().get(Integer.parseInt(pairSplit[1])));
			}
			
		}
		
		public static void findMST(Graph graph){
			int parent[] = new int[graph.getNumberOfVertices()];

			int key[] = new int[graph.getNumberOfVertices()];
			
			Boolean mstSet[] = new Boolean[graph.getNumberOfVertices()];
			
			Arrays.fill(mstSet, false);
			Arrays.fill(key, Integer.MAX_VALUE);
			
			key[0] = 0; 
			parent[0] = -1;
			
			for (int count = 0; count < graph.getNumberOfVertices() - 1; count++) {
				int u = minKey(key, mstSet, graph.getNumberOfVertices());
			
				mstSet[u] = true;
			
				for (int v = 0; v < graph.getNumberOfVertices(); v++)
					if (graph.getDistanceMatrix()[u][v] != 0 && 
					mstSet[v] == false && 
					graph.getDistanceMatrix()[u][v] < key[v]) {
						parent[v] = u;
						key[v] = graph.getDistanceMatrix()[u][v];
				}
			}
			
			printResult(parent, graph);
		 }

		private static Graph readInput() {
			
			sc = new Scanner(System.in);
			
			int numberOfVertices = Integer.parseInt(sc.nextLine());
			
			Graph prim = new Graph(numberOfVertices,0);
			
			int indexNum = 0;
			int numberOfLines = Integer.parseInt(sc.nextLine());
			for(int i=0;i<numberOfLines;i++) {
				String line = sc.nextLine().trim();
				// Regular expression split
				String[] splitted = line.split("\\s+");
				if(prim.getIndexMap().get(splitted[0]) == null) {
					prim.getIndexMap().put(splitted[0], indexNum);
					prim.getReverseIndexMap().put(indexNum++, splitted[0]);
				}
				if(prim.getIndexMap().get(splitted[1]) == null) {
					prim.getIndexMap().put(splitted[1], indexNum);
					prim.getReverseIndexMap().put(indexNum++, splitted[1]);
				}
				prim.setDistanceMatrix(
						prim.getIndexMap().get(splitted[0]),
						prim.getIndexMap().get(splitted[1]),
						Integer.parseInt(splitted[2]));
				
				// Hold all paths in a map
				// Finally, I remove non-MST paths.
				String pathPair = "" + prim.getIndexMap().get(splitted[0]) +" "+
						prim.getIndexMap().get(splitted[1]);
				allPathSet.add(pathPair);
			}
			
			String targetLine = sc.nextLine();
			String[] targets = targetLine.split("\\s+");
			sourceIndex = prim.getIndexMap().get(targets[0]);
			destinationIndex = prim.getIndexMap().get(targets[1]);

			sc.close();
			
			return prim;
		}

}
