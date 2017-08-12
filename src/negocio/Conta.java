package negocio;

import excecoes.SaldoIndisponivelException;

public class Conta extends ContaAbstrata{
	
	
	public Conta(){
		
	}
	
	public Conta(String numero, double saldo){
		super(numero, saldo);
	}
	
	public void debitar(double valor) throws SaldoIndisponivelException{
		
		if(valor <= super.saldo){
			super.saldo -= valor;
		}else{
			SaldoIndisponivelException e;
			e = new SaldoIndisponivelException(super.saldo, super.numero);
			throw e;
		}
	}

}
