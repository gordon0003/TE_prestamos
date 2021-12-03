<%
    String opcion = request.getParameter("opcion");
%>            

            <ul class="nav nav-tabs">

                <li class="nav-item">
                    <a class="nav-link <%= (opcion.equals("clientes") ? "active" : "" ) %>" href="ClienteControlador">Clientes</a>
                </li>
                
                <li class="nav-item">
                    <a class="nav-link <%= (opcion.equals("usuarios") ? "active" : "" ) %>" href="UsuarioControlador">Usuarios</a>
                </li>
                
                <li class="nav-item">
                    <a class="nav-link <%= (opcion.equals("gastos") ? "active" : "" ) %>" href="GastoControlador">Gastos</a>
                </li>
                
                <li class="nav-item">
                    <a class="nav-link <%= (opcion.equals("pagos") ? "active" : "" ) %>" href="PagoControlador">Pagos</a>
                </li>
                
                <li class="nav-item">
                    <a class="nav-link <%= (opcion.equals("prestamos") ? "active" : "" ) %>" href="PrestamoControlador">Prestamos</a>
                </li>
                
                <li class="nav-item">
                    <a href="Logout" class="btn btn-danger">Cerrar sesión</a>
                </li>

            </ul>
                
                