package com.healthIQ.healthIQ.Models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Document("Users")
@Getter
@Setter
public class User implements UserDetails {
    @Id
    private String ID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private String ville;
    private String adresse;
    private Double poid;
    private String dateNaissance;
    private String CodeMedical;
    private Integer phoneNumber;
    private String sex;
    private boolean sportActif;
    private Double taille;
    private String typeSport;
    private Integer nb_foisSport;
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
}
