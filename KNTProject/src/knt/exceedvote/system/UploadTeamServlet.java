package knt.exceedvote.system;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import knt.exceedvote.dao.DaoFactory;
import knt.exceedvote.dao.PollDAO;
import knt.exceedvote.dao.TeamDAO;
import knt.exceedvote.model.Poll;
import knt.exceedvote.model.Team;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.joda.time.DateTime;
 
/**
 * Servlet implementation class UploadServlet
 * @version 1.0
 * @author Ha Minh Nam
 */
public class UploadTeamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    private static final String UPLOAD_DIRECTORY = "filestore/teamimage";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 5; // 5MB
    private static final int REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    
    String fileName = null;
    String tname = null;
	String description = null;
	String[] filenames = new String[3];
 
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {
        	response.sendError(response.SC_BAD_REQUEST);
            // if not, we stop here
            return;
        }
        
        // configures some settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
         
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(REQUEST_SIZE);
         
        // constructs the directory path to store upload file
        String uploadPath = getServletContext().getRealPath("")
            + File.separator + UPLOAD_DIRECTORY;
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
         
        try {
            // parses the request's content to extract file data
            List formItems = upload.parseRequest(request);
            Iterator iter = formItems.iterator();
            
             int fileloops = 0;
            // iterates over form's fields
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                


                
                // processes only fields that are not form fields
                if (!item.isFormField()) {
                	

                	
                    fileName = new File(item.getName()).getName();
                    
                    String fileNameToLowerCase = fileName.toLowerCase();  

                    String fileExt = fileNameToLowerCase.substring(fileNameToLowerCase.indexOf(".")+1,fileNameToLowerCase.length());
                	System.out.println(fileExt);
                    if ("png".equals(fileExt) || "jpg".equals(fileExt)
                            || "bmp".equals(fileExt) || "gif".equals(fileExt)
                            || "jpeg".equals(fileExt)) {
                   
                    String timeString = Long.toString(DateTime.now().getMillis());
                    fileName = timeString + fileName;
                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);
                    System.out.println(fileloops + " " + fileName);
                    filenames[fileloops] = fileName;
                    fileloops += 1;
                    // saves the file on disk
                    item.write(storeFile);
                }  else if (fileExt.equals("")){ }
                else {
                    request.setAttribute("message", "Wrong Filetype!");
                    getServletContext().getRequestDispatcher("/jsp/message.jsp").forward(request, response);
                    return;
                }
                request.setAttribute("message", "Upload has been done successfully!");

                } else {
                	    String fieldName = item.getFieldName();

						if (fieldName.equals("tname")) 
                	    	 tname = item.getString();     
                }

           
            
            }
            
       	    if (tname != null ) {
              	TeamDAO teamDao = DaoFactory.getInstance("hibernate").getTeamDao();	  
          	
				if(!teamDao.addTeam(new Team(tname, filenames[0], filenames[1], filenames[2]))) {
                    request.setAttribute("message", "No connection to database");
                    getServletContext().getRequestDispatcher("/jsp/message.jsp").forward(request, response);
                    return;
				}
            }
            
            
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
            getServletContext().getRequestDispatcher("/jsp/message.jsp").forward(request, response);
            return;
        } 
        getServletContext().getRequestDispatcher("/jsp/message.jsp").forward(request, response);
    }
}