package automato;

import javax.swing.JFrame;

public class principal {
	
	public static void main(String args[]) {
		/*
		Estado estado0 = new Estado(false);
		Estado estado1 = new Estado(true);
                Estado estado2 = new Estado(false);
		
		Transicoes transicao1 = new Transicoes('1', estado0);
		Transicoes transicao2 = new Transicoes('0', estado0);
		Transicoes transicao3 = new Transicoes('0', estado1);
                Transicoes transicao4 = new Transicoes('e', estado1);
		Transicoes transicao5 = new Transicoes('1', estado1);
		Transicoes transicao6 = new Transicoes('0', estado1);
                Transicoes transicao7 = new Transicoes('e', estado2);
		
		automat automato = new automat();
		estado0.setTransicoes(transicao1);
                estado0.setTransicoes(transicao2);
                estado0.setTransicoes(transicao3);
                estado0.setTransicoes(transicao4);
                estado1.setTransicoes(transicao5);
                estado1.setTransicoes(transicao6);
                estado1.setTransicoes(transicao7);
                
		automato.setEstadoInicial(estado0);
		//System.out.println(automato.processaPalavra(""));
                */
                automat segundoAutomato = new automat();
                
                segundoAutomato.criaAutomato("1101");
		
                JFrame janela = new JFrame();
                janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                janela.getContentPane().add(segundoAutomato);
                janela.setSize(1800, 500);
                janela.setVisible(true);
                
	}	
}