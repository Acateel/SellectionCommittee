package com.SelectionCommittee.SelectionCommittee.models;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "applicant", schema = "admissions")
public class ApplicantEntity {
    @Basic
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "region")
    private String region;
    @Basic
    @Column(name = "name_educational_institution")
    private String nameEducationalInstitution;
    @Basic
    @Column(name = "attestation")
    private byte[] attestation;
    @Basic
    @Column(name = "block")
    private byte block;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNameEducationalInstitution() {
        return nameEducationalInstitution;
    }

    public void setNameEducationalInstitution(String nameEducationalInstitution) {
        this.nameEducationalInstitution = nameEducationalInstitution;
    }

    public byte[] getAttestation() {
        return attestation;
    }

    public void setAttestation(byte[] attestation) {
        this.attestation = attestation;
    }

    public byte getBlock() {
        return block;
    }

    public void setBlock(byte block) {
        this.block = block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicantEntity that = (ApplicantEntity) o;
        return block == that.block && Objects.equals(id, that.id) && Objects.equals(lastName, that.lastName) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(email, that.email) && Objects.equals(city, that.city) && Objects.equals(region, that.region) && Objects.equals(nameEducationalInstitution, that.nameEducationalInstitution) && Arrays.equals(attestation, that.attestation);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, lastName, name, surname, email, city, region, nameEducationalInstitution, block);
        result = 31 * result + Arrays.hashCode(attestation);
        return result;
    }
}
