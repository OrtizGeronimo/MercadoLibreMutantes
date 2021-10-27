package com.example.mutantes.repositories;

import com.example.mutantes.domain.Mutant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//@Component
@Repository
public interface MutantRepository extends JpaRepository<Mutant, Long> {


}
