package com.br.vinicius.Teste.repository;

import com.br.vinicius.Teste.util.JPAUtil;
import com.br.vinicius.Teste.model.Pessoa;
import com.br.vinicius.Teste.util.Util;
import com.br.vinicius.Teste.validation.PessoaValidation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaRepository {

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();
    static EntityTransaction transaction = entityManager.getTransaction();

    private List<Pessoa> pessoas = new ArrayList<>();
    private Pessoa pessoa = new Pessoa();
    private PessoaValidation validation = new PessoaValidation();

    public void salvar(Pessoa pessoa) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(pessoa);

        entityTransaction.commit();
        entityManager.close();
    }

    public Pessoa merge(Pessoa pessoa) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Pessoa retorno = entityManager.merge(pessoa);
        entityTransaction.commit();
        entityManager.close();
        Util.addMessageInfo("Salvo com sucesso!");
        return retorno;
    }

    public List<Pessoa> buscarTodos(String nome) {
        transaction.begin();
        Query query = entityManager.createNativeQuery("Select * FROM Pessoa p WHERE UPPER(p.nome) like UPPER(:nome) ORDER BY p.nome", Pessoa.class);
        query.setParameter("nome", "%" + nome + "%");
        pessoas = query.getResultList();
        transaction.commit();
        return pessoas;

    }

    public Pessoa buscarPessoaPorId(Long id) {
        Query query = entityManager.createNativeQuery("Select * FROM Pessoa p WHERE p.id = ? ", Pessoa.class);
        query.setParameter(1, id);
        pessoa = (Pessoa) query.getSingleResult();
        return pessoa;
    }

    public boolean containsCpf(Long id, String cpf) {
        Query query = entityManager.createNativeQuery("Select * FROM Pessoa p WHERE UPPER(p.cpf) = UPPER(:cpf)", Pessoa.class);
        query.setParameter("cpf", cpf);

        pessoas = query.getResultList();

        if (pessoas.size() > 0) {
            for (int i = 0; i < pessoas.size(); i++) {
                if (pessoas.size() == 1 && pessoas.get(i).getId().equals(id)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsEmail(Long id, String email) {
        Query query = entityManager.createNativeQuery("Select * FROM Pessoa p WHERE UPPER(p.email) = UPPER(:email)", Pessoa.class);
        query.setParameter("email", email);

        pessoas = query.getResultList();

        if (pessoas.size() > 0) {
            for (int i = 0; i < pessoas.size(); i++) {
                if (pessoas.size() == 1 && pessoas.get(i).getId().equals(id)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsTelefone(String telefone, Long id) {
        Query query = entityManager.createNativeQuery("Select * FROM Pessoa p WHERE UPPER(p.telefone) = UPPER(:telefone)", Pessoa.class);
        query.setParameter("telefone", telefone);

        pessoas = query.getResultList();

        if (pessoas.size() > 0) {
            for (int i = 0; i < pessoas.size(); i++) {
                if (pessoas.size() == 1 && pessoas.get(i).getId().equals(id)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public void deletePorId(Pessoa pessoa) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Object id = JPAUtil.getPrimaryKey(pessoa);
        entityManager.createQuery("DELETE FROM Pessoa WHERE id = " + id).executeUpdate();

        entityTransaction.commit();
        Util.addMessageInfo("Exclusão bem sucedida!");
    }


}
