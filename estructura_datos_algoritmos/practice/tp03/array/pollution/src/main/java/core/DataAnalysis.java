package core;

import java.io.*;             
import java.net.URL;
import java.util.HashMap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

// import eda.IndexParametricService;
// import eda.IndexWithDuplicates;

public class DataAnalysis {
    public static void main(String[] args) throws IOException {
    	
	    // leemos el archivo
    	
    	/*
       	// opcion 1:  probar con  / o sin barra
	    URL resource = DataAnalysis.class.getClassLoader().getResource("co_1980_alabama.csv");
	    Reader in = new FileReader(resource.getFile());
    	*/
    	
    	
    	/*
    	// opcion 2:  probar con  / o sin barra
        URL resource= DataAnalysis.class.getResource("/co_1980_alabama.csv");
   	    Reader in = new FileReader(resource.getFile());
    	*/
    	
    	/*
    	// opcion 3: probar con / o sin barra
    	String fileName= "/co_1980_alabama.csv";
    	InputStream is = DataAnalysis.class.getClass().getResourceAsStream(fileName);
    	Reader in = new InputStreamReader(is);
    	*/
    	
    	/*
  		// opcion 4 
 		String fileName= "/co_1980_alabama.csv"; 
 		InputStream is = DataAnalysis.class.getResourceAsStream(fileName );
 		Reader in = new InputStreamReader(is); 	
    	 */
    	
    	
    	// opcion 5 
   		String fileName= "co_1980_alabama.csv"; 
   		InputStream is = DataAnalysis.class.getClassLoader().getResourceAsStream(fileName);
   		Reader in = new InputStreamReader(is); 			
    	
    	
 		CSVFormat theCSV = CSVFormat.DEFAULT.builder()
   				.setHeader()
   				.setSkipHeaderRecord(true)
   			    .get();

   		Iterable<CSVRecord> records = theCSV.parse(in);
   		
   		// Genero las estructuras
   		
	    // Coleccion de valores
	    HashMap<Long, CSVRecord> datos= new HashMap<>();
	    
	    IndexParametricService<IdxRecord<Double, Long>> indexPolucion;
	    
	    // Indice sobre polucion sin reflection
	    indexPolucion= new IndexWithDuplicates<>();
	    
	    // Indice sobre polucion con reflection
//	    indexPolucion = new IndexWithDuplicates<>(IdxRecord.class);

		IndexParametricService<IdxRecord<Double, Long>> indexLatitude = new IndexWithDuplicates<>();
	    
	    // Pueblo con los valores ambas estructuras
	    
	    // coleccion de datos
	    for (CSVRecord record : records) {
	    	// insertamos en la coleccionIdxRecord<Double, Long>IdxRecord<Double, Long>
	    	datos.put(record.getRecordNumber(), record);
	    	
	    	// insertamos lo minimo en el indice
	        String value = record.get("daily_max_8_hour_co_concentration");
	        long id = record.getRecordNumber();
	        indexPolucion.insert(new IdxRecord<>( Double.valueOf(value), id ));

	    	// insertamos lo minimo en el indice
	        String valueLat = record.get("site_latitude");
	        long idLat = record.getRecordNumber();
	        indexLatitude.insert(new IdxRecord<>( Double.valueOf(valueLat), idLat ));
	    }
	    in.close();
	    

		System.out.println(" ------ POLLUTION INDEX QUERIES ------");
		System.out.println("");
		// Average
		System.out.println(average(indexPolucion));

		// Print avaiable data
		// IdxRecord<Double, Long>[] arr = indexPolucion.range(indexPolucion.getMin(), indexPolucion.getMax(), true, true);

		// for(IdxRecord<Double, Long> r : arr) {
		// 	System.out.println(datos.get(r.getRowID()));
		// }
		
		// Does 2.8 pollution exists?
		System.out.println(String.format("An entry with 2.8 pollution%s existed", indexPolucion.search(new IdxRecord<Double, Long>(2.8, 0L)) ? "" : " never"));

		// Maximum registeres pollution
		System.out.println(String.format("Maximum registered pollution: %f", indexPolucion.getMax().getKey()));

		// Minimum registeres pollution
		System.out.println(String.format("Minimum registered pollution: %f", indexPolucion.getMin().getKey()));

		// Minimum registeres pollution data
		System.out.println(datos.get(indexPolucion.getMin().getRowID()));

		// Print pollution registered between [3.65, 3.84]
		 IdxRecord<Double, Long>[] arr = indexPolucion.range(new IdxRecord<Double, Long>(3.65, 0L), new IdxRecord<Double, Long>(3.84, 0L), true, true);

		for(IdxRecord<Double, Long> r : arr) {
			System.out.println(r.getKey());
		}
		
		// Print pollution registered between [10.5, 12]
		IdxRecord<Double, Long>[] arr2 = indexPolucion.range(new IdxRecord<Double, Long>(10.5, 0L), new IdxRecord<Double, Long>(12.0, 0L), true, true);

		for(IdxRecord<Double, Long> r : arr2) {
			System.out.println(r.getKey());
		}

		// LATITUDE QUIERIES
		
		
		System.out.println("");
		System.out.println(" ------ LATITUDE INDEX QUERIES ------");
		System.out.println("");

		
		// Does 34.68776113 exists?
		System.out.println(String.format("An entry with 34.68776113 latitude%s existed", indexLatitude.search(new IdxRecord<Double, Long>(34.68776113, 0L)) ? "" : " never"));

		// Minimum registeres latitude
		System.out.println(String.format("Minimum registered pollution: %f", indexLatitude.getMin().getKey()));

		// Minimum registeres latitude data
		System.out.println(datos.get(indexLatitude.getMin().getRowID()));

    }
    
	public static float average(IndexParametricService<IdxRecord<Double, Long>> indexPolucion) {
		int toReturn = 0;

		IdxRecord<Double, Long>[] arr = indexPolucion.range(indexPolucion.getMin(), indexPolucion.getMax(), true, true);

		for(IdxRecord<Double, Long> r : arr) {
			toReturn += r.getKey();
		}
		return (float)toReturn / arr.length;
	}
 	    
}
