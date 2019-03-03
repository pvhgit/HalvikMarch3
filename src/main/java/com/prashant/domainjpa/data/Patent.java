package com.prashant.domainjpa.data;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "patent")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public class Patent {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty
    private String guid;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "author_patent",
            joinColumns = @JoinColumn(name = "patent_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @JsonProperty
    private Set<Author> authors;

    @JsonProperty
    @NotBlank
    private String url;

    @JsonProperty
    private Status status;

    @JsonProperty
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;

    @JsonProperty
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdTime;


    public Patent() {}

    public Patent(Set<Author> authors, String url, Status st, Date deadline) {
        this.authors = authors;
        this.url = url;
        this.status = st;
        this.deadline = deadline;
    }
}
