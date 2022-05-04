package io.auth.auth.model.bac;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by k.kezzar on 12/04/2020.
 */

@Entity
@Data
@Table(name = "dossier_bachelier",schema = "bac")
public class DossierBachelier {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "matricule")
    private String matricule;
    @Column(name = "annee_bac")
    private String anneeBac;

}
