package com.arnold.autolibrary.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "distributionrecord")
public class DistributionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int distributionID;

    @ManyToOne
    @JoinColumn(name="bookID",nullable = false)
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(name = "studentID",nullable = false)
    private Student student;

    @Column(nullable = false)
    private LocalDate dateDistributed;

    private LocalDate dateReturned;//null until stud returns
    @Column(nullable = false)
    private int academicYear;

    @ManyToOne
    @JoinColumn(name = "distributedBy")
    private UserDetails distributedBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DistributionStatus status;

    public DistributionRecord() {}

    public DistributionRecord(BookCopy bookCopy, Student student,
                              int academicYear, UserDetails distributedBy) {
        this.bookCopy = bookCopy;
        this.student = student;
        this.academicYear = academicYear;
        this.distributedBy = distributedBy;
        this.dateDistributed = LocalDate.now();
        this.status = DistributionStatus.DISTRIBUTED;
    }

    public int getDistributionID() { return distributionID; }
    public void setDistributionID(int distributionID) { this.distributionID = distributionID; }

    public BookCopy getBookCopy() { return bookCopy; }
    public void setBookCopy(BookCopy bookCopy) { this.bookCopy = bookCopy; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public LocalDate getDateDistributed() { return dateDistributed; }
    public void setDateDistributed(LocalDate dateDistributed) { this.dateDistributed = dateDistributed; }

    public LocalDate getDateReturned() { return dateReturned; }
    public void setDateReturned(LocalDate dateReturned) { this.dateReturned = dateReturned; }

    public int getAcademicYear() { return academicYear; }
    public void setAcademicYear(int academicYear) { this.academicYear = academicYear; }

    public UserDetails getDistributedBy() { return distributedBy; }
    public void setDistributedBy(UserDetails distributedBy) { this.distributedBy = distributedBy; }

    public DistributionStatus getStatus() { return status; }
    public void setStatus(DistributionStatus status) { this.status = status; }



}
