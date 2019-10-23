package com.example.backapi.sobre_escola.services;

import com.example.backapi.sobre_escola.domain.Professor;
import com.example.backapi.sobre_escola.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor update(Professor professor) {
        return professorRepository.save(professor);
    }

    public void delete(Integer id) {
        professorRepository.deleteById(id);
    }

    @Transactional
    public List<Professor> findAll() {
        List<Professor> professores = professorRepository.findAll();
        professores.forEach(professor -> {
            professor.setDisciplinas(new ArrayList<>(professor.getDisciplinas()));
        });

        return professores;
    }
}
