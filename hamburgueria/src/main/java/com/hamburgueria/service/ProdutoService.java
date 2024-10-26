package com.hamburgueria.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.hamburgueria.dto.FornecedorDTO;
import com.hamburgueria.dto.ProdutoDTO;
import com.hamburgueria.entities.Fornecedor;
import com.hamburgueria.entities.Produto;
import com.hamburgueria.repository.FornecedorRepository;
import com.hamburgueria.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    // CREATE                                                 EU recebi um id de fornecedor e um nome de produto
    public ResponseEntity<ProdutoDTO> addProduto(ProdutoDTO produtoRecebido){ // Recebe o produtoDTO e chamei ele de produtoRecebido
        //Busca o Fornecedor pelo ID                          // \/Aqui estou pegando o id do produtoRecebido e passando como parametro pro 'fornecedor', vai selecionar toda a tabela de fornecedor atraves do id e colocar dentro de fornecedor e se der erro vai dar o exception
        Fornecedor fornecedor = fornecedorRepository.findById(produtoRecebido.getFornecedor().getId()).orElseThrow(()-> new RuntimeException("Fornecedor não encontrado"));
        // Se a linha de cima der certo ele vai criar um novo objeto chamado produto
        Produto produto = new Produto();
        produto.setFornecedor(fornecedor); // Aqui ele pega esse fornecedor de cima e setar no produto
        produto.setNome(produtoRecebido.getNome()); // Aqui ele vai pegar e setar dentro do produto o nome do produtoRecebido 

        // Vai persistir os dados, foi no banco pegou o id salva pega o nome salva e devolve um objeto com essas informações, mais o id que gerou no banco
        Produto produtoSalvo = produtoRepository.save(produto); // Aqui agente salva esse produto, agora o 'produto' tem o obejto fornecedor e o nome

        // Criando um FornecedorDTO pq o fornecedor de cima é uma entidade, e colocando uma entidade dentro do produto
        // mas eu nao posso retornar pra tela uma entidade, entao cria um fornecedorDTO e coloca dentro dele todos os dados do fornecedores
        FornecedorDTO fornecedorDTO = new FornecedorDTO(fornecedor.getId(),fornecedor.getNome(),fornecedor.getNacionalidade());

        // O produtoRecebido tinha um id dentro e um nome
        // agora setei um fornecedorDTO, invez de ter um fornecedor só com id, agora vai ter um fabricante completo
        produtoRecebido.setFornecedor(fornecedorDTO); // Pega o fornecedorDTO e joga dentro de produtoRecebido
        produtoRecebido.setIdProduto(produtoSalvo.getId()); // Aqui pega o produtoSalvo e bota dentro do produtoRecebido
        // Aqui eu vou ta devolvendo um fornecedorDTO, ou seja, vai substituir aqueel fornecedor que so tinha id e vai por um objeto com id, nacionalidade e nome

        return ResponseEntity.ok(produtoRecebido); // Retornando o produtoRecebido pro controller
    }

    // READ
    public List<ProdutoDTO> getProdutos(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(produto -> new ProdutoDTO(produto.getNome(),convertFornecedorDTO(produto.getFornecedor()),produto.getId())).collect(Collectors.toList());
    }

    // READ BY ID
    public Optional <ProdutoDTO> getProdutoById(Long id){
        return produtoRepository.findById(id).map(produto -> new ProdutoDTO(produto.getNome(), convertFornecedorDTO(produto.getFornecedor()), produto.getId()));
    }
    
    // UPDATE
    public Optional<ProdutoDTO> updateProduto(Long id, ProdutoDTO produtoDTO) {
        return produtoRepository.findById(id).map(produto -> {
            // Atualizando o nome do produto
            produto.setNome(produtoDTO.getNome());
    
            // Verificando se o fornecedor existe no DTO e se contém dados para atualizar
            if (produtoDTO.getFornecedor() != null) {
                FornecedorDTO fornecedorDTO = produtoDTO.getFornecedor();  // Isso é um DTO
    
                // Recuperar o fornecedor da base de dados pelo ID que veio no FornecedorDTO
                Fornecedor fornecedor = fornecedorRepository.findById(fornecedorDTO.getId())
                        .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    
                // Atualizar os campos do fornecedor se necessário
                if (fornecedorDTO.getNacionalidade() != null) {
                    fornecedor.setNacionalidade(fornecedorDTO.getNacionalidade());
                }
                if (fornecedorDTO.getNome() != null) {
                    fornecedor.setNome(fornecedorDTO.getNome());
                }
    
                // Atualizar o fornecedor do produto
                produto.setFornecedor(fornecedor);
            }
    
            // Salvando o produto com as alterações
            produtoRepository.save(produto);
    
            // Retornando o ProdutoDTO atualizado
            return new ProdutoDTO(produto.getNome(), convertFornecedorDTO(produto.getFornecedor()), produto.getId());
        });
    }
    
    // DELETE retornando um boolean
    public boolean deleteProduto(Long id){
        Optional <Produto> produtoExiste = produtoRepository.findById(id); // Verificando se o produto existe, se sim coloca dentro do objeto Produto
        if (produtoExiste.isPresent()){
            produtoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

     // Método para converter fornecedor entidade em fornecedorDTO
    private FornecedorDTO convertFornecedorDTO(Fornecedor fornecedor){
        FornecedorDTO fornecedorDTO = new FornecedorDTO(fornecedor.getId(), fornecedor.getNome(), fornecedor.getNacionalidade());
        return fornecedorDTO;
    }
    
    /*
    public List<Produto> obterProdutosPorNacionalidadeDoFornecedor(String nacionalidade){
        return produtoRepository.findModelosByFornecedorNacionalidade(nacionalidade);
    }
    */

    /*
    // UPDATE 
    public Optional<ProdutoDTO> updateProduto(Long id, ProdutoDTO produtoDTO) {
        return produtoRepository.findById(id).map(produto -> {
            // Atualizando o nome do produto
            produto.setNome(produtoDTO.getNome());
    
            // Verificando se o fornecedor existe no DTO e se contém dados para atualizar
            if (produtoDTO.getFornecedor() != null) {
                Fornecedor fornecedor = produto.getFornecedor();
    
                // Verifica se a nacionalidade não é nula e atualiza
                if (fornecedor != null && produtoDTO.getFornecedor().getNacionalidade() != null) {
                    fornecedor.setNacionalidade(produtoDTO.getFornecedor().getNacionalidade());
                }
            }
    
            // Salvando o produto com as possíveis alterações
            produtoRepository.save(produto);
    
            // Retornando o ProdutoDTO atualizado
            return new ProdutoDTO(produto.getNome(), convertFornecedorDTO(produto.getFornecedor()), produto.getId());
        });
    }
    */
}
