package negocio;

import excecoes.ContaInexistenteException;
import interfaces.IRepositorioContas;

public class CadastroConta {
	
	private IRepositorioContas contas;
	
	public CadastroConta(IRepositorioContas repositorio){
		this.contas = repositorio;
	}
	
	public void cadastrar(ContaAbstrata conta) throws ContaInexistenteException{
		if(conta != null){
			if(!(contas.existe(conta.getNumero()))){
				contas.inserir(conta);
			}else 
				System.out.println("Conta Ja Cadastrada!");			
		}else 
			System.out.println("Conta Invalida!");
	}
	
	public ContaAbstrata procurar(String numero) throws ContaInexistenteException{
		   return this.contas.procurar(numero);
	}
	
	public void remover (String numero) throws ContaInexistenteException {
		//COLOCAR VALIDACAO E REGRA DE NEGOCIO
		this.contas.remover(numero);
	}
	
	public void atualizar(ContaAbstrata conta) {
		//COLOCAR VALIDACAO E REGRA DE NEGOCIO
		this.contas.atualizar(conta);
	}

	

	

}
