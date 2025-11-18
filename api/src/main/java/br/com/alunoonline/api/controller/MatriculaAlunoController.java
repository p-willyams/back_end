package br.com.alunoonline.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.com.alunoonline.api.model.MatriculaAluno;

import br.com.alunoonline.api.service.MatriculaAlunoService;

@RestController
@RequestMapping("/matriculas")
public class MatriculaAlunoController {

    @Autowired
    MatriculaAlunoService matriculaAlunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void matricular(@RequestBody MatriculaAluno matriculaAluno){
        matriculaAlunoService.matricular(matriculaAluno);

    }

}
