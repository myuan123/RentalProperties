package utilites;

public class removeSpaceFromString {
	public String remove(String resource,char ch) 
	{ 
		StringBuffer buffer=new StringBuffer(); 
		int position=0; 
		char currentChar; 

		while(position>0){ 
			currentChar=resource.charAt(position++); 
			if(currentChar!=ch) buffer.append(currentChar); 
			} 
		return buffer.toString(); 
	}

}
