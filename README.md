# Decaf Language - Scanner phase 

This program is a scanner made for Decaf programming language, a language made for educational purposes. You can find the language's description in *Scanner.pdf*.

## Running the program 

Run Main to scan a file and see its tokens. You'll be asked for the file's address once the program starts.

If you want to change the scanner's tokenization and behaviour, you have to change *Scanner.flex*. Once you made your changes, run this command:

```
java -jar path/to/jflex/jar/file path/to/Scanner.flex 
```

You'll get a file named *MyScanner.java*. Now you can compile and run Main.java. 

