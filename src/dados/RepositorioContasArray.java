package dados;
import excecoes.ContaInexistenteException;
import interfaces.IRepositorioContas;
import negocio.ContaAbstrata;

public class RepositorioContasArray implements IRepositorioContas{
	
	private ContaAbstrata[] contas;
	private int indice;
	
	private final static int TAMANHO = 100;
	
	public RepositorioContasArray(){
		this.contas = new ContaAbstrata[TAMANHO];
		this.indice = 0;
	}
	
	private int getIndice(String numero){
		String n;
        boolean achou = false;
        int i = 0;
        while ((!achou) && (i < this.indice)) {
            n = contas[i].getNumero();
            if (n.equals(numero)) {
                achou = true;
            } else {
                i++;
            }
        }
        return i;
	}
	
	public void inserir(ContaAbstrata conta){
		
		contas[indice] = conta;
		indice++;
	}
	
	public ContaAbstrata procurar(String numero) throws ContaInexistenteException{
		
		int i = getIndice(numero);
		
		if(contas[i] == null){
			ContaInexistenteException e = new ContaInexistenteException(numero);
			throw e;
		}
		
		return contas[i];		
		 
	}
	
	public void remover(String numero){ 
		
		int i = getIndice(numero);
		
		contas[i] = null;				
		contas[i] = contas[indice-1];
		
		indice--;
			
	}
	
	public void atualizar(ContaAbstrata conta){
		
		int i = getIndice(conta.getNumero());		
		contas[i] = conta;
		
	}
	
	public boolean existe(String numero){
		
		int i = getIndice(numero);		
		if(contas[i] == null)
			return false;
		else return true;
	}

}
