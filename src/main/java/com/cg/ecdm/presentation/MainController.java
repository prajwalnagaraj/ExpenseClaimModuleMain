package com.cg.ecdm.presentation;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.cg.ecdm.exceptions.ECMException;
import com.cg.ecdm.models.ClaimBean;
import com.cg.ecdm.models.Employee;
import com.cg.ecdm.models.ExpenseDetails;
import com.cg.ecdm.models.Project;
import com.cg.ecdm.service.IClaimService;

@RestController
@RequestMapping("/ecdm")
public class MainController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	IClaimService service;

	@RequestMapping(value = "/home")
	private ModelAndView home() {
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/claim")
	private ModelAndView claimDetails() {
		return new ModelAndView("claimForm", "claim", new ClaimBean());
	}

	@RequestMapping(value = "/modify")
	private ModelAndView modifyDetails() {
		return new ModelAndView("claimUpdateForm", "claim", new ClaimBean());
	}

	@RequestMapping(value = "/view")
	private ModelAndView viewDetails() {
		return new ModelAndView("viewById", "claim", new ClaimBean());
	}

	@RequestMapping(value = "/delete")
	private ModelAndView deleteDetails() {
		return new ModelAndView("deleteById", "claim", new ClaimBean());
	}

	@RequestMapping(value = "/deleteClaim")
	private ModelAndView deleteClaimDetails(
			@ModelAttribute("claim") ClaimBean bean) {
		ClaimBean bean2 = service.getById(bean.getClaimCode());
		ModelAndView view = new ModelAndView("deleteClaim", "bean", bean2);
		return view;
	}

	@RequestMapping(value = "/deleteExpense")
	private ModelAndView delete(@ModelAttribute("claim") ClaimBean bean) {

		service.deleteClaim(bean);
		return new ModelAndView("deleteSuccess");

	}

	@RequestMapping(value = "claimUpdate")
	private ModelAndView updateDetails(@ModelAttribute("claim") ClaimBean bean) {
		ClaimBean bean2 = service.getById(bean.getClaimCode());
		ModelAndView view = new ModelAndView("updateForm", "bean", bean2);
		return view;
	}

	@RequestMapping(value="updateClaimExpense")
	private ModelAndView updateClaimExpense(@ModelAttribute("bean") ClaimBean bean,
			BindingResult result) throws ECMException {
		ModelAndView view=null;
		ClaimBean bean2 = service.getById(bean.getClaimCode());
		if(bean2==null){
			throw new ECMException("Wrong Claim Code!!!");
		}
		if(bean2.getEmployeeId().equals(bean.getEmployeeId())){
			if(bean2.getProjectCode().equals(bean.getProjectCode())){
				if(bean2.getExpenseCode().equals(bean.getExpenseCode())){
					if(result.hasErrors()){
						view = new ModelAndView("claimForm", "claim", new ClaimBean());
						}
						ClaimBean claim = service.updateClaimDetails(bean);
						view = new ModelAndView("UpdateSuccess", "claim", claim);
				}
				else{
					throw new ECMException("Expense Code MisMatch");
				}
			}else{
				throw new ECMException("Project Code MisMatch");
			}
		}else{
			throw new ECMException("Employee Code MisMatch");
		}
		
		return view;
	}
	
	@RequestMapping(value = "claimView")
	private ModelAndView viewClaim(@ModelAttribute("claim") ClaimBean bean) throws ECMException {
		ClaimBean bean2 = service.getById(bean.getClaimCode());
		if(bean2==null){
			throw new ECMException("Wrong Claim Code");
		}
		ModelAndView view = new ModelAndView("viewClaim", "bean", bean2);
		return view;
	}

	@RequestMapping(value = "claimExpense")
	private ModelAndView claimExpense(@ModelAttribute("claim") ClaimBean bean,
			BindingResult result) throws ECMException {
		ModelAndView view=null;
		Employee employee = readByEmployeeId(bean.getEmployeeId());
		if (employee == null) {
			throw new ECMException("No such Id exists: " + bean.getEmployeeId());
		}
		Project project = readProjectById(bean.getProjectCode());
		if (project == null) {
			throw new ECMException("No such Project Code exists: "
					+ bean.getProjectCode());
		}
		ExpenseDetails details = readByExpenseCode(bean.getExpenseCode());
		if (details == null) {
			throw new ECMException("No such Expense exists: "
					+ bean.getExpenseCode());
		}
		if(result.hasErrors()){
		view = new ModelAndView("claimForm", "claim", new ClaimBean());
		}
		ClaimBean claim = service.addClaimDetails(bean);
		view = new ModelAndView("ClaimSuccess", "claim", claim);
		return view;
	}

	@RequestMapping(value = "/readExpenById")
	public ModelAndView readPageById(
			@ModelAttribute("expense") ExpenseDetails expenses) {
		ExpenseDetails expense = readByExpenseCode(expenses.getExpenseCode());
		ModelAndView view = new ModelAndView("readExpensePagebyId", "expense",
				expense);
		return view;
	}

	@ResponseBody
	public ResponseEntity<ExpenseDetails> createExpense(ExpenseDetails expense) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		final String uri = "http://ECM/create";
		HttpEntity<ExpenseDetails> requestEntity = new HttpEntity<>(expense,
				requestHeaders);
		ResponseEntity<ExpenseDetails> responseEntity = restTemplate.exchange(
				uri, HttpMethod.POST, requestEntity, ExpenseDetails.class);
		return responseEntity;
	}

	@RequestMapping(value = "/modifyExpense/{expenseCode}", method = RequestMethod.PUT)
	public ResponseEntity<ExpenseDetails> modifyExpense(
			@PathVariable int expenseCode,
			@RequestBody ExpenseDetails expenseDetails) {
		final String uri = "http://ECM/update/{expenseCode}";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<ExpenseDetails> requestEntity = new HttpEntity<>(
				expenseDetails, requestHeaders);
		ResponseEntity<ExpenseDetails> responseEntity = restTemplate.exchange(
				uri, HttpMethod.PUT, requestEntity, ExpenseDetails.class,
				expenseCode);
		return responseEntity;
	}

	@RequestMapping(value = "/readExpense/{expenseCode}", method = RequestMethod.GET)
	public ExpenseDetails readByExpenseCode(int expenseCode) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<ExpenseDetails> requestEntity = new HttpEntity<>(
				requestHeaders);
		ExpenseDetails response = restTemplate.exchange(
				"http://ECM/read/{expenseCode}", HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<ExpenseDetails>() {
				}, expenseCode).getBody();

		return response;
	}

	@RequestMapping(value = "/readExpense", method = RequestMethod.GET)
	public ResponseEntity<List<ExpenseDetails>> readExpense() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<ExpenseDetails> requestEntity = new HttpEntity<>(
				requestHeaders);
		final String uri = "http://ECM/readExpense";
		System.out.println("ABCDEFGHIJKL");
		ResponseEntity<List<ExpenseDetails>> list = restTemplate.exchange(uri,
				HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<List<ExpenseDetails>>() {
				});
		return list;
	}

	@RequestMapping(value = "/deleteExpense/{expenseCode}", method = RequestMethod.DELETE)
	public String deleteExpense(@PathVariable int expenseCode) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<ExpenseDetails> requestEntity = new HttpEntity<>(
				requestHeaders);
		restTemplate.exchange("http://ECM/delete/{expenseCode}",
				HttpMethod.DELETE, requestEntity,
				new ParameterizedTypeReference<String>() {
				}, expenseCode).getBody();
		return "Deleted";
	}

	@RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Employee> createEmployee(
			@RequestBody Employee employee) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		final String uri = "http://Employee/create";
		HttpEntity<Employee> requestEntity = new HttpEntity<>(employee,
				requestHeaders);
		ResponseEntity<Employee> responseEntity = restTemplate.exchange(uri,
				HttpMethod.POST, requestEntity, Employee.class);
		return responseEntity;
	}

	@RequestMapping(value = "/readEmployee", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> readEmployee() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<ExpenseDetails> requestEntity = new HttpEntity<>(
				requestHeaders);
		final String uri = "http://Employee/readall";
		ResponseEntity<List<Employee>> list = restTemplate.exchange(uri,
				HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<List<Employee>>() {
				});
		return list;
	}

	@RequestMapping(value = "/modifyEmployee/{employeeId}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> modifyExpense(
			@PathVariable String employeeId, @RequestBody Employee employee) {
		final String uri = "http://Employee/modify/{employeeId}";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> requestEntity = new HttpEntity<>(employee,
				requestHeaders);
		ResponseEntity<Employee> responseEntity = restTemplate.exchange(uri,
				HttpMethod.PUT, requestEntity, Employee.class, employeeId);
		return responseEntity;
	}

	@RequestMapping(value = "/deleteEmployee/{employeeId}", method = RequestMethod.DELETE)
	public String deleteData(@PathVariable String employeeId) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Employee> requestEntity = new HttpEntity<>(requestHeaders);
		restTemplate.exchange("http://Employee/delete/{employeeId}",
				HttpMethod.DELETE, requestEntity,
				new ParameterizedTypeReference<String>() {
				}, employeeId).getBody();
		return "Deleted";
	}

	@RequestMapping(value = "/readEmployee/{employeeId}", method = RequestMethod.GET)
	public Employee readByEmployeeId(@PathVariable int i) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Employee> requestEntity = new HttpEntity<>(requestHeaders);
		Employee response = restTemplate.exchange(
				"http://Employee/read/{employeeId}", HttpMethod.GET,
				requestEntity, new ParameterizedTypeReference<Employee>() {
				}, i).getBody();
		return response;
	}
	@RequestMapping(value = "/createProject", method = RequestMethod.POST)
	public Project createProject(@RequestBody Project project) {
		final String uri = "http://localhost:8082/project/insert";
		RestTemplate restTemplate = new RestTemplate();
		Project details = restTemplate.postForObject(uri, project,
				Project.class);
		return details;
	}

	@RequestMapping(value = "/readAllProject", method = RequestMethod.GET)
	public List<Project> readAllProject() {
		final String uri = "http://localhost:8082/project/getAll";
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
		List<Project> details = restTemplate.getForObject(uri, List.class);
		return details;
	}

	@RequestMapping(value = "/readProjectById/{id}", method = RequestMethod.GET)
	public Project readProjectById(@PathVariable int id) {
		final String uri = "http://localhost:8082/project/get/{id}";
		RestTemplate restTemplate = new RestTemplate();
		Project details = restTemplate.getForObject(uri, Project.class, id);
		return details;
	}

	@RequestMapping(value = "/updateProjectById/{id}", method = RequestMethod.PUT)
	public Project updateProjectById(@PathVariable int id,
			@RequestBody Project project) {
		final String uri = "http://localhost:8082/project/update/{id}";
		project.setProductCode(id);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(uri, Project.class, id);
		return project;
	}

	@RequestMapping(value = "/deleteProjectById/{id}", method = RequestMethod.DELETE)
	public String deleteProjectById(@PathVariable int id) {
		final String uri = "http://localhost:8082/project/delete/{id}";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(uri, Project.class, id);
		return "Delete Success";
	}

	@RequestMapping(value = "/claimExpense/{employeeId}", method = RequestMethod.GET)
	private ClaimBean getExpenseDetails(@PathVariable int employeeId) {
		Employee employee = readByEmployeeId(employeeId);
		ClaimBean bean = new ClaimBean();
		if (employee != null) {
			bean.setEmployeeId(employee.getEmployeeId());
			bean.setEmployeeName(employee.getEmployeeName());
		}
		return null;

	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
