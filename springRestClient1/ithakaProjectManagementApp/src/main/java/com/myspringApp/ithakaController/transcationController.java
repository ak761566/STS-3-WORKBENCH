package com.myspringApp.ithakaController;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspringApp.ithakaDao.projectDao;
import com.myspringApp.ithakaDao.transcationDao;
import com.myspringApp.ithakaDao.userDao;
import com.myspringApp.ithakaModel.Inventory;
import com.myspringApp.ithakaModel.Transaction;
import com.myspringApp.ithakaModel.Users;

@Controller
public class transcationController {

	@Autowired
	private transcationDao transcationdao;
	@Autowired
	private userDao userdao;
	@Autowired
	private projectDao projectdao;

	@RequestMapping(value = "/user/task/{setupNo}", method = RequestMethod.GET)
	public ModelAndView taskAssignPage(@PathVariable String setupNo) {
		ModelAndView model = new ModelAndView("taskAssignPage");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserId = auth.getName();

		List<Transaction> listTransaction = transcationdao.listActivityByID(setupNo);
		// System.out.println("/user/task/{setupNo}" + listTransaction);

		// Users user = userdao.getUserByName(loggedUserName);
		Users user = userdao.getUserById(loggedUserId);

		model.addObject("loggedUserName", user.getUserName());
		model.addObject("setupNo", setupNo);
		model.addObject("userInfo", user);
		model.addObject("listTransaction", listTransaction);

		return model;
	}

	@RequestMapping(value = "/user/task/assign", method = RequestMethod.POST)
	public ModelAndView AssignTask(@ModelAttribute("transaction") Transaction transcation) {
		ModelAndView model = new ModelAndView("taskAssignPage");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserId = auth.getName();
		Users user = userdao.getUserById(loggedUserId);
		List<Transaction> checkActivityList = transcationdao.getTaskFromDB(transcation);

		transcation.setActivity_start_date(new Date());
		transcation.setUserId(user.getUserId());
		transcation.setActivity_status("WIP");

		if (checkActivityList.isEmpty()) {
			transcationdao.loggTaskInDB(transcation);
		} else {


			transcationdao.updateActivityStatusComment(transcation.getActivity_status(), transcation.getSetupNo(),
					transcation.getActivity(), transcation.getActivity_comment(), transcation.getUserId());
			model.addObject("activityExist", "Activity already running or completed by you..contact admin.");
		}
		model.addObject("loggedUserName", user.getUserName());

		List<Transaction> listTransaction = transcationdao.listActivityByID(transcation.getSetupNo());
		model.addObject("listTransaction", listTransaction);
		model.addObject("setupNo", transcation.getSetupNo());
		model.addObject("userInfo", user);

		return model;
	}

	@RequestMapping(value = "/user/updateActivityStatus/pause/{setupNo}/{activity}")
	public ModelAndView ActivityStatusPause(@PathVariable String setupNo,
			@PathVariable(value = "activity") String activity) {
		ModelAndView model = new ModelAndView("userDashboard");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = auth.getName();
		Users user = userdao.getUserById(loggedUserName);
		transcationdao.updateActivityStatus("pause", setupNo, activity);

		List<Transaction> runningStreams = transcationdao.listActivityByUid(loggedUserName);
		List<Transaction> runningRevisedStreams = transcationdao.listRevisedActivityByUid(loggedUserName);
		
		model.addObject("runningRevisedStreams", runningRevisedStreams);
		model.addObject("loggedUserName", user.getUserName());
		model.addObject("runningStreams", runningStreams);

		return model;
	}

	@RequestMapping(value = "/user/updateActivityStatus/resume/{setupNo}/{activity}")
	public ModelAndView ActivityStatusResume(@PathVariable(value = "setupNo") String setupNo,
			@PathVariable(value = "activity") String activity) {
		ModelAndView model = new ModelAndView("userDashboard");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = auth.getName();
		Users user = userdao.getUserById(loggedUserName);
		transcationdao.updateActivityStatus("resume", setupNo, activity);

		List<Transaction> runningStreams = transcationdao.listActivityByUid(loggedUserName);
		List<Transaction> runningRevisedStreams = transcationdao.listRevisedActivityByUid(loggedUserName);
		model.addObject("runningRevisedStreams", runningRevisedStreams);

		model.addObject("loggedUserName", user.getUserName());
		model.addObject("runningStreams", runningStreams);
		return model;
	}

	@RequestMapping(value = "/user/updateActivityStatus/complete/{setupNo}/{activity}")
	public ModelAndView ActivityStatusComplete(@PathVariable(value = "setupNo") String setupNo,
			@PathVariable(value = "activity") String activity) {
		ModelAndView model = new ModelAndView("updateTaskStatus");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = auth.getName();
		Users user = userdao.getUserById(loggedUserName);
		// transcationdao.updateActivityStatus("resume", setupNo, activity);

		model.addObject("setupNo", setupNo);
		model.addObject("activity", activity);
		model.addObject("loggedUserName", user.getUserName());
		model.addObject("activity_status", "complete");
		return model;
	}

	@RequestMapping(value = "/updateActivityStatus/completeStatus", method = RequestMethod.POST)
	public ModelAndView completStatus(@ModelAttribute Transaction transaction) {
		ModelAndView model = new ModelAndView("userDashboard");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = auth.getName();
		Users user = userdao.getUserById(loggedUserName);

		if (transaction.getActivity_status().contains("PushedBack")) {
			transaction.setActivity_status("complete");
			// markStreamAsDeliver(String userId, String setupNo, String streamName, int
			// elapsedDays)
			projectdao.markStreamAsDeliver(transaction.getUserId(), transaction.getSetupNo(),
					transaction.getStreamName(), 1);
		}

		transcationdao.updateActivityStatusComment(transaction.getActivity_status(), transaction.getSetupNo(),
				transaction.getActivity(), transaction.getActivity_comment(), loggedUserName);

		List<Transaction> runningStreams = transcationdao.listActivityByUid(loggedUserName);
		List<Transaction> runningRevisedStreams = transcationdao.listRevisedActivityByUid(loggedUserName);
		model.addObject("runningRevisedStreams", runningRevisedStreams);
		model.addObject("loggedUserName", user.getUserName());
		model.addObject("runningStreams", runningStreams);
		return model;
	}

	@RequestMapping(value = "/user/updateActivityStatus/addIssue/{setupNo}/{activity}")
	public ModelAndView ActivityStatusAddIssue(@PathVariable(value = "setupNo") String setupNo,
			@PathVariable(value = "activity") String activity) {
		ModelAndView model = new ModelAndView("updateTaskStatus");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = auth.getName();
		Users user = userdao.getUserById(loggedUserName);
		// transcationdao.updateActivityStatus("resume", setupNo, activity);

		model.addObject("setupNo", setupNo);
		model.addObject("activity", activity);
		model.addObject("loggedUserName", user.getUserName());
		model.addObject("activity_status", "moreIssue");
		return model;
	}

	@RequestMapping(value = "/user/updateActivityStatus/hold/{setupNo}/{activity}")
	public ModelAndView ActivityStatusHold() {
		ModelAndView model = new ModelAndView("userDashboard");

		return model;
	}

	@RequestMapping(value = "/user/updateActivityStatus/moreDetails/{setupNo}/{activity}")
	public ModelAndView ActivityStatusMoreDetails(@PathVariable(value = "setupNo") String setupNo,
			@PathVariable(value = "activity") String activity) {
		ModelAndView model = new ModelAndView("streamDetails");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = auth.getName();
		Users user = userdao.getUserById(loggedUserName);

		List<Transaction> listStreamBySetupNo = transcationdao.listActivityByID(setupNo);

		model.addObject("setupNo", setupNo);
		model.addObject("activity", activity);
		model.addObject("loggedUserName", user.getUserName());
		model.addObject("listStreamBySetupNo", listStreamBySetupNo);
		model.addObject("activity_status", "moreIssue");
		return model;
	}

	@RequestMapping(value = "/user/updateActivityStatus/deliverStream/{setupNo}")
	public ModelAndView deliverStream(@PathVariable String setupNo) {
		ModelAndView model = new ModelAndView("streamDelivery");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = auth.getName();
		Users user = userdao.getUserById(loggedUserName);

		List<Transaction> wipActivities = transcationdao.runningActivity(setupNo);
		List<Transaction> completeActivites = transcationdao.listActivityByID(setupNo);

		model.addObject("loggedUserName", user.getUserName());
		model.addObject("wipActivities", wipActivities);
		model.addObject("completeActivites", completeActivites);

		return model;
	}

	@RequestMapping(value = "/user/streamComplete/{setupNo}/{streamName}", method = RequestMethod.POST)
	public ModelAndView markStreamAsDeliver(@PathVariable(value = "setupNo") String setupNo,
			@PathVariable(value = "streamName") String streamName) {
		ModelAndView model = new ModelAndView("streamDelivery");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserName = auth.getName();
		Users user = userdao.getUserById(loggedUserName);
		int elapsedDays = transcationdao.calculateElapsedDays(setupNo);

		Inventory readyStream = projectdao.markStreamAsDeliver(loggedUserName, setupNo, streamName, elapsedDays);
		// Update streamStatus, elapsedDays, deliveryDate

		model.addObject("elapsedDays", elapsedDays);
		model.addObject("userName", user.getUserName());
		model.addObject("readyStream", readyStream);
		return model;
	}

	@RequestMapping(value = "/user/history", method = RequestMethod.GET)
	public ModelAndView workHistroy() {
		ModelAndView model = new ModelAndView("workHistory");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserId = auth.getName();
		Users user = userdao.getUserById(loggedUserId);
		
		List<Inventory> streamList = projectdao.completedStreams(loggedUserId);
		List<Transaction> finishedActivity = transcationdao.finishedActivity(loggedUserId);
		List<Transaction> finishedRevisedActivity = transcationdao.finishedRevisedActivity(loggedUserId);

		model.addObject("loggedUserName", user.getUserName());
		model.addObject("completedStreamsList", streamList);
		model.addObject("finishedActivity", finishedActivity);
		model.addObject("finishedRevisedActivity", finishedRevisedActivity);
		return model;
	}

	@RequestMapping(value = "/user/reviewedPage")
	public ModelAndView reviewPage() {
		ModelAndView model = new ModelAndView("reviewedStreams");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserId = auth.getName();
		Users user = userdao.getUserById(loggedUserId);

		model.addObject("loggedUserName", user.getUserName());
		model.addObject("loggedUserId", loggedUserId);
		return model;

	}

	@RequestMapping(value = "/user/reviewPoints/assign", method = RequestMethod.POST)
	public ModelAndView reviewPointsImplementation(@ModelAttribute("transaction") Transaction transcation) {
		ModelAndView model = new ModelAndView("userDashboard");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserId = auth.getName();
		Users user = userdao.getUserById(loggedUserId);
		String searchStream = transcationdao.searchStream(transcation.getSetupNo());
		// setupNo, streamName, StreamType, Complexity, batchCount, kickOffDate, dueDate

		if (searchStream == null) {
			/*
			 * Inventory revisedStream = new Inventory();
			 * revisedStream.setSetupNo(transcation.getSetupNo());
			 * revisedStream.setStreamName(transcation.getStreamName());
			 * revisedStream.setComplexity("medium");
			 * revisedStream.setStreamType("PushedBack-Stream");
			 * revisedStream.setBatchCount(000000); revisedStream.setKickOffDate(new
			 * Date()); revisedStream.setDueDate(new Date());
			 * revisedStream.setUserId(loggedUserId);
			 * 
			 * projectdao.addNewProject(revisedStream);
			 * 
			 * ql = "insert into transaction(setupNo, userId, activity, activity_start_date,
			 * activity_comment, activity_status)" +
			 * " values (?, ?, ?, ?, CONCAT('[', now(), '] ', ?, ' '), ?)";
			 */

			Transaction reviewedStream = new Transaction();
			reviewedStream.setSetupNo(transcation.getSetupNo());
			// reviewedStream.setStreamName(transcation.getStreamName());
			reviewedStream.setRevised_stream(transcation.getRevised_stream());
			// reviewedStream.setStream_Type("PushedBack-Stream");
			// reviewedStream.setActivity_comment("PushedBack-Stream");
			reviewedStream.setActivity(transcation.getActivity());
			reviewedStream.setActivity_start_date(new Date());
			reviewedStream.setUserId(loggedUserId);
			reviewedStream.setActivity_status("WIP");

			// transcationdao.loggTaskInDB(reviewedStream);
			transcationdao.loggRevisedTaskInDB(reviewedStream);

		}

		transcation.setActivity_start_date(new Date());
		transcation.setUserId(user.getUserId());
		transcation.setActivity_status("WIP");
		transcationdao.loggTaskInDB(transcation);

		List<Transaction> runningStreams = transcationdao.listActivityByUid(loggedUserId);
		List<Transaction> runningRevisedStreams = transcationdao.listRevisedActivityByUid(loggedUserId);
		// System.out.println("loggedUserId " + loggedUserId + " runningRevisedStreams "
		// + runningRevisedStreams);
		model.addObject("userName", user.getUserName());
		model.addObject("runningStreams", runningStreams);
		model.addObject("revisedStream", "revisedStream");
		model.addObject("runningRevisedStreams", runningRevisedStreams);
		return model;
	}

	@RequestMapping(value = "/admin/search", method = RequestMethod.POST)
	public ModelAndView adminSearch(@RequestParam(value = "searchText") String text) {
		ModelAndView model = new ModelAndView("adminDashboard");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUserId = auth.getName();
		Users user = userdao.getUserById(loggedUserId);

		List<Transaction> runningStreams = transcationdao.adminReport();
		List<Transaction> searchResults = transcationdao.adminTextSearchReport(text);
		model.addObject("loggedUserName", user.getUserName());
		model.addObject("searchResults", searchResults);
		model.addObject("runningStreams", runningStreams);
		return model;
	}

	/*
	 * public ModelAndView reviewCompletStatus(@ModelAttribute Transaction
	 * transaction) { ModelAndView model = new ModelAndView("userDashboard");
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 * String loggedUserName = auth.getName(); Users user =
	 * userdao.getUserById(loggedUserName);
	 * transcationdao.updateActivityStatusComment(transaction.getActivity_status(),
	 * transaction.getSetupNo(), transaction.getActivity(),
	 * transaction.getActivity_comment(), loggedUserName);
	 * 
	 * List<Transaction> runningStreams =
	 * transcationdao.listActivityByUid(loggedUserName); List<Transaction>
	 * runningRevisedStreams =
	 * transcationdao.listRevisedActivityByUid(loggedUserName);
	 * model.addObject("runningRevisedStreams", runningRevisedStreams);
	 * model.addObject("loggedUserName", user.getUserName());
	 * model.addObject("runningStreams", runningStreams); return model; }
	 */
}
