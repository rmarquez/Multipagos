<?xml version="1.0"?>
<!--
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->

<!--+
    | This stylesheets transforms hello world XML page to ZIP serializer's format.
    | After the serializer, result is an OpenOffice Writer document (sxw).
    |
    | @author <a href="mailto:vgritsenko@apache.org>Vadim Gritsenko</a>
    | @version CVS $Id: page2sxw.xsl 433543 2006-08-22 06:22:54Z crossley $
    +-->

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:zip="http://apache.org/cocoon/zip-archive/1.0"
                xmlns:text="http://openoffice.org/2000/text">

  <xsl:template match="document">
    <zip:archive>
      <!--
        <!DOCTYPE office:document-content PUBLIC "-//OpenOffice.org//DTD OfficeDocument 1.0//EN" "office.dtd">
      -->
      <zip:entry name="content.xml" serializer="xml">
        <office:document-content xmlns:office="http://openoffice.org/2000/office"
                                 xmlns:style="http://openoffice.org/2000/style"
                                 xmlns:text="http://openoffice.org/2000/text"
                                 xmlns:table="http://openoffice.org/2000/table"
                                 xmlns:draw="http://openoffice.org/2000/drawing"
                                 xmlns:fo="http://www.w3.org/1999/XSL/Format"
                                 xmlns:xlink="http://www.w3.org/1999/xlink"
                                 xmlns:number="http://openoffice.org/2000/datastyle"
                                 xmlns:svg="http://www.w3.org/2000/svg"
                                 xmlns:chart="http://openoffice.org/2000/chart"
                                 xmlns:dr3d="http://openoffice.org/2000/dr3d"
                                 xmlns:math="http://www.w3.org/1998/Math/MathML"
                                 xmlns:form="http://openoffice.org/2000/form"
                                 xmlns:script="http://openoffice.org/2000/script"
                                 office:class="text" office:version="1.0">
          <office:script/>
          <office:font-decls>
            <style:font-decl style:name="Arial Unicode MS" fo:font-family="&apos;Arial Unicode MS&apos;" style:font-pitch="variable"/>
            <style:font-decl style:name="HG Mincho Light J" fo:font-family="&apos;HG Mincho Light J&apos;" style:font-pitch="variable"/>
            <style:font-decl style:name="Thorndale" fo:font-family="Thorndale" style:font-family-generic="roman" style:font-pitch="variable"/>
            <style:font-decl style:name="Albany" fo:font-family="Albany" style:font-family-generic="swiss" style:font-pitch="variable"/>
          </office:font-decls>
          <office:automatic-styles/>
          <office:body>
            <text:sequence-decls>
              <text:sequence-decl text:display-outline-level="0" text:name="Illustration"/>
              <text:sequence-decl text:display-outline-level="0" text:name="Table"/>
              <text:sequence-decl text:display-outline-level="0" text:name="Text"/>
              <text:sequence-decl text:display-outline-level="0" text:name="Drawing"/>
            </text:sequence-decls>
            <text:h text:style-name="Heading 1" text:level="1"><xsl:value-of select="title"/></text:h>
            <xsl:apply-templates/>
          </office:body>
        </office:document-content>
      </zip:entry>

      <!--
        <!DOCTYPE office:document-meta PUBLIC "-//OpenOffice.org//DTD OfficeDocument 1.0//EN" "office.dtd">
      -->
      <zip:entry name="meta.xml" serializer="xml">
        <office:document-meta xmlns:office="http://openoffice.org/2000/office"
                              xmlns:xlink="http://www.w3.org/1999/xlink"
                              xmlns:dc="http://purl.org/dc/elements/1.1/"
                              xmlns:meta="http://openoffice.org/2000/meta"
                              office:version="1.0">
          <office:meta>
            <meta:generator>OpenOffice.org 1.0.3 (Win32)</meta:generator>
            <dc:title><xsl:value-of select="title"/></dc:title>
            <dc:subject>Cocoon Hello World Sample Document</dc:subject>
            <meta:creation-date>2003-05-07T22:59:08</meta:creation-date>
            <dc:date>2003-05-07T23:01:22</dc:date>
            <dc:language>en-US</dc:language>
            <meta:editing-cycles>3</meta:editing-cycles>
            <meta:editing-duration>PT2M21S</meta:editing-duration>
            <meta:user-defined meta:name="Info 1"/>
            <meta:user-defined meta:name="Info 2"/>
            <meta:user-defined meta:name="Info 3"/>
            <meta:user-defined meta:name="Info 4"/>
            <meta:document-statistic meta:table-count="0" meta:image-count="0" meta:object-count="0" meta:page-count="1" meta:paragraph-count="2" meta:word-count="7" meta:character-count="34"/>
          </office:meta>
        </office:document-meta>
      </zip:entry>

      <!--
        <!DOCTYPE office:document-settings PUBLIC "-//OpenOffice.org//DTD OfficeDocument 1.0//EN" "office.dtd">
      -->
      <zip:entry name="settings.xml" serializer="xml">
        <office:document-settings xmlns:office="http://openoffice.org/2000/office"
                                  xmlns:xlink="http://www.w3.org/1999/xlink"
                                  xmlns:config="http://openoffice.org/2001/config"
                                  office:version="1.0">
          <office:settings>
            <config:config-item-set config:name="view-settings">
              <config:config-item config:name="ViewAreaTop" config:type="int">0</config:config-item>
              <config:config-item config:name="ViewAreaLeft" config:type="int">0</config:config-item>
              <config:config-item config:name="ViewAreaWidth" config:type="int">26222</config:config-item>
              <config:config-item config:name="ViewAreaHeight" config:type="int">15004</config:config-item>
              <config:config-item config:name="ShowRedlineChanges" config:type="boolean">true</config:config-item>
              <config:config-item config:name="ShowHeaderWhileBrowsing" config:type="boolean">false</config:config-item>
              <config:config-item config:name="ShowFooterWhileBrowsing" config:type="boolean">false</config:config-item>
              <config:config-item config:name="InBrowseMode" config:type="boolean">false</config:config-item>
              <config:config-item-map-indexed config:name="Views">
                <config:config-item-map-entry>
                  <config:config-item config:name="ViewId" config:type="string">view2</config:config-item>
                  <config:config-item config:name="ViewLeft" config:type="int">9202</config:config-item>
                  <config:config-item config:name="ViewTop" config:type="int">4410</config:config-item>
                  <config:config-item config:name="VisibleLeft" config:type="int">0</config:config-item>
                  <config:config-item config:name="VisibleTop" config:type="int">0</config:config-item>
                  <config:config-item config:name="VisibleRight" config:type="int">26220</config:config-item>
                  <config:config-item config:name="VisibleBottom" config:type="int">15002</config:config-item>
                  <config:config-item config:name="ZoomType" config:type="short">0</config:config-item>
                  <config:config-item config:name="ZoomFactor" config:type="short">100</config:config-item>
                  <config:config-item config:name="IsSelectedFrame" config:type="boolean">false</config:config-item>
                </config:config-item-map-entry>
              </config:config-item-map-indexed>
            </config:config-item-set>
            <config:config-item-set config:name="configuration-settings">
              <config:config-item config:name="AddParaTableSpacing" config:type="boolean">false</config:config-item>
              <config:config-item config:name="PrintReversed" config:type="boolean">false</config:config-item>
              <config:config-item config:name="LinkUpdateMode" config:type="short">1</config:config-item>
              <config:config-item config:name="CharacterCompressionType" config:type="short">0</config:config-item>
              <config:config-item config:name="PrintSingleJobs" config:type="boolean">false</config:config-item>
              <config:config-item config:name="PrintPaperFromSetup" config:type="boolean">false</config:config-item>
              <config:config-item config:name="PrintLeftPages" config:type="boolean">true</config:config-item>
              <config:config-item config:name="PrintTables" config:type="boolean">true</config:config-item>
              <config:config-item config:name="ChartAutoUpdate" config:type="boolean">true</config:config-item>
              <config:config-item config:name="PrintControls" config:type="boolean">true</config:config-item>
              <config:config-item config:name="PrinterSetup" config:type="base64Binary">kgP+/1xcdmdyaXRzZW5rb3BjMlxIUCBMYXNlckpldCA1TAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAd2luc3Bvb2wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAWAAEA2AIAAAAAAAAFAAhSAAAEdAAAM1ROVwEACABcXHZncml0c2Vua29wYzJcSFAgTGFzZXJKZXQgNUwAAAEEAAWcADQCQ++ABQEAAQCaCzQIZAABAA8AWAIBAAEAWAIDAAEAQTQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEAAAAAAAAAAQAAAAIAAAABAAAA/////wAAAAAAAAAAAAAAAAAAAABESU5VIgAAADQCAACjWpOwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=</config:config-item>
              <config:config-item config:name="PrintAnnotationMode" config:type="short">0</config:config-item>
              <config:config-item config:name="ApplyUserData" config:type="boolean">true</config:config-item>
              <config:config-item config:name="FieldAutoUpdate" config:type="boolean">true</config:config-item>
              <config:config-item config:name="SaveVersionOnClose" config:type="boolean">false</config:config-item>
              <config:config-item config:name="SaveGlobalDocumentLinks" config:type="boolean">false</config:config-item>
              <config:config-item config:name="IsKernAsianPunctuation" config:type="boolean">false</config:config-item>
              <config:config-item config:name="AlignTabStopPosition" config:type="boolean">false</config:config-item>
              <config:config-item config:name="CurrentDatabaseDataSource" config:type="string">Addresses</config:config-item>
              <config:config-item config:name="PrinterName" config:type="string">\\vgritsenkopc2\HP LaserJet 5L</config:config-item>
              <config:config-item config:name="PrintFaxName" config:type="string"/>
              <config:config-item config:name="PrintRightPages" config:type="boolean">true</config:config-item>
              <config:config-item config:name="AddParaTableSpacingAtStart" config:type="boolean">false</config:config-item>
              <config:config-item config:name="PrintProspect" config:type="boolean">false</config:config-item>
              <config:config-item config:name="PrintGraphics" config:type="boolean">true</config:config-item>
              <config:config-item config:name="CurrentDatabaseCommandType" config:type="int">0</config:config-item>
              <config:config-item config:name="PrintPageBackground" config:type="boolean">true</config:config-item>
              <config:config-item config:name="CurrentDatabaseCommand" config:type="string">Contacts</config:config-item>
              <config:config-item config:name="PrintDrawings" config:type="boolean">true</config:config-item>
              <config:config-item config:name="PrintBlackFonts" config:type="boolean">false</config:config-item>
            </config:config-item-set>
          </office:settings>
        </office:document-settings>
      </zip:entry>

      <!--
        <!DOCTYPE office:document-styles PUBLIC "-//OpenOffice.org//DTD OfficeDocument 1.0//EN" "office.dtd">
      -->
      <zip:entry name="styles.xml" serializer="xml">
        <office:document-styles xmlns:office="http://openoffice.org/2000/office"
                                xmlns:style="http://openoffice.org/2000/style"
                                xmlns:text="http://openoffice.org/2000/text"
                                xmlns:table="http://openoffice.org/2000/table"
                                xmlns:draw="http://openoffice.org/2000/drawing"
                                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                                xmlns:xlink="http://www.w3.org/1999/xlink"
                                xmlns:number="http://openoffice.org/2000/datastyle"
                                xmlns:svg="http://www.w3.org/2000/svg"
                                xmlns:chart="http://openoffice.org/2000/chart"
                                xmlns:dr3d="http://openoffice.org/2000/dr3d"
                                xmlns:math="http://www.w3.org/1998/Math/MathML"
                                xmlns:form="http://openoffice.org/2000/form"
                                xmlns:script="http://openoffice.org/2000/script"
                                office:version="1.0">
          <office:font-decls>
            <style:font-decl style:name="Arial Unicode MS" fo:font-family="&apos;Arial Unicode MS&apos;" style:font-pitch="variable"/>
            <style:font-decl style:name="HG Mincho Light J" fo:font-family="&apos;HG Mincho Light J&apos;" style:font-pitch="variable"/>
            <style:font-decl style:name="Thorndale" fo:font-family="Thorndale" style:font-family-generic="roman" style:font-pitch="variable"/>
            <style:font-decl style:name="Albany" fo:font-family="Albany" style:font-family-generic="swiss" style:font-pitch="variable"/>
          </office:font-decls>
          <office:styles>
            <style:default-style style:family="graphics">
              <style:properties draw:start-line-spacing-horizontal="0.283cm" draw:start-line-spacing-vertical="0.283cm" draw:end-line-spacing-horizontal="0.283cm" draw:end-line-spacing-vertical="0.283cm" fo:color="#000000" style:font-name="Thorndale" fo:font-size="12pt" fo:language="en" fo:country="US" style:font-name-asian="HG Mincho Light J" style:font-size-asian="12pt" style:language-asian="none" style:country-asian="none" style:font-name-complex="Arial Unicode MS" style:font-size-complex="12pt" style:language-complex="none" style:country-complex="none" style:text-autospace="ideograph-alpha" style:punctuation-wrap="simple" style:line-break="strict">
                <style:tab-stops/>
              </style:properties>
            </style:default-style>
            <style:default-style style:family="paragraph">
              <style:properties fo:color="#000000" style:font-name="Thorndale" fo:font-size="12pt" fo:language="en" fo:country="US" style:font-name-asian="HG Mincho Light J" style:font-size-asian="12pt" style:language-asian="none" style:country-asian="none" style:font-name-complex="Arial Unicode MS" style:font-size-complex="12pt" style:language-complex="none" style:country-complex="none" fo:hyphenate="false" fo:hyphenation-remain-char-count="2" fo:hyphenation-push-char-count="2" fo:hyphenation-ladder-count="no-limit" style:text-autospace="ideograph-alpha" style:punctuation-wrap="hanging" style:line-break="strict" style:tab-stop-distance="2.205cm" style:writing-mode="lr-tb"/>
            </style:default-style>
            <style:style style:name="Standard" style:family="paragraph" style:class="text"/>
            <style:style style:name="Text body" style:family="paragraph" style:parent-style-name="Standard" style:class="text">
              <style:properties fo:margin-top="0cm" fo:margin-bottom="0.212cm"/>
            </style:style>
            <style:style style:name="Heading" style:family="paragraph" style:parent-style-name="Standard" style:next-style-name="Text body" style:class="text">
              <style:properties fo:margin-top="0.423cm" fo:margin-bottom="0.212cm" style:font-name="Albany" fo:font-size="14pt" style:font-name-asian="HG Mincho Light J" style:font-size-asian="14pt" style:font-name-complex="Arial Unicode MS" style:font-size-complex="14pt" fo:keep-with-next="true"/>
            </style:style>
            <style:style style:name="Heading 1" style:family="paragraph" style:parent-style-name="Heading" style:next-style-name="Text body" style:class="text">
              <style:properties fo:font-size="115%" fo:font-weight="bold" style:font-size-asian="115%" style:font-weight-asian="bold" style:font-size-complex="115%" style:font-weight-complex="bold"/>
            </style:style>
            <text:outline-style>
              <text:outline-level-style text:level="1" style:num-format=""/>
              <text:outline-level-style text:level="2" style:num-format=""/>
              <text:outline-level-style text:level="3" style:num-format=""/>
              <text:outline-level-style text:level="4" style:num-format=""/>
              <text:outline-level-style text:level="5" style:num-format=""/>
              <text:outline-level-style text:level="6" style:num-format=""/>
              <text:outline-level-style text:level="7" style:num-format=""/>
              <text:outline-level-style text:level="8" style:num-format=""/>
              <text:outline-level-style text:level="9" style:num-format=""/>
              <text:outline-level-style text:level="10" style:num-format=""/>
            </text:outline-style>
            <text:footnotes-configuration style:num-format="1" text:start-value="0" text:footnotes-position="page" text:start-numbering-at="document"/>
            <text:endnotes-configuration style:num-format="i" text:start-value="0"/>
            <text:linenumbering-configuration text:number-lines="false" text:offset="0.499cm" style:num-format="1" text:number-position="left" text:increment="5"/>
          </office:styles>
          <office:automatic-styles>
            <style:page-master style:name="pm1"><style:properties fo:page-width="21.59cm" fo:page-height="27.94cm" style:num-format="1" style:print-orientation="portrait" fo:margin-top="2.54cm" fo:margin-bottom="2.54cm" fo:margin-left="3.175cm" fo:margin-right="3.175cm" style:footnote-max-height="0cm"><style:footnote-sep style:width="0.018cm" style:distance-before-sep="0.101cm" style:distance-after-sep="0.101cm" style:adjustment="left" style:rel-width="25%" style:color="#000000"/></style:properties><style:header-style/><style:footer-style/></style:page-master>
          </office:automatic-styles>
          <office:master-styles>
            <style:master-page style:name="Standard" style:page-master-name="pm1"/>
          </office:master-styles>
        </office:document-styles>
      </zip:entry>

      <!--
        <!DOCTYPE manifest:manifest PUBLIC "-//OpenOffice.org//DTD Manifest 1.0//EN" "Manifest.dtd">
      -->
      <zip:entry name="META-INF/manifest.xml" serializer="xml">
        <manifest:manifest xmlns:manifest="http://openoffice.org/2001/manifest">
          <manifest:file-entry manifest:media-type="application/vnd.sun.xml.writer" manifest:full-path="/"/>
          <manifest:file-entry manifest:media-type="" manifest:full-path="Pictures/"/>
          <manifest:file-entry manifest:media-type="text/xml" manifest:full-path="content.xml"/>
          <manifest:file-entry manifest:media-type="text/xml" manifest:full-path="styles.xml"/>
          <manifest:file-entry manifest:media-type="text/xml" manifest:full-path="meta.xml"/>
          <manifest:file-entry manifest:media-type="text/xml" manifest:full-path="settings.xml"/>
        </manifest:manifest>
      </zip:entry>
    </zip:archive>
  </xsl:template>
<!--
  <xsl:template match="title">
  </xsl:template>

  <xsl:template match="content">
    <xsl:apply-templates/>
  </xsl:template>

  <xsl:template match="para">
    <text:p text:style-name="Text body"><xsl:apply-templates/></text:p>
  </xsl:template>

  <xsl:template match="@*|node()" priority="-2"><xsl:copy><xsl:apply-templates select="@*|node()"/></xsl:copy></xsl:template>
  <xsl:template match="text()" priority="-1"><xsl:value-of select="."/></xsl:template>
-->

<xsl:template match="pagina" mode="layout">
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

    </xsl:template>

    <xsl:template match="pagina">

            <!-- Número inicial de la página -->
            <xsl:if test="@initial-page-number &gt; 0">
                <xsl:attribute name="initial-page-number"><xsl:value-of select="@initial-page-number"/></xsl:attribute>
            </xsl:if>
            <!-- Indicamos que el número de la página lo queremos al inicio de la página-->
            <xsl:if test="@page-number-top">

            </xsl:if>
            <!-- Indicamos que el número de página lo queremos en el final de la página -->
            <xsl:if test="@page-number-bottom">

            </xsl:if>
            <!--  Cuerpo donde va el contenido de la página -->
                <xsl:apply-templates/>


    </xsl:template>

    <!-- Hace un salto de página -->
    <xsl:template match="page-break">

    </xsl:template>

    <!-- Estilo de encabezado -->
    <xsl:template match="encabezado">

            <xsl:apply-templates/>

    </xsl:template>

    <!-- Estilo de texto normal -->
    <xsl:template match="texto">
            <xsl:choose>
                <xsl:when test="@font-size">
                    <xsl:attribute name="font-size"><xsl:value-of select="@font-size"/>pt</xsl:attribute>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="font-size">10pt</xsl:attribute>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="titulo|espacio">
            <xsl:attribute name="text-align">
                <xsl:choose>
                    <xsl:when test="@align"><xsl:value-of select="@align"/></xsl:when>
                    <xsl:otherwise>left</xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>
            <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="row">
            <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="centrado">
            <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="sp">
            <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="p">
            <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="b">
            <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="line">
            <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="derecha">

            <xsl:choose>
                <xsl:when test="@font-size">
                    <xsl:attribute name="font-size"><xsl:value-of select="@font-size"/>pt</xsl:attribute>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="font-size">10pt</xsl:attribute>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="centro">
            <xsl:choose>
                <xsl:when test="@font-size">
                    <xsl:attribute name="font-size"><xsl:value-of select="@font-size"/>pt</xsl:attribute>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="font-size">10pt</xsl:attribute>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:apply-templates/>
    </xsl:template>

    <!-- Cambia el texto a 'underline' -->
    <xsl:template match="u">
            <xsl:apply-templates/>
    </xsl:template>

    <!-- quita underline, bold e italic. Conserva el tamaño de la fuente -->
    <xsl:template match="normal">
            <xsl:apply-templates/>
     </xsl:template>

     <!-- Cambia el texto a italic -->
    <xsl:template match="i">
            <xsl:apply-templates/>
    </xsl:template>

    <!-- Usa fuente más pequeña que la actual. Conserva las otras propiedades (italic, bold, etc) -->
    <xsl:template match="small">
            <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="br">
            <xsl:choose>
                <xsl:when test="@space-before-optimum">
                    <xsl:attribute name="space-before.optimum">
                        <xsl:value-of select="@space-before-optimum"/>pt</xsl:attribute>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="space-before.optimum">3pt</xsl:attribute>
                </xsl:otherwise>
            </xsl:choose>
    </xsl:template>

    <!-- Tabla -->
    <xsl:template match="table">
            <!-- Especificar el tamaño de las columnas de la tabla.
                 Enviamos el primer thead o el primer tbody y el último tr
                 de cualquiera de ellos, en este último se supone que estan
                 todas la columnas que pueden aparecer, entonces, de este último
                 se toman los tamaños de las columnas -->
            <xsl:apply-templates mode="columns" select="(node()[name()='thead' or name()='tbody'])[1]/tr[last()]"/>
            <xsl:apply-templates/>
    </xsl:template>

    <!-- Encabezado y cuerpo de una tabla -->
    <xsl:template match="thead|tbody">
            <xsl:apply-templates />
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
            <xsl:attribute name="column-number"><xsl:value-of select="position()"/></xsl:attribute>
            <xsl:if test="@width">
                <xsl:attribute name="column-width">
                    <xsl:value-of select="@width"/>in</xsl:attribute>
            </xsl:if>
    </xsl:template>

    <!-- Filas -->
    <xsl:template match="tr">
            <xsl:apply-templates/>
    </xsl:template>

    <!--
        Encabezados de tabla. Son de tipo bold y centrados de forma implícita
        Sino verificamos que en <table> no se ha especificado width.
    -->
    <xsl:template match="th">
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
    </xsl:template>


</xsl:stylesheet>
