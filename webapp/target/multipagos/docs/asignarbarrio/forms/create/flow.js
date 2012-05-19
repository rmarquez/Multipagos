importClass(Packages.org.apache.cocoon.forms.datatype.EmptySelectionList);
function createform(form) {
    if (autorizar("cata")) {
        form.showForm("create-form-display");

        var bean = new Packages.com.metropolitana.multipagos.AsignarBarrio();
        var handlerBean = new Packages.com.metropolitana.multipagos.forms.asignacion.AsignarBarriosHandler();
		form.save(bean);
        handlerBean.insert(bean, auth_getUserID());

        dialogosino("Asigancion", "Asignacion de barrios procesada con éxito",
                        "¿Desea procesar una nueva asignacion?","create", "/bienvenidos");
    }
}

