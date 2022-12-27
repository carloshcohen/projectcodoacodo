<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="es_AR"/>

<section id="listar-autos">
    <div class="container">
        <div class="row">
            
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de autos</h4>
                    </div> 
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Marca</th>
                                <th>Modelo</th>
                                <th>Caja</th>
                                <th>Precio</th>
                                <th>Puertas</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="auto" items="${autos}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${auto.marca}</td>
                                    <td>${auto.modelo}</td>
                                    <td>${auto.caja}</td>
                                    <td><fmt:formatNumber value="${auto.precio}" type="currency"/></td>
                                    <td>${auto.puertas}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/servletControlador?accion=editar&id=${auto.id}" class="btn btn-secondary"> 
                                            <i class="fa-solid fa-angle-double-right"></i>Editar</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>  
            </div>
            
            <div class="col-md-3">
                <div class="card text-center bg-secondary text-white mb-3">
                    <div class="card-body">
                        <h3>Cantidad de Autos</h3>
                        <h4 class="display-4" style="font-size: 25px"><i class="fa-solid fa-car-side"></i>${totalAutos}</h4>
                    </div>
                </div>
                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3>Valor en planta</h3>
                        <h4 class="display-4" style="font-size: 25px"><i class="fa-solid fa-car-side"></i><fmt:formatNumber value="${valorPlanta}" type="currency"/></h4>
                    </div>
                </div>
            </div>
                    
        </div>
    </div>
</section>
<jsp:include page="../operaciones/agregarAuto.jsp"/>

                    
                    
                    
                    
                    
<!--        <ul>
            <c:forEach var="auto" items="${autos}">
                <li><i class="fa-solid fa-car"></i>${auto.marca} ${auto.modelo} ${auto.precio} ${auto.puertas}</li>
            </c:forEach>
                
        </ul>-->
