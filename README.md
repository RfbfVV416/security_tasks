This program represents one of the simplest text steganography strategies.

## Requirements

For building and running the application you need:

- [OPEN-JDK 11](https://openjdk.java.net/projects/jdk/11/)
- [Maven 3](https://maven.apache.org)

## Running the application
To run this application you have to execute the `main` method in the `org.university.security_tasks.Main` class from your IDE.

## Algorithm
In the `input.txt` file we have the information that we need to hide in the text
that we have in the `input_salt.txt` file. </br> In the `output.txt` will be the result.

The plot of the algorithm is that we are pushing apart the words of the text by adding
additional whitespaces between them. 

* By converting the input info into the binary string we obtain a string of 0 and 1.
* Next step we add one additional whitespace after the word from the `input_salt.txt` in case of 1
and do nothing in case of 0.
* As the result in the `output.txt` we have the same text as in `input_salt.txt` but with 
additional whitespaces. </br> All the `\n` symbols remain on their places.

Pay attention to that the text in `input_salt.txt` should be long enough to encode all the bytes.

## Demonstration
    input:
        123

    salt:
        adf htv cgfyht hby bu k bjhbihbiu
        dsvcsd sdcdsc saxas ashchc cdscsj
        csdcv cc csc s cshb jjkc jns jjk;
        cc cszhn.
    
    output:
        adf  htv  cgfyht hby bu k  bjhbihbiu
        dsvcsd  sdcdsc  saxas ashchc cdscsj
        csdcv  cc csc  s  cshb jjkc jns  jjk;
        cc  cszhn.
