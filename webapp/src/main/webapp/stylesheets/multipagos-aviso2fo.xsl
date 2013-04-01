<?xml version="1.0"?>
<!-- Hoja para la generación de cupones en formato PDF -->
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:fo="http://www.w3.org/1999/XSL/Format"
  xmlns:xi="http://www.w3.org/2001/XInclude">

<xsl:param name="baseUrl"/>


    <!-- Inicio de la página -->
    <xsl:template match="document">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <!-- Creamos el "fo:simple-page-master" para indicar las propiedades de la "pagina" -->
                <xsl:apply-templates select="." mode="layout"/>
            </fo:layout-master-set>
            <xsl:apply-templates/>
        </fo:root>
    </xsl:template>

    <xsl:template match="pagina" mode="layout">
        <fo:simple-page-master master-name="page"
                page-height="11in" page-width="8.5in"
                margin-top="1cm" margin-bottom="1cm"
                margin-left="1.7cm" margin-right="1cm">
                <!-- Otras propiedades -->
                <xsl:if test="@page-height">
                    <xsl:attribute name="page-height"><xsl:value-of select="@page-height"/>in</xsl:attribute>
                </xsl:if>
                <xsl:if test="@page-width">
                    <xsl:attribute name="page-width"><xsl:value-of select="@page-width"/>in</xsl:attribute>
                </xsl:if>
                <xsl:if test="@margin-top">
                    <xsl:attribute name="margin-top"><xsl:value-of select="@margin-top"/>cm</xsl:attribute>
                </xsl:if>
                <xsl:if test="@margin-bottom">
                    <xsl:attribute name="margin-bottom"><xsl:value-of select="@margin-bottom"/>cm</xsl:attribute>
                </xsl:if>
                <xsl:if test="@margin-left">
                    <xsl:attribute name="margin-left"><xsl:value-of select="@margin-left"/>cm</xsl:attribute>
                </xsl:if>
                <xsl:if test="@margin-right">
                    <xsl:attribute name="margin-right"><xsl:value-of select="@margin-right"/>cm</xsl:attribute>
                </xsl:if>
            <!-- <fo:region-before extent="1cm"/>
            <fo:region-body margin-top="0cm"/>
            <fo:region-after extent="1cm"/> -->
            <fo:region-before extent="1cm" region-name="header-next"/>
            <fo:region-body margin-top="0cm" region-name="body-next"/>
            <fo:region-after extent="1cm" region-name="after-next"/>
        </fo:simple-page-master>

        <fo:page-sequence-master master-name="pages">
            <fo:repeatable-page-master-reference master-reference="page"/>
        </fo:page-sequence-master>
    </xsl:template>

    <xsl:template match="pagina">
        <fo:page-sequence force-page-count="no-force" master-reference="pages">
            <!-- Número inicial de la página -->
            <xsl:if test="@initial-page-number &gt; 0">
                <xsl:attribute name="initial-page-number"><xsl:value-of select="@initial-page-number"/></xsl:attribute>
            </xsl:if>
            <!-- Indicamos que el número de la página lo queremos al inicio de la página-->
            <xsl:if test="@page-number-top">
                <fo:static-content flow-name="header-next">
                    <fo:block font-size="10pt" text-align="right"><fo:page-number/> de <fo:page-number-citation ref-id="last-page"/></fo:block>
                </fo:static-content>
            </xsl:if>
            <!-- Indicamos que el número de página lo queremos en el final de la página -->
            <xsl:if test="@page-number-bottom">
                <fo:static-content flow-name="after-next">
                    <fo:block font-size="10pt" text-align="center"><fo:page-number/> de <fo:page-number-citation ref-id="last-page"/></fo:block>
                </fo:static-content>
            </xsl:if>
            
            <!--  Cuerpo donde va el contenido de la página -->
            <fo:flow flow-name="body-next">
               
	             
                <xsl:apply-templates/>
                <!-- Bloque vacío para poder tomar el total de páginas del documento el id es
                     importante para luego hacer referencia hacia el
                     Hay situaciones en las que esto no funciona como son:
                     VER FAQ de FOP -->
                <fo:block id="last-page"/>
               
            </fo:flow>
        </fo:page-sequence>
    </xsl:template>
    
    <xsl:template match="logos">
	    <fo:block margin-top="0pt" margin-left="110pt">
	      <fo:external-graphic width="150pt" height="40pt" src="{$baseUrl}/images/multipagos.jpg"/>
	      <fo:external-graphic content-width="120pt" content-height="6pt" display-align="before" src="{$baseUrl}/images/separator2.gif"/>
	      <fo:external-graphic width="30pt" height="30pt" src="{$baseUrl}/images/claro.gif"/>
	      <xsl:apply-templates/>
	    </fo:block>
	</xsl:template>

    <!-- Hace un salto de página -->
    <xsl:template match="page-break">
        <fo:block break-before="page"/>
    </xsl:template>

    <!-- Estilo de encabezado -->
    <xsl:template match="encabezado">
        <fo:block font-size="14pt" font-weight="bold" space-before.optimum="16pt" text-align="center">
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <!-- Estilo de texto normal -->
    <xsl:template match="texto">
        <fo:block space-before.precedence="12" space-after.precedence="12" text-align="justify">
            <xsl:choose>
                <xsl:when test="@font-size">
                    <xsl:attribute name="font-size"><xsl:value-of select="@font-size"/>pt</xsl:attribute>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="font-size">10pt</xsl:attribute>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <xsl:template match="titulo|espacio">
        <fo:block font-size="16pt" font-weight="bold" space-before.optimum="8pt">
            <xsl:attribute name="text-align">
                <xsl:choose>
                    <xsl:when test="@align"><xsl:value-of select="@align"/></xsl:when>
                    <xsl:otherwise>left</xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <xsl:template match="row">
        <fo:block font-size="11pt" space-before.optimum="8pt" text-align="left">
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <xsl:template match="centrado">
        <fo:block font-size="10pt" space-before.precedence="0" text-align="center">
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <xsl:template match="sp">
        <fo:block font-size="10pt" space-before.precedence="10" space-after.precedence="10" text-align="justify">
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <xsl:template match="p">
        <fo:block font-size="10pt" space-before.precedence="0" text-align="left">
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <xsl:template match="b">
        <fo:inline font-weight="bold">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>

    <xsl:template match="line">
        <fo:block font-size="12pt" space-before.optimum="10" text-align="left">
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <xsl:template match="derecha">
        <fo:block space-before.optimum="10" text-align="right">
            <xsl:choose>
                <xsl:when test="@font-size">
                    <xsl:attribute name="font-size"><xsl:value-of select="@font-size"/>pt</xsl:attribute>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="font-size">10pt</xsl:attribute>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <xsl:template match="centro">
        <fo:block space-before.optimum="10" text-align="center">
            <xsl:choose>
                <xsl:when test="@font-size">
                    <xsl:attribute name="font-size"><xsl:value-of select="@font-size"/>pt</xsl:attribute>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="font-size">10pt</xsl:attribute>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <!-- Cambia el texto a 'underline' -->
    <xsl:template match="u">
        <fo:inline text-decoration="underline">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>

    <!-- quita underline, bold e italic. Conserva el tamaño de la fuente -->
    <xsl:template match="normal">
        <fo:inline text-decoration="none" font-style="normal" font-weight="normal">
            <xsl:apply-templates/>
        </fo:inline>
     </xsl:template>

     <!-- Cambia el texto a italic -->
    <xsl:template match="i">
        <fo:inline font-style="italic">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>

    <!-- Usa fuente más pequeña que la actual. Conserva las otras propiedades (italic, bold, etc) -->
    <xsl:template match="small">
        <fo:inline font-size="80%">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>

    <xsl:template match="br">
        <fo:block>
            <xsl:choose>
                <xsl:when test="@space-before-optimum">
                    <xsl:attribute name="space-before.optimum">
                        <xsl:value-of select="@space-before-optimum"/>pt</xsl:attribute>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="space-before.optimum">3pt</xsl:attribute>
                </xsl:otherwise>
            </xsl:choose>
        </fo:block>
    </xsl:template>

    <!-- Tabla -->
    <xsl:template match="table">
        <!-- Actualmente el único valor soportado es "fixed"
             table-omit-header-at-break sirve para que el encabezado
             de la table salga en cada página -->
        <fo:table table-layout="fixed" table-omit-header-at-break="false">
            <!-- Especificar el tamaño de las columnas de la tabla.
                 Enviamos el primer thead o el primer tbody y el último tr
                 de cualquiera de ellos, en este último se supone que estan
                 todas la columnas que pueden aparecer, entonces, de este último
                 se toman los tamaños de las columnas -->
            <xsl:apply-templates mode="columns" select="(node()[name()='thead' or name()='tbody'])[1]/tr[last()]"/>
            <xsl:apply-templates/>
        </fo:table>
    </xsl:template>

    <!-- Encabezado y cuerpo de una tabla -->
    <xsl:template match="thead|tbody">
        <fo:table-body>
            <xsl:apply-templates />
        </fo:table-body>
    </xsl:template>

    <!--
        Template para dar el tamaño a cada columna de
        de la tabla. FOP exige que se indique el tamaño
        máximo de la tabla o el tamaño para cada columna
    -->
    <xsl:template mode="columns" match="tr">
        <xsl:apply-templates mode="columns" select="th|td"/>
    </xsl:template>

    <xsl:template mode="columns" match="th|td">
        <fo:table-column column-number="1" column-width="1in">
            <xsl:attribute name="column-number"><xsl:value-of select="position()"/></xsl:attribute>
            <xsl:if test="@width">
                <xsl:attribute name="column-width">
                    <xsl:value-of select="@width"/>in</xsl:attribute>
            </xsl:if>
        </fo:table-column>
    </xsl:template>

    <!-- Filas -->
    <xsl:template match="tr">
        <fo:table-row>
            <xsl:apply-templates/>
        </fo:table-row>
    </xsl:template>

    <!--
        Encabezados de tabla. Son de tipo bold y centrados de forma implícita
        Sino verificamos que en <table> no se ha especificado width.
    -->
    <xsl:template match="th">
        <fo:table-cell font-weight="bold">
            <xsl:if test="ancestor::table[1]/@border > 0 or @border > 0">
                <xsl:attribute name="border-style">solid</xsl:attribute>
                <xsl:attribute name="border-width">0.1pt</xsl:attribute>
            </xsl:if>
            <xsl:if test="ancestor::table[1]/@border-left > 0 or @border-left > 0">
                <xsl:attribute name="border-left-style">solid</xsl:attribute>
                <xsl:attribute name="border-left-width">0.1pt</xsl:attribute>
            </xsl:if>
            <xsl:if test="ancestor::table[1]/@border-right > 0 or @border-right > 0">
                <xsl:attribute name="border-right-style">solid</xsl:attribute>
                <xsl:attribute name="border-right-width">0.1pt</xsl:attribute>
            </xsl:if>
            <xsl:if test="ancestor::table[1]/@border-top > 0 or @border-top > 0">
                <xsl:attribute name="border-top-style">solid</xsl:attribute>
                <xsl:attribute name="border-top-width">0.1pt</xsl:attribute>
            </xsl:if>
            <xsl:if test="ancestor::table[1]/@border-bottom > 0 or @border-bottom >0">
                <xsl:attribute name="border-bottom-style">solid</xsl:attribute>
                <xsl:attribute name="border-bottom-width">0.1pt</xsl:attribute>
            </xsl:if>
            <xsl:if test="@colspan">
                <xsl:attribute name="number-columns-spanned"><xsl:value-of select="@colspan"/></xsl:attribute>
            </xsl:if>
            <xsl:if test="@rowspan">
                <xsl:attribute name="number-rows-spanned"><xsl:value-of select="@rowspan"/></xsl:attribute>
            </xsl:if>
            <fo:block font-size="10pt">
                <!-- configuramos alineación para que sea igual al de la etiqueta <td> -->
                <xsl:choose>
                    <xsl:when test="@align='left'">
                        <xsl:attribute name="text-align">start</xsl:attribute>
                    </xsl:when>
                    <xsl:when test="@align='center'">
                        <xsl:attribute name="text-align">center</xsl:attribute>
                    </xsl:when>
                    <xsl:when test="@align='right'">
                        <xsl:attribute name="text-align">end</xsl:attribute>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:attribute name="text-align">center</xsl:attribute>
                    </xsl:otherwise>
                </xsl:choose>
                <xsl:if test="@space-before">
                    <xsl:attribute name="space-before"><xsl:value-of select="@space-before"/>em</xsl:attribute>
                </xsl:if>
                <xsl:if test="@start-indent">
                    <xsl:attribute name="start-indent"><xsl:value-of select="@start-indent"/>em</xsl:attribute>
                </xsl:if>
                <xsl:if test="@end-indent">
                    <xsl:attribute name="end-indent"><xsl:value-of select="@end-indent"/>em</xsl:attribute>
                </xsl:if>
                <xsl:if test="@font-size">
                    <xsl:attribute name="font-size"><xsl:value-of select="@font-size"/>pt</xsl:attribute>
                </xsl:if>
                <xsl:apply-templates/>
            </fo:block>
        </fo:table-cell>
    </xsl:template>

    <!--
        Celdas de datos. Verificamos que en la etiqueta <table> no se ha especificado width.
        Nota:

        collapse-with-precedence => manda el siguiente error:
            Unknown enumerated value for property 'border-collapse': collapse-with-precedence
            Error in border-collapse property value 'collapse-with-precedence': org.apache.fop.fo.expr.PropertyException: No conversion defined
            por eso usamos collapse, aunque en el libro de XSL-FO aparece como válido el valor
    -->
    <xsl:template match="td">
        <fo:table-cell border-collapse="collapse">
            <xsl:if test="ancestor::table[1]/@border > 0 or @border > 0">
                <xsl:attribute name="border-style">solid</xsl:attribute>
                <xsl:attribute name="border-width">0.1pt</xsl:attribute>
            </xsl:if>
            <xsl:if test="ancestor::table[1]/@border-left > 0 or @border-left > 0">
                <xsl:attribute name="border-left-style">solid</xsl:attribute>
                <xsl:attribute name="border-left-width">0.1pt</xsl:attribute>
            </xsl:if>
            <xsl:if test="ancestor::table[1]/@border-right > 0 or @border-right > 0">
                <xsl:attribute name="border-right-style">solid</xsl:attribute>
                <xsl:attribute name="border-right-width">0.1pt</xsl:attribute>
            </xsl:if>
            <xsl:if test="ancestor::table[1]/@border-top > 0 or @border-top > 0">
                <xsl:attribute name="border-top-style">solid</xsl:attribute>
                <xsl:attribute name="border-top-width">0.1pt</xsl:attribute>
            </xsl:if>
            <xsl:if test="ancestor::table[1]/@border-bottom or @border-bottom > 0">
                <xsl:attribute name="border-bottom-style">solid</xsl:attribute>
                <xsl:attribute name="border-bottom-width">0.1pt</xsl:attribute>
            </xsl:if>
            <xsl:if test="@colspan">
                <xsl:attribute name="number-columns-spanned"><xsl:value-of select="@colspan"/></xsl:attribute>
            </xsl:if>
            <xsl:if test="@rowspan">
                <xsl:attribute name="number-rows-spanned"><xsl:value-of select="@rowspan"/></xsl:attribute>
            </xsl:if>
            <fo:block font-size="10pt">
                <!-- configuramos alineación para que sea igual al de la etiqueta <td> -->
                <xsl:choose>
                    <xsl:when test="@align='left'">
                        <xsl:attribute name="text-align">start</xsl:attribute>
                    </xsl:when>
                    <xsl:when test="@align='center'">
                        <xsl:attribute name="text-align">center</xsl:attribute>
                    </xsl:when>
                    <xsl:when test="@align='right'">
                        <xsl:attribute name="text-align">end</xsl:attribute>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:attribute name="text-align">start</xsl:attribute>
                    </xsl:otherwise>
                </xsl:choose>
                <xsl:if test="@space-before">
                    <xsl:attribute name="space-before"><xsl:value-of select="@space-before"/>em</xsl:attribute>
                </xsl:if>
                <xsl:if test="@start-indent">
                    <xsl:attribute name="start-indent"><xsl:value-of select="@start-indent"/>em</xsl:attribute>
                </xsl:if>
                <xsl:if test="@end-indent">
                    <xsl:attribute name="end-indent"><xsl:value-of select="@end-indent"/>em</xsl:attribute>
                </xsl:if>
                <xsl:if test="@font-size">
                    <xsl:attribute name="font-size"><xsl:value-of select="@font-size"/>pt</xsl:attribute>
                </xsl:if>
                <xsl:apply-templates/>
            </fo:block>
        </fo:table-cell>
    </xsl:template>
    
    <xsl:template match="imge">
      <fo:block>
       <fo:external-graphic width="@width" height="@height" src="{$baseUrl}{@src}"/>
      </fo:block>
    </xsl:template>

    <xsl:template match="imagen">
        <fo:block>
            <fo:instream-foreign-object>
              <xi:include href="{@href}" xpointer="xpointer(/node())"/>
            </fo:instream-foreign-object>
       </fo:block>
    </xsl:template>
  
</xsl:stylesheet>
