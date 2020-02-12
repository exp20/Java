<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/recipesList">
        <html>
            <body>
                <a href="/lab4_war/xslt/patients">Patients</a><br/>
                <a href="/lab4_war/xslt/doctors">Doctors</a><br/>
                <a href="/lab4_war/xslt/history">History</a><br/>
                <a href="/lab4_war/xslt/email_history">Email history</a><br/>
                <table align="center">
                    <thead>
                        <th>id</th>
                        <th>Doctor_ID</th>
                        <th>Patient_ID</th>
                        <th>Description</th>
                        <th>Priority</th>
                    </thead>
                    <tbody>
                        <xsl:for-each select="recipe">
                            <tr>
                                <td><xsl:value-of select="id"/></td>
                                <td><xsl:value-of select="doctor/id"/></td>
                                <td><xsl:value-of select="patient/id"/></td>
                                <td><xsl:value-of select="description"/></td>
                                <td><xsl:value-of select="priority"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
