package br.com.fiap.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;

import br.com.fiap.bo.ClienteBO;

@ManagedBean
public class GraficoBean {

	private PieChartModel pizza;
	private ClienteBO bo;
	
	@PostConstruct
	private void init(){
		pizza = new PieChartModel();
		bo = new ClienteBO();
		pizza.set("Ótimo", bo.buscarPorRating(10));
		pizza.set("Regular", bo.buscarPorRating(5));
		pizza.set("Ruim", bo.buscarPorRating(1));
		
		pizza.setShowDataLabels(true);
		pizza.setLegendPosition("e");
	}

	public PieChartModel getPizza() {
		return pizza;
	}

	public void setPizza(PieChartModel pizza) {
		this.pizza = pizza;
	}
}
