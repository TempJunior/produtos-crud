package br.com.tempjunior.service;

import br.com.tempjunior.dao.ProdutoDAO;
import br.com.tempjunior.model.CategoriaProduto;
import br.com.tempjunior.model.Produto;

public class ProdutoService {
    public void cadastraService(){
        Produto videoGame = new Produto("PlayStation 5", 49999L, CategoriaProduto.ELETRONICO);
        try {
            cadastraProduto(videoGame);

            System.out.println("Produto cadastrado com sucesso: " + "'" +  videoGame.getNome()+ "'");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   private Integer cadastraProduto(Produto produto) throws Exception {
       ProdutoDAO produtos = new ProdutoDAO();
       return produtos.cadastrar(produto);
   }

   public void atualizaService(){
        Produto produto = new Produto("Pacote de arroz", 29L, CategoriaProduto.COMESTIVEL);

       try {
           atualizaProduto(produto);
           System.out.println("Produto atualizado com sucesso! ");
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }
   private Integer atualizaProduto(Produto produto) throws Exception {
        ProdutoDAO produtos = new ProdutoDAO();
        return produtos.atualizar(produto);
   }

   public void buscarService(Long id){
       ProdutoDAO produtos = new ProdutoDAO();
       try {
           System.out.println(produtos.buscar(id));
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }
}
