package automato;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Estado {
	
    static int contador = 0;
    private ArrayList<Transicoes> transicoes;
    private boolean terminal = false;
    private int identificador;
    static public int x = 0;
    private boolean desenhado = false;
    private int y;
    
    public Estado(){
	this.identificador = contador++;
        this.terminal = false;
	this.transicoes = new ArrayList<Transicoes>();
    }
    
    public Estado(boolean terminal) {
	this.terminal = terminal;
	this.identificador = contador++;
	this.transicoes = new ArrayList<Transicoes>();
    }
	
    public ArrayList<Transicoes> getTransicoes() {
	return transicoes;
    }
    public void setTransicoes(Transicoes transicoes) {
	this.transicoes.add(transicoes);
    }
    public boolean isTerminal() {
	return terminal;
    }
    public void setTerminal(boolean terminal) {
	this.terminal = terminal;
    }
    public int getIdentificador() {
	return identificador;
    }
    public void setIdentificador(int identificador) {
	this.identificador = identificador;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isDesenhado() {
        return desenhado;
    }

    public void setDesenhado(boolean desenhado) {
        this.desenhado = desenhado;
    }
    
    public void desenhar(Graphics2D g){

        g.drawOval(x, 50, 100, 100);
        g.drawString("q"+this.identificador, (x+1)+40, 105);
        if(!this.terminal){
            g.drawString(""+this.getTransicoes().get(0).getSimboloLido(), x+125, 95);
            g.drawLine(x+150, 100, x+100, 100);
        }

        this.setDesenhado(true);
        x = x + 150;
        
        for(Transicoes t : this.getTransicoes()){
            
            if(!t.getProximoEstado().isDesenhado()){
                t.getProximoEstado().desenhar((Graphics2D)g);
                
            }  
        }    
    }
    
    public void desmarcarTodos(){
        this.setDesenhado(false);
        for(Transicoes t : this.getTransicoes()){
            if(t.getProximoEstado().isDesenhado()){
                t.getProximoEstado().desmarcarTodos();   
                t.getProximoEstado().setX(0);
            }
        }
    }
	
}