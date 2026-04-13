package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    
    private List<String> enderecos;
    private String enderecoEntrega;

    public Usuario() {
        this.enderecos = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public List<String> getEnderecos() { return enderecos; }
    public void setEnderecos(List<String> enderecos) { this.enderecos = enderecos; }

    public String getEnderecoEntrega() { return enderecoEntrega; }
    public void setEnderecoEntrega(String enderecoEntrega) { this.enderecoEntrega = enderecoEntrega; }

    public void adicionarEndereco(String endereco) {
        if (endereco != null && !endereco.trim().isEmpty()) {
            this.enderecos.add(endereco);
            if (this.enderecoEntrega == null || this.enderecoEntrega.isEmpty()) {
                this.enderecoEntrega = endereco;
            }
        }
    }
}