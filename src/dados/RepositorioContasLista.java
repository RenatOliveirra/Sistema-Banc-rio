package dados;

import excecoes.ContaInexistenteException;
import interfaces.IRepositorioContas;
import negocio.ContaAbstrata;

public class RepositorioContasLista implements IRepositorioContas{

	private ContaAbstrata conta;
	private RepositorioContasLista prox;
	public static int tam; //Tamanho da Lista. Atributo pertence a classe e não ao objeto;
	private int indice; //Respectiva posição na lista. Atributo pertence ao objeto.
	public RepositorioContasLista(){
	}
	
	private int getIndice(String numero){
		return getNo(numero).indice;
	}
	
	public RepositorioContasLista getProx(){
		return prox;
	}	
	
	//Pega uma celula específica na lista
	//Recebe como parametro o numero de uma conta para encontrar um nó com a respectiva conta na lista
	//Retorna o nó que contem a conta com o número digitado ou retorna null;
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
			indice = tam;//Quando o objeto é instanciado ele armazena sua posição na lista;
		}else{
			prox.inserir(conta);
		}
	}
	
	//Procura uma conta no Repositorio Lista e Retorna a Conta procurada
	//Recebe o número da conta que será procurada
	//Enquanto não achar a conta com o número referente a proxima celular é checada em busca do número da conta.
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
