<?xml version= "1.0" encoding= "UTF-8"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <xsl:output method= "xml" encoding= "UTF-8"/>
    <xsl:template match="choices">
        <choices>
            <xsl:for-each select="choice">
                <choice>
                    <sid>
                        <xsl:value-of select="学号 | 学生编号 | Sno"/>
                    </sid>
                    <cid>
                        <xsl:value-of select="课程编号 | Cno"/>
                    </cid>
                    <score>
                        <xsl:value-of select="成绩 | 得分 | Grd"/>
                    </score>
                </choice>
            </xsl:for-each>
        </choices>
    </xsl:template>
</xsl:stylesheet>