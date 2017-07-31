package com.chanaka.track.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by chanaka on 7/27/17.
 */

@Entity
@Table(name = "location")
public class LocationJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "location_id")
    private Integer id;

    @Size(max = 10)
    @Column(name = "location_type")
    private String type;

    @Size(max = 100)
    @Column(name = "location_object_type")
    private String objectType;

    @Size(max = 100)
    @Column(name = "location_species")
    private String species;

    @Size(max = 100)
    @Column(name = "location_dbtype")
    private String dbtype;

    @Size(max = 255)
    @Column(name = "location_uri")
    private String uri;

    @OneToMany(mappedBy="location")
    private List<TrackJPA> tracks;

    public LocationJPA() {
    }

    public LocationJPA(String type, String objectType, String species, String dbtype, String uri) {
        this.type = type;
        this.objectType = objectType;
        this.species = species;
        this.dbtype = dbtype;
        this.uri = uri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDbtype() {
        return dbtype;
    }

    public void setDbtype(String dbtype) {
        this.dbtype = dbtype;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "LocationJPA{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", objectType='" + objectType + '\'' +
                ", species='" + species + '\'' +
                ", dbtype='" + dbtype + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
