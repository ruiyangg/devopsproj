


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import devopsproj.note;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;


/**
 * Servlet implementation class noteServlet
 */
@WebServlet("/noteServlet")
public class noteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 //Step 1: Prepare list of variables used for database connections
	 private String jdbcURL = "jdbc:mysql://localhost:3306/notedetails";
	 private String jdbcUsername = "root";
	 private String jdbcPassword = "password";
	 //Step 2: Prepare list of SQL prepared statements to perform CRUD to our database
	 private static final String INSERT_NOTE_SQL = "INSERT INTO notedetails" + " (name, description, target_date, accomplish) VALUES " + " (?, ?, ?);";
	 private static final String SELECT_NOTE_BY_ID = "select name,description,target_date,target_date from notedetails where name =?";
	 private static final String SELECT_ALL_NOTE = "select * from notedetails ";
	 private static final String DELETE_NOTE_SQL = "delete from notedetails where name = ?;";
	 private static final String UPDATE_NOTE_SQL = "update notedetails set name = ?,notedetails= ?, target_date =?,accomplish =? where name = ?;";
	 //Step 3: Implement the getConnection method which facilitates connection to the database via JDBC
	 protected Connection getConnection() {
	 Connection connection = null;
	 try {
	 Class.forName("com.mysql.jdbc.Driver");
	 connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	 } catch (SQLException e) {
	 e.printStackTrace();
	 } catch (ClassNotFoundException e) {
	 e.printStackTrace();
	 }
	 return connection;
	 }
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public noteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getServletPath();
		 try {
		 switch (action) {
		 case "/noteServlet/delete":
		 deletenote(request, response);
		 break;
		 case "/noteServlet/edit":
		 showEditForm(request, response);
		 break;
		 case "/noteServlet/update":
		 updatenote(request, response);
		 break;
		 case "/noteServlet/dashboard":
		 listnote(request, response);
		 break;
		 }
		 } catch (SQLException ex) {
		 throw new ServletException(ex);
		 } 
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		
		//Step 1: Initialize a PrintWriter object to return the html values via the response PrintWriter out = response.getWriter();
		//Step 2: retrieve the four parameters from the request from the web form
		String n = request.getParameter("userName");
		String p = request.getParameter("description");
		String e = request.getParameter("target_date");
		String c = request.getParameter("accomplish");
		//Step 3: attempt connection to database using JDBC, you can change the username and password accordingly using the phpMyAdmin > User Account dashboard
		try {
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection con = DriverManager.getConnection(
		 "jdbc:mysql://localhost:3306/notedetails", "root", "password");
		//Step 4: implement the sql query using prepared statement (https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html)
		 PreparedStatement ps = con.prepareStatement("insert into NOTEDETAILS values(?,?,?,?)");
		//Step 5: parse in the data retrieved from the web form request into the prepared statement accordingly
		 ps.setString(1, n);
		 ps.setString(2, p);
		 ps.setString(3, e);
		 ps.setString(4, c);
		//Step 6: perform the query on the database using the prepared statement
		 int i = ps.executeUpdate();
		//Step 7: check if the query had been successfully execute, return “You are successfully registered” via the response,
		 if (i > 0){
			 response.sendRedirect("/devopsproj/notesServlet/dashboard");
			 }
			 }
			 //Step 8: catch and print out any exception
			 catch (Exception exception) {
			  System.out.println(exception);
			  System.out.close();
			 }
		 
		
		doGet(request, response);
	}
	//method to get parameter, query database for existing user data and redirect to user edit page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, ServletException, IOException {
	//get parameter passed in the URL
	String name = request.getParameter("name");
	note existingnote = new note("", "", "", "");
	// Step 1: Establishing a Connection
	try (Connection connection = getConnection();
	// Step 2:Create a statement using connection object
	PreparedStatement preparedStatement =
	connection.prepareStatement(SELECT_NOTE_BY_ID);) {
	preparedStatement.setString(1, name);
	// Step 3: Execute the query or update query
	ResultSet rs = preparedStatement.executeQuery();
	// Step 4: Process the ResultSet object
	while (rs.next()) {
	name = rs.getString("name");
	String description = rs.getString("description");
	String target_date = rs.getString("target_date");
	String accomplish = rs.getString("accomplish");
	existingnote = new note(name, description, target_date, accomplish);
	}
	} catch (SQLException e) {
	System.out.println(e.getMessage());
	}
	//Step 5: Set existingUser to request and serve up the userEdit form
	request.setAttribute("note", existingnote);
	request.getRequestDispatcher("/noteEdit.jsp").forward(request, response);
	}

	
	//method to update the user table base on the form data
	private void updatenote(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
	//Step 1: Retrieve value from the request
	String oriName = request.getParameter("oriName");
	 String name = request.getParameter("name");
	 String description = request.getParameter("description");
	 String target_date = request.getParameter("target_date");
	 String accomplish = request.getParameter("accomplish");

	 //Step 2: Attempt connection with database and execute update user SQL query
	 try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_NOTE_SQL);) {
	 statement.setString(1, name);
	 statement.setString(2, description);
	 statement.setString(3, target_date);
	 statement.setString(4, accomplish);
	 statement.setString(5, oriName);
	 int i = statement.executeUpdate();
	 }
	 //Step 3: redirect back to UserServlet (note: remember to change the url to your project name)
	 response.sendRedirect("http://localhost:8090/devopsproj/notesServlet/dashboard");
	}
	
	//method to delete user
	private void deletenote(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
	//Step 1: Retrieve value from the request
	 String name = request.getParameter("note");
	 //Step 2: Attempt connection with database and execute delete user SQL query
	 try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_NOTE_SQL);) {
	 statement.setString(1, name);
	 int i = statement.executeUpdate();
	 }
	 //Step 3: redirect back to UserServlet dashboard (note: remember to change the url to your project name)
	 response.sendRedirect("http://localhost:8090/devopsproj/notesServlet/dashboard");
	}
	
	//Step 5: listUsers function to connect to the database and retrieve all users records
	private void listnote(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException, ServletException
	{
	List <note> note = new ArrayList <>();
	 try (Connection connection = getConnection();
	 // Step 5.1: Create a statement using connection object
	 PreparedStatement preparedStatement =
	connection.prepareStatement(SELECT_ALL_NOTE);) {
	 // Step 5.2: Execute the query or update query
	 ResultSet rs = preparedStatement.executeQuery();
	 // Step 5.3: Process the ResultSet object.
	 while (rs.next()) {
	 String name = rs.getString("name");
	 String description = rs.getString("description");
	 String target_date = rs.getString("target_date");
	 String accomplish = rs.getString("accomplish");
	 note.add(new note(name, description, target_date, accomplish));
	 }
	 } catch (SQLException e) {
	 System.out.println(e.getMessage());
	 }
	// Step 5.4: Set the users list into the listUsers attribute to be pass to th noteManagement.jsp
	request.setAttribute("listnote", note);
	request.getRequestDispatcher("/noteManagement.jsp").forward(request, response);
	}
	
}
