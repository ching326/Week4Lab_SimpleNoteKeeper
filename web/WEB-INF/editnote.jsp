<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple Note Keeper</title>
    </head>
    <body>
        <h1>Simple Note Keeper</h1>
        <h2>Edit Note</h2>


        <form method = "post" action = "note"> 

            Title: <input type="text" name ="title" value= "${note.title}">
            <br>
            Contents: <textarea name="content" cols="20" rows="5">${note.content}</textarea>
            <br>

            <%-- submit to the URL note, which is mapped to the noteServlet, then noteServlet will run the doPost  --%>
            <input type="submit" value ="Save">  

        </form>



    </form>
</body>
</html>