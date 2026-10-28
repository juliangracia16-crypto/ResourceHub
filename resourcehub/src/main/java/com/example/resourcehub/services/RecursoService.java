package com.example.resourcehub.services;

import com.example.resourcehub.models.Recurso;
import com.example.resourcehub.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    public List<Recurso> obtenerTodos(){
        return recursoRepository.findAll();
    }

    public Optional<Recurso> obtenerPorId(Long id){
        return recursoRepository.findById(id);
    }

    public Recurso crearRecurso(Recurso recurso){
        //Aqui se podria agregar alguna validacion de negocio
        return recursoRepository.save(recurso);
    }

    //PUT -> actualicen el objeto completo
    //PATCH -> actualizacion parcial

    public Optional<Recurso> actualizarCompleto(Long id, Recurso recursoActualizado){
        return recursoRepository.findById(id).map(recursoExistente->{
            //PUT actualiza el objeto completo
            recursoExistente.setTitulo(recursoActualizado.getTitulo());
            recursoExistente.setCategoria(recursoActualizado.getCategoria());
            recursoExistente.setUrl(recursoActualizado.getUrl());
            recursoExistente.setTipo(recursoActualizado.getTipo());
            return recursoRepository.save(recursoExistente);
        });
    }

    public Optional<Recurso> actualizarParcial(Long id, Recurso recursoActualizado){
        return recursoRepository.findById(id).map(recursoExistente -> {
            //PATCH solo actualiza los campos que no vienen nulos
            if(recursoActualizado != null){
                recursoExistente.setTitulo(recursoActualizado.getTitulo());
            }
            if(recursoActualizado.getCategoria() != null){
                recursoExistente.setCategoria(recursoActualizado.getCategoria());
            }
            if(recursoActualizado.getUrl() != null){
                recursoExistente.setUrl(recursoActualizado.getUrl());
            }
            if(recursoActualizado.getTipo() != null){
                recursoExistente.setTipo(recursoActualizado.getTipo());
            }
            return recursoRepository.save(recursoExistente);
        });
    }

    public boolean eliminarRecurso(Long id){
        return recursoRepository.deleteById(id);
    }
}
