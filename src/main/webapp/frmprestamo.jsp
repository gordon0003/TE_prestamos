<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet" >
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">

        <title>Punto de venta</title>
    </head>
    <body>
        <div class="container" border="2">
            <h1>Punto de Prestamo</h1>
            <br>
            <jsp:include page="WEB-INF/menu.jsp">
                <jsp:param name="opcion" value="prestamos" />
            </jsp:include>
            <br>
            <br>
            <form action="PrestamoControlador" method="post">
                <input type="hidden" name="idprestamo" value="${prestamo.idprestamo}" />
                <div class="form-group">
                    <label for="cliente">Cliente</label>
                    <select name="idcliente" class="form-control">
                        <c:forEach var="item" items="${lista_cliente}">
                            <option value="${item.idcliente}" 
                                    <c:if test="${prestamo.idcliente == item.idcliente}">
                                        selected
                                    </c:if>>${item.nombre}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="usuario">Usuario</label>
                    <select name="usuario" class="form-control">
                        <c:forEach var="item" items="${lista_usuario}">
                            <option value="${item.idusuario}" 
                                    <c:if test="${prestamo.usuario == item.idusuario}">
                                        selected
                                    </c:if>>${item.nombre}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="fprestamo">Fecha de prestamo</label>
                    <input type="text" name="fprestamo" value="${prestamo.fprestamo}" class="form-control" placeholder="Fecha de prestamo">
                </div> 
                
                <div class="form-group">
                    <label for="monto">Monto</label>
                    <input type="text" name="monto" value="${prestamo.monto}" class="form-control" placeholder="Monto a prestar">
                </div>
                
                <div class="form-group">
                    <label for="interes">Interes</label>
                    <input type="text" name="interes" value="${prestamo.interes}" class="form-control" placeholder="Interes a dar">
                </div>
                
                <div class="form-group">
                    <label for="saldo">Saldo</label>
                    <input type="text" name="saldo" value="${prestamo.saldo}" class="form-control" placeholder="Saldo">
                </div>
                
                <div class="form-group">
                    <label for="formapago">Forma de Pago</label>
                    <input type="text" name="formapago" value="${prestamo.formapago}" class="form-control" placeholder="Forma de pago">
                </div>
                
                <div class="form-group">
                    <label for="fpago">Fecha de pago</label>
                    <input type="text" name="fpago" value="${prestamo.fpago}" class="form-control" placeholder="Fecha de pago">
                </div>
                
                <div class="form-group">
                    <label for="plazo">Plazo</label>
                    <input type="text" name="plazo" value="${prestamo.plazo}" class="form-control" placeholder="Plazo">
                </div>
               
                <div class="form-group">
                    <label for="fplazo">Fecha de Plazo</label>
                    <input type="text" name="fplazo" value="${prestamo.fplazo}" class="form-control" placeholder="Fecha de plazo">
                </div>
                <br>
                <br>
                                             
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>
        </div>
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>

    </body>
</html>