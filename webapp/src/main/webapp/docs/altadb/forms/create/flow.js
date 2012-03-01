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
    //if (autorizar("cata")) {
        //form.showForm("create-form-display");
        
        var formUpload = new Form("forms/create/definition.xml");
        
        //form.setAttribute("counter", new java.lang.Integer(0));
        
        //var k = form.showForm("create-form-display");
        //var k = formUpload.showForm("upload-display-pipeline.jx");
        
        //k.invalidate();
        cocoon.sendPage("migrar");        

       // dialogosino("El archivo ha sido procesado con exito",
       //                 "¿Desea procesar un nuevo archivo?","create", "/bienvenidos");
   // }
}

