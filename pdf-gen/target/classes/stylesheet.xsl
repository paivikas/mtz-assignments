<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">

    <!-- Root template -->
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4" page-height="29.7cm" page-width="21cm">
                    <fo:region-body margin="2cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="A4">
                <fo:flow flow-name="xsl-region-body">

                    <fo:block font-size="24pt" font-weight="bold" text-align="center" space-after="20pt">List of Books</fo:block>


                    <fo:block font-size="18pt" font-style="italic" space-after="12pt">Books:</fo:block>


                    <fo:table table-layout="fixed" width="100%" border="0.5pt solid black">
                        <fo:table-column column-width="50%"/>
                        <fo:table-column column-width="25%"/>
                        <fo:table-column column-width="25%"/>


                        <fo:table-header>
                            <fo:table-row background-color="#CCCCCC">
                                <fo:table-cell padding="5pt" border="0.5pt solid black">
                                    <fo:block font-weight="bold">Title</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt" border="0.5pt solid black">
                                    <fo:block font-weight="bold">Author</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt" border="0.5pt solid black">
                                    <fo:block font-weight="bold">Year</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>


                        <fo:table-body>
                            <xsl:for-each select="books/book">
                                <fo:table-row>
                                    <fo:table-cell padding="5pt" border="0.5pt solid black">
                                        <fo:block>
                                            <xsl:value-of select="title"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="5pt" border="0.5pt solid black">
                                        <fo:block>
                                            <xsl:value-of select="author"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="5pt" border="0.5pt solid black">
                                        <fo:block>
                                            <xsl:value-of select="year"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

</xsl:stylesheet>
