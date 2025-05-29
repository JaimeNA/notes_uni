package core;
 
import java.util.Collection; 



public class SimpleOrDefault<V,E> extends AdjacencyListGraph<V,E> {

	protected SimpleOrDefault(boolean isDirected, boolean acceptSelfLoops, boolean isWeighted) {
		super(true, isDirected, acceptSelfLoops, isWeighted);
	
	}
	
	
	@Override
	public void addEdge(V aVertex, V otherVertex, E theEdge) {

		// validacion y creacion de vertices si fuera necesario
		super.addEdge(aVertex, otherVertex, theEdge);

		checkEdge(aVertex, otherVertex);
		getAdjacencyList().get(aVertex).add(new InternalEdge(theEdge, otherVertex));

		if (!isDirected)
			getAdjacencyList().get(otherVertex).add(new InternalEdge(theEdge, aVertex));
	
	}

	private void checkEdge(V aVertex, V otherVertex) {
		for (InternalEdge internalEdge : getAdjacencyList().get(aVertex)) {
			if (internalEdge.target.equals(otherVertex))
				throw new IllegalArgumentException(
						String.format(Messages.getString("addEdgeSimpleOrDefaultError"), aVertex, otherVertex) );
		}
	}

}
