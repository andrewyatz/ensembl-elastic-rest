/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chanaka.track.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.List;

/**
 * Created by chanaka on 7/27/17.
 */
@Entity
@Table(name = "data_release")
public class ReleaseJPA implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "data_release_id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "data_release_division")
    private String division;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_release_version")
    private int version;

    @ManyToMany(mappedBy="releases")
    private List<TrackJPA> tracks;

    public ReleaseJPA() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<TrackJPA> getTracks() {
        return tracks;
    }

    public List<TrackJPA> setTracks(List<TrackJPA> tracks) {
        return this.tracks = tracks;
    }
}
