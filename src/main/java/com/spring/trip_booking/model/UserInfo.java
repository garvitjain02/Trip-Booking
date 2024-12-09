package com.spring.trip_booking.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.trip_booking.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class UserInfo implements UserDetails {
	
/*	public UserInfo(int id, String vendor_approved, String username, String password, String name, String lastName,
            LocalDate dob, String phone, String email, Role role) {
super();
this.id = id;
this.vendor_approved = vendor_approved;
this.username = username;
this.password = password;
this.name = name;
this.lastName = lastName;
this.dob = dob;
this.phone = phone;
this.email = email;
this.role = role;
}*/

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private String vendor_approved;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    
    @Column()
    private String name;
    
    @Column()
    private String lastName;
    
    @Column()
    private LocalDate dob;
    
    @Column()
    private String phone;
    
    @Column()
    private String email;
    
    @Enumerated(EnumType.STRING)
    private Role role;

    // Getters and Setters
	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(authority);
		return list;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getVendor_approved() {
		return vendor_approved;
	}

	public void setVendor_approved(String vendor_approved) {
		this.vendor_approved = vendor_approved;
	}
}
