<?xml version="1.0"?>
<!-- Hoja para la generación del informe en formato PDF
     Autor: AG Software, S. A. "info@agssa.net"
     Version: 1.0
     Fecha: Enero-2007
-->
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

    <xsl:template match="page-first" mode="layout">
        <!--  Capa para las primeras páginas donde se coloca la etiqueta paginainicio-->
        <fo:simple-page-master master-name="page-first"
            page-height="27.94cm"
            page-width="21.59cm"
            margin-top="0.5cm"
            margin-bottom="0cm"
            margin-left="1cm"
            margin-right="1cm">
            <!-- Otras propiedades -->
            <xsl:if test="@page-height">
                <xsl:attribute name="page-height"><xsl:value-of select="@page-height"/>cm</xsl:attribute>
            </xsl:if>
            <xsl:if test="@page-width">
                <xsl:attribute name="page-width"><xsl:value-of select="@page-width"/>cm</xsl:attribute>
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
            <fo:region-before extent="2.5cm" region-name="header-first"/>
            <fo:region-body margin-top="2.4cm" margin-bottom="0cm" region-name="body-first"/>
            <fo:region-after extent="3cm" region-name="after-first"/>
        </fo:simple-page-master>
    </xsl:template>

    <xsl:template match="page-next" mode="layout">
        <!--  Capa siguiente -->
        <fo:simple-page-master master-name="page-next"
            page-height="27.94cm" 
            page-width="21.59cm"
            margin-top="0.5cm"
            margin-bottom="0cm"
            margin-left="1cm"
            margin-right="1cm">
            <!-- Otras propiedades -->
            <xsl:if test="@page-height">
                <xsl:attribute name="page-height"><xsl:value-of select="@page-height"/>cm</xsl:attribute>
            </xsl:if>
            <xsl:if test="@page-width">
                <xsl:attribute name="page-width"><xsl:value-of select="@page-width"/>cm</xsl:attribute>
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
            <fo:region-before extent="2.5cm" region-name="header-next"/>
            <fo:region-body margin-top="2.4cm" margin-bottom="0cm" region-name="body-next"/>
            <fo:region-after extent="3cm" region-name="after-next"/>
        </fo:simple-page-master>
    </xsl:template>

    <xsl:template match="page-first">
        <fo:page-sequence force-page-count="no-force" master-reference="page-first">
            <!-- Número inicial de la página -->
            <xsl:if test="@initial-page-number &gt; 0">
                <xsl:attribute name="initial-page-number"><xsl:value-of select="@initial-page-number"/></xsl:attribute>
            </xsl:if>
           <!-- Indicamos que el número de la página lo queremos al inicio de la página -->
            <xsl:if test="@page-number-top">
                <fo:static-content flow-name="before-first">
                    <fo:block font-size="10pt" text-align="right">
                      <fo:page-number/> de <fo:page-number-citation ref-id="last-page"/>
                    </fo:block>
                </fo:static-content>
            </xsl:if>
            <!-- Indicamos que el número de página lo queremos en el final de la página -->
            <xsl:if test="@page-number-bottom">
                <fo:static-content flow-name="after-first">
                    <fo:block font-size="10pt" text-align="center">
                      <fo:page-number/> de <fo:page-number-citation ref-id="last-page"/>
                    </fo:block>
                </fo:static-content>
            </xsl:if>
            <fo:static-content flow-name="header-first" padding-bottom="0pt" margin-bottom="0pt" padding-top="0pt" margin-top="0pt">
	            <fo:block  padding-bottom="0pt" margin-bottom="0pt">
	               <fo:external-graphic src="{$baseUrl}/images/multipagos.jpg"/>
	             </fo:block>
            </fo:static-content>
            <fo:static-content flow-name="after-first">
              <fo:block>
                <fo:external-graphic width="auto" height="auto" src="{$baseUrl}/images/claro3.jpg"/>
              </fo:block>
            </fo:static-content>
            <!--  Cuerpo donde va el contenido de la página -->
            <fo:flow flow-name="body-first">
              <xsl:if test="@border">
                <fo:block-container position="absolute" left="0.1pt" top="-0.48cm" width="19.58cm" height="22.3cm"
                                    border-color="black" border-style="solid">
                  <xsl:attribute name="border-width"><xsl:value-of select="@border"/>pt</xsl:attribute>
                  <xsl:if test="@color">
                    <xsl:attribute name="border-color"><xsl:value-of select="@color"/></xsl:attribute>
                  </xsl:if>
                </fo:block-container>
              </xsl:if>
              <xsl:apply-templates/>
            </fo:flow>
        </fo:page-sequence>
    </xsl:template>

    <xsl:template match="page-next">
      <fo:page-sequence force-page-count="no-force" master-reference="page-next">
        <!-- Número inicial de la página -->
        <xsl:if test="@initial-page-number &gt; 0">
            <xsl:attribute name="initial-page-number"><xsl:value-of select="@initial-page-number"/></xsl:attribute>
        </xsl:if>
        <!-- Indicamos que el número de la página lo queremos al inicio de la página -->
        <xsl:if test="@page-number-top">
          <fo:static-content flow-name="before-next">
            <fo:block font-size="10pt" text-align="right">
              <fo:page-number/> de <fo:page-number-citation ref-id="last-page"/>
            </fo:block>
          </fo:static-content>
        </xsl:if>
        <!-- Indicamos que el número de página lo queremos en el final de la página -->
        <xsl:if test="@page-number-bottom">
          <fo:static-content flow-name="after-next">
            <fo:block font-size="10pt" text-align="center">
              <fo:page-number/> de <fo:page-number-citation ref-id="last-page"/>
            </fo:block>
          </fo:static-content>
        </xsl:if>
        <fo:static-content flow-name="header-next">
            <fo:block>
              <fo:external-graphic width="auto" height="auto" src="{$baseUrl}/images/Logo_final.png"/>
            </fo:block>
        </fo:static-content>
        <fo:static-content flow-name="after-next">
          <fo:block>
             <fo:external-graphic width="auto" height="auto" src="{$baseUrl}/images/Logo_final.png"/>
          </fo:block>
        </fo:static-content>
        <!--  Cuerpo donde va el contenido de la página -->
        <fo:flow flow-name="body-next">
          <xsl:if test="@border">
            <fo:block-container position="absolute" left="0.1pt" top="-0.48cm" width="19.58cm" height="22.3cm"
                                border-color="black" border-style="solid">
              <xsl:attribute name="border-width"><xsl:value-of select="@border"/>pt</xsl:attribute>
              <xsl:if test="@color">
                <xsl:attribute name="border-color"><xsl:value-of select="@color"/></xsl:attribute>
              </xsl:if>
            </fo:block-container>
          </xsl:if>
          <xsl:apply-templates/>
          <!-- Bloque vacío para poder tomar el total de páginas del documento el id es
               importante para luego hacer referencia hacia el
               Hay situaciones en las que esto no funciona como son:
               VER FAQ de FOP -->
          <fo:block id="last-page"/>
        </fo:flow>
      </fo:page-sequence>
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
        <fo:block space-before.precedence="12" space-after.precedence="12" margin-left="0.3cm" margin-right="0.3cm">
            <xsl:choose>
                <xsl:when test="@font-size">
                    <xsl:attribute name="font-size"><xsl:value-of select="@font-size"/>pt</xsl:attribute>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="font-size">10pt</xsl:attribute>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:choose>
                <xsl:when test="@text-align">
                    <xsl:attribute name="text-align"><xsl:value-of select="@text-align"/></xsl:attribute>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="text-align">justify</xsl:attribute>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:if test="@font">
              <xsl:attribute name="font-family"><xsl:value-of select="@font"/></xsl:attribute>
            </xsl:if>
            <xsl:if test="@color">
              <xsl:attribute name="color"><xsl:value-of select="@color"/></xsl:attribute>
            </xsl:if>
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>

    <xsl:template match="lista">
      <fo:list-item>
       <fo:list-item-label end-indent="label-end()">
          <fo:block>&#x2022;</fo:block><!--Corresponde a &bull;-->
       </fo:list-item-label>
       <fo:list-item-body start-indent="body-start()">
          <fo:block><xsl:value-of select="."/></fo:block>
       </fo:list-item-body>
     </fo:list-item>
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

    <xsl:template match="linerule">
        <fo:block>
            <fo:leader leader-pattern="rule" rule-thickness="0.5mm" leader-length="100%" color="black"/>
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

    <!-- Encabezado de una tabla -->
    <xsl:template match="thead">
        <fo:table-header>
          <xsl:apply-templates/>
        </fo:table-header>
    </xsl:template>

    <!-- Cuerpo de una tabla -->
    <xsl:template match="tbody">
        <fo:table-body>
            <xsl:apply-templates/>
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

    <!-- FOP-0.20.5 no acepta column-width con porcentajes. -->
    <xsl:template mode="columns" match="th|td">
        <fo:table-column column-number="1" column-width="1in">
            <xsl:attribute name="column-number"><xsl:value-of select="position()"/></xsl:attribute>
            <xsl:if test="@width">
                <xsl:attribute name="column-width"><xsl:value-of select="@width"/>cm</xsl:attribute>
            </xsl:if>
        </fo:table-column>
    </xsl:template>

    <!-- Filas
      La propiedad keep-together="always" funciona con FOP-0.20.5 si la versión cambia especificar esta
      propiedad en las definiciones de fo:table-cell correspondientes.-->
    <xsl:template match="tr">
        <fo:table-row>
          <xsl:if test="@keep-together">
            <xsl:attribute name="keep-together"><xsl:value-of select="@keep-together"/></xsl:attribute>
          </xsl:if>
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
                <xsl:if test="@font">
                  <xsl:attribute name="font-family"><xsl:value-of select="@font"/></xsl:attribute>
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
       hyphenate=true divide las palabras si sobrepasa el ancho de la celda de acuerdo al lenguaje especificado.
    -->
    <xsl:template match="td">
		<fo:table-cell border-collapse="collapse" hyphenate="true" language="es">
            <xsl:if test="ancestor::table[1]/@border > 0 or @border > 0">
                <xsl:attribute name="border-style">solid</xsl:attribute>
                <xsl:attribute name="border-width">0.5pt</xsl:attribute><!-- 0.05, 0.5 -->
                <xsl:if test="@border != 1">
                  <xsl:attribute name="border-width"><xsl:value-of select="@border"/>pt</xsl:attribute>
                </xsl:if>
            </xsl:if>
            <xsl:if test="@border-color">
              <xsl:attribute name="border-color"><xsl:value-of select="@border-color"/></xsl:attribute>
            </xsl:if>
            <xsl:if test="@line-height">
              <xsl:attribute name="line-height"><xsl:value-of select="@line-height"/>cm</xsl:attribute>
            </xsl:if>
            <xsl:if test="ancestor::table[1]/@border-left > 0 or @border-left > 0">
                <xsl:attribute name="border-left-style">solid</xsl:attribute>
                <xsl:attribute name="border-left-width">0.5pt</xsl:attribute>
                <xsl:if test="@border-left != 1">
                  <xsl:attribute name="border-left-width"><xsl:value-of select="@border-left"/>mm</xsl:attribute>
                </xsl:if>
            </xsl:if>
            <xsl:if test="ancestor::table[1]/@border-right > 0 or @border-right > 0">
                <xsl:attribute name="border-right-style">solid</xsl:attribute>
                <xsl:attribute name="border-right-width">0.5pt</xsl:attribute>
                <xsl:if test="@border-right != 1">
                  <xsl:attribute name="border-right-width"><xsl:value-of select="@border-right"/>mm</xsl:attribute>
                </xsl:if>
            </xsl:if>
            <xsl:if test="ancestor::table[1]/@border-top > 0 or @border-top > 0">
                <xsl:attribute name="border-top-style">solid</xsl:attribute>
                <xsl:attribute name="border-top-width">0.5pt</xsl:attribute>
                <xsl:if test="@border-top != 1">
                  <xsl:attribute name="border-top-width"><xsl:value-of select="@border-top"/>mm</xsl:attribute>
                </xsl:if>
            </xsl:if>
            <xsl:if test="ancestor::table[1]/@border-bottom or @border-bottom > 0">
                <xsl:attribute name="border-bottom-style">solid</xsl:attribute>
                <xsl:attribute name="border-bottom-width">0.5pt</xsl:attribute>
                <xsl:if test="@border-bottom != 1">
                  <xsl:attribute name="border-bottom-width"><xsl:value-of select="@border-bottom"/>mm</xsl:attribute>
                </xsl:if>
            </xsl:if>
            <xsl:if test="@colspan">
                <xsl:attribute name="number-columns-spanned"><xsl:value-of select="@colspan"/></xsl:attribute>
            </xsl:if>
            <xsl:if test="@rowspan">
                <xsl:attribute name="number-rows-spanned"><xsl:value-of select="@rowspan"/></xsl:attribute>
            </xsl:if>
            <fo:block font-size="10pt" margin-left="2.5pt" margin-right="2.5pt" padding-top="3pt" padding-bottom="3pt"
                      color="black"><!-- fondo de celda, color de letra -->
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
                    <xsl:when test="@align='justify'">
                        <xsl:attribute name="text-align">justify</xsl:attribute>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:attribute name="text-align">start</xsl:attribute>
                    </xsl:otherwise>
                </xsl:choose>
                <xsl:if test="@margin-left != 2.5">
                    <xsl:attribute name="margin-left"><xsl:value-of select="@margin-left"/>pt</xsl:attribute>
                </xsl:if>
                <xsl:if test="@margin-right != 2.5">
                    <xsl:attribute name="margin-right"><xsl:value-of select="@margin-right"/>pt</xsl:attribute>
                </xsl:if>
                <xsl:if test="@padding-top != 3">
                    <xsl:attribute name="padding-top"><xsl:value-of select="@padding-top"/>pt</xsl:attribute>
                </xsl:if>
                <xsl:if test="@padding-bottom != 3">
                    <xsl:attribute name="padding-bottom"><xsl:value-of select="@padding-bottom"/>pt</xsl:attribute>
                </xsl:if>
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
                <xsl:if test="@font">
                  <xsl:attribute name="font-family"><xsl:value-of select="@font"/></xsl:attribute>
                </xsl:if>
                <xsl:if test="@background-color">
                    <xsl:attribute name="background-color"><xsl:value-of select="@background-color"/></xsl:attribute>
                </xsl:if>
                <xsl:if test="@color">
                  <xsl:attribute name="color"><xsl:value-of select="@color"/></xsl:attribute>
                </xsl:if>
                <xsl:apply-templates/>
            </fo:block>
        </fo:table-cell>
    </xsl:template>

    <xsl:template match="piepagina">
      <fo:footnote>
        <fo:inline>
            <fo:inline font-size="75%" font-weight="normal" font-style="normal" baseline-shift="super"/>
        </fo:inline>
        <fo:footnote-body font-family="Times Roman" font-size="10pt" font-weight="normal" font-style="normal"
                          text-align="center" start-indent="0pt" text-indent="0pt">
          <xsl:if test="@font">
            <xsl:attribute name="font-family"><xsl:value-of select="@font"/></xsl:attribute>
          </xsl:if>
          <xsl:if test="@font-size">
            <xsl:attribute name="font-size"><xsl:value-of select="@font-size"/>pt</xsl:attribute>
          </xsl:if>
          <xsl:if test="@text-align">
            <xsl:attribute name="text-align"><xsl:value-of select="@text-align"/></xsl:attribute>
          </xsl:if>
          <fo:block>
            <xsl:apply-templates/>
          </fo:block>
        </fo:footnote-body>
     </fo:footnote>
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