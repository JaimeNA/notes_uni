package core;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import org.w3c.dom.Node;

abstract public class AdjacencyListGraph<V, E> implements GraphService<V, E> {

	private boolean isSimple;
	protected boolean isDirected;
	private boolean acceptSelfLoop;
	private boolean isWeighted;
	protected String type;
	
	// HashMap no respeta el orden de insercion. En el testing considerar eso
	private Map<V,Collection<InternalEdge>> adjacencyList= new HashMap<>();
	
	// respeta el orden de llegada y facilita el testing
	//	private Map<V,Collection<InternalEdge>> adjacencyList= new LinkedHashMap<>();
	
	protected   Map<V,  Collection<AdjacencyListGraph<V, E>.InternalEdge>> getAdjacencyList() {
		return adjacencyList;
	}
	
	
	protected AdjacencyListGraph(boolean isSimple, boolean isDirected, boolean acceptSelfLoop, boolean isWeighted) {
		this.isSimple = isSimple;
		this.isDirected = isDirected;
		this.acceptSelfLoop= acceptSelfLoop;
		this.isWeighted = isWeighted;

		this.type = String.format("%s %sWeighted %sGraph with %sSelfLoop", 
				isSimple ? "Simple" : "Multi", isWeighted ? "" : "Non-",
				isDirected ? "Di" : "", acceptSelfLoop? "":"No ");
	}
	
	
	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public void addVertex(V aVertex) {
	
		if (aVertex == null )
		throw new IllegalArgumentException(Messages.getString("addVertexParamCannotBeNull"));
	
		// no edges yet
		getAdjacencyList().putIfAbsent(aVertex, 
				new ArrayList<InternalEdge>());
	}

	
	@Override
	public int numberOfVertices() {
		return getVertices().size();
	}

	@Override
	public Collection<V> getVertices() {
		return getAdjacencyList().keySet() ;
	}
	
	@Override
	public int numberOfEdges() {	// Uses Euler sum
		int count = 0;
		int multiplier = (isDirected) ? 2 : 1;

		for (V vertex : adjacencyList.keySet()) {
			count += multiplier * adjacencyList.get(vertex).size();
		}

		return count / 2;
	}

	public int numberOfEdgesAlt() {	// Doesnt use Euler sum
		if (isDirected)
			return numberOfEdgesDirected();

		return numberOfEdgesNotDirected();
	}

	private int numberOfEdgesNotDirected() {
		
		int count = 0;

		Set<V> visited = new TreeSet<>();	// Only need to count once

		for (V vertex : adjacencyList.keySet()) {
			for (InternalEdge edge : adjacencyList.get(vertex)) {
				if (visited.contains(edge.target))
					count++;
				
			}
			visited.add(vertex);
		}

		return count;

	}

	private int numberOfEdgesDirected() {
		
		int count = 0;

		for (Collection<AdjacencyListGraph<V, E>.InternalEdge> edges : adjacencyList.values()) {
			count += edges.size();
		}

		return count;

	}

	@Override
	public void addEdge(V aVertex, V otherVertex, E theEdge) {

		// validation!!!!
		if (aVertex == null || otherVertex == null || theEdge == null)
			throw new IllegalArgumentException(Messages.getString("addEdgeParamCannotBeNull"));

		// es con peso? debe tener implementado el metodo double getWeight()
		if (isWeighted) {
			// reflection
			Class<? extends Object> c = theEdge.getClass();
			try {
				c.getDeclaredMethod("getWeight");
			} catch (NoSuchMethodException | SecurityException e) {
				throw new RuntimeException(
						type + " is weighted but the method double getWeighed() is not declared in theEdge");
			}
		}
		
		if (! acceptSelfLoop && aVertex.equals(otherVertex)) {
			throw new RuntimeException(String.format("%s does not accept self loops between %s and %s" , 
					type, aVertex, otherVertex) );
		}

		// if any of the vertex is not presented, the node is created automatically
		addVertex(aVertex);
		addVertex(otherVertex);
		

	}

	

	
	@Override
	public boolean removeVertex(V aVertex) {
		
		if(aVertex == null)
			throw new RuntimeException("Vertex cannot be null");

		if(adjacencyList.get(aVertex) == null)
			return false;

		if (!isDirected) {
			for (InternalEdge edge : adjacencyList.get(aVertex)) {	
				// Remove the vertex from the target list
				adjacencyList.get(edge.target).removeIf(e -> e.target.equals(aVertex));
			}

		} else {
			// If reached this point, then its directed
			
			// Go through al the vertices and delete the adyacency of any adyacent to sVertex
			for (Collection<InternalEdge> internalEdgeList: adjacencyList.values()) {
				internalEdgeList.removeIf(e -> e.target.equals(aVertex));
			}

		}

		// Delete the vertex entry to adyacency list 
		adjacencyList.remove(aVertex);

		return true;
	}

	@Override
	public boolean removeEdge(V aVertex, V otherVertex) {
		// COMPLETAR
		throw new RuntimeException("not implemented yet");
	}

	
	@Override
	public boolean removeEdge(V aVertex, V otherVertex, E theEdge) {
		// COMPLETAR
		throw new RuntimeException("not implemented yet");
	}
	
	
	@Override
	public void dump() {
		StringBuilder s = new StringBuilder();
		for(V vertex: getAdjacencyList().keySet()) {
			s.append(vertex).append(": ");
			for (InternalEdge edge : getAdjacencyList().get(vertex)) {
				s.append("{ ").append(edge.edge).append(" : ").append(edge.target).append(" }");
			}
			s.append("\n");
		}

		System.out.println(s.toString());
	}
	
	
	@Override
	public int degree(V aVertex) {
		
		if (isDirected)
			throw new RuntimeException("Directed graph cannot call this method");

		if(aVertex == null)
			throw new RuntimeException("Vertex cannot be null");

		if(adjacencyList.get(aVertex) == null)
			throw new RuntimeException("Vertex not found");

		return adjacencyList.get(aVertex).size();

	}

	

	@Override
	public int inDegree(V aVertex) {

		if (!isDirected)
			throw new RuntimeException("Undirected graph cannot call this method");
			
		if(aVertex == null)
			throw new RuntimeException("vertex cannot be null");

		int count = 0;

		for (V vertex : adjacencyList.keySet()) {
			for (InternalEdge edge : adjacencyList.get(vertex)) {
				if (edge.target.equals(aVertex))
					count++;
				
			}
		}

		return count;
	}



	@Override
	public int outDegree(V aVertex) {
		if (!isDirected)
			throw new RuntimeException("Undirected graph cannot call this method");
			
		if(aVertex == null)
			throw new RuntimeException("vertex cannot be null");

		return adjacencyList.get(aVertex).size();
	}

	

	
	
	class InternalEdge {
		E edge;
		V target;

		InternalEdge(E propEdge, V target) {
			this.target = target;
			this.edge = propEdge;
		}

		@Override
		public boolean equals(Object obj) {
			@SuppressWarnings("unchecked")
			InternalEdge aux = (InternalEdge) obj;

			return ((edge == null && aux.edge == null) || (edge != null && edge.equals(aux.edge)))
					&& target.equals(aux.target);
		}

		@Override
		public int hashCode() {
			return target.hashCode();
		}

		@Override
		public String toString() {
			return String.format("-[%s]-(%s)", edge, target);
		}
	}
	
	
}
