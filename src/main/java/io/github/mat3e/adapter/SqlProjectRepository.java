package io.github.mat3e.adapter;

import io.github.mat3e.model.Project;
import io.github.mat3e.model.ProjectRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlProjectRepository extends ProjectRepository, JpaRepository<Project, Integer> {
    @Override
    @Query("select distinct g from Project g join fetch g.projectSteps")
    List<Project> findAll();

}
