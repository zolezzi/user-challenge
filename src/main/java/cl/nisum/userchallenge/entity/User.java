package cl.nisum.userchallenge.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", insertable = false, updatable = false, nullable = false)
	private UUID id;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create", nullable=false)
	private Date create;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modify")
	private Date modify;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_login", nullable=false)
	private Date lastLogin;
	
	@Column
	private String token;
	
	@Getter
	@Column(name = "is_active")
	private boolean isActive;
	
}
