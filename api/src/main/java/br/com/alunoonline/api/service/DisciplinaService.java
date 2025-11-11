package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Disciplina;
import br.com.alunoonline.api.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    public void criarDisciplina(Disciplina disciplina){
        disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> buscarTodasDisciplinas(){
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> buscarDisciplinaPeloID(Long id){
        return disciplinaRepository.findById(id);
    }

    public void atualizarDisciplina(Long id, Disciplina disciplinaAtualizada) {
        Optional<Disciplina> disciplinaExistente = disciplinaRepository.findById(id);
        if (disciplinaExistente.isPresent()) {
            Disciplina disciplina = disciplinaExistente.get();
            disciplina.setNome(disciplinaAtualizada.getNome());
            disciplina.setProfessor(disciplinaAtualizada.getProfessor());
            disciplinaRepository.save(disciplina);
        }
    }

    public void deletarDisciplina(Long id) {
        disciplinaRepository.deleteById(id);
    }
}
