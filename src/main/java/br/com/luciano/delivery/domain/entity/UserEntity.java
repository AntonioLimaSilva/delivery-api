package br.com.luciano.delivery.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class UserEntity extends IdBase {

	@Column(name="nome", nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(name="senha", nullable = false)
	private String password;
	
	@CreationTimestamp
	@Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
	private LocalDateTime createAt;
	
	@ManyToMany
	@JoinTable(name = "usuario_permissao", joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "permissao_id"))
	private List<PermissionEntity> permissions = new ArrayList<>();
	
}
