package dados;

import excecoes.ContaInexistenteException;
import interfaces.IRepositorioContas;
import negocio.ContaAbstrata;

public class RepositorioContasLista implements IRepositorioContas{

	private ContaAbstrata conta;
	private RepositorioContasLista prox;
	public static int tam; //Tamanho da Lista. Atributo pertence a classe e n�o ao objeto;
	private int indice; //Respectiva posi��o na lista. Atributo pertence ao objeto.
	public RepositorioContasLista(){
	}
	
	private int getIndice(String numero){
		return getNo(numero).indice;
	}
	
	public RepositorioContasLista getProx(){
		return prox;
	}	
	
	//Pega uma celula espec�fica na lista
	//Recebe como parametro o numero de uma conta para encontrar um n� com a respectiva conta na lista
	//Retorna o n� que contem a conta com o n�mero digitado ou retorna null;
	public RepositorioContasLista getNo(String numero){
		RepositorioContasLista atual = prox;
		
		while(numero != conta.getNumero()){
			if(atual == null)
				return null;
			else return atual.getNo(numero);
		}		
		return atual;
	}
	
	public void inserir(ContaAbstrata conta){
		if(this.conta == null){
			this.conta = conta;
			tam++;//Aumenta o tamanho da lista;
			prox = new RepositorioContasLista();
			indice = tam;//Quando o objeto � instanciado ele armazena sua posi��o na lista;
		}else{
			prox.inserir(conta);
		}
	}
	
	//Procura uma conta no Repositorio Lista e Retorna a Conta procurada
	//Recebe o n�mero da conta que ser� procurada
	//Enquanto n�o achar a conta com o n�mero referente a proxima celular � checada em busca do n�mero da conta.
	public ContaAbstrata procurar(String numero)throws ContaInexistenteException{
		boolean achou = false;
		String n;
		while(!achou && conta != null){
			 n = conta.getNumero();
			 if(n.equals(numero)){
				 achou = true;
			 }else	{
				 return prox.procurar(numero);
			 }
		}	
			
		if(conta == null){
			ContaInexistenteException e = new ContaInexistenteException(numero);
			throw e;
		}
		return conta;	
	}
	
	//Com defeito!! >.< 
	public void remover(String numero) throws ContaInexistenteException{
		
		if(conta != null){
			if(numero == conta.getNumero()){
				conta = getProx().conta;
				prox = getProx().prox;
			}
			else{
				getProx().remover(numero);
			}
		}else{
			ContaInexistenteException e = new ContaInexistenteException(numero);
			throw e;
		}
	}

	public void atualizar(ContaAbstrata conta){		
		conta = getNo(conta.getNumero()).conta;	
	}
	
	public boolean existe(String numero) throws ContaInexistenteException{
		if(procurar(numero) != null)
			return true;
		else 
			return false;
	}
	
	
}
