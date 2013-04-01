importClass(Packages.com.metropolitana.multipagos.forms.auth_user.Auth_userHandler);
importClass(Packages.java.lang.Boolean);
cocoon.load("resource://org/apache/cocoon/forms/flow/javascript/Form.js");
function editform(form) {
	var usrId = auth_getUserID();
	var handlerUser = new Auth_userHandler();
	var usuario = handlerUser.retrieve(usrId);
	var editarDb = usuario.getUsrEditdb();
	if(editarDb.equals(Boolean.TRUE)){
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.empresa.EmpresaHandler();
        var bean = handlerBean.getBeanEmpresa();

        form.load(bean);
        form.showForm("edit-form-display");
        form.save(bean);
        handlerBean.setBeanEmpresa(bean, usrId);

        dialogosino("Empresa", "Datos generales procesados con éxito",
                        "Todos los cambios se procesaron exitosamente. ¿Desea procesar otra vez los datos?",
                        "edit","/bienvenidos");
   }
}

