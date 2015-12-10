package ie.gmit.sw.client;

import java.rmi.Naming;

import ie.gmit.sw.breaker.VigenereBreaker;

public class Client {
	public static void main(String[] args) throws Exception {
		/*
		 * this stuff needs to be in your tomcat app
		 */
		VigenereBreaker vb = (VigenereBreaker)Naming.lookup("///cypher-service");
		String result = vb.decrypt("JNOISRSZSIJBGIHQMZNIJRDACRSH", 5);
		System.out.println(result);
	}
}
