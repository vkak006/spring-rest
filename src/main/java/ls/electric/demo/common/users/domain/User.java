package ls.electric.demo.common.users.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import ls.electric.demo.config.security.Role;
import ls.electric.demo.sample.domain.BaseTimeEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Document(collection = "user")
public class User extends BaseTimeEntity implements UserDetails {
    private String id;
    private String email;
    private String username;
    private String password;

    private Boolean enabled;
    private List<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(auth -> new SimpleGrantedAuthority(auth.name())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired(){
        return false;
    }

    @Override
    public boolean isAccountNonLocked(){
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public User(String username, String password,Boolean enabled,List<Role> roles){
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public static User newInstance(String username, String password,Boolean enabled, List<Role> roles){
        return new User(username, password, enabled, roles);
    }
}
