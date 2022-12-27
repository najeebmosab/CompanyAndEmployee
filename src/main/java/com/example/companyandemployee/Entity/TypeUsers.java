package com.example.companyandemployee.Entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "type_users")
public class TypeUsers {
    @Id
    private Long id;
    @Column(name = "name_type")
    private String nameType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeUsers typeUsers = (TypeUsers) o;
        return Objects.equals(id, typeUsers.id) && Objects.equals(nameType, typeUsers.nameType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameType);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public TypeUsers() {
    }

    public TypeUsers(Long id, String nameType) {
        this.id = id;
        this.nameType = nameType;
    }
}
