<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://kit.fontawesome.com/b538ace6f3.js" crossorigin="anonymous"></script>
        <title>Concecionario "EL AUTITO"</title>
    </head>
    
    <body>
        
        <jsp:include page="../comunes/cabecera.jsp"/>
        
        <form action="${pageContext.request.contextPath}/servletControlador?accion=modificar&id=${auto.id}" method="POST" class="was-validated">
        <jsp:include page="../comunes/botonesNavegacionEditar.jsp"/>
        <section id="details">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="card">
                            <div class="card-header">
                                <h4>Modificar Auto</h4>
                            </div>
                            <div class="card-body">
                                <div class="form-group">
                                    <label for="marca">Marca</label>
                                    <input type="text" class="form-control" name="marca" value="${auto.marca}" required/>
                                </div>
                                <div class="form-group"> 
                                    <label for="modelo">Modelo</label>
                                    <input type="text" class="form-control" name="modelo" value="${auto.modelo}" required/>
                                </div>
                                <div class="form-group">
                                    <label for="caja">Caja</label>
                                    <input type="text" class="form-control" name="caja" value="${auto.caja}" required/>
                                </div>
                                <div class="form-group">
                                    <label for="precio">Precio</label>
                                    <input type="currency" class="form-control" name="precio" value="${auto.precio}" required/>
                                </div>
                                <div class="form-group">
                                    <label for="puertas">Puertas</label>
                                    <input type="number" class="form-control" name="puertas" value="${auto.puertas}" required/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        </form>
        
        <jsp:include page="../comunes/pieDePagina.jsp"/>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        
    </body>
</html>
