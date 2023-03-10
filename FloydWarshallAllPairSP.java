//-----------------------------------------------------
// Title: Floyd Warshall All Pair Shortest Path class
// Author: Ecenaz Güngör
// ID: 38210050188
// Section: 2
// Assignment: 3
// Description: This class finds the all shortest paths with using floyd warshall algorithm.
//-----------------------------------------------------

import java.util.Scanner;

// Q1
public class FloydWarshallAllPairSP {

	private static Scanner sc;
	
	public static void main(String[] args) {
		Graph graph = readInput();
		floydWarshall(graph);
		findMinimumDistances(graph);
	}
	
	// Outputs
    private static void findMinimumDistances(Graph graph) {
    	int maximumLine = graph.getNumberOfVertices()-1;
		for(int i=0;i<maximumLine;i++) {
			String targetLine = sc.nextLine();
			String[] targets = targetLine.split("\\s+");
			int sourceIndex = graph.getIndexMap().get(targets[0]);
			int destinationIndex = graph.getIndexMap().get(targets[1]);
			System.out.println(graph.getDistanceMatrix()[sourceIndex][destinationIndex]);
		}
		sc.close();
		
	}

	private static void floydWarshall(Graph graph) {

        for (int k = 0; k < graph.getNumberOfVertices(); k++) {
            for (int i = 0; i < graph.getNumberOfVertices(); i++) {
            	for (int j = 0; j < graph.getNumberOfVertices(); j++) {
            		// A path that does not exist
            		if (graph.getDistanceMatrix()[i][k] == Integer.MAX_VALUE || 
            				graph.getDistanceMatrix()[k][j] == Integer.MAX_VALUE)
            			continue;
            		
            		// Update current value
            		if (graph.getDistanceMatrix()[i][j] > 
            		graph.getDistanceMatrix()[i][k] + graph.getDistanceMatrix()[k][j]) {
            			graph.getDistanceMatrix()[i][j] = 
            					graph.getDistanceMatrix()[i][k] + graph.getDistanceMatrix()[k][j];
            		}
                }
            }
        }
    }

	private static Graph readInput() {
		
		sc = new Scanner(System.in);
		
		int numberOfVertices = Integer.parseInt(sc.nextLine());
		
		Graph graph = new Graph(numberOfVertices, Integer.MAX_VALUE);
		
		int indexNum = 0;
		int numberOfLines = Integer.parseInt(sc.nextLine());
		for(int i=0;i<numberOfLines;i++) {
			String line = sc.nextLine().trim();
			// Regular expression split // A B 3
			String[] splitted = line.split("\\s+");
			if(graph.getIndexMap().get(splitted[0]) == null) {
				graph.getIndexMap().put(splitted[0], indexNum++);
			}
			if(graph.getIndexMap().get(splitted[1]) == null) {
				graph.getIndexMap().put(splitted[1], indexNum++);
			}
			graph.setDistanceMatrix(
					graph.getIndexMap().get(splitted[0]),
					graph.getIndexMap().get(splitted[1]),
					Integer.parseInt(splitted[2]));
			
		}
		return graph;
	}

}
