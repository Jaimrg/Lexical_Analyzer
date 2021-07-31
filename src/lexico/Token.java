/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

/**
 *
 * @author Jaime Rungo
 */
public class Token{
        public static final int TK_IDENTIFIER  = 0;
	public static final int TK_NUMBER      = 1;
	public static final int TK_OPERATOR    = 2;
	public static final int TK_PONCTUATION = 3;
	public static final int TK_ASSIGN      = 4;
        public static final int TK_SPECIAL_SYMBOL=5;
	
	public static final String TK_TEXT[] = {
			"IDENTIFIER", "NUMBER", "OPERATOR", "PONCTUACTION", "ASSIGNMENT","TK_SPECIAL_SYMBOL"
	};
	
	private int    type;
	private String text;
	private int    line;
	private int    column;
        private String tipo;
        private String erro;
	
	public Token(int type, String text) {
		super();
		this.type = type;
		this.text = text;
	}

	public Token() {
		super();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

        public String getTipo() {
        return tipo;
        }

        public void setTipo(String tipo) {
        this.tipo = tipo;
        }
        
        public String getErro(){
            return erro;
        }
        
        public void setErro(String erro){
            this.erro = erro;
        }

    @Override
    public String toString() {
        return "Token{" + "type=" + type + ", text=" + text + ", line=" + line + ", column=" + column + ", tipo=" + tipo + '}';
    }
        

    
        
       
	
}