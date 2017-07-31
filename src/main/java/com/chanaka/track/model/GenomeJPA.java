package com.chanaka.track.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by chanaka on 7/27/17.
 */

@Entity
@Table(name = "genome")
public class GenomeJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "genome_id")
    private Integer id;

    @Size(max = 100)
    @Column(name = "genome_assembly")
    private String assembly;

    @Size(max = 1)
    @Column(name = "genome_strain")
    private Boolean strain;

    @Size(max = 11)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="species_id")
    private SpeciesJPA species;

    public GenomeJPA() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SpeciesJPA getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesJPA species) {
        this.species = species;
    }

    public String getAssembly() {
        return assembly;
    }

    public void setAssembly(String assembly) {
        this.assembly = assembly;
    }

    public Boolean isStrain() {
        return strain;
    }

    public void setStrain(Boolean strain) {
        this.strain = strain;
    }

    @Override
    public String toString() {
        return "GenomeJPA{" +
                "id=" + id +
                ", species='" + species + '\'' +
                ", assembly='" + assembly + '\'' +
                ", strain='" + strain + '\'' +
                ", species_id='" + species.getId() + '\'' +
                '}';
    }
}
