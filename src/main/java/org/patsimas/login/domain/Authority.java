package org.patsimas.login.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority {

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "desc")
	private String description;
}
