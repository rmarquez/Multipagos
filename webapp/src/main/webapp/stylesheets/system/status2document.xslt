<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:status="http://apache.org/cocoon/status/2.0"
    xmlns:i18n="http://apache.org/cocoon/i18n/2.1" i18n:attr="language"
    xmlns:xi="http://www.w3.org/2001/XInclude">

    <xsl:template match="status:statusinfo">
        <document menu="true">
            <header>
                <title>Acerca</title>
            </header>
            <body>
                <s1 title="Información del Sistema">
                    <p class="descripcion">
                        <span class="descripcion">Elaborado por: </span><xi:include href="context:///resources/datosapp.xml#xpointer(/aplicacion/elaborado/node())"/><br/>
                        <span class="descripcion">Programación: </span><xi:include href="context:///resources/datosapp.xml#xpointer(/aplicacion/html/meta[1]/@content)"/><br/>
                        <!--<span class="descripcion">Interfaz Gráfica: </span><xi:include href="context:///resources/datosapp.xml#xpointer(/aplicacion/html/meta[2]/@content)"/><br/> -->
                        <span class="descripcion">Documentación: </span><xi:include href="context:///resources/datosapp.xml#xpointer(/aplicacion/documentacion/node())"/></p>
                </s1>
                <s1 title="Información del Servidor">
                    <p class="descripcion">
                        <span class="descripcion">Nombre del Servidor: </span>
                        <xsl:value-of select="@status:host"/>
                        <br/>
                        <span class="descripcion">Versión de Cocoon: </span>
                        <xsl:value-of select="@status:cocoon-version"/>
                        <br/>
                        <span class="descripcion">Fecha y Hora del Sistema: </span>
                        <i18n:date-time src-pattern="MM-dd-yyyy hh:mm:ss aa" pattern="dd 'de' MMMM 'de' yyyy hh:mm:ss aa">
                            <xsl:value-of select="@status:date"/>
                        </i18n:date-time>
                    </p>
                    <xsl:apply-templates/>
                </s1>
            </body>
        </document>
    </xsl:template>

    <xsl:template match="status:group">
        <s2 i18n:attr="title">
            <xsl:attribute name="title">
                <xsl:value-of select="@status:name"/>
            </xsl:attribute>
            <ul>
                <xsl:apply-templates select="status:value"/>
            </ul>
            <xsl:apply-templates select="status:group"/>
        </s2>
    </xsl:template>

    <xsl:template match="status:value">
        <li class="descripcion">
            <span class="descripcion">
                <i18n:text>
                    <xsl:value-of select="@status:name"/>
                </i18n:text>: </span>
            <xsl:choose>
                <xsl:when test="contains(@status:name,'free') or contains(@status:name,'total')">
                    <xsl:call-template name="suffix">
                        <xsl:with-param name="bytes" select="number(.)"/>
                    </xsl:call-template>
                </xsl:when>
                <xsl:when test="count(status:line) &lt;= 1">
                    <xsl:value-of select="status:line"/>
                </xsl:when>
                <xsl:otherwise>
                    <span class="switch" id="{generate-id(.)}-switch" onclick="toggle('{generate-id(.)}')">[ver]</span>
                    <ul id="{generate-id(.)}" style="display: none">
                        <xsl:apply-templates/>
                    </ul>
                </xsl:otherwise>
            </xsl:choose>
        </li>
    </xsl:template>

    <xsl:template match="status:line">
        <li>
            <xsl:value-of select="."/>
        </li>
    </xsl:template>

    <xsl:template name="suffix">
        <xsl:param name="bytes"/>
        <xsl:choose>
			<!-- More than 4 MB (=4194304) -->
            <xsl:when test="$bytes &gt;= 4194304">
                <xsl:value-of select="round($bytes div 10485.76) div 100"/> MB
			</xsl:when>
			<!-- More than 4 KB (=4096) -->
            <xsl:when test="$bytes &gt; 4096">
                <xsl:value-of select="round($bytes div 10.24) div 100"/> KB
			</xsl:when>
			<!-- Less -->
            <xsl:otherwise>
                <xsl:value-of select="$bytes"/> B
			</xsl:otherwise>
        </xsl:choose>
    </xsl:template>

</xsl:stylesheet>

