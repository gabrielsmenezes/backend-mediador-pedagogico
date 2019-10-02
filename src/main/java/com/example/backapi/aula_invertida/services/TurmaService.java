package com.example.backapi.aula_invertida.services;

import com.example.backapi.aula_invertida.domain.turma.Turma;
import com.example.backapi.aula_invertida.repositories.TurmaRepository;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class TurmaService {

    @Autowired
    TurmaRepository turmaRepository;


    public Turma save(Turma turma) {
        if (turma.getNome() == null || turma.getChaveDeAcesso() == null){
            throw new ConstraintViolationException("Nome da turma e chave de acesso é necessário", null);
        }

        verificarSeNomeDaTurmaJaExiste(turma);

        verificarSeChaveDeAcessoJaExiste(turma);

        Turma turmaCadastrada = turmaRepository.save(turma);

        return turma;
    }

    private void verificarSeChaveDeAcessoJaExiste(Turma turma) {
        List<Turma> turmas = findAll();
        for (int i = 0; i < turmas.size(); i++){
            if (turma.getChaveDeAcesso().equals(turmas.get(i).getChaveDeAcesso())){
                throw new ConstraintViolationException("Chave de acesso já cadastrado", null);
            }
        }
    }

    private void verificarSeNomeDaTurmaJaExiste(Turma turma) {
        List<Turma> turmas = findAll();
        for (int i = 0; i < turmas.size(); i++){
            if (turma.getNome().equals(turmas.get(i).getNome())){
                throw new ConstraintViolationException("Nome já cadastrado", null);
            }
        }
    }

    public List<Turma> findAll(){
        return turmaRepository.findAll();
    }

    public void update(Turma turma) {
        turmaRepository.save(turma);
    }

    public Turma findById(Integer id) throws ObjetoNaoEncontrado {
        return turmaRepository.findById(id).orElseThrow(ObjetoNaoEncontrado::new);
    }
}
