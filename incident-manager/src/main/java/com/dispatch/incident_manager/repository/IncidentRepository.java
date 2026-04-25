package com.dispatch.incident_manager.repository;

import com.dispatch.incident_manager.entity.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// in JpaRepository Long is used for primary key, IncidentEntity tells what table to query
public interface IncidentRepository extends JpaRepository<IncidentEntity, Long>{
}
