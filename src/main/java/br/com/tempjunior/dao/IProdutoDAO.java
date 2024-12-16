package br.com.tempjunior.dao;

import br.com.tempjunior.model.Produto;

import java.util.List;

public interface IProdutoDAO {
    public Integer cadastrar(Produto cliente) throws Exception;
    public Integer atualizar(Produto cliente) throws Exception;
    public Produto buscar (Long id) throws Exception;
    public List<Produto> buscarTodos() throws Exception;
    public Integer excluir(Long id) throws Exception;
}
