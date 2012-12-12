/* ==================================================================
 * IbatisUserAuthTokenDaoTest.java - Dec 12, 2012 4:41:05 PM
 * 
 * Copyright 2007-2012 SolarNetwork.net Dev Team
 * 
 * This program is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as 
 * published by the Free Software Foundation; either version 2 of 
 * the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with this program; if not, write to the Free Software 
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 
 * 02111-1307 USA
 * ==================================================================
 */

package net.solarnetwork.central.user.dao.ibatis.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import net.solarnetwork.central.dao.ibatis.IbatisSolarNodeDao;
import net.solarnetwork.central.domain.SolarNode;
import net.solarnetwork.central.user.dao.ibatis.IbatisUserAuthTokenDao;
import net.solarnetwork.central.user.domain.User;
import net.solarnetwork.central.user.domain.UserAuthToken;
import net.solarnetwork.central.user.domain.UserAuthTokenStatus;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test cases for {@link IbatisUserAuthTokenDao}.
 * 
 * @author matt
 * @version 1.0
 */
public class IbatisUserAuthTokenDaoTest extends AbstractIbatisUserDaoTestSupport {

	private static final String[] DELETE_TABLES = new String[] { "solaruser.user_auth_token",
			"solaruser.user_node", "solaruser.user_user" };

	private static final String TEST_TOKEN = "public.token12345678";
	private static final String TEST_SECRET = "secret.token12345678";

	@Autowired
	private IbatisSolarNodeDao solarNodeDao;

	@Autowired
	private IbatisUserAuthTokenDao userAuthTokenDao;

	private User user = null;
	private SolarNode node = null;
	private UserAuthToken userAuthToken = null;

	@Before
	public void setUp() throws Exception {
		setupTestNode();
		this.node = solarNodeDao.get(TEST_NODE_ID);
		assertNotNull(this.node);
		deleteFromTables(DELETE_TABLES);
		this.user = createNewUser(TEST_EMAIL);
		assertNotNull(this.user);
		userAuthToken = null;
	}

	@Test
	public void storeNew() {
		UserAuthToken authToken = new UserAuthToken();
		authToken.setCreated(new DateTime());
		authToken.setUserId(this.user.getId());
		authToken.setAuthSecret(TEST_SECRET);
		authToken.setAuthToken(TEST_TOKEN);
		authToken.setStatus(UserAuthTokenStatus.v);
		String id = userAuthTokenDao.store(authToken);
		assertNotNull(id);
		this.userAuthToken = authToken;
	}

	private void validate(UserAuthToken token, UserAuthToken entity) {
		assertNotNull("UserAuthToken should exist", entity);
		assertNotNull("Created date should be set", entity.getCreated());
		assertEquals(token.getId(), entity.getId());
		assertEquals(token.getStatus(), entity.getStatus());
		assertEquals(token.getAuthToken(), entity.getAuthToken());
		assertEquals(token.getAuthSecret(), entity.getAuthSecret());
	}

	@Test
	public void getByPrimaryKey() {
		storeNew();
		UserAuthToken token = userAuthTokenDao.get(userAuthToken.getId());
		validate(this.userAuthToken, token);
	}

	@Test
	public void update() {
		storeNew();
		UserAuthToken token = userAuthTokenDao.get(userAuthToken.getId());
		token.setStatus(UserAuthTokenStatus.z);
		UserAuthToken updated = userAuthTokenDao.get(userAuthTokenDao.store(token));
		validate(token, updated);
	}

	@Test
	public void delete() {
		storeNew();
		UserAuthToken token = userAuthTokenDao.get(userAuthToken.getId());
		userAuthTokenDao.delete(token);
		token = userAuthTokenDao.get(token.getId());
		assertNull(token);
	}

}
