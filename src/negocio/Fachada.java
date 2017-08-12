package negocio;
import dados.RepositorioContasArray;
import excecoes.ContaInexistenteException;
import interfaces.IRepositorioContas;

public class Fachada {
	
	private CadastroConta contas;
	private static Fachada instance;
	
	public Fachada(){
		IRepositorioContas repositorioContas = new RepositorioContasArray();
		contas = new CadastroConta(repositorioContas);
	}
		
	public static Fachada getInstance(){
		if(instance == null){
			instance = new Fachada();
		}
		
		return instance;
		
	}
	
	public void inserir(ContaAbstrata conta) throws ContaInexistenteException{
		this.contas.cadastrar(conta);
	}
	
	public ContaAbstrata procurar(String numero) throws ContaInexistenteException{
		return this.contas.procurar(numero);
	}
	
	public void remover (String numero) throws ContaInexistenteException{
		this.contas.remover(numero);
	}
	
	public void atualizar(ContaAbstrata conta){
		this.contas.atualizar(conta);
	}

}
