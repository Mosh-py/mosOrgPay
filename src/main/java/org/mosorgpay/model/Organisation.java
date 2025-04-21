package org.mosorgpay.model;



import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "organisations")
@Data
public class Organisation {
	@Id
	@Nonnull
	private String id;
	
	private String name;
	
	private String password;
	
}
