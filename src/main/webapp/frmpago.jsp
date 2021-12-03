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

        <title>Control de Pagos</title>
    </head>
    <body>
        <div class="container" border="2">
            <h1>Punto de pagos</h1>
            <br/>
            <jsp:include page="WEB-INF/menu.jsp">
                <jsp:param name="opcion" value="pagos" />
            </jsp:include>
            <br />
            <br/>
            <br/>
            <jsp:useBean id="pagos" scope="request" class="com.emergentes.modelo.Pago" />
            <form action="PagoControlador" method="post">
                <input type="hidden" name="idpago" value="${pago.idpago}" />


                <div class="form-group">
                    <label for="cliente">Cliente</label>
                    <select name="idprestamo" class="form-control">
                        <c:forEach var="item" items="${lista_cliente}">
                            <option value="${item.idcliente}" 
                                    <c:if test="${pago.idprestamo == item.idcliente}">
                                        selected
                                    </c:if>
                                    >${item.nombre}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">

                    <label for="usuario">Usuario</label>
                    <input type="text" name="usuario" value="${pago.usuario}" class="form-control" placeholder="Escriba Usuario">

                </div>

                <div class="form-group">
                    <label for="fecha">Fecha</label>
                    <input type="text" name="fecha" value="${pago.fecha}" class="form-control" placeholder="Escriba la Fecha">
                </div>
                <div class="form-group">
                    <label for="cuota">Cuota</label>
                    <input type="number" name="cuota" value="${pago.cuota}" class="form-control" placeholder="Escriba la cuota">
                </div>
                <br>
                <br>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>
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