package com.SelectionCommittee.SelectionCommittee.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "faculties", schema = "admissions")
public class FacultiesEntity {
    @Basic
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "faculty_name")
    private String facultyName;
    @Basic
    @Column(name = "budget_seats")
    private int budgetSeats;
    @Basic
    @Column(name = "total_seats")
    private int totalSeats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getBudgetSeats() {
        return budgetSeats;
    }

    public void setBudgetSeats(int budgetSeats) {
        this.budgetSeats = budgetSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultiesEntity that = (FacultiesEntity) o;
        return budgetSeats == that.budgetSeats && totalSeats == that.totalSeats && Objects.equals(id, that.id) && Objects.equals(facultyName, that.facultyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, facultyName, budgetSeats, totalSeats);
    }
}
