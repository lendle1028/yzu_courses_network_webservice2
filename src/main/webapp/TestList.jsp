<%-- 
    Document   : TestList
    Created on : Oct 11, 2018, 10:26:58 PM
    Author     : lendle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/vue"></script>
    </head>
    <body>
        <script>
            $(document).ready(function () {
                $.ajax("TestList", {
                    success: function (d) {
                        //create Vue model with #app as el
                        //and map d to logins in the data part
                        new Vue({
                            "el": "",
                            "data": {
                                
                            }
                        });
                        ////////////////////////////////////
                    }
                });
            });
        </script>
        <table border="1" id="app" widths="100%">
            <thead>
                <tr>
                    <th></th>
                    <th>ID</th>
                    <th>PASSWORD</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(login, index) in logins">
                    <!--
                    use the {{ }} syntax to put index, login.id, login.password
                    into UI
                    -->
                    
                    <td><button>EDIT</button></td>
                    <td><button>DELETE</button></td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
