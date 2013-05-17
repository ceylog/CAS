/**
 * GenerPasswordEncoder.java Create on 2013-3-14
 * Copyright(c) Gener-Tech Inc.
 * ALL Rights Reserved.
 */
package com.genertech.cas.authauthentication.handler;

import org.jasig.cas.authentication.handler.PasswordEncoder;

/**
 * <pre>
 * 功能说明：
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
public class GenerPasswordEncoder implements PasswordEncoder {

	/* (non-Javadoc)
	 * @see org.jasig.cas.authentication.handler.PasswordEncoder#encode(java.lang.String)
	 */
	@Override
	public String encode(String password) {
		return password;
	}
}
