<?xml version="1.0"?>

<!--
     Hoja para la generación de Informes en Hojas de Cálculo -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:gmr="http://www.gnome.org/gnumeric/v7">
	<xsl:template match="document">
		<gmr:Workbook xmlns:gmr="http://www.gnome.org/gnumeric/v10.dtd">
			<gmr:SheetNameIndex>
				<gmr:SheetName><xsl:value-of select="title"/></gmr:SheetName>
			</gmr:SheetNameIndex>
			<gmr:Names/>
			<gmr:Geometry Width="983" Height="528"/>
			<gmr:Sheets>
				<gmr:Sheet DisplayFormulas="false" HideZero="false" HideGrid="false" HideColHeader="false"
							HideRowHeader="false" DisplayOutlines="true" OutlineSymbolsBelow="true"
							OutlineSymbolsRight="true">
                    <gmr:Name><xsl:value-of select="title"/></gmr:Name>
                    <!-- MaxCol, MaxRow No se indican así que tienen el valor de -1 -->
					<gmr:Zoom>1.000000</gmr:Zoom>
					<gmr:Names/>
					<gmr:PrintInformation>
						<gmr:Margins>
						  <gmr:top Points="0.5" PrefUnit="Pt"/>
						  <gmr:bottom Points="0.5" PrefUnit="Pt"/>
						  <gmr:left Points="0.1" PrefUnit="Pt"/>
						  <gmr:right Points="0.1" PrefUnit="Pt"/>
						  <gmr:header Points="1" PrefUnit="Pt"/>
						  <gmr:footer Points="1" PrefUnit="Pt"/>
						</gmr:Margins>
						<gmr:Scale type="percentage" percentage="100"/>
                        <gmr:vcenter value="0"/>
						<gmr:hcenter value="1"/>
                        <gmr:grid value="0"/>
                        <gmr:monochrome value="0"/>
                        <gmr:draft value="0"/>
                        <gmr:titles value="0"/>
                        <gmr:repeat_top value=""/>
						<gmr:repeat_left value=""/>
                        <gmr:order>d_then_r</gmr:order>
                        <gmr:orientation>portrait</gmr:orientation>
                        <!--<gmr:Header Left="" Middle="&amp;[TAB]" Right=""/>
                        <gmr:Footer Left="" Middle="P&#195;&#161;gina &amp;[PAGE]" Right=""/>-->
                        <gmr:paper>USLetter</gmr:paper>
					</gmr:PrintInformation>
                    <gmr:Styles>
                        <gmr:StyleRegion startCol="0" startRow="4096" endCol="63" endRow="65535">
                          <gmr:Style HAlign="1" VAlign="2" WrapText="0" Shade="0" Indent="0" Fore="0:0:0"
                                     Back="FFFF:FFFF:FFFF" PatternColor="C0C0:C0C0:C0C0" Format="General">
                            <gmr:Font Unit="10" Bold="0" Italic="0" Underline="0" StrikeThrough="0">Arial</gmr:Font>
                            <gmr:StyleBorder>
                              <gmr:Top Style="0"/>
                              <gmr:Bottom Style="0"/>
                              <gmr:Left Style="0"/>
                              <gmr:Right Style="0"/>
                              <gmr:Diagonal Style="0"/>
                              <gmr:Rev-Diagonal Style="0"/>
                            </gmr:StyleBorder>
                          </gmr:Style>
                        </gmr:StyleRegion>
                        <xsl:apply-templates select="estilo"/>
                    </gmr:Styles>
					<gmr:Cols DefaultSizePts="33">
                        <xsl:apply-templates select="columnas"/>
                    </gmr:Cols>
					<gmr:Rows DefaultSizePts="12.8">
                        <xsl:apply-templates select="filas"/>
                    </gmr:Rows>
                    <!-- Selecctions, CellComment -->
					<gmr:Cells>
                        <xsl:apply-templates select="celda"/>
                    </gmr:Cells>
                    <!-- Solver Tool no ha sido usada -->
                    <gmr:Solver TargetCol="-1" TargetRow="-1" ProblemType="1" Inputs=""/>
				</gmr:Sheet>
			</gmr:Sheets>
			<gmr:UIData SelectedTab="0"/>
		</gmr:Workbook>
	</xsl:template>

	<xsl:template match="celda">
		<gmr:Cell>
            <xsl:attribute name="Col"><xsl:value-of select="@Col"/></xsl:attribute>
            <xsl:attribute name="Row"><xsl:value-of select="@Row"/></xsl:attribute>
			<xsl:attribute name="ValueType">
				<xsl:choose>
                    <xsl:when test="@ValueType='vacio'">10</xsl:when>
                    <xsl:when test="@ValueType='booleano'">20</xsl:when>
					<xsl:when test="@ValueType='entero'">30</xsl:when>
					<xsl:when test="@ValueType='flotante'">40</xsl:when>
                    <!-- Cadena es el tipo del valor predefinido -->
					<xsl:otherwise>60</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
            <xsl:if test="@ValueFormat">
                <xsl:attribute name="ValueFormat"><xsl:value-of select="@ValueFormat"/></xsl:attribute>
            </xsl:if>
            <xsl:value-of select="."/>
		</gmr:Cell>
	</xsl:template>

	<xsl:template match="columnas">
        <gmr:ColInfo>
            <xsl:attribute name="No"><xsl:value-of select="@No"/></xsl:attribute>
            <xsl:attribute name="Unit"><xsl:value-of select="@Unit"/></xsl:attribute>
            <xsl:choose>
                <xsl:when test="@MarginA"><xsl:attribute name="MarginA"><xsl:value-of select="@MarginA"/></xsl:attribute></xsl:when>
                <xsl:otherwise><xsl:attribute name="MarginA">2</xsl:attribute></xsl:otherwise>
            </xsl:choose>
            <xsl:choose>
                <xsl:when test="@MarginB"><xsl:attribute name="MarginB"><xsl:value-of select="@MarginB"/></xsl:attribute></xsl:when>
                <xsl:otherwise><xsl:attribute name="MarginB">2</xsl:attribute></xsl:otherwise>
            </xsl:choose>
            <!-- HardSize??, Hidden, Collapsed, OutlineLevel -->
            <xsl:if test="@Count">
              <xsl:attribute name="Count"><xsl:value-of select="@Count"/></xsl:attribute>
            </xsl:if>
        </gmr:ColInfo>
	</xsl:template>

    <xsl:template match="filas">
        <gmr:RowInfo>
            <xsl:attribute name="No"><xsl:value-of select="@No"/></xsl:attribute>
            <xsl:attribute name="Unit"><xsl:value-of select="@Unit"/></xsl:attribute>
            <xsl:choose>
                <xsl:when test="@MarginA"><xsl:attribute name="MarginA"><xsl:value-of select="@MarginA"/></xsl:attribute></xsl:when>
                <xsl:otherwise><xsl:attribute name="MarginA">2</xsl:attribute></xsl:otherwise>
            </xsl:choose>
            <xsl:choose>
                <xsl:when test="@MarginB"><xsl:attribute name="MarginB"><xsl:value-of select="@MarginB"/></xsl:attribute></xsl:when>
                <xsl:otherwise><xsl:attribute name="MarginB">2</xsl:attribute></xsl:otherwise>
            </xsl:choose>
            <!-- HardSize??, Hidden, Collapsed, OutlineLevel -->
            <xsl:if test="@Count">
              <xsl:attribute name="Count"><xsl:value-of select="@Count"/></xsl:attribute>
            </xsl:if>
        </gmr:RowInfo>
	</xsl:template>

    <xsl:template match="estilo">
        <gmr:StyleRegion>
            <xsl:attribute name="startCol"><xsl:value-of select="@startCol"/></xsl:attribute>
            <xsl:attribute name="startRow"><xsl:value-of select="@startRow"/></xsl:attribute>
            <xsl:attribute name="endCol"><xsl:value-of select="@endCol"/></xsl:attribute>
            <xsl:attribute name="endRow"><xsl:value-of select="@endRow"/></xsl:attribute>
            <gmr:Style>
                <xsl:choose>
                    <xsl:when test="@HAlign"><xsl:attribute name="HAlign"><xsl:value-of select="@HAlign"/></xsl:attribute></xsl:when>
                    <xsl:otherwise><xsl:attribute name="HAlign">1</xsl:attribute></xsl:otherwise>
                </xsl:choose>
                <xsl:choose>
                    <xsl:when test="@VAlign"><xsl:attribute name="VAlign"><xsl:value-of select="@VAlign"/></xsl:attribute></xsl:when>
                    <xsl:otherwise><xsl:attribute name="VAlign">2</xsl:attribute></xsl:otherwise>
                </xsl:choose>
                <xsl:choose>
                    <xsl:when test="@WrapText"><xsl:attribute name="WrapText"><xsl:value-of select="@WrapText"/></xsl:attribute></xsl:when>
                    <xsl:otherwise><xsl:attribute name="WrapText">0</xsl:attribute></xsl:otherwise>
                </xsl:choose>
                <xsl:choose>
                    <xsl:when test="@Orient"><xsl:attribute name="Orient"><xsl:value-of select="@Orient"/></xsl:attribute></xsl:when>
                    <xsl:otherwise><xsl:attribute name="Orient">0</xsl:attribute></xsl:otherwise>
                </xsl:choose>
                <xsl:choose>
                    <xsl:when test="@Shade"><xsl:attribute name="Shade"><xsl:value-of select="@Shade"/></xsl:attribute></xsl:when>
                    <xsl:otherwise><xsl:attribute name="Shade">0</xsl:attribute></xsl:otherwise>
                </xsl:choose>
                <xsl:choose>
                    <xsl:when test="@Indent"><xsl:attribute name="Indent"><xsl:value-of select="@Indent"/></xsl:attribute></xsl:when>
                    <xsl:otherwise><xsl:attribute name="Indent">0</xsl:attribute></xsl:otherwise>
                </xsl:choose>
                <xsl:choose>
                    <xsl:when test="@Fore"><xsl:attribute name="Fore"><xsl:value-of select="@Fore"/></xsl:attribute></xsl:when>
                    <xsl:otherwise><xsl:attribute name="Fore">0:0:0</xsl:attribute></xsl:otherwise>
                </xsl:choose>
                <xsl:choose>
                    <xsl:when test="@Back"><xsl:attribute name="Back"><xsl:value-of select="@Back"/></xsl:attribute></xsl:when>
                    <xsl:otherwise><xsl:attribute name="Back">FFFF:FFFF:FFFF</xsl:attribute></xsl:otherwise>
                </xsl:choose>
                <xsl:choose>
                    <xsl:when test="@PatternColor"><xsl:attribute name="PatternColor"><xsl:value-of select="@PatternColor"/></xsl:attribute></xsl:when>
                    <xsl:otherwise><xsl:attribute name="PatternColor">C0C0:C0C0:C0C0</xsl:attribute></xsl:otherwise>
                </xsl:choose>
                <xsl:choose>
                    <xsl:when test="@Format"><xsl:attribute name="Format"><xsl:value-of select="@Format"/></xsl:attribute></xsl:when>
                    <xsl:otherwise><xsl:attribute name="Format">General</xsl:attribute></xsl:otherwise>
                </xsl:choose>
                <gmr:Font>
                    <xsl:choose>
                        <xsl:when test="@Unit"><xsl:attribute name="Unit"><xsl:value-of select="@Unit"/></xsl:attribute></xsl:when>
                        <xsl:otherwise><xsl:attribute name="Unit">10</xsl:attribute></xsl:otherwise>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="@Bold"><xsl:attribute name="Bold"><xsl:value-of select="@Bold"/></xsl:attribute></xsl:when>
                        <xsl:otherwise><xsl:attribute name="Bold">0</xsl:attribute></xsl:otherwise>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="@Italic"><xsl:attribute name="Italic"><xsl:value-of select="@Italic"/></xsl:attribute></xsl:when>
                        <xsl:otherwise><xsl:attribute name="Italic">0</xsl:attribute></xsl:otherwise>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="@Underline"><xsl:attribute name="Underline"><xsl:value-of select="@Underline"/></xsl:attribute></xsl:when>
                        <xsl:otherwise><xsl:attribute name="Underline">0</xsl:attribute></xsl:otherwise>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="@StrikeThrough"><xsl:attribute name="StrikeThrough"><xsl:value-of select="@StrikeThrough"/></xsl:attribute></xsl:when>
                        <xsl:otherwise><xsl:attribute name="StrikeThrough">0</xsl:attribute></xsl:otherwise>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="@Font"><xsl:value-of select="@Font"/></xsl:when>
                        <xsl:otherwise>Arial</xsl:otherwise>
                    </xsl:choose>
                </gmr:Font>
                <gmr:StyleBorder>
                    <gmr:Top>
                        <xsl:choose>
                            <xsl:when test="@Recuadro"><xsl:attribute name="Style"><xsl:value-of select="@Recuadro"/></xsl:attribute></xsl:when>
                            <xsl:when test="@StyleTop"><xsl:attribute name="Style"><xsl:value-of select="@StyleTop"/></xsl:attribute></xsl:when>
                            <xsl:otherwise><xsl:attribute name="Style">0</xsl:attribute></xsl:otherwise>
                        </xsl:choose>
                        <!-- Atributo Color -->
                    </gmr:Top>
                    <gmr:Bottom>
                        <xsl:choose>
                            <xsl:when test="@Recuadro"><xsl:attribute name="Style"><xsl:value-of select="@Recuadro"/></xsl:attribute></xsl:when>
                            <xsl:when test="@StyleBottom"><xsl:attribute name="Style"><xsl:value-of select="@StyleBottom"/></xsl:attribute></xsl:when>
                            <xsl:otherwise><xsl:attribute name="Style">0</xsl:attribute></xsl:otherwise>
                        </xsl:choose>
                        <!-- Atributo Color -->
                    </gmr:Bottom>
                    <gmr:Left>
                        <xsl:choose>
                            <xsl:when test="@Recuadro"><xsl:attribute name="Style"><xsl:value-of select="@Recuadro"/></xsl:attribute></xsl:when>
                            <xsl:when test="@StyleLeft"><xsl:attribute name="Style"><xsl:value-of select="@StyleLeft"/></xsl:attribute></xsl:when>
                            <xsl:otherwise><xsl:attribute name="Style">0</xsl:attribute></xsl:otherwise>
                        </xsl:choose>
                        <!-- Atributo Color -->
                    </gmr:Left>
                    <gmr:Right>
                        <xsl:choose>
                            <xsl:when test="@Recuadro"><xsl:attribute name="Style"><xsl:value-of select="@Recuadro"/></xsl:attribute></xsl:when>
                            <xsl:when test="@StyleRight"><xsl:attribute name="Style"><xsl:value-of select="@StyleRight"/></xsl:attribute></xsl:when>
                            <xsl:otherwise><xsl:attribute name="Style">0</xsl:attribute></xsl:otherwise>
                        </xsl:choose>
                        <!-- Atributo Color -->
                    </gmr:Right>
                    <gmr:Diagonal>
                        <xsl:choose>
                            <xsl:when test="@Recuadro"><xsl:attribute name="Style"><xsl:value-of select="@Recuadro"/></xsl:attribute></xsl:when>
                            <xsl:when test="@StyleDiagonal"><xsl:attribute name="Style"><xsl:value-of select="@StyleDiagonal"/></xsl:attribute></xsl:when>
                            <xsl:otherwise><xsl:attribute name="Style">0</xsl:attribute></xsl:otherwise>
                        </xsl:choose>
                        <!-- Atributo Color -->
                    </gmr:Diagonal>
                    <gmr:Rev-Diagonal>
                        <xsl:choose>
                            <xsl:when test="@Recuadro"><xsl:attribute name="Style"><xsl:value-of select="@Recuadro"/></xsl:attribute></xsl:when>
                            <xsl:when test="@StyleRev-Diagonal"><xsl:attribute name="Style"><xsl:value-of select="@StyleRev-Diagonal"/></xsl:attribute></xsl:when>
                            <xsl:otherwise><xsl:attribute name="Style">0</xsl:attribute></xsl:otherwise>
                        </xsl:choose>
                        <!-- Atributo Color -->
                    </gmr:Rev-Diagonal>
                </gmr:StyleBorder>
            </gmr:Style>
        </gmr:StyleRegion>
    </xsl:template>
</xsl:stylesheet>
