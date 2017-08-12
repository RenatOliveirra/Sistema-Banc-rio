package base;

import excecoes.ContaInexistenteException;
import excecoes.SaldoIndisponivelException;
import negocio.Conta;
import negocio.ContaAbstrata;
import negocio.Fachada;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fachada f = Fachada.getInstance();
		ContaAbstrata c = new Conta("12345-6", 1000);
		try{

			f.inserir(c);
		}catch(ContaInexistenteException e){
			e.getStackTrace();
		}
		
		
		try{
			f.procurar("12345-6");
		}catch(ContaInexistenteException e){
			//e.printStackTrace();
			System.out.println("A conta "+ e.getNumero() + " Não existe!");
		}
		
		try{
			c.debitar(1001);			
		}catch(SaldoIndisponivelException e){
			//e.printStackTrace();
			System.out.println("A conta " + e.getNumero() + " tem " + e.getSaldo());
		}
	
	}

}
