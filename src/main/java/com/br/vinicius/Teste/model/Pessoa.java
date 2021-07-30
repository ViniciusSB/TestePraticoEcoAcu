package com.br.vinicius.Teste.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Sexo sexo;
    private LocalDate dataNascimento;

    public Pessoa(Long id, String nome, String cpf, String email, String telefone, Sexo sexo, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {return id; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getTelefone() {return telefone;}

    public void setEmail(String email) {this.email = email;}

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
