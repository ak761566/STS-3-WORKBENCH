package com.myspringApp.ithakaDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.myspringApp.ithakaModel.Inventory;

public class projectDaoImpl implements projectDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addNewProject(Inventory inventory) {
	  String sql = "insert into inventory(setupNo, streamName, StreamType, Complexity, batchCount, kickOffDate, dueDate, userId)"
	  		+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
	   jdbcTemplate.update(sql, new Object[] {
			   inventory.getSetupNo(),
			   inventory.getStreamName(),
			   inventory.getStreamType(),
			   inventory.getComplexity(),
			   inventory.getBatchCount(),
			   inventory.getKickOffDate(),
			   inventory.getDueDate(),
			   inventory.getUserId()
			   });
		
	}

	public Inventory getProjectById(String setupNo) {
		String sql = "select * from inventory wdhere setupNo=?";
		Inventory inventory = jdbcTemplate.queryForObject(sql, new Object[] {setupNo}, new RowMapper<Inventory>(){

			public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
				Inventory inventory = new Inventory();
				
				inventory.setSetupNo(rs.getString(1));
				inventory.setStreamName(rs.getString(2));
				inventory.setStreamType(rs.getString(3));
				inventory.setComplexity(rs.getString(4));
				inventory.setBatchCount(rs.getInt(5));
				inventory.setKickOffDate(rs.getDate(6));
				inventory.setDueDate(rs.getDate(7));
				
				return inventory;
			} } );
		
		return inventory;
	}

	public List<Inventory> listProjects() {
		//String sql = "select * from inventory";
		String sql = "select * from inventory where streamStatus is null";
		
		List<Inventory> listInventory = new ArrayList<Inventory>();
		
		listInventory = jdbcTemplate.query(sql, new ResultSetExtractor<List<Inventory>>() {

			public List<Inventory> extractData(ResultSet rs) throws SQLException, DataAccessException {
					List<Inventory> list = new ArrayList<Inventory>();
					
					while(rs.next())
						{
							Inventory inventory = new Inventory();
							inventory.setSetupNo(rs.getString(1));
							inventory.setStreamName(rs.getString(2));
							inventory.setComplexity(rs.getString(3));
							inventory.setStreamType(rs.getString(4));
							inventory.setBatchCount(rs.getInt(5));
							inventory.setKickOffDate(rs.getDate(6));
							inventory.setDueDate(rs.getDate(7));
							
							list.add(inventory);
						}
								
				
				return list;
			}
			
		}
		);
		
		return listInventory;
	}//end of method
	
	
	public Inventory markStreamAsDeliver(String userId, String setupNo, String streamName, int elapsedDays) {
		Inventory readyStream = new Inventory();
		
		/*
		 * String sql =
		 * "update inventory set userId = ?, deliveryDate = now(), streamStatus = 'DELIVERED', "
		 * + "elapsedDays = ? where (setupNo = ? and streamName = ?)";
		 */
		String sql = "update inventory set userId = ?, deliveryDate = now(), streamStatus = 'DELIVERED', "
				+ "elapsedDays = ? where (setupNo = ?)";
		
		/*
		 * jdbcTemplate.update(sql, new Object[] {userId, elapsedDays, setupNo,
		 * streamName});
		 */
		jdbcTemplate.update(sql, new Object[] {userId, elapsedDays, setupNo});
			//System.out.println("markStreamAsDeliver-1 " + userId + " " + setupNo + " " + streamName + " " + elapsedDays);
		String sql1 = "select setupNo, streamName, elapsedDays, deliveryDate, streamStatus from inventory where (setupNo = ? and streamStatus LIKE '%DELIVERED%')";
				
		readyStream = jdbcTemplate.queryForObject(sql1, new Object[] {setupNo}, new RowMapper<Inventory>() {

			public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
				Inventory inventory = new Inventory();
				
				inventory.setSetupNo(rs.getString(1));
				inventory.setStreamName(rs.getString(2));
				inventory.setElapsedDays(rs.getInt(3));
				inventory.setDeliveryDate(rs.getDate(4));
				inventory.setStreamStatus(rs.getString(5));
				
				//System.out.println("markStreamAsDeliver " + " inventory.setSetupNo " + inventory.getSetupNo());
					
				return inventory;
			}
		});
		
		System.out.println("markStreamAsDeliver-2 " + setupNo);
	
		return readyStream;
	}//end of method

	public List<Inventory> completedStreams(String userId) {
		String sql = "select setupNo, streamName, batchCount, kickOffDate, deliveryDate, streamType from inventory where "
				+ "(streamStatus like '%DELIVERED%' and userId= ?)";
		List<Inventory> streams = new ArrayList<Inventory>();
		
		streams = jdbcTemplate.query(sql, new Object[] {userId}, new ResultSetExtractor<List<Inventory>>() {
			List<Inventory> list = new ArrayList<Inventory>();
			
			public List<Inventory> extractData(ResultSet rs) throws SQLException, DataAccessException {
					while(rs.next())
					{
						Inventory inventory = new Inventory();
						
						inventory.setSetupNo(rs.getString(1));
						inventory.setStreamName(rs.getString(2));
						inventory.setBatchCount(rs.getInt(3));
						inventory.setKickOffDate(rs.getDate(4));
						inventory.setDeliveryDate(rs.getDate(5));
						inventory.setStreamType(rs.getString(6));
						list.add(inventory);
					}
				
				return list;
			}
			
			
		});

		return streams;
	}//end of method
	
	public List<Inventory> inCompleteStreams(){
		//In the following SQL statement elapsed day are calculated by excluding weekends from the total days of month
		
		/*
		 * String sql =
		 * "select setupNo, streamName, streamType, batchCount, kickOffDate, deliveryDate, streamStatus, "
		 * +
		 * "( dateDiff(last_day(now()), date_add(date_add(last_day(now()), interval 1 day), interval - 1 month)) - round(floor(dateDiff(last_day(now()), date_add(date_add(last_day(now()), interval 1 day), interval - 1 month)))/7)*2) "
		 * + "as elapsedDays from inventory where  (streamStatus is null)";
		 */
		
		/*
		 * String sql =
		 * "select setupNo, streamName, streamType, batchCount, kickOffDate, deliveryDate, streamStatus, "
		 * +
		 * "dateDiff(curdate(), date_add(date_add(last_day(now()),interval 1 day), interval -1 month)) as elapsedDays from inventory where (streamStatus is null)"
		 * ;
		 */
		
		String sql = "select setupNo, streamName, streamType, batchCount, kickOffDate, deliveryDate, streamStatus, "
					+ "dateDiff(curdate(), kickOffDate) as elapsedDays, "
					+ "CASE "
					+ "WHEN ( (year(kickOffDate) = year(curdate())) AND (month(kickOffDate) = month(curdate())) ) "
					+ "THEN dateDiff(curdate(), kickOffDate) "
					+ "ELSE "
					+ "dateDiff(curdate(), date_add(date_add(last_day(now()),interval 1 day), interval -1 month)) END AS monthlyElapsedDays from inventory where (streamStatus is null)";		
		List<Inventory> streams = new ArrayList<Inventory>();
		
		streams = jdbcTemplate.query(sql, new ResultSetExtractor<List<Inventory>>(){

			public List<Inventory> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Inventory> listIncompleteStream = new ArrayList<Inventory>();
					
					while(rs.next()) {
						Inventory inventory = new Inventory();
							inventory.setSetupNo(rs.getString(1));
							inventory.setStreamName(rs.getString(2));
							inventory.setStreamType(rs.getString(3));
							inventory.setBatchCount(rs.getInt(4));
							inventory.setKickOffDate(rs.getDate(5));
							inventory.setDeliveryDate(rs.getDate(6));
							inventory.setStreamStatus(rs.getString(7));
							inventory.setElapsedDays(rs.getInt(8));
							inventory.setMonthlyElapsedDays(rs.getInt(9));
							listIncompleteStream.add(inventory);
					}
					
				return listIncompleteStream;
			}
			
		});
	
		return streams;
	}
	
	public List<Inventory> completedStreams() {
		
		  String sql = "select setupNo, streamName, streamType, batchCount, kickOffDate, deliveryDate, streamStatus, elapsedDays "
		  + "from inventory where (streamStatus like '%DELIVERED%')";
		 
		/*
		 * String sql =
		 * "select setupNo, streamName, streamType, batchCount, kickOffDate, deliveryDate, streamStatus, elapsedDays, "
		 * + "CASE " +
		 * "when (year(kickOffDate) <  year(deliveryDate)) and (month(deliveryDate) = month(curdate())) and (year(deliveryDate) = year(curdate())) "
		 * +
		 * "then datediff(adddate(adddate(last_day(now()), interval 1 day), interval - 1 month), deliveryDate) "
		 * +
		 * "when (year(kickOffDate) <  year(deliveryDate)) and ((year(deliveryDate) < year(curdate())) "
		 * + "then 0 " +
		 * "when (month(kickOffDate) = month(deliveryDate)) AND (year(kickOffDate) = year(deliveryDate)) "
		 * + "then datediff(deliveryDate, kickOffDate) " +
		 * "when (month(deliveryDate) = month(curdate())) AND (year(kickOffDate) = year(deliveryDate)) "
		 * +
		 * "then (datediff(adddate(adddate(last_day(now()), interval 1 day), interval - 1 month), deliveryDate)) "
		 * +
		 * "when (month(deliveryDate) < month(curdate())) AND (year(deliveryDate) =  year(curdate())) "
		 * + "then 0 " +
		 * "else datediff(deliveryDate, adddate(adddate(last_day(now()), interval 1 day), interval - 1 month)) "
		 * + "end as monthlyElapsedDays " +
		 * "from inventory where (streamStatus like '%DELIVERED%')";
		 */
		 
		/*
		 * String sql =
		 * "select setupNo, streamName, streamType, batchCount, kickOffDate, deliveryDate, streamStatus, elapsedDays, "
		 * +"CASE " +
		 * "when (year(kickOffDate) <  year(deliveryDate)) and (year(deliveryDate) = year(curdate())) and (month(deliveryDate) = month(curdate())) "
		 * +
		 * "then datediff(deliveryDate, adddate(adddate(last_day(now()), interval 1 day), interval - 1 month)) "
		 * +
		 * "when (year(kickOffDate) =  year(deliveryDate)) and (month(kickOffDate) =  month(deliveryDate)) and (month(curdate()) =  month(deliveryDate)) "
		 * + "then datediff(deliveryDate, kickOffDate) " +
		 * "when (year(kickOffDate) =  year(deliveryDate)) and (month(kickOffDate) <  month(deliveryDate)) and (month(curdate()) =  month(deliveryDate)) "
		 * +
		 * "then datediff(deliveryDate, adddate(adddate(last_day(now()), interval 1 day), interval - 1 month)) "
		 * +
		 * "when (year(kickOffDate) =  year(deliveryDate)) and (month(deliveryDate) < month(curdate())) "
		 * + "then 0 " + "else 0 " + "end as monthlyElapsedDays " +
		 * "from inventory where (streamStatus like '%DELIVERED%')";
		 */

		
		List<Inventory> streams = new ArrayList<Inventory>();
		
		streams = jdbcTemplate.query(sql, new ResultSetExtractor<List<Inventory>>() {
			List<Inventory> list = new ArrayList<Inventory>();
			
			public List<Inventory> extractData(ResultSet rs) throws SQLException, DataAccessException {
					while(rs.next())
					{
						Inventory inventory = new Inventory();
						
						inventory.setSetupNo(rs.getString(1));
						inventory.setStreamName(rs.getString(2));
						inventory.setStreamType(rs.getString(3));
						inventory.setBatchCount(rs.getInt(4));
						inventory.setKickOffDate(rs.getDate(5));
						inventory.setDeliveryDate(rs.getDate(6));
						inventory.setStreamStatus(rs.getString(7));
						inventory.setElapsedDays(rs.getInt(8));
						//inventory.setMonthlyElapsedDays(rs.getInt(9));
						list.add(inventory);
					}
				
				return list;
			}
			
			
		});

		return streams;
	}//end of method

	public List<Inventory> CurrentMonthCompletedStreams() {
		/*
		 * String sql =
		 * "select setupNo, streamName, streamType, batchCount, kickOffDate, deliveryDate, streamStatus, elapsedDays from inventory"
		 * + " where ( (streamStatus like '%DELIVERED%') " +
		 * "and (deliveryDate BETWEEN date_add(date_add(last_day(now()),interval 1 day), interval - 1 month) "
		 * + "and last_day(now())) )";
		 */
		
/*		String sql = "select setupNo, streamName, streamType, batchCount, kickOffDate, deliveryDate, streamStatus, elapsedDays, "
				+"CASE "
				+ "when (year(kickOffDate) <  year(deliveryDate)) and (year(deliveryDate) = year(curdate())) and (month(deliveryDate) = month(curdate())) "
				+ "then datediff(deliveryDate, adddate(adddate(last_day(now()), interval 1 day), interval - 1 month)) "
				+ "when (year(kickOffDate) =  year(deliveryDate)) and (month(kickOffDate) =  month(deliveryDate)) and (month(curdate()) =  month(deliveryDate)) "
				+ "then datediff(deliveryDate, kickOffDate) "
				+ "when (year(kickOffDate) =  year(deliveryDate)) and (month(kickOffDate) <  month(deliveryDate)) and (month(curdate()) =  month(deliveryDate)) "
				+ "then datediff(deliveryDate, adddate(adddate(last_day(now()), interval 1 day), interval - 1 month)) "
				+ "when (year(kickOffDate) =  year(deliveryDate)) and (month(deliveryDate) < month(curdate())) "
				+ "then 0 "
				+ "else 0 "
				+ "end as monthlyElapsedDays "
				+ "from inventory where (streamStatus like '%DELIVERED%') and (deliveryDate BETWEEN date_add(date_add(last_day(now()),interval 1 day), interval - 1 month) and last_day(now()))";*/
		
		
		
		String sql = "select setupNo, streamName, streamType, batchCount, kickOffDate, deliveryDate, streamStatus, elapsedDays, "  
				+ "CASE " 
				+ "WHEN ( (year(deliveryDate) = year(curdate())) AND (month(deliveryDate) = month(curdate())) ) AND ( month(kickoffdate) < month(deliveryDate)  ) "
				+ "THEN datediff(deliveryDate, adddate(adddate(last_day(now()), interval 1 day), interval - 1 month)) " 
				+ "WHEN ( (year(deliveryDate) = year(curdate())) AND (month(deliveryDate) = month(curdate())) ) AND ( month(kickoffdate) = month(deliveryDate)  ) "
				+ "THEN datediff(deliveryDate, kickoffdate) " 
				+ "else 0 " 
				+ "end as monthlyElapsedDays " 
				+ "from inventory where (streamStatus like '%DELIVERED%') and (deliveryDate BETWEEN date_add(date_add(last_day(now()),interval 1 day), interval - 1 month) and last_day(now())) ";
		
		List<Inventory> streams = new ArrayList<Inventory>();
		
		streams = jdbcTemplate.query(sql, new ResultSetExtractor<List<Inventory>>() {
			List<Inventory> list = new ArrayList<Inventory>();
			
			public List<Inventory> extractData(ResultSet rs) throws SQLException, DataAccessException {
					while(rs.next())
					{
						Inventory inventory = new Inventory();
						
						inventory.setSetupNo(rs.getString(1));
						inventory.setStreamName(rs.getString(2));
						inventory.setStreamType(rs.getString(3));
						inventory.setBatchCount(rs.getInt(4));
						inventory.setKickOffDate(rs.getDate(5));
						inventory.setDeliveryDate(rs.getDate(6));
						inventory.setStreamStatus(rs.getString(7));
						inventory.setElapsedDays(rs.getInt(8));
						inventory.setMonthlyElapsedDays(rs.getInt(9));
						list.add(inventory);
					}
				
				return list;
			}
			
			
		});

		return streams;

	}//end

}
