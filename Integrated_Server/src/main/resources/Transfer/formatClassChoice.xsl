<?xml version= "1.0" encoding= "gb2312"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="choices">
        <xsl:apply-templates/>
        <choices>
            <xsl:for-each select="choice">
                <choice>
                    <sid>
                        <xsl:value-of select="ѧ�� | ѧ����� | Sno"/>
                    </sid>
                    <cid>
                        <xsl:value-of select="�γ̱�� | Cno"/>
                    </cid>
                    <score>
                        <xsl:value-of select="�ɼ� | �÷� | Grd"/>
                    </score>
                </choice>
            </xsl:for-each>
        </choices>
    </xsl:template>
</xsl:stylesheet>