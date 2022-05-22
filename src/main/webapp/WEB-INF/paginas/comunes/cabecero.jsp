<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>             
        <header id="main-header" class="py-2 bg-info text-white">
            <div class="container">
                <div class="row" >
                    <div class="col-md-6">
                        <h1>
                            <i class="fas fa-cog"></i> Control de clientes
                        </h1>
                    </div>
                </div>
            </div>
        </header>
        
        <section id="actions" class="py-4 mb-4 bg-light">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <button type="button" class="btn btn-primary botonAgregar" data-toggle="modal" data-target="#agregarClienteModal"><i class="fas fa-plus"></i>Agregar Cliente</button>
                    </div>
                </div>    
            </div>
        </section>
    </body>
</html>
