package negocio;

import excecoes.SaldoIndisponivelException;

public abstract class ContaAbstrata{
	protected String numero;
	protected double saldo;
	
	public ContaAbstrata(){
		
	}

	public ContaAbstrata(String numero, double saldo){
		this.numero = numero;
		this.saldo = saldo;
	}

	public String getNumero(){
		return this.numero;
	}
	
	public void setNumero(String numero){
		this.numero = numero;
	}
	
	public double getSaldo(){
		return this.saldo;
	}
	
	public void setSaldo(double saldo){
		if(saldo < 0){
			System.out.println("Saldo não pode ser negativo");
		}else
			this.saldo = saldo;
	}

	public void creditar(double valor){
		this.saldo += valor;
	}
	
	public abstract void debitar(double valor) throws SaldoIndisponivelException;
	
	public void transfere(ContaAbstrata destino, double valor) throws SaldoIndisponivelException{
		
		if(valor > this.saldo){
			System.out.println("NÃ£o tem saldo suficiente para transferir");
		}else{
			destino.creditar(valor);
			debitar(valor);
		}		
	}
}
