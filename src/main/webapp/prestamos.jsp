<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" rel="stylesheet">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <title>Punto de Prestamos</title>
    </head>
    <body>
        <div class="container">
            <h1>Prestamos a realizar</h1>
            <br/>
            <jsp:include page="WEB-INF/menu.jsp">
                <jsp:param name="opcion" value="prestamos" />
            </jsp:include>
            <br/>
            <a href="PrestamoControlador?action=add" class="btn btn-primary btn-sm mt-10">+ Realizar un nuvo prestamo</a>
            <br/>
            <br/>
            <table class="table table-hover" border="2">
                <tr>
                    <th>Id</th>
                    <th>Cliente</th>
                    <th>Usuario</th>
                    <th>Fecha Prestamo</th>
                    <th>Monto</th>
                    <th>Interes</th>
                    <th>Saldo</th>
                    <th>Forma de Pago</th>
                    <th>Fecha de Pago</th>
                    <th>Plazo</th>
                    <th>Fecha Plazo</th>
                    <th></th>
                    <th></th>
                    
                </tr>
                <c:forEach var="item" items="${prestamos}">
                    <tr>
                        <td>${item.idprestamo}</td>
                        <td>${item.cliente}</td>
                        <td>${item.usuarios}</td>
                        <td>${item.fprestamo}</td>
                        <td>${item.monto}</td>
                        <td>${item.interes}</td>
                        <td>${item.saldo}</td>
                        <td>${item.formapago}</td>
                        <td>${item.fpago}</td>
                        <td>${item.plazo}</td>
                        <td>${item.fplazo}</td>
                        
                        <td><a href="PrestamoControlador?action=edit&idprestamo=${item.idprestamo}"><i class="fas fa-edit"></a></td>
                        <td><a href="PrestamoControlador?action=delete&idprestamo=${item.idprestamo}" onclick="return(confirm('Esta seguro'))"><i class="fas fa-trash-alt"></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
 <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
    </body>
</html>