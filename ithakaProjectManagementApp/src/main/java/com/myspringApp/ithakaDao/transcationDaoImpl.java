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
import com.myspringApp.ithakaModel.Transaction;

public class transcationDaoImpl implements transcationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Transaction> listActivityByID(String setupNo) {
		
		//String sql = "select * from transaction where setupNo = ?";
		
		/*
		 * String sql =
		 * "select transaction.setupNo, transaction.userId, users.userName, inventory.streamName, "
		 * +
		 * "transaction.activity, transaction.activity_status, transaction.activity_comment from "
		 * +
		 * "transaction JOIN inventory on transaction.setupNo = inventory.setupNo JOIN users on"
		 * +
		 * " transaction.userId = users.userId where transaction.setupNo = ?  group by transaction.activity"
		 * ;
		 */
		
		String sql = "select transaction.setupNo, transaction.userId, users.userName, inventory.streamName, inventory.dueDate, "
				+ "transaction.activity, transaction.activity_status, transaction.activity_comment, transaction.activity_start_date, transaction.activity_end_date from "
				+ "transaction JOIN inventory on transaction.setupNo = inventory.setupNo JOIN users on"
				+ " transaction.userId = users.userId where transaction.setupNo = ?  group by transaction.activity";
		
		List<Transaction> listTransaction = new ArrayList<Transaction>();
		
		listTransaction = jdbcTemplate.query(sql, new Object[] {setupNo}, new ResultSetExtractor<List<Transaction>>() {

			public List<Transaction> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Transaction> list = new ArrayList<Transaction>();
					while(rs.next()) {
					Transaction transaction = new Transaction();
					transaction.setSetupNo(rs.getString(1));
					transaction.setUserId(rs.getString(2));
					transaction.setUserName(rs.getString(3));
					transaction.setStreamName(rs.getString(4));
					transaction.setDueDate(rs.getDate(5));
					transaction.setActivity(rs.getString(6));
					transaction.setActivity_status(rs.getString(7));
					transaction.setActivity_comment(rs.getString(8));
					transaction.setActivity_start_date(rs.getDate(9));
					transaction.setActivity_end_date(rs.getDate(10));
					
					list.add(transaction);
							/*
							 * System.out.println("listActivityByID(String setupNo)" +
							 * transaction.getSetupNo() + " " + transaction.getStreamName() + " " +
							 * transaction.getUserName());
							 */
					}
				
				return list;
			}
		});
		
		
		return listTransaction;
	}//end of method

	public void loggRevisedTaskInDB(Transaction transaction) {
		String sql = "insert into transaction(setupNo, userId, activity, activity_start_date, activity_comment, activity_status)"
				+ " values (?, ?, ?, ?, CONCAT('\n\n[', now(), '] ', ?, ' '), ?)";
		
		jdbcTemplate.update(sql, new Object[] {
				transaction.getSetupNo(),
				transaction.getUserId(),
				transaction.getActivity(),
				transaction.getActivity_start_date(),
				transaction.getActivity_comment(),
				transaction.getActivity_status()
		});
		
	}


	
	public void loggTaskInDB(Transaction transaction) {
		
		
		String sql = "insert into transaction(setupNo, userId, revised_stream, activity, activity_start_date, activity_comment, activity_status)"
				+ " values (?, ?, ?, ?, ?, CONCAT('[', now(), '] ', ?, ' '), ?)";
		
		jdbcTemplate.update(sql, new Object[] {
				transaction.getSetupNo(),
				transaction.getUserId(),
				transaction.getRevised_stream(),
				transaction.getActivity(),
				transaction.getActivity_start_date(),
				transaction.getActivity_comment(),
				transaction.getActivity_status()
		});
		
	
	}
	
	public List<Transaction> getTaskFromDB(Transaction transcation) {
		String sql = "select * from transaction where (setupNo = ? and userId = ?) and activity like ?";
		
		List<Transaction> existingActivityTransaction = new ArrayList<Transaction>();
		existingActivityTransaction = jdbcTemplate.query(sql, new Object[] {transcation.getSetupNo(), transcation.getUserId(), transcation.getActivity()},
				new ResultSetExtractor<List<Transaction>>() {

					public List<Transaction> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<Transaction> listTransaction = new ArrayList<Transaction>();
							while(rs.next()) {
								Transaction actTransaction = new Transaction();
								
								actTransaction.setSetupNo(rs.getString(1));
								actTransaction.setUserId(rs.getString(2));
								actTransaction.setRevised_stream(rs.getString(3));
								actTransaction.setActivity(rs.getString(4));
								actTransaction.setActivity_start_date(rs.getDate(5));
								actTransaction.setActivity_end_date(rs.getDate(6));
								actTransaction.setActivity_comment(rs.getString(7));
								actTransaction.setActivity_elapsed_time(rs.getInt(8));
								actTransaction.setActivity_status(rs.getString(9));
								listTransaction.add(actTransaction);
							}
						
						return listTransaction;
					}
			
			
		}
				);
		
		return existingActivityTransaction;
	}

	public List<Transaction> listActivityByUid(String UID) {
		/*
		 * String sql =
		 * "select transaction.setupNo, inventory.streamName, transaction.activity, users.userName, transaction.activity_start_date,"
		 * +
		 * "transaction.activity_end_date, transaction.activity_status, transaction.activity_comment from transaction JOIN "
		 * +
		 * "users on transaction.userId = users.userId JOIN inventory on transaction.setupNo = inventory.setupNo where "
		 * + "transaction.userId = ? group by transaction.activity";
		 */
		
		/*
		 * String sql =
		 * "select transaction.setupNo, inventory.streamName, transaction.activity, users.userName, transaction.activity_start_date, "
		 * +
		 * "transaction.activity_end_date, transaction.activity_status, transaction.activity_comment from transaction JOIN "
		 * +
		 * "users on transaction.userId = users.userId JOIN inventory on transaction.setupNo = inventory.setupNo where "
		 * +
		 * "((transaction.userId = ? and transaction.activity NOT LIKE '%reviewPoints%') and (inventory.streamStatus NOT LIKE '%DELIVERED%' or inventory.streamStatus is NULL)) group by transaction.setupNo, transaction.activity"
		 * ;
		 */
		
		String sql = "select transaction.setupNo, inventory.streamName, transaction.activity, users.userName, transaction.activity_start_date, " +
				"transaction.activity_end_date, transaction.activity_status, transaction.activity_comment from transaction JOIN " + 
				"users on transaction.userId = users.userId JOIN inventory on transaction.setupNo = inventory.setupNo where " + 
				"((transaction.userId = ? and transaction.activity NOT LIKE '%reviewPoints%') and (inventory.streamStatus NOT LIKE '%DELIVERED%' or inventory.streamStatus is NULL))";
				
		
			// and transaction.activity_status = 'WIP'
		
			//System.out.println("listActivityByUid" + UID);
			//System.out.println("Testing");
			List<Transaction> listTransaction = new ArrayList<Transaction>();
		
			listTransaction = 
					jdbcTemplate.query(sql, new Object[] {UID}, new ResultSetExtractor<List<Transaction>>() {

			public List<Transaction> extractData(ResultSet rs) throws SQLException, DataAccessException {
					List<Transaction> list = new ArrayList<Transaction>();
					
					while(rs.next()) {
						Transaction transaction = new Transaction();
						transaction.setSetupNo(rs.getString(1));
						transaction.setStreamName(rs.getString(2));
						transaction.setActivity(rs.getString(3));
						transaction.setUserName(rs.getString(4));
						transaction.setActivity_start_date(rs.getDate(5));
						transaction.setActivity_end_date(rs.getDate(6));
						transaction.setActivity_status(rs.getString(7));
						transaction.setActivity_comment(rs.getString(8));
						
						list.add(transaction);
						
						
					}
				
				return list;
			}
		});
		
		return listTransaction;
	}//end of method;

	public void updateActivityStatus(String Status, String SetupNo, String activity) {
		if(Status.equals("pause")) {
			String sql = "update transaction set activity_status = 'pause' where (setupNo = ? and activity = ?)";
			jdbcTemplate.update(sql, new Object[] {SetupNo, activity});
			
		}else if(Status.equals("resume")) {
			String sql = "update transaction set activity_status = 'WIP' where (setupNo = ? and activity = ?)";
			jdbcTemplate.update(sql, new Object[] {SetupNo, activity});
			
		}else if(Status.equals("hold")) {
			String sql = "update transaction set activity_status = 'hold'  where (setupNo = ? and activity = ?)";
			jdbcTemplate.update(sql, new Object[] {SetupNo, activity});
			
		}else if(Status.equals("moreDetails")) {
			//moreDetails
		}else {
			//complete
		}
		
	}

	public void updateActivityStatusComment(String Status, String SetupNo, String activity, String Comment, String userId) {
		if(Status.equals("complete"))
		{
			//String sql = "update transaction set activity_status = ?, activity_comment = CONCAT('[', now(), '] ', ?,'.'), activity_end_date = now() where ((userId = ? and setupNo = ?) and activity = ?)";
			String sql = "update transaction set activity_status = ?, activity_comment = CONCAT(activity_comment,' ', '\n\n[', now(), '] ', ?,'.'), activity_end_date = now(), activity_elapsed_time = (datediff(now(),activity_start_date) + 1) where ((userId = ? and setupNo = ?) and activity = ?)";
			jdbcTemplate.update(sql, new Object[] {Status, Comment, userId, SetupNo, activity});
		}else {
			String sql = "update transaction set activity_status = 'WIP', activity_comment = CONCAT(activity_comment, ' ', '\n\n[', now(), '] ', ?,'.') where ((userId = ? and setupNo = ?) and (activity = ? and activity_status NOT LIKE '%complete%'))";
			jdbcTemplate.update(sql, new Object[] {Comment, userId, SetupNo, activity});
		}
		
		
		
	}

	public List<Transaction> runningActivity(String setupNo) {
		String sql = "select transaction.setupNo, inventory.streamName, users.userName, transaction.activity, "
				+ "transaction.activity_status, transaction.activity_comment from transaction JOIN inventory ON transaction.setupNo = inventory.setupNo "
				+ "JOIN users on transaction.userId = users.userID where "
				+ "(transaction.setupNo = ? and (transaction.activity_status != 'complete'))";
		
		List<Transaction> listWIPActivity = new ArrayList<Transaction>();
		
		listWIPActivity = jdbcTemplate.query(sql, new Object[] {setupNo}, new ResultSetExtractor<List<Transaction>>() {

			public List<Transaction> extractData(ResultSet rs) throws SQLException, DataAccessException {
					
					List<Transaction> list = new ArrayList<Transaction>();
					
						while(rs.next()) {
							Transaction wipActivity = new Transaction();
							wipActivity.setSetupNo(rs.getString(1));
							wipActivity.setStreamName(rs.getString(2));
							wipActivity.setUserName(rs.getString(3));
							wipActivity.setActivity(rs.getString(4));
							wipActivity.setActivity_status(rs.getString(5));
							wipActivity.setActivity_comment(rs.getString(6));
							list.add(wipActivity);
						}

				return list;
			}
		});
		
		
		return listWIPActivity;
	}//end of method

	public int calculateElapsedDays(String setupNo) {
		
		String sql = "select datediff(now(), min(activity_start_date)) from transaction where setupNo = ?";
		
		int elapsedDays = jdbcTemplate.queryForObject(sql, new Object[] {setupNo}, Integer.class);
		
		
		return elapsedDays;
	}//end of method

	public List<Transaction> finishedActivity(String userId) {
		/*
		 * String sql =
		 * "select transaction.setupNo, inventory.streamName, transaction.activity, transaction.activity_elapsed_time from  "
		 * +
		 * "transaction JOIN inventory ON transaction.setupNo = inventory.setupNo where (((transaction.userId = ?) and "
		 * +
		 * "(transaction.activity_status like '%complete%')) and inventory.streamStatus NOT LIKE '%DELIVERED%')"
		 * ;
		 */
		
		String sql = "select transaction.setupNo, inventory.streamName, transaction.activity, transaction.activity_elapsed_time from  "
				+ "transaction JOIN inventory ON transaction.setupNo = inventory.setupNo where (((transaction.userId = ?) and "
				+ "(transaction.activity_status like '%complete%')) and (inventory.userId is not null) and (inventory.userId != ?) and revised_stream is null)";
				
		
		List<Transaction> finishedActivity = new ArrayList<Transaction>();
		finishedActivity = jdbcTemplate.query(sql, new Object[] {userId, userId}, new ResultSetExtractor<List<Transaction>>() {
			
			List<Transaction> list = new ArrayList<Transaction>();
			
			public List<Transaction> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while(rs.next()) {
					Transaction transaction = new Transaction();
					transaction.setSetupNo(rs.getString(1));
					transaction.setStreamName(rs.getString(2));
					transaction.setActivity(rs.getString(3));
					transaction.setActivity_elapsed_time(rs.getInt(4));
					list.add(transaction);
				}
				return list;
			}
			
			
		});
		
		
		return finishedActivity;
	}

	public String searchStream(String setupNo) {
		String sql = "select streamName from inventory where setupNo = ?";
		List<String> streamName =  jdbcTemplate.query(sql, new Object[] {setupNo}, new RowMapper<String>() {

			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
			
		});
		
		if(streamName.isEmpty()) {
			return null;
		}else {
			
			return "Present In DB";
		}
	}

	
	public List<Transaction> listRevisedActivityByUid(String UID) {
		
		/*
		 * String sql =
		 * "select transaction.setupNo, inventory.streamName, transaction.activity, users.userName, transaction.activity_start_date, "
		 * +
		 * "transaction.activity_end_date, transaction.activity_status, transaction.activity_comment from transaction JOIN "
		 * +
		 * "users on transaction.userId = users.userId JOIN inventory on transaction.setupNo = inventory.setupNo where "
		 * +
		 * "(transaction.userId = ? and transaction.activity LIKE '%reviewPoints%' and transaction.activity_status NOT LIKE '%complete%') group by transaction.activity"
		 * ;
		 */
		
		String sql = "select transaction.setupNo, transaction.activity, users.userName, transaction.activity_start_date, " +
				"transaction.activity_end_date, transaction.activity_status, transaction.activity_comment from transaction JOIN " + 
				"users on transaction.userId = users.userId where " + 
				"(transaction.userId = ? and transaction.activity LIKE '%reviewPoints%' and transaction.activity_status NOT LIKE '%complete%') group by transaction.activity";
		
		
			// and transaction.activity_status = 'WIP'
		
			//System.out.println("listActivityByUid" + UID);
			//System.out.println("Testing");
			List<Transaction> listTransaction = new ArrayList<Transaction>();
		
			listTransaction = 
					jdbcTemplate.query(sql, new Object[] {UID}, new ResultSetExtractor<List<Transaction>>() {

			public List<Transaction> extractData(ResultSet rs) throws SQLException, DataAccessException {
					List<Transaction> list = new ArrayList<Transaction>();
					
					while(rs.next()) {
						Transaction transaction = new Transaction();
						
						transaction.setSetupNo(rs.getString(1));
						//transaction.setStreamName(rs.getString(2));
						transaction.setActivity(rs.getString(2));
						transaction.setUserName(rs.getString(3));
						transaction.setActivity_start_date(rs.getDate(4));
						transaction.setActivity_end_date(rs.getDate(5));
						transaction.setActivity_status(rs.getString(6));
						transaction.setActivity_comment(rs.getString(7));
						
						list.add(transaction);
						
						
					}
				
				return list;
			}
		});
		
		return listTransaction;

	}

	public List<Transaction> adminReport() {
		String sql = "select transaction.setupNo, transaction.activity, transaction.activity_start_date, transaction.activity_elapsed_time, transaction.activity_status, inventory.streamName, users.userName, transaction.activity_comment from transaction JOIN inventory ON transaction.setupNo = inventory.setupNo JOIN users ON transaction.userId = users.userId where transaction.activity_status NOT LIKE '%complete%'";
		
		List<Transaction> wipActivites = new ArrayList<Transaction>();
		wipActivites = jdbcTemplate.query(sql, new ResultSetExtractor<List<Transaction>>() {
			List<Transaction> list = new ArrayList<Transaction>();
			
			public List<Transaction> extractData(ResultSet rs) throws SQLException, DataAccessException {
					
					while(rs.next()) {
						Transaction activity = new Transaction();
						
						activity.setSetupNo(rs.getString(1));
						activity.setActivity(rs.getString(2));
						activity.setActivity_start_date(rs.getDate(3));
						activity.setActivity_elapsed_time(rs.getInt(4));
						activity.setActivity_status(rs.getString(5));
						activity.setStreamName(rs.getString(6));
						activity.setUserName(rs.getString(7));
						activity.setActivity_comment(rs.getString(8));
						list.add(activity);
					}
				
				return list;
			}
			
			
		});
		
		
		return wipActivites;
	}

	public List<Transaction> adminTextSearchReport(String text) {
		  String sql = "select transaction.setupNo, transaction.activity, "
		  		+ "transaction.activity_status, transaction.activity_elapsed_time, "
		  		+ "transaction.activity_comment, inventory.streamName, users.userName, "
		  		+ "transaction.activity_start_date from transaction "
		  		+ "JOIN inventory ON transaction.setupNo = inventory.setupNo "
		  		+ "JOIN users ON transaction.userID = users.userID"
		  + " where (transaction.setupNo LIKE '%" + text + "%')"
		  + " OR (transaction.activity LIKE '%" + text + "%')"
		  + " OR (transaction.activity_status LIKE '%" + text + "%')"
		  + " OR (transaction.activity_elapsed_time LIKE '%" + text + "%')"
		  + " OR (transaction.activity_comment LIKE '%" + text + "%')"
		  + " OR (inventory.streamName LIKE '%" + text + "%')"
		  + " OR (users.userName LIKE '%" + text + "%')"
		  + " OR (transaction.activity_start_date LIKE '%" + text + "%')";
		
		  List<Transaction> searchResult = new ArrayList<Transaction>();
		
		searchResult = jdbcTemplate.query(sql, new ResultSetExtractor<List<Transaction>>() {
			List<Transaction> list = new ArrayList<Transaction>();
			
			public List<Transaction> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
					
					while(rs.next()) {
						Transaction activity = new Transaction();
						activity.setSetupNo(rs.getString(1));
						activity.setActivity(rs.getString(2));
						
						//System.out.println("adminTextSearchReport " + activity.getActivity());
						
						activity.setActivity_status(rs.getString(3));
						activity.setActivity_elapsed_time(rs.getInt(4));
						activity.setActivity_comment(rs.getString(5));
						activity.setStreamName(rs.getString(6));
						activity.setUserName(rs.getString(7));
						activity.setActivity_start_date(rs.getDate(8));
						list.add(activity);
					}
				
				return list;
			}
			
			
		});
		
		return searchResult;

}

	public List<Transaction> listRevisedNotListedSetupActivityByUid(String UID) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Transaction> finishedRevisedActivity(String userId) {

		String sql = "select * from transaction where revised_stream is not null and userId = ? and activity_status like '%complete%'";
		
		List<Transaction> wipActivites = new ArrayList<Transaction>();
		wipActivites = jdbcTemplate.query(sql, new Object[] {userId}, new ResultSetExtractor<List<Transaction>>() {
		
			List<Transaction> list = new ArrayList<Transaction>();
			
			public List<Transaction> extractData(ResultSet rs) throws SQLException, DataAccessException {
					
					while(rs.next()) {
						Transaction activity = new Transaction();
						
						activity.setSetupNo(rs.getString(1));
						activity.setUserId(rs.getString(2));
						activity.setRevised_stream(rs.getString(3));
						
						activity.setActivity(rs.getString(4));
						activity.setActivity_start_date(rs.getDate(5));
						activity.setActivity_end_date(rs.getDate(6));
						activity.setActivity_comment(rs.getString(7));
						
						activity.setActivity_elapsed_time(rs.getInt(8));
						
						activity.setActivity_status(rs.getString(9));
						
						list.add(activity);
					}
				
				return list;
			}
			
			
		});
		
		
		return wipActivites;
		
		
	}


}
