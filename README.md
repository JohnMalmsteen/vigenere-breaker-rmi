# RMI Vigenere Breaker Object Service
Remote Objects for vigenere code breaking

![ArchitectureDiagram](http://i.imgur.com/lVcnffB.png)

Contents:
---------
1. VigenereBreaker.java
2. VigenereBreakerImpl.java
3. Vigenere.java
4. QuadgramMap.java
5. KeyEnumerator.java
6. Deployment

1 - VigenereBreaker.java
---
This is the remote interface for the vigenere breaker object, it extends remote and only contains one method:

```java
public String decrypt(String cypherText, int maxKeylength) throws RemoteException;
```

2 - VigenereBreakerImpl.java
---
This is the implementation of the remote interface, it implements the method but passes the actual work over to a KeyEnumerator object that it encapsulates. It also has a main method which binds the object to the name cypher-service on a registry on port 1099.

3 - Vigenere.java
---
This class actually does the decryption and encription of strings using the Vigenere cypher block using whatever key you pass in.

4 - QuadgramMap.java
---
This class parses a text file (in our current case I am using 'War and Peace' by Tolstoy and breaks it up into 4 letter sequences (Quadgrams) which are then put into a map along with their frequency of occurence in order to be able to score how 'Englishy' a piece of text is.

5 - KeyEnumerator
---
This class iterates through all the possible keys between AAA and the maxlength key the user passes in and tests each decryption to see how 'Englishy' it is and takes the best score achieved to be the correct key.

6 - Deployment
---
This project will be deployed as a jar file and must be adjacent to the warandpeace.txt file, which it reads from.
