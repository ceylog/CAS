package com.genertech.cas.authauthentication.handler;

import org.apache.commons.lang3.StringUtils;
import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.genertech.cas.utils.Digests;
import com.genertech.cas.utils.Encodes;

import javax.validation.constraints.NotNull;

public final class GenerDatabaseAuthenticationHandler extends
		AbstractJdbcUsernamePasswordAuthenticationHandler {
	
	public static final int HASH_INTERATIONS = 1024;
	
	@NotNull
	private String sql;

	protected final boolean authenticateUsernamePasswordInternal(
			final UsernamePasswordCredentials credentials)
			throws AuthenticationException {
		String username = credentials.getUsername();
		String password = credentials.getPassword();
		
		String bitrixPassword = null;
		boolean pass = false;
		try {
			bitrixPassword = getJdbcTemplate().queryForObject(this.sql,String.class, username);
			
			if (StringUtils.isNotBlank(bitrixPassword) && bitrixPassword.length() == 56) {
				String[] ps = bitrixPassword.split(",");
				byte[] salt = Encodes.decodeHex(ps[1]);
				byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_INTERATIONS);
				String cpwd = Encodes.encodeHex(hashPassword);
				String spwd = ps[0];
				System.out.println(".\n\n\n................"+cpwd+":"+ spwd);
				pass = spwd.equals(cpwd);
			}
		} catch (final IncorrectResultSizeDataAccessException e) {
			
		}
		return pass;
	}

	/**
	 * @param sql
	 *            The sql to set.
	 */
	public void setSql(final String sql) {
		this.sql = sql;
	}
}