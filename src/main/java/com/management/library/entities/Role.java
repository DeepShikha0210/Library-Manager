package com.management.library.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private  String name;
	
	@ManyToMany(mappedBy = "roles")
    private Set<User> users;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name;
	}
	
	
}
