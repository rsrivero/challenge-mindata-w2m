package com.project.challenge.infrastructure.persistence.repository;

import com.project.challenge.application.adapter.SpaceshipCommandService;
import com.project.challenge.application.adapter.SpaceshipQueryService;
import com.project.challenge.domain.model.Spaceship;
import com.project.challenge.infrastructure.persistence.SpaceshipSpecification;
import com.project.challenge.infrastructure.persistence.entity.SpaceshipEntity;
import com.project.challenge.infrastructure.persistence.mapper.SpaceshipDomainMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpaceshipRepository extends JpaRepository<SpaceshipEntity, Integer>,
        JpaSpecificationExecutor<SpaceshipEntity>,
        SpaceshipCommandService,
        SpaceshipQueryService {

    default Optional<Spaceship> findSpaceship(Integer id) {
        return this.findById(id).map(SpaceshipDomainMapper::toDomain);
    }

    default void delete(Integer id) {
        this.deleteById(id);
    }

    default Spaceship update(Spaceship spaceship) {
        SpaceshipEntity entity = SpaceshipDomainMapper.toEntity(spaceship);
        SpaceshipEntity saved = this.save(entity);
        return SpaceshipDomainMapper.toDomain(saved);
    }

    default Page<Spaceship> findAllPaged(Pageable pageable) {
        return this.findAll(pageable).map(SpaceshipDomainMapper::toDomain);
    }

    default List<Spaceship> searchByName(String name) {
        Specification<SpaceshipEntity> spec = SpaceshipSpecification.getName(name);

        return this.findAll(spec)
                .stream()
                .map(SpaceshipDomainMapper::toDomain)
                .toList();
    }

    default Spaceship save(Spaceship spaceship) {
        SpaceshipEntity entity = SpaceshipDomainMapper.toEntity(spaceship);
        SpaceshipEntity saved = save(entity);
        return SpaceshipDomainMapper.toDomain(saved);
    }

}
