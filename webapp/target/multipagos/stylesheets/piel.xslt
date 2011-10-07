<?xml version="1.0"?>
<!--
    Autor: Multipagos
    Version: 1.0
    Fecha: 28-Jun-2010
-->

<!-- + Plantilla del sistema
          |
          |  Este archivo xslt se incluye en multipagos.xslt por ese motivo aqui no se define el parámetro XSLT "base-uri".
          |
         +-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template name="encabezado">
        <!--  CAPA ENCABEZADO -->
        <div class="encabezado">
          <img class="logo" src="{$base-uri}/images/Logo_final.png" alt="logo"/>
          <!-- <img class="empresa" src="{$base-uri}/images/banner_parte3.jpg" alt="empresa"/> -->
          <div class="aplicacion">
            <p class="nombre_aplicacion">Sistema de Control de Cobros</p>            
          </div>
        </div>        
    </xsl:template>

    <xsl:template name="piedepagina">
        <div id="pie">
            <p class="copyright">Copyright &#169; Multipagos 2011.</p>
        </div>
    </xsl:template>
</xsl:stylesheet>
