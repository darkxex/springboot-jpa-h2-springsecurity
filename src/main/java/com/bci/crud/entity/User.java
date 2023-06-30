package com.bci.crud.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
@Id	
@GeneratedValue
private int id;
@Temporal(TemporalType.TIMESTAMP)
private Date created = new Date();
private Date modified = created;
private Date last_login = created;
private String name;
private String email;
private String password;
private boolean isactive = false;
@ElementCollection(fetch = FetchType.LAZY)
@CollectionTable(name = "phones")
private Set<userPhones> phones = new HashSet<>();

}

