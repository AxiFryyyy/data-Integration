<?xml version= "1.0" encoding= "gb2312"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="choices">
        <xsl:apply-templates/>
        <choices>
            <xsl:for-each select="choice">
                <choice>
                    <ѧ��>
                        <xsl:value-of select="sid"/>
                    </ѧ��>
                    <�γ̱��>
                        <xsl:value-of select="cid"/>
                    </�γ̱��>
                    <�ɼ�>
                        <xsl:value-of select="score"/>
                    </�ɼ�>
                </choice>
            </xsl:for-each>
        </choices>
    </xsl:template>
</xsl:stylesheet>