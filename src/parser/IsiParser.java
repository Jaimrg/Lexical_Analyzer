/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;
import exceptions.*;
import lexico.*;
/**
 *
 * @author Jaime Rungo
 */
public class IsiParser {
    private Scanner scanner; // analisador léxico
	private Token      token;   // o token atual
	
	/* o Parser recebe o analisador léxico como parâmetro no construtor
	 * pois a cada procedimento invoca-o sob demanda
	 */
	public IsiParser(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void E() {
		//T();
		El();
		
	}
	
	public void El() {
		token = scanner.nextToken();
		if (token != null) {
			//OP();
			//T();
			//El();
		}
	}
	
	public void T() {
		token = scanner.nextToken();
		if (token.getType() != Token.TK_IDENTIFIER && token.getType() != Token.TK_NUMBER) {
			throw new IsiSyntaxException("ID or NUMBER Expected!, found "+Token.TK_TEXT[token.getType()]+" ("+token.getText()+") at LINE "+token.getLine()+" and COLUMN "+token.getColumn());
		}
		
	}
	
	public void OP() {
		if (token.getType() != Token.TK_OPERATOR) {
			throw new IsiSyntaxException("Operator Expected, found "+Token.TK_TEXT[token.getType()]+" ("+token.getText()+")  at LINE "+token.getLine()+" and COLUMN "+token.getColumn());
		}
	}
}
