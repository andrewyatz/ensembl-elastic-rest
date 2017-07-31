package com.chanaka.track.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by chanaka on 7/27/17.
 */

@Entity
@Table(name = "species")
public class SpeciesJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "species_id")
    private Integer id;

    @Size(max = 100)
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="species")
    private List<GenomeJPA> genomes;

    public SpeciesJPA() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SpeciesJPA{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
