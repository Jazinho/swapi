package pl.starwars.swapi.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class PersonEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "mass")
    private Integer mass;

    @Column(name = "height")
    private Integer height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
