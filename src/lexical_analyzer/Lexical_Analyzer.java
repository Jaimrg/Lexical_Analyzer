/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical_analyzer;
import exceptions.*;
import lexico.*;
import parser.*;
import view.Tela;

/**
 *
 * @author Jaime Rungo
 */
public class Lexical_Analyzer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tela t = new Tela();
        t.setVisible(true);
		try {
			IsiScanner sc = new IsiScanner("input.isi");
			IsiParser  pa = new IsiParser(sc);
			
			//pa.E();
			
			System.out.println("Compilation Successful!");
                        Token token = null;
                        Token token2 = null;
        
        do{
          token = sc.nextToken();
          
            if(token != null){
                System.out.println(token);
            }
        } while(token != null);
		} catch (IsiLexicalException ex) {
			System.out.println("Lexical Error "+ex.getMessage());
		}
		catch (IsiSyntaxException ex) {
			System.out.println("Syntax Error "+ex.getMessage());
		}
		catch (Exception ex) {
			System.out.println("Generic Error!!");
			System.out.println(ex.getClass().getName());
		}
	}
    
}
