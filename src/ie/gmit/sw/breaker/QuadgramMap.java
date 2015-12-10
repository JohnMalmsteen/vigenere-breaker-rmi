package ie.gmit.sw.breaker;
import java.util.*;
import java.io.*;
public class QuadgramMap {
	private Map<String, Integer> map = new HashMap<String, Integer>();

	public QuadgramMap(String fileName) throws Exception{
		parse(fileName);
		System.out.println(map.size());
	}
	
	public double getScore(String text){
		double score = 0.00;
		for (int i = 0; i < text.length(); i++) {
			if(i+4 >= text.length()) break;
			String next = text.substring(i, i+4);
			if(map.containsKey(next)){
				double freq = (double)map.get(next);
				score += Math.log(freq);
			}	
		}
		return score;
	}
	
	private void parse(String fileName) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		StringBuffer sb = new StringBuffer();
		int j;
		while((j = br.read()) != -1){
			char next = (char)j;
			if(next >= 'A' && next <= 'z'){
				sb.append(next);
			}
			
			if(sb.length() == 4){
				String qGram = sb.toString().toUpperCase();
				sb = new StringBuffer();
				
				int freq = 0;
				if(map.containsKey(qGram)){
					freq = map.get(qGram);
				}
				
				freq++;
				map.put(qGram, freq);
			}
		}
		br.close();
	}
	
	public static void main(String[] args) throws Exception {
		new QuadgramMap("./warandpeace.txt");
	}
}
