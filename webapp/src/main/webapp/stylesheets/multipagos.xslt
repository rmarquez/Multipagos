<?xml version="1.0"?>

<!-- Plantilla del sistema -->
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xi="http://www.w3.org/2001/XInclude"
    xmlns:i18n="http://apache.org/cocoon/i18n/2.1"
    exclude-result-prefixes="xi i18n jx">

    <xsl:param name="base-uri">/multipagos</xsl:param>

    <!-- Piel de la aplicación -->
    <xsl:include href="piel.xslt"/>

    <xsl:template match="document">
        <html lang="es-NI">
            <head>
                <xi:include href="context://resources/datosapp.xml" xpointer="xpointer(/aplicacion/html/node())"/>
                <meta http-equiv="Content-Style-Type" content="text/css"/>
                <meta http-equiv="Default-Style" content="multipagos"/>
                <meta http-equiv="Pragma" content="no-cache"/>
                <meta http-equiv="Expires" content="-1"/>
                <meta http-equiv="Language" content="es"/>
                <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
                <script type="text/javascript" src="{$base-uri}/scripts/util.js"/>
                <!-- Colocamos el titulo de la aplicación -->
                <title><xi:include href="context://resources/datosapp.xml" xpointer="xpointer(/aplicacion/titulo/node())"/> - <xsl:value-of select="header/title"/></title>
                <link rel="shortcut icon" href="{$base-uri}/images/favicon.ico"/>
                <!-- Hoja CSS de Aplicación -->
                <link rel="stylesheet" type="text/css" href="{$base-uri}/styles/multipagos.css" title="multipagos"/>
                <xsl:if test="@js!=''">
                    <script type="text/javascript">
                        <xsl:attribute name="src"><xsl:value-of select="@js"/></xsl:attribute>
                    </script>
                </xsl:if>
                <xsl:copy-of select="header/style"/>
           </head>
            <body>
                <!-- Evento que se ejecuta al terminar de cargar pagina -->
                <xsl:copy-of select="@onload"/>
                <!-- Todo el contenido de la página debe estar contenido en una capa -->
                <!--  CAPA CONTENIDO -->
                <div id="contenido">
                    <!--  CAPA ENCABEZADO -->
                    <xsl:call-template name="encabezado"/>
                    <!--  MENU -->
                    <xsl:if test="@menu='true'">
                		<div id="menu">
                  			<xi:include href="cocoon://display-menu" />
                  		</div>
                   	</xsl:if>

                    <!--  CAPA APLICACION -->
                    <div id="formulario">
                      <table class="contenido">
                        <tr><td><xsl:apply-templates/></td></tr>
                      </table>
                    </div>
                </div>
                <!--  CAPA PIE DE PAGINA -->
                <xsl:call-template name="piedepagina"/>
            </body>
        </html>
    </xsl:template>

	<!-- No hacer nada en estos casos -->
    <xsl:template match="title|header|url"/>

    <xsl:template match="br"><br/></xsl:template>

    <xsl:template match="s1 | s2">
      <div class="subtitulo">
        <xsl:if test="name()='s2'">
            <xsl:attribute name="id">s2</xsl:attribute>
        </xsl:if>
        <p><span class="texto_subtitulo"><xsl:value-of select="@title"/></span></p>
        <xsl:apply-templates/>
      </div>
    </xsl:template>

    <xsl:template match="p">
        <p align="justify">
            <xsl:copy-of select="@class"/> <!-- Si tiene class, agregarla! -->
            <xsl:apply-templates/>
        </p>
    </xsl:template>

    <xsl:template match="span">
        <xsl:copy>
            <xsl:copy-of select="@class | @id | @onclick"/>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="pre">
        <xsl:copy>
            <xsl:copy-of select="@class | @id | @style"/>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="note">
        <p/>
        <table class="nota">
            <tr>
                <td class="notaimg">
                    <img class="nota" src="{$base-uri}/images/note.gif" alt=""/>
                </td>
                <td class="nota">
                    <xsl:apply-templates/>
                </td>
            </tr>
        </table>
    </xsl:template>

    <xsl:template match="li">
        <xsl:copy-of select="@class"/>
        <xsl:copy>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>

    <!-- Bold -->
    <xsl:template match="strong">
        <b><xsl:apply-templates/></b>
    </xsl:template>

    <xsl:template match="table">
        <table class="informe" cellspacing="2" cellpadding="2">
            <xsl:copy-of select="@border"/>
            <caption><xsl:value-of select="caption"/></caption>
            <xsl:apply-templates/>
        </table>
    </xsl:template>

    <xsl:template match="thead | tbody">
        <xsl:copy>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="tr">
        <tr>
            <xsl:choose>
                <xsl:when test="@class">
                    <xsl:copy-of select="@class"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="class">borde</xsl:attribute>
                </xsl:otherwise>
                </xsl:choose>
            <xsl:apply-templates/>
        </tr>
    </xsl:template>

    <xsl:template match="th">
        <td class="titulotabla">
            <xsl:copy-of select="@colspan | @rowspan | @align | @width | @class"/>
            <b><xsl:apply-templates/></b>&#160;</td>
    </xsl:template>

    <xsl:template match="thb">
        <td class="titulotablaconborde">
            <xsl:copy-of select="@colspan | @rowspan | @align"/>
            <b><xsl:apply-templates/></b>&#160;</td>
    </xsl:template>

    <xsl:template match="td">
        <td class="detalletabla">
            <xsl:copy-of select="@colspan | @rowspan | @align | @width"/>
            <xsl:apply-templates/>&#160;</td>
    </xsl:template>

    <!-- Línea con fondo blanco de tabla -->
    <xsl:template match="tw">
        <td>
	        <!-- Definimos la alineación -->
            <xsl:copy-of select="@colspan"/>
            <xsl:choose>
                <xsl:when test="@align">
                    <xsl:copy-of select="@align"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="align">left</xsl:attribute>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:apply-templates/>
        </td>
    </xsl:template>

    <xsl:template match="link">
        <a title="{@title}" href="{@href}"><xsl:apply-templates/></a>
    </xsl:template>

    <xsl:template match="agscheck">
        <xsl:choose>
            <xsl:when test="@required='true'">
                <span class="requerido"> * </span>
            </xsl:when>
            <xsl:otherwise>&#160;</xsl:otherwise>
        </xsl:choose>
        <input type="checkbox">
            <xsl:copy-of select="@name | @value | @onClick | @tabindex | @checked | @title"/>
            <xsl:if test="@elejido=@value">
                <xsl:attribute name="checked">checked</xsl:attribute>
            </xsl:if>
        </input>
    </xsl:template>

    <xsl:template match="agsradio">
        <xsl:choose>
            <xsl:when test="@required='true'">
                <span class="requerido"> * </span>
            </xsl:when>
            <xsl:otherwise>&#160;</xsl:otherwise>
        </xsl:choose>
        <input type="radio">
            <xsl:copy-of select="@name | @value | @onClick | @tabindex | @checked | @title"/>
            <xsl:if test="@elejido=@value">
                <xsl:attribute name="checked">checked</xsl:attribute>
            </xsl:if>
        </input>
        <label><xsl:value-of select="@label"/></label>
    </xsl:template>

    <xsl:template match="form">
		<table width="100%" cellpadding="5">
			<tr><td><form name="form_{@name}" action="{@handler}" method="post">
				<xsl:copy-of select="@onsubmit"/>
				<xsl:apply-templates/>
			</form></td></tr>
		</table>
	</xsl:template>

    <!-- Formulario para usar dentro de tablas -->
    <xsl:template match="form-table">
        <form name="form_{@name}" action="{@handler}" method="POST"><xsl:copy-of select="@onsubmit"/><xsl:apply-templates/></form>
    </xsl:template>

    <!-- Formulario para usar dentro de tablas -->
    <xsl:template match="submit">
        <input type="submit" name="accion" value="{@name}" title="{@title}">
            <xsl:copy-of select="@tabindex | @onclick"/>
        </input>
    </xsl:template>

    <xsl:template match="parameter">
        <input type="hidden">
            <xsl:copy-of select="@name | @value"/>
        </input>
    </xsl:template>

    <!-- Sin este template, el body se presenta 2 veces. AG (8-abr-2005)-->
    <xsl:template match="body">
        <xsl:apply-templates/>
    </xsl:template>

    <!-- Todo lo demás que no conoce lo copia -->
    <xsl:template match="@*|node()" priority="-1">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>
