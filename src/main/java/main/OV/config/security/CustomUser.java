package main.OV.config.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/* (non-Javadoc)
 * @see org.springframework.security.core.userdetails.User#toString()
 */

public class CustomUser extends org.springframework.security.core.userdetails.User{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;
	
	/** The delegation id. */
	private Long delegationId;
	
	/** The administrators. */
	private List<Long> administrators;

	
	
	/**
	 * Instantiates a new custom user.
	 *
	 * @param id the id
	 * @param username the username
	 * @param password the password
	 * @param authorities the authorities
	 */
	public CustomUser(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.id =id;
	}
	
	/**
	 * Instantiates a new custom user.
	 *
	 * @param id the id
	 * @param username the username
	 * @param password the password
	 * @param administrators 
	 * @param authorities the authorities
	 */
	public CustomUser(Long id, String username, String password, Long delegationId, List<Long> administrators, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.id =id;
		this.delegationId = delegationId;
		this.administrators = administrators;
	}
}
