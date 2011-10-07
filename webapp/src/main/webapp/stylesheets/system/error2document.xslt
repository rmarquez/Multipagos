<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:error="http://apache.org/cocoon/error/2.1"
	xmlns:i18n="http://apache.org/cocoon/i18n/2.1" i18n:attr="language">

    <xsl:template match="error:notify">
	 <document menu="true">
	 <header>
		 <title>
		   <xsl:if test="@type">
			 <xsl:value-of select="@type"/>
		   </xsl:if>
		   <i18n:text><xsl:value-of select="error:title"/></i18n:text>
		 </title>
	   </header>
	   <body>
		 <s1 title="Error">
		   <p class="mensaje">
		   <xsl:call-template name="returns2br">
				<xsl:with-param name="string" select="error:message"/>
			</xsl:call-template>
		   </p>
		   <xsl:if test="@sender">
			 <s2 title="De">
			   <p><xsl:value-of select="@sender"/></p>
			 </s2>
		   </xsl:if>
		   <p class="descripcion">
			   <span class="descripcion">Descripción : </span>
					<xsl:call-template name="returns2br">
						<xsl:with-param name="string" select="error:description"/>
					</xsl:call-template>
			</p>
			<xsl:apply-templates select="error:extra"/>
			<p><note><strong>Origen: </strong><xsl:value-of select="error:source"/></note></p>
			<p><note><link href="javascript:history.back();">Regresar a la página anterior</link></note></p>
		 </s1>
		</body>
	 </document>
    </xsl:template>

	<xsl:template match="error:extra">
		<xsl:choose>
			<xsl:when test="contains(@error:description,'stacktrace')">
				<p class="stacktrace">
				<span class="descripcion"><i18n:text><xsl:value-of select="@error:description"/></i18n:text></span>
				<span class="switch" id="{@error:description}-switch" onclick="toggle('{@error:description}')">[ver]</span>
				<pre id="{@error:description}" style="display: none" class="error">
					<xsl:call-template name="returns2br">
						<xsl:with-param name="string" select="."/>
					</xsl:call-template>
				</pre>
				</p>
			</xsl:when>
			<xsl:when test="contains(@error:description,'cause')">
				<p class="extra">
					<span class="descripcion"><i18n:text><xsl:value-of select="@error:description"/></i18n:text>:&#160;</span>
					<xsl:call-template name="returns2br">
						<xsl:with-param name="string" select="."/>
					</xsl:call-template>
				</p>
			</xsl:when>
			<xsl:otherwise>
				<p class="extra">
					<span class="descripcion"><i18n:text><xsl:value-of select="@error:description"/></i18n:text>:&#160;</span>
					<xsl:call-template name="returns2br">
						<xsl:with-param name="string" select="."/>
					</xsl:call-template>
				</p>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template name="returns2br">
		<xsl:param name="string"/>
		<xsl:variable name="return" select="'&#xa;'"/>
		<xsl:choose>
			<xsl:when test="contains($string,$return)">
				<i18n:text><xsl:value-of select="substring-before($string,$return)"/></i18n:text>
				<br/>
				<xsl:call-template name="returns2br">
					<xsl:with-param name="string" select="substring-after($string,$return)"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<i18n:text><xsl:value-of select="$string"/></i18n:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
