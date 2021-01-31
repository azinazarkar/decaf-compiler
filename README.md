# Decaf Compiler 

A compiler program made for Decaf language. This project is done for Compiler Design course held by *MohammadReza Bahrami*, in Shahid Beheshti University, fall 2020.

## Running the program 

### Compiling one program 

Run Main to compile the program in "src/compiler/program.txt" and produce a MIPS code called *out.asm* in the root folder of project.

Compiling the program:

```
java -jar lib/jflex-full-1.8.2.jar src/compiler/Scanner/Scanner.flex
java -jar lib/java-cup-11b.jar src/compiler/Parser/parser.cup
mv parser.java sym.java src/compiler/Parser/
javac -cp .:src/:lib/java-cup-11b.jar src/compiler/Main.java
java -cp .:src/:lib/java-cup-11b.jar compiler.Main
```

Steps 1 through 4 need to be run once (unless you change parser, scanner, or the main). After that, you only need to run compiler.Main to produce MIPS code.

Running the produced MIPS code:

```
spim -f out.asm
```

### Compiling and testing a bunch of codes

run ```run.sh``` in the root folder. Your tests should be in ```tests/``` folder (as some tests that are there now).

## Understanding the Code

I think the best point to start reading the code is from file ```parser.cup```. You'll see what code is generated in each block of program. 

## Used Libraries and Technologies

### Scanner Phase

We used JFlex to scan the program and extract its tokens. You can see it in ```src/compiler/Scanner/Scanner.flex```.

### Parser Phase

We used Java CUP to parse the program and check for syntax errors. Check out file ```src/compiler/Parser/parser.cup``` and the Java CUP's documentations for more details and documentations respectively.

### Code Generation Phase

We used Java (which was easy to integrate with Java CUP) to connect all parts of the project to each other and generate MIPS code for given programs.  
