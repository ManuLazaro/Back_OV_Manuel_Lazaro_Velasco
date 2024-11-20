package main.OV.service;

import java.util.Locale;


import main.OV.dto.ChangePasswordDto;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

/**
 * The Interface IUserService.
 */
@Service
public interface IUserService {

	/**
	 * Find user by email.
	 *
	 * @param userEmail the user email
	 * @return the user
	 */
	 User findUserByEmail(String userEmail);

	/**
	 * Creates the password reset token for user.
	 *
	 * @param userId the user id
	 * @param token the token
	 */
	 void createPasswordResetTokenForUser(Long userId, String token);

	/**
	 * Validate password reset token.
	 *
	 * @param id the id
	 * @param token the token
	 * @return the string
	 */
	 String validatePasswordResetToken(Long id, String token);

	/**
	 * Change user password.
	 *
	 * @param passwordDto the new password
	 * @return the string
	 */
	 String changeUserPassword(ChangePasswordDto passwordDto);
	

	
	/**
	 * Send mail register user.
	 *
	 * @param locale from the request
	 *
	 */
	 void sendMailRegisterUser(Locale locale, String token, Long userId, String userMail, String userName);

	/**
	 * Send mail register user.
	 *
	 * @param locale from the request

	 */
	void sendMailRegisterAdminUser(Locale locale, String token, Long userId, String userMail, String userName);

	/**
	 * Send mail register user.
	 *
	 * @param locale from the request

	 */
	void sendMailRegisteredAdminUser(Locale locale, String token, Long userId, String userMail, String userName);
	 

}
