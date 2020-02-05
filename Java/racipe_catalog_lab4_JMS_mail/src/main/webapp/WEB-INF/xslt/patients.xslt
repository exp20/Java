<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/patientsList">
        <html>
            <body>
                <a href="/lab3_war/xslt/doctors">Doctors</a><br/>
                <a href="/lab3_war/xslt/recipes">Recipes</a><br/>
                <table align="center">
                    <thead>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Last_name</th>
                        <th>Patronymic</th>
                        <th>Phone</th>
                    </thead>
                    <tbody>
                        <xsl:for-each select="patient">
                            <tr>
                                <td><xsl:value-of select="id"/></td>
                                <td><xsl:value-of select="name"/></td>
                                <td><xsl:value-of select="last_name"/></td>
                                <td><xsl:value-of select="patronymic"/></td>
                                <td><xsl:value-of select="phone"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>