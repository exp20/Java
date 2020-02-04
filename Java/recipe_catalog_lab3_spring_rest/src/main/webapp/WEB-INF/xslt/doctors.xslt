<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/doctorsList">
        <html>
            <body>
                <a href="/lab3_war/xslt/patients">Patients</a><br/>
                <a href="/lab3_war/xslt/recipes">Recipes</a><br/>
                <table align="center">
                    <thead>
                        <th>id</th>
                        <th>Name</th>
                        <th>Last_name</th>
                        <th>patronymic</th>
                        <th>specialization</th>
                    </thead>
                    <tbody>
                        <xsl:for-each select="doctor">
                            <tr>
                                <td><xsl:value-of select="id"/></td>
                                <td><xsl:value-of select="name"/></td>
                                <td><xsl:value-of select="last_name"/></td>
                                <td><xsl:value-of select="patronymic"/></td>
                                <td><xsl:value-of select="specialization"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>