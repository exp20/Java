<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/HistoryList">
        <html>
            <body>
                <a href="/lab4_war/xslt/patients">Patients</a><br/>
                <a href="/lab4_war/xslt/recipes">Recipes</a><br/>
                <a href="/lab4_war/xslt/doctors">Doctors</a><br/>
                <a href="/lab4_war/xslt/email_history">Email history</a><br/>
                <table align="center">
                    <thead>
                        <th>id</th>
                        <th>Change type</th>
                        <th>Change entity</th>
                        <th>Value</th>
                    </thead>
                    <tbody>
                        <xsl:for-each select="history">
                            <tr>
                                <td><xsl:value-of select="id"/></td>
                                <td><xsl:value-of select="changeType"/></td>
                                <td><xsl:value-of select="changeEntity"/></td>
                                <td><xsl:value-of select="value"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
