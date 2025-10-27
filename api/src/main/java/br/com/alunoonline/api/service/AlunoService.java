package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public void criarAluno(Aluno aluno){
        alunoRepository.save(aluno);
    }

    public List<Aluno> buscarTodosAlunos(){
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarAlunoPeloID(Long id){
        return alunoRepository.findById(id);
    }

    public void atualizarAluno(Long id, Aluno alunoAtualizado) {
        Optional<Aluno> alunoExistente = alunoRepository.findById(id);
        if (alunoExistente.isPresent()) {
            Aluno aluno = alunoExistente.get();
            aluno.setNomeCompleto(alunoAtualizado.getNomeCompleto());
            aluno.setEmail(alunoAtualizado.getEmail());
            aluno.setCpf(alunoAtualizado.getCpf());
            alunoRepository.save(aluno);
        }
    }

    public void deletarAluno(Long id) {
        alunoRepository.deleteById(id);
    }
}
