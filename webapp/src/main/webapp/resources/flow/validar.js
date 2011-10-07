// Esta función retorna un valor booleando deacuerdo a los siguientes criterios :
// 1. Si ambas fecha son nulas:
//    1.1 Si el campo permitirnulo es true, entonces, retorna true.
//    1.2 Si el campo permitirnulo es false, entonces, retorna false.
// 2. Si la fecha inicial es menor o igual que la fecha final, entonces, retorna true.
// 3. Si una de las fecha tiene un valor y la otra fecha no, entonces, retorna false.
// 4. Si la fecha final es menor que la fecha inicial, entonces, retorna false.
//
// Paramétros:
// 1. form - Objeto que representa el formulario.
// 2. widget_fecha1 - Almacena la fecha inical del intervalo.
// 3. widget_fecha2 - Almacena la fecha final del intervalo.
// 4. widget_mensajes - Presenta algunos mensajes de error
// 5. permitirnulo - Valor booleano que indica si se puede permitir que las
//                   dos fechas sean nulas
//
function validarfechas(form, widget_fecha1, widget_fecha2, widget_mensajes, permitirnulo ){
    var  resultado = false;
    if ( form.getChild(widget_fecha1).getValue() == null && form.getChild(widget_fecha2).getValue() == null ){
        resultado = permitirnulo;
        if ( !permitirnulo ) {
            var i18nMessage = new Packages.org.apache.cocoon.forms.util.I18nMessage("com.metropolitana.key.fechasnull");
            form.getChild(widget_mensajes).addMessage(i18nMessage);
        }
    }
    else
        if ( form.getChild(widget_fecha1).getValue() == null ){
            var i18nMessage = new Packages.org.apache.cocoon.forms.util.I18nMessage("com.metropolitana.key.fechaininull");
            form.getChild(widget_mensajes).addMessage(i18nMessage);
        }
        else
            if ( form.getChild(widget_fecha2).getValue() == null ){
                var i18nMessage = new Packages.org.apache.cocoon.forms.util.I18nMessage("com.metropolitana.key.fechafinnull");
                form.getChild(widget_mensajes).addMessage(i18nMessage);
            }
            else{
                resultado = new Date(form.getChild(widget_fecha1).getValue()) <= new Date(form.getChild(widget_fecha2).getValue());
                if ( !resultado ){
                     var i18nMessage = new Packages.org.apache.cocoon.forms.util.I18nMessage("com.metropolitana.key.fechamenor");
                     form.getChild(widget_mensajes).addMessage(i18nMessage);
                }
           }
   return resultado;
}
