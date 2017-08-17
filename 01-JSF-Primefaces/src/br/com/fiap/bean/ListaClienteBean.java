package br.com.fiap.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.bo.ClienteBO;
import br.com.fiap.entity.Cliente;
import br.com.fiap.exception.DBException;

@ManagedBean
@ViewScoped
public class ListaClienteBean {

	private List<Cliente> lista;
	
	private ClienteBO bo;
	
	private String nome;
	
	private int codigo;
	
	public String excluir(){
		FacesMessage msg;
		try {
			bo.remover(codigo);
			msg = new FacesMessage("Excluido!");
		} catch (DBException e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro...");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext()
		.getFlash().setKeepMessages(true);
		return "lista-cliente?faces-redirect=true";
	}
	
	//método para o autocomplete
	public List<String> completar(String texto){
		return bo.completarPorNome(texto);
	}
	
	public void buscar(){
		lista = bo.buscarPorNome(nome);
	}
	
	@PostConstruct
	private void init(){
		bo = new ClienteBO();
		lista = bo.listar();
	}

	public List<Cliente> getLista() {
		return lista;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
