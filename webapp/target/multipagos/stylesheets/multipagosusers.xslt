<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="name"/>
    <xsl:param name="password"/>

    <xsl:template match="autenticacion">
        <authentication>
            <xsl:apply-templates select="usuarios"/>
        </authentication>
    </xsl:template>

    <xsl:template match="usuarios">
        <xsl:apply-templates select="usuario"/>
    </xsl:template>

    <xsl:template match="usuario">
        <xsl:if test="normalize-space(name) = $name and normalize-space(password) = $password">
            <ID><xsl:value-of select="id"/></ID>
            <data>
                <ID><xsl:value-of select="id"/></ID>
                <nombre><xsl:value-of select="name"/></nombre>
                <nombre_completo><xsl:value-of select="fullname"/></nombre_completo>
                <email><xsl:value-of select="email"/></email>
                <cargo><xsl:value-of select="cargo"/></cargo>
                <xsl:apply-templates select="roles"/>
            </data>
        </xsl:if>
    </xsl:template>

    <xsl:template match="roles"><roles><xsl:apply-templates select="role"/></roles></xsl:template>

    <xsl:template match="role"><rol><xsl:value-of select="."/></rol></xsl:template>

</xsl:stylesheet>

