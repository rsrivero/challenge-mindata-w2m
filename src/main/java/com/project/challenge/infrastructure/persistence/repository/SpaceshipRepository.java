package com.project.challenge.infrastructure.persistence.repository;

import com.project.challenge.application.adapter.SpaceshipCommandService;
import com.project.challenge.application.adapter.SpaceshipQueryService;
import com.project.challenge.domain.entity.Spaceship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SpaceshipRepository extends JpaRepository<Spaceship, Integer>,
        JpaSpecificationExecutor<Spaceship>,
        SpaceshipCommandService,
        SpaceshipQueryService {

    default Optional<Spaceship> findSpaceship(Integer id) {
        return this.findById(id);
    }

    default void delete(Integer id) {
        this.deleteById(id);
    }

    default Spaceship update(Spaceship spaceship) {
        return ((CrudRepository<Spaceship, Integer>) this).save(spaceship);
    }

    default Page<Spaceship> findAllPaged(Pageable pageable, Specification<Spaceship> where) {
        return this.findAll(where, pageable);
    }

}
