<?xml version= "1.0" encoding= "UTF-8"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <xsl:output method= "xml" encoding= "UTF-8"/>
    <xsl:template match="classes">
        <classes>
            <xsl:for-each select="class">
                <class>
                    <id>
                        <xsl:value-of select="课程编号 | 编号 | Cno"/>
                    </id>
                    <name>
                        <xsl:value-of select="课程名称 | 名称 | Cnm"/>
                    </name>
                    <score>
                        <xsl:value-of select="学分 | Cpt"/>
                    </score>
                    <teacher>
                        <xsl:value-of select="授课老师 |老师 |Tec"/>
                    </teacher>
                    <location>
                        <xsl:value-of select="授课地点 | 地点 |Pla"/>
                    </location>
                </class>
            </xsl:for-each>
        </classes>
    </xsl:template>
</xsl:stylesheet>