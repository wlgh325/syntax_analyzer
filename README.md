# compiler term project 2
- making simple C syntax analyzer
- lexical anaylizer git url : https://github.com/choiyooung/lexical_ananlyzer
- using bottom-up parsing (SLR Table)

# Executing syntax analyzer
- test input, production file, slrtable file and output file are in docs folder.
- If you want to execute JAR file, follow this command statement
- java -jar << lexical analyzer JAR file directory>> << test input file directory>
- java -jar << syntax analyzer JAR file directory>> <<symbol table file directory>>

# ex)
- java -jar C:\Users\Guest1\Desktop\lex_analyzer.jar C:\Users\Guest1\Desktop\test.c
- java -jar C:\Users\Guest1\Desktop\syntax_analyzer.jar C:\Users\Guest1\Desktop\test.c.out
