package com.br.vinicius.Teste.validation;

import com.br.vinicius.Teste.model.Pessoa;
import com.br.vinicius.Teste.repository.PessoaRepository;
import com.br.vinicius.Teste.util.Util;
import com.br.vinicius.Teste.util.ValidaCpf;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Validated
public class PessoaValidation {

    public boolean validaDataAniversarioPessoa(Pessoa pessoa) {

        LocalDate hoje = LocalDate.now();

        if (pessoa.getDataNascimento().isAfter(hoje)) {
            Util.addMessageWarn("Data Inválida");
            return false;
        }
        return true;
    }

    public boolean validaCpfPessoa(Pessoa pessoa) {
        PessoaRepository repo = new PessoaRepository();
        ValidaCpf valida = new ValidaCpf();
        valida.setCpf(pessoa.getCpf());
        if (repo.containsCpf(pessoa.getId(), pessoa.getCpf())) {
            Util.addMessageWarn("Este CPF já está sendo utilizado.");
            return false;
        } else if (!valida.isCPF()) {
            Util.addMessageWarn("CPF inválido.");
            return false;
        }
        return true;
    }

    public boolean validaEmailPessoa(Pessoa pessoa) {
        PessoaRepository repo = new PessoaRepository();
        if (repo.containsEmail(pessoa.getId(), pessoa.getEmail())) {
            Util.addMessageWarn("Este e-mail já está sendo utilizado.");
            return false;
        } else if (!validarEmail(pessoa)) {
            Util.addMessageWarn("E-mail inválido.");
            return false;
        }
        return true;
    }

    public boolean validarTelefone(Pessoa pessoa) {
        PessoaRepository repo = new PessoaRepository();
        if (repo.containsTelefone(pessoa.getTelefone(), pessoa.getId())) {
            Util.addMessageWarn("Este telefone já está sendo usado.");
            return false;
        }
        return true;
    }

    public boolean validarEmail(Pessoa pessoa) {
        boolean isEmailValid = false;
        if (pessoa.getEmail() != null && pessoa.getEmail().length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(pessoa.getEmail());
            if (matcher.matches()) {
                isEmailValid = true;
            }
        }
        return isEmailValid;
    }

}
