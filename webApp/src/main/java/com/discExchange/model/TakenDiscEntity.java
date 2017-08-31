package com.discExchange.model;

import javax.persistence.*;

/**
 * Created by adMin on 10.08.2017.
 */
@Entity
@Table(name = "TAKENDISCS")
public class TakenDiscEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "ownerUserId")
    private UserEntity ownerUser;

    @OneToOne
    @JoinColumn(name = "takingUserId")
    private UserEntity takerUser;

    @OneToOne
    @JoinColumn(name = "discId")
    private DiscEntity disc;


    private String description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getTakerUser() {
        return takerUser;
    }

    public void setTakerUser(UserEntity takerUser) {
        this.takerUser = takerUser;
    }

    public DiscEntity getDisc() {
        return disc;
    }

    public void setDisc(DiscEntity disc) {
        this.disc = disc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(UserEntity ownerUser) {
        this.ownerUser = ownerUser;
    }
}
