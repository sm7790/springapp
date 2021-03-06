package com.david.demo.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    public Role() {
    }

    /**
     * Property getter
     */
    public Long getId() {
        return id;
    }

    /**
     * Property setter
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Property getter
     */
    public String getName() {
        return name;
    }

    /**
     * Property setter
     */
    public void setName(String name) {
        this.name = name;
    }
}
