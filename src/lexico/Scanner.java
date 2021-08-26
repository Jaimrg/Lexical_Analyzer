/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import lexico.Token;
import exceptions.IsiLexicalException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import view.*;

/**
 *
 * @author Jaime Rungo
 */
public class Scanner {
	
	private char[] content;
	private int    estado;
	private int    pos;
	private int    line;
	private int    column;
	
	public Scanner(String filename) {
		try {
			line = 1;
			column = 0;
			String txtConteudo;
			txtConteudo = new String(Files.readAllBytes(Paths.get(filename)),StandardCharsets.UTF_8);
//			System.out.println("DEBUG --------");
//			System.out.println(txtConteudo);
//			System.out.println("--------------");
			content = txtConteudo.toCharArray();
			pos=0;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}		
        
        // vamos testar another metodo
        
        public Token nextToken() {
		char currentChar;
		Token token = null;
		String term="";
                String symbol="";
                Tela t = new Tela();
		if (isEOF()) {
			return null;
		}
		estado = 0;
		while (true) {
			currentChar = nextChar();
			column++;
			
			switch(estado) {
			case 0:
				if (isChar(currentChar)) {
					term += currentChar;
					estado = 1;
				}
				else if (isDigit(currentChar)) {
					estado = 2;
					term += currentChar;
                                        
				}
				else if (isSpace(currentChar)) {
					estado = 0;
				}
                                else if(isSimpleSpecialSymbol(currentChar)){
                                    symbol+=currentChar;
                                    estado=3;
                                }
                                
				else if (isOperator(currentChar)) {
					term += currentChar;
					token = new Token();
					token.setType(Token.TK_OPERATOR);
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
                                        if(term.equals(">=")||term.equals("=")||term.equals("<>")||term.equals(">")||term.equals("<")){
                                            token.setTipo("Operador Relacional");
                                        }
                                        else{
                                            token.setTipo("Operador Simples");
                                        }
					return token;
				}
				else {                                     
                                        t.getError("Simbolo Nao Reconhecido na linha "+line);
					throw new IsiLexicalException("Simbolo Nao Reconhecido");                                        
				}
				break;
			case 1:
				if (isChar(currentChar) || isDigit(currentChar)) {
					estado = 1;
					term += currentChar;
				}
				else if (isSpace(currentChar) || isOperator(currentChar) || isEOF(currentChar) || isSimpleSpecialSymbol(currentChar)){
					if (!isEOF(currentChar))
						back();
					token = new Token();
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
                                        // se for um simbolo especial
                                        if(isSpecialSymbol(term)){
                                            token.setType(Token.TK_SPECIAL_SYMBOL);
                                                token.setTipo("Palavra Reservada");
                                            symbol+=currentChar;
                                            
                                        }
                                        //se nao for simbolo especial
                                        if(!isSpecialSymbol(term)){
                                            token.setType(Token.TK_IDENTIFIER);
                                            token.setTipo("Identificador");
                                            symbol+=currentChar;
                                            
                                            
                                        }
                                        
					return token;
				}
				else {
                                    t.getError("Identificador Mal formado na Linha "+line);
                                    throw new IsiLexicalException("Identificador mal formado");
				}
				break;
			case 2:
				if (isDigit(currentChar) || currentChar == '.') {
					estado = 2;
					term += currentChar;
                                        
				}
				else if (!isChar(currentChar) || isEOF(currentChar)|| isSimpleSpecialSymbol(currentChar)) {
					if (!isEOF(currentChar))
						back();
					token = new Token();
					token.setType(Token.TK_NUMBER);
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
                                        token.setTipo("Numero");
					return token;
				}
				else {
                                        //t.getError("Numero nao Reconhecido na linha "+line);
                                       // token.setErro("Numero nao Reconhecido na linha "+line);
                                       // System.out.println(token.getErro());
					throw new IsiLexicalException("Numero Nao Reconhecido");
				}
				break;
                        
                        case 3:
                            
                            if (isSpace(currentChar) || isOperator(currentChar) || isEOF(currentChar) || isSimpleSpecialSymbol(currentChar) || isChar(currentChar) || isDigit(currentChar)){
//                                symbol+=currentChar;
                                if (!isEOF(currentChar))
						back();
					token = new Token();
					token.setType(Token.TK_SPECIAL_SYMBOL);
					token.setText(symbol);
					token.setLine(line);
					token.setColumn(column - term.length());
                                        token.setTipo("Simbolo especial");
                                        return token;
                                
                            }
                            
                            //currentChar=' ';  
                            break;
                                
                         
			}
		}
		
		
		
	}

        
        
        public Token auxToken(Token token, char currentchar,String term){
            if(isSimpleSpecialSymbol(currentchar)){
                token.setText(term);
                token.setType(Token.TK_SPECIAL_SYMBOL);
                
            }
            return token;
        }
        
        
	private boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}
	
	private boolean isChar(char c) {
		return (c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z');
	}
	
	private boolean isOperator(char c) {
		return c == '>' || c == '<' || c == '=' || c == '!' || c == '+' || c == '-' || c == '*' || c == '/';
	}
        
        private boolean isSpecialSymbol(String s) {
		return  "<>".equals(s)||":=".equals(s)||".".equals(s)||";".equals(s)||":".equals(s)||"..".equals(s)||"(".equals(s)||")".equals(s)||"[".equals(s)||"]".equals(s)
                        ||"div".equals(s)||"or".equals(s)||"and".equals(s)||"and".equals(s)||"not".equals(s)||"if".equals(s)||"then".equals(s)||"else".equals(s)
                        ||"of".equals(s)||"while".equals(s)||"do".equals(s)||"begin".equals(s)||"end".equals(s)||"read".equals(s)||"write".equals(s)||"var".equals(s)
                        ||"array".equals(s)||"function".equals(s)||"procedure".equals(s)||"program".equals(s)||"true".equals(s)||"false".equals(s)
                        ||"char".equals(s)||"integer".equals(s)||"boolean".equals(s)||"writeln".equals(s)||"readln".equals(s);
                       
	}
        
        private boolean isSimpleSpecialSymbol(char c){
            String f = "'";
            char b = f.charAt(0);
            return c=='"' || c=='.' ||c==';'|| c=='(' || c==')' || c=='[' || c==']' || c==':' || c==b || c==',';
        }
        
	private boolean isSpace(char c) {
		if (c == '\n') {
			line++;
			column=0;
		}
		return c == ' ' || c == '\t' || c == '\n' || c == '\r'; 
	}
        
        
	
	private char nextChar() {
		if (isEOF()) {
			return '\0';
		}
		return content[pos++];
	}
	private boolean isEOF() {
		return pos >= content.length;
	}
	
    private void back() {
    	pos--;
    	column--;
    }
    
    private boolean isEOF(char c) {
    	return c == '\0';
    }
	
	
	
	
	
}
