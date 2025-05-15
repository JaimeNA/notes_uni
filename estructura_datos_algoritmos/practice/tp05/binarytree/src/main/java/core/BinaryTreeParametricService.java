package core;

import java.io.IOException;

public interface BinaryTreeParametricService<T> {
	
	void preorder();

	void postorder();

	void printHierarchy(); // Added by me

	void toFile(String filename) throws IOException ; // Added by me

	int getHeight(); // Added by me

}