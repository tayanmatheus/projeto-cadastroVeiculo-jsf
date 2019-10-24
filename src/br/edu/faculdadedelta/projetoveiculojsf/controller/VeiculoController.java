package br.edu.faculdadedelta.projetoveiculojsf.controller;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.projetoveiculojsf.dao.VeiculoDAO;
import br.edu.faculdadedelta.projetoveiculojsf.modelo.Veiculo;

@ManagedBean
@SessionScoped
public class VeiculoController {

	private Veiculo veiculo = new Veiculo();
	private VeiculoDAO dao = new VeiculoDAO();

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public void limparCampos() {
		veiculo = new Veiculo();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public String salvar() {

		try {
			if (veiculo.getId() == null) {
				// incluir

				dao.incluir(veiculo);
				limparCampos();
				exibirMensagem("inclusão feita com sucesso.");
			} else {
				// alterar

				dao.alterar(veiculo);
				limparCampos();
				exibirMensagem("Alteração feita com sucesso");

			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, " 
			+ "tente novamente mais tarde." + e.getMessage());
		}

		return "cadastroVeiculo.xhtml";

	}
	
	public List<Veiculo> getLista(){
		
		List<Veiculo> listaRetorno = new ArrayList<>(); 
		try {
			listaRetorno = dao.Listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar operação"
					+ "tente novamente mais tarde" + e.getMessage());
		}
		return listaRetorno;
	}
	
}
