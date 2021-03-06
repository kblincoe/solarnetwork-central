/* ==================================================================
 * NodeInstructionDao.java - Sep 29, 2011 6:47:11 PM
 * 
 * Copyright 2007-2011 SolarNetwork.net Dev Team
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

package net.solarnetwork.central.instructor.dao;

import net.solarnetwork.central.dao.FilterableDao;
import net.solarnetwork.central.dao.GenericDao;
import net.solarnetwork.central.domain.EntityMatch;
import net.solarnetwork.central.instructor.domain.InstructionFilter;
import net.solarnetwork.central.instructor.domain.NodeInstruction;
import org.joda.time.DateTime;

/**
 * DAO API for {@link NodeInstruction}.
 * 
 * @author matt
 * @version 1.1
 */
public interface NodeInstructionDao extends GenericDao<NodeInstruction, Long>,
		FilterableDao<EntityMatch, Long, InstructionFilter> {

	/**
	 * Purge instructions that have reached a final state and are older than a
	 * given date.
	 * 
	 * @param olderThanDate
	 *        The maximum date for which to purge completed instructions.
	 * @return The number of instructions deleted.
	 */
	long purgeCompletedInstructions(DateTime olderThanDate);

}
