package core;

import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;
import java.nio.file.Paths;


public class TheSearcher {
	
	private static IndexReader getIndexReader() throws IOException {
		
		// target index directory
		Directory indexDir = FSDirectory.open( Paths.get(Utils.getPrefixDir() + "/index/"));
		
		return DirectoryReader.open( indexDir );
		
	}


    public static void main( String[] args ) {
    	
        try {
           	IndexReader index = getIndexReader();
        	IndexSearcher searcher= new IndexSearcher(index);
        	searcher.setSimilarity(new ClassicSimilarity());
        	
        	
        	// field of interest
        	String queryStr= "Fly OR Moon";
        	
        	// Term myTerm = new Term(fieldName, queryStr);
        	// Query query= new TermRangeQuery(fieldName, new BytesRef("gam"), new BytesRef("gum"), true, true); // ["gam", "gum"]
        	// Query query= new TermRangeQuery(fieldName, new BytesRef("game"), new BytesRef("game"), true, true); // ["game", "game"], equivale a hacer la busqueda por termino
        	// Query query= new TermRangeQuery(fieldName, new BytesRef("gaming"), new BytesRef("gum"), false, false); // ("gaming", "gum")
        	// Query query= new TermRangeQuery(fieldName, new BytesRef("gum"), new BytesRef("gam"), true, true);

			// Query query = new PhraseQuery(fieldName, "video", "game"); // No encuentra
			// Query query = new PhraseQuery(fieldName, "game video"); // Encuentra
        	
			Term myTerm = new Term("content", queryStr);

			QueryParser queryparser = new QueryParser(null, new SimpleAnalyzer() );
			Query query = queryparser.parse("content:Fly OR content:Moon");
        	
        	// run the query
        	long startTime = System.currentTimeMillis();       	
        	TopDocs topDocs = searcher.search(query, 20);
        	long endTime = System.currentTimeMillis();
        	
			// show the resultset
			System.out.println(String.format("Query=> %s\n", query));
			System.out.println(String.format("%d topDocs documents found in %d ms.\n", topDocs.totalHits,
					(endTime - startTime) ) );
        	
			ScoreDoc[] orderedDocs = topDocs.scoreDocs;

			int position= 1;
			System.out.println("Resultset=> \n");
			
			for (ScoreDoc aD : orderedDocs) {
				
				// print info about finding
				int docID= aD.doc;
				double score = aD.score;
				System.out.println(String.format("position=%-10d  score= %10.7f", position, score ));
				
				// print docID, score
				System.out.println(aD);
				
				// obtain ALL the stored fields
				Document aDoc = searcher.doc(docID);
				System.out.println("Stored fields: " + aDoc);
				System.out.println(aDoc.get("path"));
				System.out.println(aDoc.get("content"));
				 /*
				Explanation rta = searcher.explain(query, docID);
	            System.out.println(rta);*/
	         
	            position++;
	            System.out.println();
			}

			index.close();
        } 
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    

}