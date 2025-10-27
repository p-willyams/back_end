package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Professor;
import br.com.alunoonline.api.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    public void criarProfessor(Professor professor){
        professorRepository.save(professor);
    }

    public List<Professor> buscarTodosProfessores(){
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarProfessorPeloID(Long id){
        return professorRepository.findById(id);
    }

    public void atualizarProfessor(Long id, Professor professorAtualizado) {
        Optional<Professor> professorExistente = professorRepository.findById(id);
        if (professorExistente.isPresent()) {
            Professor professor = professorExistente.get();
            professor.setNomeCompleto(professorAtualizado.getNomeCompleto());
            professor.setEmail(professorAtualizado.getEmail());
            professor.setCpf(professorAtualizado.getCpf());
            professorRepository.save(professor);
        }
    }

    public void deletarProfessor(Long id) {
        professorRepository.deleteById(id);
    }
}
