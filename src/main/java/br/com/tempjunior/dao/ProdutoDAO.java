package br.com.tempjunior.dao;

import br.com.tempjunior.connection.ConnectionFactory;
import br.com.tempjunior.model.CategoriaProduto;
import br.com.tempjunior.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements IProdutoDAO{
    @Override
    public Integer cadastrar(Produto cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSQLInsert();
            stm = connection.prepareStatement(sql);
            adicionarParametrosDeInsert(stm, cliente);
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }
        finally {
            connection.close();
            stm.close();
        }

    }

    private String getSQLInsert(){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO tb_produtos (NOME, PRECO, CATEGORIA)");
        sb.append("VALUES (?, ?, ?)");
        return sb.toString();
    }

    private void adicionarParametrosDeInsert(PreparedStatement stm, Produto cliente) throws Exception{
        stm.setString(1, cliente.getNome());
        stm.setLong(2, cliente.getPreco());
        stm.setString(3, cliente.getCategoria().name());
    }

    @Override
    public Integer atualizar(Produto cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSQLUpdate();
            stm = connection.prepareStatement(sql);
            adicionaParametroDeUpdate(stm, cliente);
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }
        finally {
            connection.close();
            stm.close();
        }
    }

    private String getSQLUpdate(){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE tb_produtos ");
        sb.append("SET NOME = ?, PRECO = ?, CATEGORIA = ? ");
        sb.append("WHERE ID = 2");
        return sb.toString();
    }

    private void adicionaParametroDeUpdate(PreparedStatement stm, Produto produto) throws Exception{
        stm.setString(1, produto.getNome());
        stm.setLong(2, produto.getPreco());
        stm.setString(3, produto.getCategoria().name());
    }

    @Override
    public Produto buscar(Long id) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Produto produto = null;

        try {
            connection = ConnectionFactory.getConnection();
            produto = new Produto();
            String sql = getSQLBuscar();
            stm = connection.prepareStatement(sql);
            adicionaParametroAoBuscar(stm, id);
            rs = stm.executeQuery();
            if (rs.next()){
                Long ids= rs.getLong("ID");
                String nome = rs.getString("NOME");
                Long preco = rs.getLong("PRECO");
                String categoria = rs.getString("CATEGORIA");

                produto.setId(ids);
                produto.setNome(nome);
                produto.setPreco(preco);
                produto.setCategoria(CategoriaProduto.valueOf(categoria));

            }
        }catch (Exception e){
            throw e;
        }
        finally {
            connection.close();
            stm.close();
            rs.close();
        }

        return produto;
    }

    private String getSQLBuscar(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_produtos ");
        sb.append("WHERE id = ? ");
        return sb.toString();
    }

    private void adicionaParametroAoBuscar(PreparedStatement stm, Long id) throws SQLException {
        stm.setLong( 1,id);
    }

    @Override
    public List<Produto> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Produto produto = null;
        List<Produto> listaProdutos = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            produto = new Produto();
            String sql = getSQLBuscarTodos();
            stm = connection.prepareStatement(sql);

            rs = stm.executeQuery();
            while (rs.next()){
                Long ids= rs.getLong("ID");
                String nome = rs.getString("NOME");
                Long preco = rs.getLong("PRECO");
                String categoria = rs.getString("CATEGORIA");

                produto.setId(ids);
                produto.setNome(nome);
                produto.setPreco(preco);
                produto.setCategoria(CategoriaProduto.valueOf(categoria));

            }
        }catch (Exception e){
            throw e;
        }
        finally {
            connection.close();
            stm.close();
            rs.close();
        }

        return listaProdutos;
    }

    private String getSQLBuscarTodos(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_produtos");
        return sb.toString();
    }

    @Override
    public Integer excluir(Long id) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSQLDelete();
            stm = connection.prepareStatement(sql);
            adicionaParametroDeDelete(stm, id);
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }
        finally {
            connection.close();
            stm.close();
        }
    }

    private String getSQLDelete(){
        StringBuilder sb = new StringBuilder();
        sb.append("DELTE FROM tb_produtos ");
        sb.append("WHERE id = ? ");
        return sb.toString();
    }

    public void adicionaParametroDeDelete(PreparedStatement stm, Long id) throws Exception{
        stm.setLong(1 ,id);
    }
}
