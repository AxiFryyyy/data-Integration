<?xml version= "1.0" encoding= "gb2312"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="students">
        <xsl:apply-templates/>
        <students>
            <xsl:for-each select="student">
                <student>
                    <id>
                        <xsl:value-of select="学号 | Sno"/>
                    </id>
                    <name>
                        <xsl:value-of select="姓名 | 名称 | Snm"/>
                    </name>
                    <sex>
                        <xsl:value-of select="性别 | Sex"/>
                    </sex>
                    <major>
                        <xsl:value-of select="院系 | 专业 | Sde"/>
                    </major>
                </student>
            </xsl:for-each>
        </students>
    </xsl:template>
</xsl:stylesheet>