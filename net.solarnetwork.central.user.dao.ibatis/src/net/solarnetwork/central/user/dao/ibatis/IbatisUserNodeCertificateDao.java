/* ==================================================================
 * IbatisUserNodeCertificateDao.java - Nov 29, 2012 8:52:02 PM
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

package net.solarnetwork.central.user.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.solarnetwork.central.dao.ibatis.IbatisGenericDaoSupport;
import net.solarnetwork.central.user.dao.UserNodeCertificateDao;
import net.solarnetwork.central.user.domain.UserNodeCertificate;

/**
 * iBATIS implementation of {@link UserNodeCertificateDao}.
 * 
 * @author matt
 * @version 1.0
 */
public class IbatisUserNodeCertificateDao extends IbatisGenericDaoSupport<UserNodeCertificate> implements
		UserNodeCertificateDao {

	/** The query name used for {@link #getActiveCertificateForNode(Long)}. */
	public static final String QUERY_ACTIVE_FOR_NODE = "get-UserNodeCertificate-for-active-node";

	/** The query name used for {@link #getCertificateForKey(Long, String)}. */
	public static final String QUERY_FOR_KEY = "get-UserNodeCertificate-for-key";

	/**
	 * Default constructor.
	 */
	public IbatisUserNodeCertificateDao() {
		super(UserNodeCertificate.class);
	}

	@Override
	public UserNodeCertificate getActiveCertificateForNode(Long nodeId) {
		@SuppressWarnings("unchecked")
		List<UserNodeCertificate> results = getSqlMapClientTemplate().queryForList(
				QUERY_ACTIVE_FOR_NODE, nodeId);
		if ( results.size() > 0 ) {
			return results.get(0);
		}
		return null;
	}

	@Override
	public UserNodeCertificate getCertificateForKey(Long userId, String key) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("key", key);
		@SuppressWarnings("unchecked")
		List<UserNodeCertificate> results = getSqlMapClientTemplate()
				.queryForList(QUERY_FOR_KEY, params);
		if ( results.size() > 0 ) {
			return results.get(0);
		}
		return null;
	}

}
