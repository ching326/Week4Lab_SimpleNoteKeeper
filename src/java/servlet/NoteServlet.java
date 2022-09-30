package servlet;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.servlet.*;
import javax.servlet.http.*;
import models.Note;

/**
 *
 * @author keith
 */
public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileNotFoundException {

        //Get the value from viewnote jsp <a href="note?edit">
        String edit = request.getParameter("edit");
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));

        // Set first line in the file as title
        String title = br.readLine();

        //Content: Read lines from second line 
        //https://www.baeldung.com/java-buffered-reader
        String content = br.lines().collect(Collectors.joining("<br>"));

        Note note = new Note(title, content);

        //Return the note object (title, content) to viewnote.jsp
        request.setAttribute("note", note);

        // Depends on value of "edit", refer to viewnote.jsp or editnote.jsp
        if (edit == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String content = request.getParameter("content");
           
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));

        pw.println(title);
        pw.println(content);
        pw.close();

        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        request.setAttribute("note", note); // let note available for jsp
        

        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
    }

}
