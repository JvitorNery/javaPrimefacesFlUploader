package br.com.fiap.bean;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.fiap.bo.ClienteBO;
import br.com.fiap.entity.Cliente;
import br.com.fiap.exception.DBException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class ClienteBean {

	private Cliente cliente;
	
	private ClienteBO bo;
	
	@PostConstruct
	private void init(){
		//Inicializando os objetos
		cliente = new Cliente();
		cliente.setDataNascimento(Calendar.getInstance());
		bo = new ClienteBO();
	}
	
	public String cadastrar(){
		FacesMessage msg;
		try {
			if (cliente.getCodigo() == 0){
				bo.cadastrar(cliente);
				msg = new FacesMessage("Cadastrado!");
			}else{
				bo.atualizar(cliente);
				msg = new FacesMessage("Atualizado!");
			}
		} catch (DBException e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro..");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext()
								.getFlash().setKeepMessages(true);
		return "cliente?faces-redirect=true";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
