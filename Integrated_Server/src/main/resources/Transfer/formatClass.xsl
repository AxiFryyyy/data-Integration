<?xml version= "1.0" encoding= "gb2312"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="classes">
        <xsl:apply-templates/>
        <classes>
            <xsl:for-each select="class">
                <class>
                    <id>
                        <xsl:value-of select="�γ̱�� | ��� | Cno"/>
                    </id>
                    <name>
                        <xsl:value-of select="�γ����� | ���� | Cnm"/>
                    </name>
                    <score>
                        <xsl:value-of select="ѧ�� | Cpt"/>
                    </score>
                    <teacher>
                        <xsl:value-of select="�ڿ���ʦ |��ʦ |Tec"/>
                    </teacher>
                    <location>
                        <xsl:value-of select="�ڿεص� | �ص� |Pla"/>
                    </location>
                </class>
            </xsl:for-each>
        </classes>
    </xsl:template>
</xsl:stylesheet>