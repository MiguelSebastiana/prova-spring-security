package br.com.ctw.prova_miguel.repository;

import br.com.ctw.prova_miguel.entity.ChamadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository<ChamadoEntity, Long> {

}
