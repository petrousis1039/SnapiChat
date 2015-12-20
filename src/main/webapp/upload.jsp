<%-- 
    Document   : upload
    Created on : Dec 15, 2015, 11:53:08 PM
    Author     : Petros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Ajax File Upload with Progress Bar</title>
        <!-- Include jQuery form & jQuery script file. -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.js" ></script>
        <script src="http://malsup.github.com/jquery.form.js" ></script>
        <script src="assets/fileUploadScript.js" ></script>
        <!-- Include css styles here -->
        <link href="assetes/upload.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h3>Ajax File Upload with Progress Bar</h3>
        <form id="UploadForm" action="uploadFile" method="post" enctype="multipart/form-data">
            <input type="file" size="60" id="myfile" name="myfile"> 
            <input type="submit" value="Ajax File Upload">
            <div id="progressbox">
                <div id="progressbar"></div>
                <div id="percent">0%</div>
            </div>
            <br />
            <div id="message"></div>
        </form>
    </body>
</html>
