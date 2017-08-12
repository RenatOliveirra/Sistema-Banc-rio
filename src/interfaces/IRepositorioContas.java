package interfaces;
import excecoes.ContaInexistenteException;
import negocio.ContaAbstrata;

public interface IRepositorioContas {

	public void inserir(ContaAbstrata conta);
	public ContaAbstrata procurar(String numero) throws ContaInexistenteException;
	public void remover(String numero) throws ContaInexistenteException;
	public void atualizar(ContaAbstrata conta);
	public boolean existe(String numero) throws ContaInexistenteException;	
	
}
