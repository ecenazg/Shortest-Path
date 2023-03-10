//-----------------------------------------------------
// Title: Graph class
// Author: Ecenaz Güngör
// ID: 38210050188
// Section: 2
// Assignment: 3
// Description: This class is for graph implementation with using hash map.
//-----------------------------------------------------

import java.util.HashMap;
import java.util.Map;

public class Graph {
	//Map stores A->0, B->1 ... 
	//Vertices may be different such as Ankara, Istanbul, Londra...
	//Map stores an index number for each vertex 
	private Map<String,Integer> indexMap;
	
	// Store reverse of indexMap, 0->A, 1->B ...
	private Map<Integer,String> reverseIndexMap;
	private int[][] distanceMatrix;
	private int numberOfVertices;
	
	public Map<String, Integer> getIndexMap() {
		return indexMap;
	}

	public int[][] getDistanceMatrix() {
		return distanceMatrix;
	}

	public int getNumberOfVertices() {
		return numberOfVertices;
	}
	
	public void setDistanceMatrix(int row, int col, int val) {
		// if A-B is 5, then B-A also 5
		// Bidirectional graph
		this.distanceMatrix[row][col] = val;
		this.distanceMatrix[col][row] = val;
	}
	
	public Map<Integer, String> getReverseIndexMap() {
		return reverseIndexMap;
	}

	public Graph(int numberOfVertices, int initialValue) {
		this.numberOfVertices = numberOfVertices;
		this.distanceMatrix = new int[numberOfVertices][numberOfVertices];
		// Set all values to initial value except diagonal members
		for(int i=0;i<numberOfVertices;i++)
			for(int j=0;j<numberOfVertices;j++) {
				if(i!=j)
					distanceMatrix[i][j] = initialValue; 
			}
		this.indexMap = new HashMap<>();
		this.reverseIndexMap = new HashMap<>();
	}

}
