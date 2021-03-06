/* ==================================================================
 * TextArrayTypeHandler.java - Nov 8, 2014 11:24:54 AM
 * 
 * Copyright 2007-2014 SolarNetwork.net Dev Team
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

package net.solarnetwork.central.dao.mybatis.type;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * Text array type hanlder.
 * 
 * @author matt
 * @version 1.0
 */
public class TextArrayTypeHandler implements TypeHandler<String[]> {

	private String elementJdbcType;

	/**
	 * Default constructor.
	 */
	public TextArrayTypeHandler() {
		super();
		elementJdbcType = "text";
	}

	@Override
	public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType)
			throws SQLException {
		if ( parameter == null ) {
			ps.setNull(i, Types.ARRAY);
		} else {
			Connection conn = ps.getConnection();
			Array loc = conn.createArrayOf(elementJdbcType, parameter);
			ps.setArray(i, loc);
		}
	}

	@Override
	public String[] getResult(ResultSet rs, String columnName) throws SQLException {
		Array result = rs.getArray(columnName);
		return (result == null ? null : (String[]) result.getArray());
	}

	@Override
	public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
		Array result = rs.getArray(columnIndex);
		return (result == null ? null : (String[]) result.getArray());
	}

	@Override
	public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
		Array result = cs.getArray(columnIndex);
		return (result == null ? null : (String[]) result.getArray());
	}

	public String getElementJdbcType() {
		return elementJdbcType;
	}

	public void setElementJdbcType(String elementJdbcType) {
		this.elementJdbcType = elementJdbcType;
	}

}
