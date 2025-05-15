package core;

import java.io.IOException;

public interface BinaryTreeService {
	
	void preorder();

	void postorder();

	void printHierarchy(); // Added by me

	void toFile(String filename) throws IOException ; // Added by me

	int getHeight(); // Added by me

}