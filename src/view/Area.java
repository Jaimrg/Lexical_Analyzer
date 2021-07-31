/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Jaime Rungo
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.border.AbstractBorder;


public class Area extends AbstractBorder {
	private int lineHeight = 20;
	private int characterHeight = 8;
	private int characterWidth = 7;
	private Color myColor;
	private JViewport viewport;
	
	public Area() {
		this.myColor = new Color(164, 164, 164);
	}
	
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		if (this.viewport == null) {
			searchViewport(c);
		}
		
		Point point;
		Dimension d = null;
		if (this.viewport != null) {
			point = this.viewport.getViewPosition();			
		} else {
			point = new Point();
		}
		
		Color oldColor = g.getColor();
		g.setColor(this.myColor);
		
		double r = (double) height / (double) this.lineHeight;
		int rows = (int) (r + 0.5);
		String str = String.valueOf(rows);
		int maxLenght = str.length();
		
		int py;
		int i = 0;
		if (point.y > 0) {
			i = point.y / this.lineHeight;
		}
		
		if (d != null) {
			r = (double) d.height / (double) this.lineHeight;
			rows = (int) (r + 0.5);
			rows += i + 1;
		}
		
		int lenght;
		int px;
		for( ; i < rows; i++) {			
			str = String.valueOf(i + 1);
			lenght = str.length();
			lenght = maxLenght - lenght;
			
			py = this.lineHeight * i + 14;
			px = this.characterWidth * lenght + 2;
			//px += point.x;
			
			g.drawString(str, px, py);
		}		
		
		int left = this.calculateLeft(height) + 7;
		//left += point.x;
		g.drawLine(left, 0, left, height);
		
        g.setColor(oldColor);
	}
	
	public Insets getBorderInsets(Component c) {
		int left = this.calculateLeft(c.getHeight()) + 10;
		return new Insets(1, left, 1, 1);
	}
	
	public Insets getBorderInsets(Component c, Insets insets) {			
		insets.top = 1;
		insets.left = this.calculateLeft(c.getHeight()) + 10;
		insets.bottom = 1;
		insets.right = 1;
		return insets;
	}
	
	protected int calculateLeft(int height) {
		double r = (double) height / (double) this.lineHeight;
		int rows = (int) (r + 0.5);
		String str = String.valueOf(rows);
		int lenght = str.length();

		return this.characterHeight * lenght;
	}
	
	protected void searchViewport(Component c) {
		Container parent = c.getParent();
		if (parent instanceof JViewport) {
			this.viewport = (JViewport) parent;
		}
	}
       
}