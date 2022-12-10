package automato;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JComponent;

public class automat extends JComponent{
        
    private Estado estadoInicial;

    public Estado getEstadoInicial() {
	return estadoInicial;
    }

    public void setEstadoInicial(Estado estadoInicial) {
	this.estadoInicial = estadoInicial;
    }
        
    public boolean processaPalavra(String palavra){
            
        boolean aceito = false;
        ArrayList<Estado> estadoInicial = new ArrayList<>();
        char caractereLido;
            
        estadoInicial.add(this.estadoInicial);
        estadoInicial = verificaVazio(estadoInicial);
        for(int i = 0; i < palavra.length(); i++){
            ArrayList<Estado> estadosFuturos = new ArrayList<>();
            for(int j = 0; j < estadoInicial.size(); j++){
                for(Transicoes t : estadoInicial.get(j).getTransicoes()){
                    caractereLido = t.getSimboloLido(); 
                    if(palavra.charAt(i) == caractereLido){       
                        if(!estadosFuturos.contains(t.getProximoEstado())){
                            estadosFuturos.add(t.getProximoEstado()); 
                        }
                    }
                }
            }
            if(estadosFuturos.isEmpty()){
               return false;
            }else{
                estadoInicial = estadosFuturos;
            }
        }
        Estado estadoTeste;
        Iterator it = estadoInicial.iterator();
        while(it.hasNext()){
           estadoTeste = (Estado)it.next();
           System.out.println("Estado "+estadoTeste.getIdentificador());
        }
           
        for(int k = 0; k < estadoInicial.size(); k++){
            if(estadoInicial.get(k).isTerminal()){
                return true;
            }
        }
        return false;
    }
        
        public ArrayList<Estado> verificaVazio(ArrayList<Estado> estadoInicial){
            
            char caractereTransicao;
            int tamanhoAntes = 0;
            int tamanhoDepois = 1;
            
            while(tamanhoAntes != tamanhoDepois){
                tamanhoAntes = estadoInicial.size();
                for(int i = 0; i < estadoInicial.size(); i++){
                    for(Transicoes t :estadoInicial.get(i).getTransicoes()){
                        caractereTransicao = t.getSimboloLido();
                        if(caractereTransicao == 'e'){
                            if(!estadoInicial.contains(t.getProximoEstado())){
                                estadoInicial.add(t.getProximoEstado());   
                            }
                        }
                    }
                }
                tamanhoDepois = estadoInicial.size();
            }
            return estadoInicial;
        }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    
        estadoInicial.desmarcarTodos();  
        estadoInicial.desenhar((Graphics2D) g);
      
    }
    
    public void criaAutomato(String cadeia){
         
        Estado estado = new Estado(false);
        
        for(int i = 0; i < cadeia.length(); i++){

            Estado proximoEstado = new Estado(false);
            
            if(i == 0){
                this.setEstadoInicial(estado);
            }
            
            if(cadeia.charAt(i) == 'T'){
                if(i+1 < cadeia.length()){
                    if(cadeia.charAt(i+1)=='*'){

                        estado.setTransicoes(new Transicoes('0', estado));
                        estado.setTransicoes(new Transicoes('1', estado));
                        i++;

                    }else if(cadeia.charAt(i+1) == '+'){

                        estado.setTransicoes(new Transicoes('0', proximoEstado));
                        estado.setTransicoes(new Transicoes('1', proximoEstado));

                        proximoEstado.setTransicoes(new Transicoes('0', proximoEstado));
                        proximoEstado.setTransicoes(new Transicoes('1', proximoEstado));

                        estado = proximoEstado;

                        i++;
                    }else if(cadeia.charAt(i+1) == '?'){

                        estado.setTransicoes(new Transicoes('0', proximoEstado));
                        estado.setTransicoes(new Transicoes('1', proximoEstado));
                        estado.setTransicoes(new Transicoes('e', proximoEstado));

                        estado = proximoEstado;

                        i++;
                    }else{
                        estado.setTransicoes(new Transicoes('0', proximoEstado));
                        estado.setTransicoes(new Transicoes('1', proximoEstado));
                        estado = proximoEstado;
                    }
                }else{
                    estado.setTransicoes(new Transicoes('0', proximoEstado));
                    estado.setTransicoes(new Transicoes('1', proximoEstado));
                    estado = proximoEstado;
                    estado.setTerminal(true);
                }
            } else if(cadeia.charAt(i) == '1'){
                if(i+1 < cadeia.length()){
                    if(cadeia.charAt(i+1) == '*'){
                        estado.setTransicoes(new Transicoes('1', estado));
                        i++;
                    }else if(cadeia.charAt(i+1) == '+'){
                        estado.setTransicoes(new Transicoes('1', proximoEstado));
                        proximoEstado.setTransicoes(new Transicoes('1', proximoEstado));
                        estado = proximoEstado;
                        i++;
                    }else if(cadeia.charAt(i+1) == '?'){
                        estado.setTransicoes(new Transicoes('1', proximoEstado));
                        estado.setTransicoes(new Transicoes('e', proximoEstado));
                        estado = proximoEstado;
                        i++;
                    }else{
                        estado.setTransicoes(new Transicoes('1', proximoEstado));
                        estado = proximoEstado;
                    }
                }else{
                    estado.setTransicoes(new Transicoes('1', proximoEstado));
                    estado = proximoEstado;
                    estado.setTerminal(true);
                }
            } else if(cadeia.charAt(i) == '0'){
                if(i+1 < cadeia.length()){
                    if(cadeia.charAt(i+1) == '*'){
                        estado.setTransicoes(new Transicoes('0', estado));
                        i++;
                    }else if(cadeia.charAt(i+1) == '+'){
                        estado.setTransicoes(new Transicoes('0', proximoEstado));
                        proximoEstado.setTransicoes(new Transicoes('0', proximoEstado));
                        estado = proximoEstado;
                        i++;
                    }else if(cadeia.charAt(i+1) == '?'){
                        estado.setTransicoes(new Transicoes('0', proximoEstado));
                        estado.setTransicoes(new Transicoes('e', proximoEstado));
                        estado = proximoEstado;
                        i++;
                    }else{
                        estado.setTransicoes(new Transicoes('0', proximoEstado));
                        estado = proximoEstado;
                    }
                }else{
                    estado.setTransicoes(new Transicoes('0', proximoEstado));
                    estado = proximoEstado;
                    estado.setTerminal(true);
                }
            }
        }
    }
}

    