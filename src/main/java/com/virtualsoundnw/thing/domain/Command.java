package com.virtualsoundnw.thing.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Command.
 */
@Entity
@Table(name = "command")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Command implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "user", nullable = false)
    private Long user;

    @NotNull
    @Size(max = 1024)
    @Column(name = "request", length = 1024, nullable = false)
    private String request;

    @Size(max = 1024)
    @Column(name = "response", length = 1024)
    private String response;

    @Size(max = 1024)
    @Column(name = "status", length = 1024)
    private String status;

    @Column(name = "started")
    private Boolean started;

    @Column(name = "completed")
    private Boolean completed;

    @Column(name = "error")
    private Boolean error;

    @Column(name = "failed")
    private Boolean failed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Command date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getUser() {
        return user;
    }

    public Command user(Long user) {
        this.user = user;
        return this;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getRequest() {
        return request;
    }

    public Command request(String request) {
        this.request = request;
        return this;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public Command response(String response) {
        this.response = response;
        return this;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public Command status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean isStarted() {
        return started;
    }

    public Command started(Boolean started) {
        this.started = started;
        return this;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public Command completed(Boolean completed) {
        this.completed = completed;
        return this;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean isError() {
        return error;
    }

    public Command error(Boolean error) {
        this.error = error;
        return this;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Boolean isFailed() {
        return failed;
    }

    public Command failed(Boolean failed) {
        this.failed = failed;
        return this;
    }

    public void setFailed(Boolean failed) {
        this.failed = failed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Command command = (Command) o;
        if(command.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, command.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Command{" +
            "id=" + id +
            ", date='" + date + "'" +
            ", user='" + user + "'" +
            ", request='" + request + "'" +
            ", response='" + response + "'" +
            ", status='" + status + "'" +
            ", started='" + started + "'" +
            ", completed='" + completed + "'" +
            ", error='" + error + "'" +
            ", failed='" + failed + "'" +
            '}';
    }
}
