package com.chanaka.track.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by chanaka on 7/27/17.
 */
@Entity
@Table(name = "track_type")
public class TrackTypeJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "track_type_id")
    private Integer id;

    @Size(max = 30)
    @Column(name = "track_type_name")
    private String name;

    @OneToMany(mappedBy="trackType")
    private List<TrackJPA> tracks;

    public TrackTypeJPA() {
    }

    public TrackTypeJPA(Integer id, String name) {
        this.id = id;
        this.name = name;
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
        return "TrackTypeJPA{" + "id=" + id + ", name=" + name+ '}';
    }
}
