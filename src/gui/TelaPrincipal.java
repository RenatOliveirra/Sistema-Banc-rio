package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dados.RepositorioContasLista;
import excecoes.ContaInexistenteException;
import excecoes.SaldoIndisponivelException;
import negocio.Conta;
import negocio.ContaAbstrata;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNumero;
	private JTextField textFieldSaldo;
	private ContaAbstrata conta = new Conta();
	private RepositorioContasLista rep = new RepositorioContasLista();
	private JTextField textFieldResultado;
	private JTextField textFieldValor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumero = new JLabel("N\u00FAmero:");
		lblNumero.setBounds(5, 19, 54, 14);
		contentPane.add(lblNumero);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(5, 59, 54, 14);
		contentPane.add(lblSaldo);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setBounds(69, 16, 86, 20);
		contentPane.add(textFieldNumero);
		textFieldNumero.setColumns(10);
		
		textFieldSaldo = new JTextField();
		textFieldSaldo.setBounds(69, 56, 86, 20);
		contentPane.add(textFieldSaldo);
		textFieldSaldo.setColumns(10);
		
		JButton btnCriarConta = new JButton("Criar Conta");
		btnCriarConta.setBounds(183, 15, 113, 23);
		btnCriarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conta.setNumero(textFieldNumero.getText());
				conta.setSaldo(Double.parseDouble(textFieldSaldo.getText()));
				textFieldResultado.setText(conta.getNumero());
			}
		});
		contentPane.add(btnCriarConta);
		
		JButton btnSaldo = new JButton("Saldo");
		btnSaldo.setBounds(183, 55, 113, 23);
		contentPane.add(btnSaldo);
		
		JButton btnCreditar = new JButton("Creditar");
		btnCreditar.setBounds(183, 89, 113, 23);
		btnCreditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conta.creditar(Double.parseDouble(textFieldValor.getText()));
				textFieldResultado.setText(String.valueOf(conta.getSaldo()));
			}
		});
		contentPane.add(btnCreditar);
		
		JLabel lblResultado = new JLabel("Resultado:");
		lblResultado.setBounds(5, 163, 64, 14);
		contentPane.add(lblResultado);
		
		textFieldResultado = new JTextField();
		textFieldResultado.setBounds(69, 160, 86, 20);
		contentPane.add(textFieldResultado);
		textFieldResultado.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(5, 107, 54, 14);
		contentPane.add(lblValor);
		
		textFieldValor = new JTextField();
		textFieldValor.setBounds(69, 104, 86, 20);
		contentPane.add(textFieldValor);
		textFieldValor.setColumns(10);
		
		JButton btnDebitar = new JButton("Debitar");
		btnDebitar.setBounds(183, 113, 113, 23);
		btnDebitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					conta.debitar(Double.parseDouble(textFieldValor.getText()));
				}catch(SaldoIndisponivelException ex){
					//e.printStackTrace();
					System.out.println("A conta " + ex.getNumero() + " tem " + ex.getSaldo());
				}
				textFieldResultado.setText(String.valueOf(conta.getSaldo()));
				
			}
		});
		contentPane.add(btnDebitar);
		
		JScrollPane scrollPaneDados = new JScrollPane();
		scrollPaneDados.setBounds(69, 203, 227, 129);
		contentPane.add(scrollPaneDados);
		
		JTextArea textAreaDados = new JTextArea();
		scrollPaneDados.setViewportView(textAreaDados);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conta.setNumero(textFieldNumero.getText());
				conta.setSaldo(Double.parseDouble(textFieldSaldo.getText()));
				rep.inserir(conta);
				JOptionPane.showMessageDialog(null, "Conta adicionada ao repositorio com Sucesso!");
			}
		});
		btnAdicionar.setBounds(357, 15, 89, 23);
		contentPane.add(btnAdicionar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					rep.remover(textFieldNumero.getText());
					JOptionPane.showMessageDialog(null, "Conta removida do repositorio com Sucesso!");
				}catch(ContaInexistenteException e){
					e.getStackTrace();
					JOptionPane.showMessageDialog(null, "A conta "+ e.getNumero() +" Não existe!");
				}
			}
		});
		btnRemover.setBounds(357, 49, 89, 23);
		contentPane.add(btnRemover);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(357, 83, 89, 23);
		contentPane.add(btnAtualizar);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					ContaAbstrata tmp = new Conta();
					tmp = rep.procurar(textFieldNumero.getText());
					textAreaDados.append("DADOS: \n Numero: "+ tmp.getNumero()+ "\n Saldo: "+ tmp.getSaldo());
				}catch(ContaInexistenteException ex){
					textAreaDados.append("DADOS: \n Conta de Numero: "+ ex.getNumero()+ "\n Não Existe!");
				}
			}
		});
		btnProcurar.setBounds(357, 117, 89, 23);
		contentPane.add(btnProcurar);
	}
}
