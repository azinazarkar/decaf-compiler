# Decaf Compiler 

A compiler program made for Decaf language. This project is done for Compiler Design course in Shahid Beheshti University.

## Running the program 

### Compiling one program 

Run Main to parse the program in "src/compiler/program.txt" and see if it's OK or it has syntax errors.

Compiling the program:

```
java -jar lib/jflex-full-1.8.2.jar src/compiler/Scanner/Scanner.flex
java -jar lib/java-cup-11b.jar src/compiler/Parser/parser.cup
mv parser.java sym.java src/compiler/Parser/
javac -cp .:src/:lib/java-cup-11b.jar src/compiler/Main.java
```

Running the program:

```
java -cp .:src/:lib/java-cup-11b.jar compiler.Main
```

### Compiling and testing a bunch of codes

run ```run.sh``` in the root folder. Your tests should be in ```tests/``` folder (as some tests that are there now).
