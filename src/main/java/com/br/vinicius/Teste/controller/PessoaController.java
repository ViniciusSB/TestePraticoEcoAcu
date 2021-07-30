package com.br.vinicius.Teste.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.br.vinicius.Teste.repository.PessoaRepository;
import com.br.vinicius.Teste.model.Pessoa;
import com.br.vinicius.Teste.model.Sexo;
import com.br.vinicius.Teste.util.ValidaCpf;
import com.br.vinicius.Teste.validation.PessoaValidation;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class PessoaController implements Serializable {

	private Pessoa pessoa = new Pessoa();
	private ValidaCpf validaCpf = new ValidaCpf();
	private List<Pessoa> pessoas = new ArrayList<>();
	private PessoaRepository pessoaRepository = new PessoaRepository();
	private String filtro = "";
	private PessoaValidation validation = new PessoaValidation();

	public void salvar() {
		if (pessoa.getCpf() != null) {
			ValidaCpf valida = new ValidaCpf();
			pessoa.setCpf(valida.TirarMascara(pessoa.getCpf()));
		}
		if (validation.validaEmailPessoa(pessoa) && validation.validaCpfPessoa(pessoa) &&
				validation.validarTelefone(pessoa) && validation.validaDataAniversarioPessoa(pessoa)) {
			pessoa = pessoaRepository.merge(pessoa);
			limparCampos();
			listarPessoas();
		}
		listarPessoas();
	}

	public void listarPessoas() {
		pessoas = pessoaRepository.buscarTodos(filtro);
	}

	public Pessoa editar(Long id) {
		pessoa = pessoaRepository.buscarPessoaPorId(id);
		return pessoa;
	}

	public void exluir() {
		pessoaRepository.deletePorId(pessoa);
		pessoaRepository.buscarTodos(filtro);
		limparCampos();
	}

	public Pessoa limparCampos() {
		pessoa = new Pessoa();
		return pessoa;
	}

	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public String getFiltro() {
		return filtro;
	}
}
