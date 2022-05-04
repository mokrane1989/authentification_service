package io.auth.auth.repository;

/**
 * Created by k.kezzar on 12/04/2020.
 */

import io.auth.auth.model.bac.DossierBachelier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierBachlierRepository  extends JpaRepository<DossierBachelier,Long>
{
    @Query(value = "SELECT distinct db.* FROM bac.dossier_bachelier db\n" +
            "inner join cursus.dossier_etudiant de on de.id_dossier_bachelier=db.id\n" +
            "inner join cursus.dossier_inscription_administrative dia on dia.id_dossier_etudiant=de.id \n" +
            "where concat(matricule,annee_bac)=?",
            nativeQuery = true)
    List<DossierBachelier> findByMatricule(String matricule);
    DossierBachelier findOneByMatriculeAndAnneeBac(String matricule,String anneeBac);
}
