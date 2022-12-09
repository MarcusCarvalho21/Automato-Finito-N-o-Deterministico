package automato;

public class Transicoes {

	private char simboloLido;
	private Estado proximoEstado;	

	public Transicoes(char a, Estado e) {
		this.simboloLido = a;
		this.proximoEstado = e;
	}

	public char getSimboloLido() {
		return simboloLido;
	}
	public void setSimboloLido(char simboloLido) {
		this.simboloLido = simboloLido;
	}
	public Estado getProximoEstado() {
		return proximoEstado;
	}
	public void setProximoEstado(Estado proximoEstado) {
		this.proximoEstado = proximoEstado;
	}
	
}
