package ie.gmit.sw.breaker;

public class KeyEnumerator {
	
	private Vigenere cypher;
	private QuadgramMap  map = null;
	//private double bestScore = Double.NEGATIVE_INFINITY;
	//private String bestKey = null;
	
	public KeyEnumerator() throws Exception{
		map = new QuadgramMap("./warandpeace.txt");
	}
	private char[] getNextKey(char[] key){
		for (int i = key.length - 1; i >=0; i--){
			if (key[i] =='Z'){
				if (i == 0) return null;
				key[i] = 'A';
			}else{
				key[i]++;
				break;
			}
		}
		return key;
	}
	
	
	public String crackCypher(String cypherText, int maxKeyLength){
		char[] key = null;
		/*
		 * I made these variables local scope so that the RMI threading didn't cause concurrency issues with two processes sharing these values.
		 */
		String bestKey = null;
		double bestScore = Double.NEGATIVE_INFINITY;
		//int counter = 0;
		for (int j = 3; j <= maxKeyLength; j++){
			key = new char[j];
			for (int k = 0; k < key.length; k++) key[k] = 'A';
			
			do{
				//counter++;
				String result = new Vigenere(new String(key)).doCypher(cypherText, false);
				double score = map.getScore(result);

				if(score > bestScore){
					bestScore = score;
					bestKey = new String(key);
				}

			}while ((key = getNextKey(key)) != null);
		}
		//System.out.println("Enumerated " + counter + " keys.");
		String yahoo = new Vigenere(bestKey).doCypher(cypherText, false);
		return yahoo;
	}
}