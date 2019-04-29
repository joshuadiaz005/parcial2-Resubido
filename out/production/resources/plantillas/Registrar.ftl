<#import "/plantillas/principal.ftl" as base>
<@base.pagina>
<div class="p-0">
    <div class="row">
        <div class="col md-4">
            <div class="form-group">
                <label><strong>Nombre</strong></label>
                <input class="form-control" type="text" id="nombre" required=""/>
            </div>
            <div class="form-group">
                <label><strong>Direccion</strong></label>
                <input class="form-control" type="text" id="sector" required=""/>
            </div>
            <div class="form-group">
                <label><strong>GradoEscolar</strong></label>
                <select class="form-control" id="nivelEscolar">
                    <option value="Básico">Básico</option>
                    <option value="Medio">Medio</option>
                    <option value="Grado Universitario">Universitario</option>
                    <option value="Postgrado">Postgrado</option>
                    <option value="Doctorado">Doctorado</option>
                </select>
            </div>
            <div class="form-group">
                <button onclick="agregarDelFormulario()" class="btn btn-primary">Enviar</button>
            </div>
        </div>
    </div>
</div>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/baseDeDatos.js"></script>
</@base.pagina>