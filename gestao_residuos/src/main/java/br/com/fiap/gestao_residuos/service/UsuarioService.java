package br.com.fiap.gestao_residuos.service;

import br.com.fiap.gestao_residuos.dto.UsuarioCadastroDTO;
import br.com.fiap.gestao_residuos.dto.UsuarioExibicaoDTO;
import br.com.fiap.gestao_residuos.model.Usuario;
import br.com.fiap.gestao_residuos.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    //LISTAR TODOS
    public Page<UsuarioExibicaoDTO> listarTodos(Pageable paginacao){
        return repository
                .findAll(paginacao)
                .map(UsuarioExibicaoDTO::new);
    }

    //BUSCAR POR ID
    public UsuarioExibicaoDTO buscarPorId(Long id){
        Optional<Usuario> usuarioOptional = repository.findById(id);

        if (usuarioOptional.isPresent()){
            return new UsuarioExibicaoDTO(usuarioOptional.get());
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    // CADASTRAR
    @Transactional
    public UsuarioExibicaoDTO cadastrar (UsuarioCadastroDTO usuarioCadastroDTO){
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDTO, usuario);

        Usuario usuarioSalvo = repository.save(usuario);

        return new UsuarioExibicaoDTO(usuarioSalvo);
    }

    //ATUALIZAR
    @Transactional
    public UsuarioExibicaoDTO atualizar (UsuarioCadastroDTO usuarioCadastroDTO){
        Optional<Usuario> usuarioOptional = repository.findById(usuarioCadastroDTO.id());

        if (usuarioOptional.isPresent()){
            Usuario usuario = new Usuario();
            BeanUtils.copyProperties(usuarioCadastroDTO, usuario);

            return new UsuarioExibicaoDTO(repository.save(usuario));
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    //EXCLUIR
    @Transactional
    public void excluir(Long id){
        Optional<Usuario> usuarioOptional = repository.findById(id);

        if (usuarioOptional.isPresent()){
            repository.delete(usuarioOptional.get());
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }
}
