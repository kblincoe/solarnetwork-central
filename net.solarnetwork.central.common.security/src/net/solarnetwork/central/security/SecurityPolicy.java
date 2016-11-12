/* ==================================================================
 * SecurityPolicy.java - 9/10/2016 7:32:07 AM
 * 
 * Copyright 2007-2016 SolarNetwork.net Dev Team
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

package net.solarnetwork.central.security;

import java.util.Set;
import net.solarnetwork.central.domain.Aggregation;
import net.solarnetwork.central.domain.LocationPrecision;

/**
 * API for a security policy, that is rules defining access permissions.
 * 
 * @author matt
 * @version 1.1
 */
public interface SecurityPolicy {

	/**
	 * Get a set of node IDs this policy applies to.
	 * 
	 * @return set of node IDs, or {@code null}
	 */
	Set<Long> getNodeIds();

	/**
	 * Get a set of source IDs this policy applies to.
	 * 
	 * @return set of source IDs
	 */
	Set<String> getSourceIds();

	/**
	 * Get a set of aggregations this policy applies to.
	 * 
	 * @return set of aggregations
	 */
	Set<Aggregation> getAggregations();

	/**
	 * Get a minimum aggregation level this policy applies to.
	 * 
	 * @return The minimum aggregation level.
	 */
	Aggregation getMinAggregation();

	/**
	 * Get a location precision this policy applies to.
	 * 
	 * @return set of precisions
	 */
	Set<LocationPrecision> getLocationPrecisions();

	/**
	 * Get a minimum location precision this policy applies to.
	 * 
	 * @return The minimum location precision.
	 */
	LocationPrecision getMinLocationPrecision();

	/**
	 * Get a set of node metadata paths this policy applies to.
	 * 
	 * @return set of node metadata paths
	 * @since 1.1
	 */
	Set<String> getNodeMetadataPaths();

	/**
	 * Get a set of user metadata paths this policy applies to.
	 * 
	 * @return set of user metadata paths
	 * @since 1.1
	 */
	Set<String> getUserMetadataPaths();

}
