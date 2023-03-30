<?xml version= "1.0" encoding= "gb2312"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="classes">
        <xsl:apply-templates/>
        <classes>
            <xsl:for-each select="class">
                <class>
                    <�γ̱��>
                        <xsl:value-of select="id"/>
                    </�γ̱��>
                    <�γ�����>
                        <xsl:value-of select="name"/>
                    </�γ�����>
                    <ѧ��>
                        <xsl:value-of select="score"/>
                    </ѧ��>
                    <�ڿ���ʦ>
                        <xsl:value-of select="teacher"/>
                    </�ڿ���ʦ>
                    <�ڿεص�>
                        <xsl:value-of select="location"/>
                    </�ڿεص�>
                </class>
            </xsl:for-each>
        </classes>
    </xsl:template>
</xsl:stylesheet>