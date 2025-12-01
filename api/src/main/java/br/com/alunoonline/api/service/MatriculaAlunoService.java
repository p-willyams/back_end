package br.com.alunoonline.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import br.com.alunoonline.api.dtos.AtualizarNotasRequestDTO;
import br.com.alunoonline.api.enums.MatriculaStatusEnum;
import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.repository.MatriculaAlunoRepository;

@Service
public class MatriculaAlunoService {

    private static final Double MEDIA_PARA_APROVACAO = 7.0;

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;
    
    public void matricular(MatriculaAluno matriculaAluno){
        matriculaAluno.setStatus(MatriculaStatusEnum.MATRICULADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }
    public void trancarMatricula(Long id){
        MatriculaAluno matriculaAluno = matriculaAlunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matricula não encontrada"));
        if (matriculaAluno.getStatus() == MatriculaStatusEnum.MATRICULADO) {
            matriculaAluno.setStatus(MatriculaStatusEnum.TRANCADO);
            matriculaAlunoRepository.save(matriculaAluno);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possível trancar matrículas com status MATRICULADO");
        }
    }
    public void atualizarNotas(Long id, AtualizarNotasRequestDTO atualizarNotasRequestDTO){ 
        MatriculaAluno matriculaAluno = matriculaAlunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matricula não encontrada"));
        if (atualizarNotasRequestDTO.getNota1() != null){
            matriculaAluno.setNota1(atualizarNotasRequestDTO.getNota1());
        }
        if (atualizarNotasRequestDTO.getNota2() != null){
            matriculaAluno.setNota2(atualizarNotasRequestDTO.getNota2());
        }
        matriculaAlunoRepository.save(matriculaAluno);

        atualizarStatus(matriculaAluno);

        matriculaAlunoRepository.save(matriculaAluno);
        

    }
    private Double calcularMedia(Double nota1, Double nota2){
        return nota1 != null && nota2 != null ? (nota1 + nota2) / 2 : null;
    }
    public void atualizarStatus(MatriculaAluno matriculaAluno){
        Double media = calcularMedia(matriculaAluno.getNota1(), matriculaAluno.getNota2());
        if (media == null) {
            matriculaAluno.setStatus(MatriculaStatusEnum.MATRICULADO);
        } else if (media >= MEDIA_PARA_APROVACAO) {
            matriculaAluno.setStatus(MatriculaStatusEnum.APROVADO);
        } else {
            matriculaAluno.setStatus(MatriculaStatusEnum.REPROVADO);
        }
        matriculaAlunoRepository.save(matriculaAluno);
    }
}



