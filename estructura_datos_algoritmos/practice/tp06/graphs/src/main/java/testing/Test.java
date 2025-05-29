package testing;
import core.EmptyEdgeProp;
import core.GraphBuilder;
import core.GraphService;
import core.GraphService.*;

public class Test {
    public static void main(String[] args) {
        GraphService<Character, EmptyEdgeProp> g = 
            new GraphBuilder().withAcceptSelfLoop(SelfLoop.YES)
                .withAcceptWeight(Weight.NO)
                .withDirected(EdgeMode.DIRECTED)
                .withStorage(Storage.SPARSE)
                .withMultiplicity(Multiplicity.SIMPLE)
                .build();

            g.addEdge('D', 'C', new EmptyEdgeProp());
            g.addEdge('A', 'B', new EmptyEdgeProp());
            g.addEdge('B', 'C', new EmptyEdgeProp());
            g.addEdge('D', 'A', new EmptyEdgeProp());
            g.addEdge('A', 'A', new EmptyEdgeProp());
            g.dump();

            g.removeVertex('A');
            g.dump();
          //  System.out.println(g.degree('A'));
    }
}
