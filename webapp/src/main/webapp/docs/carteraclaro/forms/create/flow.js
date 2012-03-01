/**
    Este formulario contiene un control upload para subir archivos,
    con botones de Adjuntar
    Parámetros:
    	Este formulario almacena los archivos en la carpeta
    	resources/files para luego tomar el xls y migrar los datos
    	a postgres
*/
cocoon.load("resource://org/apache/cocoon/forms/flow/javascript/Form.js");

function createform(form) {
        form.showForm("create-form-display");
        
        var formUpload = new Form("forms/create/definition.xml");
	    cocoon.sendPage("migrar");    
}

